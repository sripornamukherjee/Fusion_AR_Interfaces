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
import org.springframework.beans.factory.annotation.Value;

import com.compasites.constants.Constants;
import com.compasites.helper.CreditMemoSingleton;
import com.compasites.pojo.CreditMemo;
import com.compasites.utils.FileUtil;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
public class CreditMemoCustomWriter implements ItemWriter<CreditMemo> {
    static Logger LOG = LoggerFactory.getLogger(CreditMemoCustomWriter.class);

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
                /*if (!creditMemo.isLastLine()) {
                    if (creditMemo.isValid()) {
                        bw.write(creditMemo.getContent());
                    } else {
                        errorFileBw.write(creditMemo.getErrorLine());
                    }
                }*/
            	LOG.info("WRITER for CM number "+creditMemo.getCreditNoteNumber());
                boolean valid = true;
                if (!creditMemo.isLastLine()) {
                    ArrayList<CreditMemo> creditMemoList = singleton.getValidList(creditMemo);
                    //LOG.info("cmList is null? "+(creditMemoList == null));
                    if (creditMemoList == null) {
                    	creditMemoList = singleton.getErrorList(creditMemo);
                        valid = false;
                    }
                    //LOG.info("cmList is null? "+(creditMemoList == null));
                    if (creditMemoList != null) {
                        if (valid) {
                        	
                            for (CreditMemo cm : creditMemoList) {
                            	BigDecimal checkHeaderAmt = new BigDecimal(df.format(cm.getHeaderAmt()));
                                BigDecimal checkGrossTotalAmt = new BigDecimal(df.format(new BigDecimal(cm.getGrossTotalAmt())));
                                BigDecimal roundingDiff = (checkHeaderAmt.subtract(checkGrossTotalAmt)).abs();
                                
                               if (roundingDiff.compareTo(new BigDecimal("0.10")) == 1){
	                            	cm.setErrorMsg(Constants.GROSS_TOTAL_AMOUNT_ERROR_MSG);
	                                errorFileBw.write(cm.getErrorLine());
	                            }else {
	                            	BigDecimal allocatedRev = new BigDecimal(cm.getAllocatedRevAmt());
	                            	
	                            	if(!(cm.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)&
	                            			cm.getWdaFundAmt().equalsIgnoreCase(Constants.EMTPY))) {
	                            		//if gross total is 0, calculate 7% tax on line amount
	                            		if(cm.getGrossTotalAmt().equals("0.00")) {
		                            		cm.setUnitSellingPrice("");
		                            		if(allocatedRev.signum() == 1)
		                            			cm.setLineAmtIncludesTaxFlag("Y");
		                            		else
		                            			cm.setLineAmtIncludesTaxFlag("N");
	                            		} else {
	                            			LOG.info("NO TAX on Funding line");
	                            			if(allocatedRev.signum() == 1) {
	                            				cm.setTaxClassificationCode("");
	                            			}
	                            			cm.setLineAmtIncludesTaxFlag("");
	                            		}
	                            		
	                            	} else {
		                            	// If allocated rev is +ve OR (SFC and WDA are empty)
		                            	// Deduct discount amount
		                            	if(!cm.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) {
		                            		if(allocatedRev.signum() == 1)
		                            			allocatedRev = allocatedRev.multiply(new BigDecimal(-1));
		                            		
		                            		BigDecimal newAmt = allocatedRev.add(new BigDecimal(cm.getDiscountAmt()));
		                            		cm.setTransactionLineAmt(newAmt.toString());
		                            		cm.setUnitSellingPrice(newAmt.toString());
		                            	}
		                            	// if GST is 0
		                            	if(cm.getGstAmount().equals("0.00"))
		                            		cm.setTaxClassificationCode("");
		                            	
		                            	cm.setLineAmtIncludesTaxFlag("");
	                            	}
	                            	bw.write(cm.getContent());
	                            	
	                            }
                            }
                            	    
                        } else {
                            for (CreditMemo c : creditMemoList) {
                            	LOG.info("CM NUMBER: "+c.getCreditNoteNumber()+" being written to error file");
                            	LOG.info("HEADER AMOUNT for CM "+c.getCreditNoteNumber()+": "+c.getHeaderAmt());
                                errorFileBw.write(c.getErrorLine());
                            }
                        }
                    }

                    //putting the invoice item into singleton
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
