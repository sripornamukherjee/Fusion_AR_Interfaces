package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.constants.InvoiceEnum;
import com.compasites.helper.InvoiceSingleton;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.Customer;
import com.compasites.pojo.Invoice;
import com.compasites.utils.DateUtil;
import com.compasites.validations.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public class InvoiceProcessor implements ItemProcessor<Invoice, Invoice>{

    static Logger LOG = LoggerFactory.getLogger(InvoiceProcessor.class);

    @Autowired
    private ReportRunHelper reportRunHelper;

    @Value("${bsh.invoice.mappingfile.location}")
    private String mappingFile;

    @Value("${billDate.validDays}")
    private int validDays;


    @Override
    public Invoice process(final Invoice invoice) throws Exception {
        try {
        	LOG.info("Processing Invoice number: "+invoice.getInvoiceNumber());

            String fileDate = invoice.getBillDate();
            if (fileDate != null && fileDate.length() > 0){
                String dateSplit[] = fileDate.split(Constants.SLASH);
                invoice.setBillMonth(dateSplit[1]);
                invoice.setBillYear(dateSplit[0]);
            }
           
            if(!invoice.isLastLine()) {
                Interpreter i = new Interpreter(); // Construct an interpreter
                i.source(mappingFile);

                Map<String, String> map = new HashMap<String, String>();
                InvoiceEnum.setValuesMap(invoice, map);

                i.set("map", map);
                i.eval("mapInvoiceValues(map)");
                map = (Map) i.get("map");

                InvoiceEnum.setValuesInvoice(invoice, map);

                // If the renewal year is in the past, revenue recognition should be immediate.
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                if(!invoice.getRenewalYear().equalsIgnoreCase(Constants.EMTPY)) {
	                if(sdf.parse(invoice.getRenewalYear()).before(sdf.parse(invoice.getBillYear()))) {
	                	invoice.setRevenueSchedulingRuleName("Immediate");
	                	invoice.setRevenuedSchedulingRuleStrtDt(fileDate);
	                	invoice.setNoOfRevenuePeriods(Constants.EMTPY);
	                }
                }

                //removing comma from customer name
                if(invoice.getCustomerName() != null)
                	invoice.setCustomerName(CommonValidation.removeComma(invoice.getCustomerName()));

                //validation starts
                DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
                boolean isNotEmptyAmt = CommonValidation.stringNotEmpty(invoice.getGrossTotalAmt()) && 
                			CommonValidation.stringNotEmpty(invoice.getGstAmount()) &&
                			CommonValidation.stringNotEmpty(invoice.getAllocatedRevAmt());
                
                boolean isValidAmt = true;
                if (isNotEmptyAmt) {
                	isValidAmt = CommonValidation.amtDecimalLen(invoice.getAllocatedRevAmt()) &&
                			CommonValidation.amtDecimalLen(invoice.getGstAmount()) && CommonValidation.amtDecimalLen(invoice.getGrossTotalAmt());
                }
                
                boolean isCustomerAvailable = false;
                boolean isInvoiceAvailable = false;

                String billDate = invoice.getBillDate();
                boolean isNotEmptyBillDate = CommonValidation.stringNotEmpty(billDate);
                boolean isValidDate = false;
                if (isValidAmt && isNotEmptyAmt && billDate != null && billDate.length() > 0) {
                    isValidDate = CommonValidation.isValidDate(billDate, (validDays * -1));
                }
                

                boolean isValidRevenueAccntCode = CommonValidation.stringNotEmpty(invoice.getRevenueAcCode());
                boolean isValidTransactionType = CommonValidation.stringNotEmpty(invoice.getFmsTransactionType());
                boolean isValidTransactionTypeLineItem = CommonValidation.stringNotEmpty(invoice.getTransactionTypeLineItem());
                
                boolean isWdaSfcValid = true;
                boolean isRenewalYearValid = true;
                
                if(invoice.getBshMappingValid().equalsIgnoreCase(Constants.WDA_SFC_INVALID)){
                    isWdaSfcValid = false;
                } 
                
                if(invoice.getBshMappingValid().equalsIgnoreCase(Constants.RENEWAL_YEAR_INVALID)) {
                	isRenewalYearValid = false;
                }
                
                boolean isValidMemoLine = true;
                if(isValidAmt && isNotEmptyAmt && isWdaSfcValid && isRenewalYearValid) {
                		if(invoice.getMemoLineName() == null || !CommonValidation.stringNotEmpty(invoice.getMemoLineName()))
                			isValidMemoLine = false;
                }
                
                // format all amounts to 2 decimal places
                if(isValidAmt && isNotEmptyAmt && isWdaSfcValid && isRenewalYearValid && isValidMemoLine) {
                	invoice.setGrossTotalAmt(df.format(new BigDecimal(invoice.getGrossTotalAmt())));
                	invoice.setGstAmount(df.format(new BigDecimal(invoice.getGstAmount())));
                	invoice.setAllocatedRevAmt(df.format(new BigDecimal(invoice.getAllocatedRevAmt())));	
                }
                
                
                if (isValidDate && isValidAmt && isNotEmptyAmt && isValidRevenueAccntCode && 
                		isValidTransactionTypeLineItem && isValidTransactionType && isWdaSfcValid && isRenewalYearValid && isValidMemoLine){
                    //isCustomerAvailable = true;
                    //isInvoiceAvailable = false;
                    InvoiceSingleton singleton = InvoiceSingleton.getInstance();
                    LOG.info("Validating line "+singleton.getLineCount());
                    singleton.setLineCount(singleton.getLineCount()+1);
                    
                    if (!singleton.isCustomerAvailable(invoice.getCustomerId())) {
                        isCustomerAvailable = reportRunHelper.checkCustomerAvailable(invoice.getCustomerId());
                        singleton.addCustomer(invoice.getCustomerId(), isCustomerAvailable);
                    }else {
                        isCustomerAvailable = singleton.getCustomerStatus(invoice.getCustomerId());
                    }

                    if (isCustomerAvailable) {
                        if (!singleton.isInvoiceAvailable(invoice.getInvoiceNumber())) {
                            isInvoiceAvailable = reportRunHelper.checkInvoiceAvailability(invoice.getCustomerId(),
                                    invoice.getInvoiceNumber());
                            singleton.addInvoice(invoice.getInvoiceNumber(), isInvoiceAvailable);
                        }else {
                            isInvoiceAvailable = singleton.getInvoiceStatus(invoice.getInvoiceNumber());
                        }
                    }

                }
                if (isCustomerAvailable && !isInvoiceAvailable) {
                    invoice.setGrossTotalAmt(df.format(new BigDecimal(invoice.getGrossTotalAmt())));
                    invoice.setAllocatedRevAmt(df.format(new BigDecimal(invoice.getAllocatedRevAmt())));
                    invoice.setValid(true);
                } else {
                    if (!isNotEmptyBillDate){
                        invoice.setErrorMsg(Constants.BILLDATE_EMPTY_ERROR_MSG);
                    } else if(!isNotEmptyAmt){
                        invoice.setErrorMsg(Constants.AMT_EMPTY_ERROR_MSG);
                    } else if(!isValidAmt){
                        invoice.setErrorMsg(Constants.INVALID_AMT_ERROR_MSG);
                    } else if (!isValidDate) {
                        invoice.setErrorMsg(Constants.BILL_DATE_ERROR_MSG);
                    } else if (!isValidRevenueAccntCode) {
                        invoice.setErrorMsg(Constants.REVENUEACCOUNTCODE_EMPTY_ERROR_MSG);
                    } else if(!isValidTransactionType){
                        invoice.setErrorMsg(Constants.FMS_TRANSACTION_TYPE_EMPTY_ERROR_MSG);
                    } else if(!isValidTransactionTypeLineItem){
                        invoice.setErrorMsg(Constants.TRANSACTION_TYPE_LINEITEM_EMPTY_ERROR_MSG);
                    } else if(!isWdaSfcValid){
                        invoice.setErrorMsg(Constants.WDA_SFC_AMOUNT_ERROR_MSG);
                    } else if(!isRenewalYearValid){
                    	invoice.setErrorMsg(Constants.INVALID_RENEWAL_YR_ERROR_MSG);
                    } else if(!isValidMemoLine){
                        invoice.setErrorMsg(Constants.INVALID_MEMO_LINE);
                    }  else if(!isCustomerAvailable){
                        invoice.setErrorMsg(Constants.CUSTOMER_NOTAVAILABLE_ERROR_MSG);
                    } else if(isInvoiceAvailable){
                        invoice.setErrorMsg(Constants.INVOICE_AVAILABLE_ERROR_MSG);
                    } else{
                        invoice.setErrorMsg(Constants.INVOICE_AVAILABLE_ERROR_MSG);
                    }
                }
                //validation ends
                
                invoice.setCurrecnyConversionDt(invoice.getBillDate());
            }
        } catch (IOException ioe){
            LOG.error("IOException message : " + ioe.getMessage());
            LOG.error("IOException : ", ioe);
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("EvalError message : " + evlError.getMessage());
            LOG.error("EvalError : ", evlError);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception message: " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }

        return invoice;
    }

    /*private void writeToErrorFile(Invoice invoice) throws IOException {
        InvoiceSingleton singleton = InvoiceSingleton.getInstance();
        String fileName = errorFolder + filename + singleton.getErrorFileDateTime() + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);
        try {
            errorFileBw.write(invoice.getErrorLine());
        } catch (Exception e){
            LOG.error("Exception : " + e.getMessage());
            LOG.error("Exception ", e);
            throw e;
        }finally {
            erroFileWriter.flush();
            errorFileBw.close();
        }
    }*/
}


