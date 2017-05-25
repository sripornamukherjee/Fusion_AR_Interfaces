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
                        	boolean additionalTaxLineCheck = false;
                        	
                        	for (Invoice invoce : invoiceList) {
                        		additionalTaxLineCheck = invoce.getMemoLineName().equalsIgnoreCase(Constants.SFC_MEMOLINE) &&
                          				!invoce.getGstAmount().equals("0.00") && invoce.getGrossTotalAmt().equals("0.00");
                        		if(additionalTaxLineCheck)
                        			break;
                        	}
                        	
                        	Invoice taxLine = null;
                            for (Invoice invoce : invoiceList) {
                            		
                            	BigDecimal checkHeaderAmt = new BigDecimal(df.format(invoce.getHeaderAmt()));
	                            BigDecimal checkGrossTotalAmt = new BigDecimal(df.format(new BigDecimal(invoce.getGrossTotalAmt())));
	                            BigDecimal roundingDiff = (checkHeaderAmt.subtract(checkGrossTotalAmt)).abs();
	                            
	                            // Ignore rounding differences
	                            if (roundingDiff.compareTo(new BigDecimal("0.10")) == 1){
	                            	LOG.info("INVOICE NUMBER: "+invoce.getInvoiceNumber()+" being written to error file");
	                            	invoce.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
	                                errorFileBw.write(invoce.getErrorLine());
	                            } else {
	                            	BigDecimal allocatedRev = new BigDecimal(invoce.getAllocatedRevAmt());
	                            	
	                            	//If tax needs to be added as separate line
	                            	if(additionalTaxLineCheck/*|| !(invoce.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)&
	                            			invoce.getWdaFundedAmt().equalsIgnoreCase(Constants.EMTPY))*/) {
	                            		invoce.setTaxClassificationCode("");
	                            	} else {
	                            		
	                            		// If allocated rev is +ve OR (SFC and WDA are empty)
		                            	// Deduct discount amount
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
		                            	// if GST is 0 or line is for WDA
		                            	if(invoce.getGstAmount().equals("0.00") /*|| invoce.getMemoLineName().equalsIgnoreCase(Constants.WDA_MEMOLINE)*/) {
		                            		//LOG.info(""+invoce.getGstPercent().contains("0"));
		                            		// Condition added for MSM 0 GST invoices - OUTPUT-OZR tax code needed
		                            		if(!invoce.getGstPercent().contains("0"))
		                            			invoce.setTaxClassificationCode("");
		                            	}
		                            	
		                            	invoce.setLineAmtIncludesTaxFlag("");
	                            	}
	                            	bw.write(invoce.getContent());
	                            	if(additionalTaxLineCheck && !(invoce.getMemoLineName().equalsIgnoreCase(Constants.SFC_MEMOLINE) 
	                            			|| invoce.getMemoLineName().equalsIgnoreCase(Constants.WDA_MEMOLINE))) {
	        			        		taxLine = invoce;
	        			        	}
	                            }
                            }
                            
                            if(additionalTaxLineCheck && taxLine != null) {
                            	taxLine.setMemoLineName("Tax memo");
    			        		taxLine.setTransactionLineAmt(taxLine.getGstAmount());
    			        		taxLine.setUnitSellingPrice(taxLine.getGstAmount());
    			        		taxLine.setRevenuedSchedulingRuleStrtDt(taxLine.getBillDate());
    			        		taxLine.setTaxClassificationCode("OUTPUT-ONA");
    			        		LOG.info("Writing tax line");
    			        		singleton.setLineSegment(taxLine);
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
