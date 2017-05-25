package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.constants.ReceiptEnum;
import com.compasites.constants.ReceiptLockEnum;
import com.compasites.helper.ReceiptLockSingleton;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.Receipt;
import com.compasites.pojo.ReceiptLock;
import com.compasites.utils.DateUtil;
import com.compasites.validations.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 20-06-2016.
 */
public class ReceiptLockProcessor implements ItemProcessor<ReceiptLock, ReceiptLock> {

    static Logger LOG = LoggerFactory.getLogger(ReceiptLockProcessor.class);

    @Autowired
    private ReportRunHelper reportRunHelper;

    @Value("${bsh.receiptlock.mappingfile.location}")
    private String mappingFile;

    @Value("${billDate.validDays}")
    private int validDays;

    @Override
    public ReceiptLock process(final ReceiptLock receipt) throws Exception {
        try {
            if(!receipt.isLastLine()) {
            	// Setting accounting date for ESS job
            	
            	
            	//changing the date format of collectionDate property as this format is needed for output csv
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.RECEIPT_DT_FORMAT);
                SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_NEW);
                String receiptDate = receipt.getCollectionDate();
                String accountingDate = receipt.getAccountingDate();
                
                if(receiptDate != null && receiptDate.length() > 0) {
	                Date dt = format.parse(receipt.getCollectionDate());
	                //receipt.setCollectionDate(sdf.format(dt));
	                receipt.setFormatedCollectionDate(sdf.format(dt));
                } else 
                	receipt.setFormatedCollectionDate(Constants.EMTPY);

                Interpreter i = new Interpreter(); // Construct an interpreter
                i.source(mappingFile);

                Map<String, String> map = new HashMap<String, String>();
                ReceiptLockEnum.setValuesMap(receipt, map);
                i.set("map", map);

                i.eval("mapReceiptValues(map)");
                map = (Map) i.get("map");

                ReceiptLockEnum.setValuesReceipt(receipt, map);
                
                //for unique batch name
                ReceiptLockSingleton singleton = ReceiptLockSingleton.getInstance();
                receipt.setBatchName(singleton.getBatchName());
                LOG.info("Validating line "+singleton.getLineCount());
                singleton.setLineCount(singleton.getLineCount()+1);

                //removing comma from customer name
                receipt.setCustomerName(CommonValidation.removeComma(receipt.getCustomerName()));

                //validation
                DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
                boolean isValidAmt = CommonValidation.amtDecimalLen(receipt.getReceiptAmount());
                boolean isValidInvoiceAmt = CommonValidation.amtDecimalLen(receipt.getInvoiceAmount());
                boolean isCustomerAvailable = false;
                boolean isInvoiceAvailable = false;
                
                
                boolean isValidInvoiceNumber = CommonValidation.stringNotEmpty(receipt.getInvoiceNumber());
                boolean isValidReceiptNumber = CommonValidation.stringNotEmpty(receipt.getReceiptNumber());
                boolean isNotEmptyInvoiceAmt = CommonValidation.stringNotEmpty(receipt.getInvoiceAmount());
                boolean isNotEmptyReceiptAmt = CommonValidation.stringNotEmpty(receipt.getReceiptAmount());
                
                //put in validation for payment mode can't be empty
                boolean isNotEmptyPaymentMode = CommonValidation.stringNotEmpty(receipt.getPaymentMode());
                boolean isValidReceiptMethod = false;
                if(!receipt.getPaymentLockBoxNumber().equals("Invalid Lockbox") && !receipt.getReceiptMethod().equals("Invalid Lockbox")) {
                	isValidReceiptMethod = true;
                	singleton.setTrailerReceiptMethod(receipt.getReceiptMethod());
                	singleton.setTrailerBankOrigNum(receipt.getBankOrigNumber());
                	singleton.setTrailerLockboxNum(receipt.getLockBoxNumber());
                	singleton.setTrailerDestBankAc(receipt.getDestBankAccount());
                }

                boolean isValidDate = false;
                if (isValidReceiptNumber && isValidInvoiceNumber && receiptDate != null && receiptDate.length() > 0 &&
                		accountingDate != null && accountingDate.length() > 0) {
                	isValidDate = true;	
                	singleton.setTrailerDate(receipt.getLockBoxHdrDepositDt());
                }
                
                if(accountingDate != null && accountingDate.length() > 0) 
                	ReceiptLock.lockboxAccountingDt = receipt.getAccountingDate();
                
                if (isValidAmt && isValidInvoiceAmt && isValidDate && isNotEmptyInvoiceAmt 
                		&& isNotEmptyReceiptAmt && isNotEmptyPaymentMode && isValidReceiptMethod){
                    isCustomerAvailable = reportRunHelper.checkCustomerAvailable(receipt.getCustomerId());
                    if (isCustomerAvailable) {
                        isInvoiceAvailable = reportRunHelper.checkInvoiceAvailability(receipt.getCustomerId(),
                                receipt.getInvoiceNumber());
                    }
                }

                if (isCustomerAvailable && isInvoiceAvailable) {
                    BigDecimal bigDecimal = new BigDecimal(receipt.getPaymentRemittanceAmt());
                    bigDecimal = bigDecimal.multiply(new BigDecimal(100.00));
                    receipt.setPaymentRemittanceAmt(df.format(bigDecimal));

                    bigDecimal = new BigDecimal(receipt.getPaymentAppliedAmt1());
                    bigDecimal = bigDecimal.multiply(new BigDecimal(100.00));
                    receipt.setPaymentAppliedAmt1(df.format(bigDecimal));
                    receipt.setValid(true);
                } else {
                    if (!isValidReceiptNumber){
                        receipt.setErrorMsg(Constants.RECEIPT_NUMBER_ERROR_MSG);
                    }else if(!isValidInvoiceNumber){
                        receipt.setErrorMsg(Constants.INVOICE_NUMBER_ERROR_MSG);
                    } else if(!isValidDate){
                        receipt.setErrorMsg(Constants.COLLECTION_DATE_EMPTY_MSG);
                    } else if (!isNotEmptyInvoiceAmt) {
                        receipt.setErrorMsg(Constants.INVOICE_AMT_EMPTY_ERROR_MSG);
                    } else if (!isNotEmptyReceiptAmt) {
                        receipt.setErrorMsg(Constants.RECEIPT_AMT_EMPTY_ERROR_MSG);
                    } else if (!isValidAmt) {
                        receipt.setErrorMsg(Constants.RECEIPT_AMOUNT_ERROR_MSG);
                    } else if (!isValidInvoiceAmt) {
                        receipt.setErrorMsg(Constants.RECEIPT_INVOICE_AMOUNT_ERROR_MSG);
                    } else if(!isNotEmptyPaymentMode) {
                    	receipt.setErrorMsg(Constants.PAYMENTMODE_EMPTY_ERROR_MSG);
                    } else if(!isValidReceiptMethod) {
                    	receipt.setErrorMsg(Constants.INVALID_RECEIPTMETHOD);
                    } else if(!isCustomerAvailable){
                        receipt.setErrorMsg(Constants.CUSTOMER_NOTAVAILABLE_ERROR_MSG);
                    } else if(!isInvoiceAvailable){
                        receipt.setErrorMsg(Constants.INVOICE_NOTAVAILABLE_ERROR_MSG);
                    }
                }
                //end validation

                
            }
        } catch (IOException ioe){
            LOG.error("Exception message : " + ioe.getMessage());
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("Exception message : " + evlError.getMessage());
            LOG.error("EvalError Exception : ", evlError);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }

        return receipt;
    }
}
