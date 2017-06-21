package com.compasites.listener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.compasites.constants.Constants;
import com.compasites.helper.CreditMemoSingleton;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.CreditMemo;
import com.compasites.pojo.Invoice;
import com.compasites.utils.FileUtil;
import com.compasites.validations.CommonValidation;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
public class CreditMemoCustomWriter implements ItemWriter<CreditMemo> {
    static Logger LOG = LoggerFactory.getLogger(CreditMemoCustomWriter.class);

    @Autowired
    private ReportRunHelper reportRunHelper;
    
    @Value("${creditmemo.output.folder}")
    private String outputFolder;

    @Value("${creditmemo.output.rainterface}")
    private String interfaceFile;

    @Value("${creditmemo.errorfolder}")
    private String errorFolder;

    @Value("${creditmemo.errorfile}")
    private String filename;

    @Override
    public void write(List<? extends CreditMemo> items) throws Exception {
    	CreditMemoSingleton singleton = CreditMemoSingleton.getInstance();
        FileWriter writer = new FileWriter(outputFolder + interfaceFile, true);
        BufferedWriter bw = new BufferedWriter(writer);

        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);
        
        DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        try{

            for(CreditMemo creditMemo : items) {
               
            	LOG.info("WRITER for CM number "+creditMemo.getCreditNoteNumber());
                boolean valid = true;
                if (!creditMemo.isLastLine()) {
                    ArrayList<CreditMemo> creditMemoList = singleton.getValidList(creditMemo);
                    //LOG.info("cmList is null? "+(creditMemoList == null));
                    if (creditMemoList == null) {
                    	creditMemoList = singleton.getErrorList(creditMemo);
                        valid = false;
                    }
                   
                    if (creditMemoList != null) {
                        if (valid) {
                        	CreditMemo taxLine = null;
                            for (CreditMemo cm : creditMemoList) {
                            	BigDecimal headerAmtSum = new BigDecimal(df.format(cm.getHeaderAmt()));
                                BigDecimal grossTotalAmt = new BigDecimal(df.format(new BigDecimal(cm.getGrossTotalAmt())));
                                BigDecimal gst = new BigDecimal(df.format(new BigDecimal(cm.getGstAmount())));
                                
                               BigDecimal allocatedRev = new BigDecimal(cm.getAllocatedRevAmt());
                               if (gst.add(headerAmtSum).compareTo(grossTotalAmt) != 0){
	                            	cm.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
	                                errorFileBw.write(cm.getErrorLine());
	                            } else {

	                            	if(!cm.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == 1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt = allocatedRev.add(new BigDecimal(cm.getDiscountAmt()));
		                            	cm.setTransactionLineAmt(newAmt.toString());
		                            	cm.setUnitSellingPrice(newAmt.toString());
		                            }
		                            if(!cm.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == 1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt1 = allocatedRev.add(new BigDecimal(cm.getForfeitedAmt()));
		                            	cm.setTransactionLineAmt(newAmt1.toString());
		                            	cm.setUnitSellingPrice(newAmt1.toString());
		                            }
		                            if(!cm.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            	if(allocatedRev.signum() == 1)
		                            		allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            	BigDecimal newAmt2 = allocatedRev.add(new BigDecimal(cm.getCouponAmt()));
		                            	cm.setTransactionLineAmt(newAmt2.toString());
		                            	cm.setUnitSellingPrice(newAmt2.toString());
		                            }
		                            
		                            if(headerAmtSum.signum() == 1) {
		                            	if(cm.getMemoLineName().equals(Constants.SFC_MEMOLINE))
		                            		cm.setLineAmtIncludesTaxFlag("Y");
		                            	else
		                            		cm.setLineAmtIncludesTaxFlag("N");
		                            }else
		                            	cm.setLineAmtIncludesTaxFlag("");
		                            
		                            bw.write(cm.getContent());
		                            if(!cm.getMemoLineName().equals(Constants.WDA_MEMOLINE) && 
		                            		!cm.getMemoLineName().equals(Constants.SFC_MEMOLINE) &&
		                            		allocatedRev.signum() < 0)
		                            	taxLine = cm;	
		                            }
	                            }
                            if(taxLine != null) {
                            	boolean isNotEmptyVoucherRef = CommonValidation.stringNotEmpty(taxLine.getVoucherRefNumber());
                    			
                    			if(isNotEmptyVoucherRef) {
                    				BigDecimal s = new BigDecimal(taxLine.getGstAmount()).multiply(new BigDecimal(-1));
                    				String refAmount = df.format(s);
                    				
                    				String invoiceTaxRef = reportRunHelper.getInvoiceTaxLineReference(taxLine.getCustomerId(),
    	                				taxLine.getVoucherRefNumber(), refAmount);
                    				
                    				String[] arr = new String[3];
		             			     if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) >= 0) {
		             			    	 arr[0] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) + 24, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_CONTEXT_END));
		                             }
		                             if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) > 0) {
		                            	 arr[1] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) + 27, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1_END));
		                             }
		                             if (invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) > 0) {
		                            	 arr[2] = invoiceTaxRef.substring(invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) + 27, invoiceTaxRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END));
		                             }
		                            if (arr[0] != null && arr[1] != null && arr[2] != null) {	
		                            	taxLine.setRefTransactionFlexfieldCntxt(arr[0]);
		                            	taxLine.setRefTransactionFlexfieldSegment1(arr[1]);
		                            	taxLine.setRefTransactionFlexfieldSegment2(arr[2]);
			        				} else {
			        					taxLine.setRefTransactionFlexfieldCntxt(Constants.EMTPY);
			        					taxLine.setRefTransactionFlexfieldSegment1(Constants.EMTPY);
			        					taxLine.setRefTransactionFlexfieldSegment2(Constants.EMTPY);
			        					taxLine.setCreditMtdUsedRevenueSchedulingRules(Constants.EMTPY);
			        				}
                    			}
    			        		taxLine.setTransactionLineAmt(taxLine.getGstAmount());
    			        		taxLine.setUnitSellingPrice(taxLine.getGstAmount());
    			        		
    			        		if((taxLine.getCreditNoteNumber().contains("MSM") || taxLine.getRecordType().contains("MSD") || taxLine.getGstPercent().contains("0")) &&
    			        				taxLine.getGstAmount().equals("0.00")) 
    			        			taxLine.setTaxRateCode(Constants.OUTPUT_OZR_0_TAX);
    			        		else
    			        			taxLine.setTaxRateCode(Constants.OUTPUT_OSR_7_TAX);
    			        		
    			        		taxLine.setTransactionLineDescr("GST");
    			        		LOG.info("Writing tax line");
    			        		taxLine.setLinkToTransactionsFlexfieldSegment2(taxLine.getLineTransactionFlexfieldSeg2());
    			        		singleton.setLineSegment(taxLine);
    			        		
    			        		taxLine.setLinkToTransactionsFlexfieldCntxt(taxLine.getLineTransactionFlexfieldContxt());
    			        		taxLine.setLinkToTransactionsFlexfieldSegment1(taxLine.getLineTransactionFlexfieldSeg1());
    			        		
    			        		bw.write(taxLine.getTaxLine());
                            }
                            	    
                        } else {
                            for (CreditMemo c : creditMemoList) {
                            	LOG.info("CM NUMBER: "+c.getCreditNoteNumber()+" being written to error file");
                                errorFileBw.write(c.getErrorLine());
                            }
                        }
                    }

                    //putting the credit memo item into singleton
                    singleton.setLineSegment(creditMemo);
                    LOG.info("Putting CM into singleton "+creditMemo.getCreditNoteNumber()+","+creditMemo.isValid());
                    singleton.putCreditMemo(creditMemo, creditMemo.isValid());
                    
                }
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
