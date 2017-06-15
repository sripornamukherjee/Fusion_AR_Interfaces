package com.compasites.listener;

import com.compasites.constants.Constants;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.SingletonInvoice;
import com.compasites.pojo.Invoice;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sobhan Babu on 17-05-2016.
 */
public class InvoiceCustomWriter implements ItemWriter<Invoice> {

    static Logger LOG = LoggerFactory.getLogger(InvoiceCustomWriter.class);

    @Value("${invoice.output.folder}")
    private String outputFolder;

    @Value("${invoice.output.rainterface}")
    private String interfaceFile;

    @Value("${invoice.errorfolder}")
    private String errorFolder;

    @Value("${invoice.errorfile}")
    private String filename;

    @Override
    public void write(List<? extends Invoice> items) throws IOException {
        InvoiceSingleton singleton = InvoiceSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);

        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        
        try{
            for(Invoice invoice : items) {
                boolean valid = true;
                if (!invoice.isLastLine()) {
                    ArrayList<Invoice> invoiceList = singleton.getValidList(invoice);
                    if (invoiceList == null) {
                        invoiceList = singleton.getErrorList(invoice);
                        valid = false;
                    }
                    if (invoiceList != null) {
                        if (valid) {
                        	Invoice taxLine = null;
                            for (Invoice invoce : invoiceList) {
                            		
                            	BigDecimal headerAmtSum = new BigDecimal(df.format(invoce.getHeaderAmt()));
	                            BigDecimal grossTotalAmt = new BigDecimal(df.format(new BigDecimal(invoce.getGrossTotalAmt())));
	                            BigDecimal gst = new BigDecimal(df.format(new BigDecimal(invoce.getGstAmount())));
	                            BigDecimal allocatedRev = new BigDecimal(invoce.getAllocatedRevAmt());
	                            // Changed on 01/06/17
	                            if (/*roundingDiff.compareTo(new BigDecimal("0.10")) == 1*/ 
	                            		gst.add(headerAmtSum).compareTo(grossTotalAmt) != 0){
	                            	LOG.info("INVOICE NUMBER: "+invoce.getInvoiceNumber()+" being written to error file");
	                            	invoce.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
	                                errorFileBw.write(invoce.getErrorLine());
	                            } else {
	                            	
		                            if(!invoce.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == -1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt = allocatedRev.subtract(new BigDecimal(invoce.getDiscountAmt()));
		                            	invoce.setTransactionLineAmt(newAmt.toString());
		                            	invoce.setUnitSellingPrice(newAmt.toString());
		                            }
		                            if(!invoce.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == -1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt1 = allocatedRev.subtract(new BigDecimal(invoce.getForfeitedAmt()));
		                            	invoce.setTransactionLineAmt(newAmt1.toString());
		                            	invoce.setUnitSellingPrice(newAmt1.toString());
		                            }
		                            if(!invoce.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == -1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt2 = allocatedRev.subtract(new BigDecimal(invoce.getCouponAmt()));
		                            	invoce.setTransactionLineAmt(newAmt2.toString());
		                            	invoce.setUnitSellingPrice(newAmt2.toString());
		                            }
		                            if(headerAmtSum.signum() == -1) {
		                            	if(invoce.getMemoLineName().equals(Constants.SFC_MEMOLINE))
		                            		invoce.setLineAmtIncludesTaxFlag("Y");
		                            	else
		                            		invoce.setLineAmtIncludesTaxFlag("N");
		                            }else
		                            	invoce.setLineAmtIncludesTaxFlag("");
	                            
	                            bw.write(invoce.getContent());
	                            
	                            if(!invoce.getMemoLineName().equals(Constants.WDA_MEMOLINE) && 
	                            		!invoce.getMemoLineName().equals(Constants.SFC_MEMOLINE) &&
	                            		allocatedRev.signum() > 0)
	                            	taxLine = invoce;	
	                            }
                            }
                            
                            if(taxLine != null) {
    			        		taxLine.setTransactionLineAmt(taxLine.getGstAmount());
    			        		taxLine.setUnitSellingPrice(taxLine.getGstAmount());
    			        		taxLine.setTransactionLineDescr("GST");
    			        		LOG.info("Writing tax line");
    			        		taxLine.setLinkToTransactionsFlexfieldSegment2(taxLine.getLineTransactionFlexfieldSeg2());
    			        		singleton.setLineSegment(taxLine);
    			        		
    			        		taxLine.setLinkToTransactionsFlexfieldCntxt(taxLine.getLineTransactionFlexfieldContxt());
    			        		taxLine.setLinkToTransactionsFlexfieldSegment1(taxLine.getLineTransactionFlexfieldSeg1());
    			        		
    			        		bw.write(taxLine.getTaxLine());
    			        		
                            }
                        } else {
                            for (Invoice invoce : invoiceList) {
                            	LOG.info("INVOICE NUMBER: "+invoce.getInvoiceNumber()+" being written to error file");
                                errorFileBw.write(invoce.getErrorLine());
                            }
                            
                        }
                    }
                }

                    //putting the invoice item into singleton
                    singleton.setLineSegment(invoice);
                    singleton.putInvoice(invoice, invoice.isValid());
                    
                }
            
        }catch (Exception e){
            LOG.error("Exception message  : " + e.getMessage());
            LOG.error("Exception : ", e);
        }finally {
            bw.flush();
            bw.close();

            erroFileWriter.flush();
            errorFileBw.close();

            //to delete the error file if it is empty
            FileUtil.deleteFile(fileName);
        }
    }
}
