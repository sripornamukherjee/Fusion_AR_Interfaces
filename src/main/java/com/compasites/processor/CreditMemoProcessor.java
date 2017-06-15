package com.compasites.processor;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.compasites.constants.Constants;
import com.compasites.constants.CreditMemoEnum;
import com.compasites.helper.CreditMemoSingleton;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.CreditMemo;
import com.compasites.validations.CommonValidation;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
public class CreditMemoProcessor implements ItemProcessor<CreditMemo, CreditMemo> {

    static Logger LOG = LoggerFactory.getLogger(CreditMemoProcessor.class);

    @Value("${bsh.creditmemo.mappingfile.location}")
    private String mappingFile;

    @Value("${billDate.validDays}")
    private int validDays;

    @Autowired
    private ReportRunHelper reportRunHelper;

    @Override
    public CreditMemo process(final CreditMemo creditMemo) throws Exception {
        try {
            if(!creditMemo.isLastLine()) {
                Interpreter i = new Interpreter(); // Construct an interpreter
                i.source(mappingFile);

                Map<String, String> map = new HashMap<String, String>();
                CreditMemoEnum.setValuesMap(creditMemo, map);
                i.set("map", map);

                i.eval("mapCreditMemoValues(map)");
                map = (Map) i.get("map");

                CreditMemoEnum.setValuesCreditMemo(creditMemo, map);
                creditMemo.setCurrecnyConversionDt(creditMemo.getBillDate());

                //removing comma from customer name
                creditMemo.setCustomerName(CommonValidation.removeComma(creditMemo.getCustomerName()));

                //validation starts
                DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
                boolean isNotEmptyAmt = CommonValidation.stringNotEmpty(creditMemo.getGrossTotalAmt()) && 
            			CommonValidation.stringNotEmpty(creditMemo.getGstAmount()) &&
            			CommonValidation.stringNotEmpty(creditMemo.getAllocatedRevAmt());
                
                boolean isValidAmt = true;
                if (isNotEmptyAmt) {
                	isValidAmt = CommonValidation.amtDecimalLen(creditMemo.getAllocatedRevAmt()) &&
                			CommonValidation.amtDecimalLen(creditMemo.getGstAmount()) && CommonValidation.amtDecimalLen(creditMemo.getGrossTotalAmt());
                }
                
                boolean isNotEmptyBillingHeaderStatus = CommonValidation.stringNotEmpty(creditMemo.getBillingHeaderStatus());
                boolean isNotEmptyPaymentMode = CommonValidation.stringNotEmpty(creditMemo.getPaymentMode());
                
                boolean isCustomerAvailable = false;
                boolean isCreditMemoAvailable = true;
                boolean isInvoiceAvailable = true;
                String invoiceRef;

                //Revenue Account code not empty validation
                boolean isNotEmptyRevenueAccntCode = CommonValidation.stringNotEmpty(creditMemo.getRevenueAcCode());
                boolean isValidTransactionTypeLineItem = CommonValidation.stringNotEmpty(creditMemo.getTransactionTypeLineItem());

                //bill date should not be negative validation
                String billDate = creditMemo.getBillDate();
                boolean isNotEmptyBillDate = CommonValidation.stringNotEmpty(billDate);
                boolean isValidDate = false;
                if (isValidAmt && isNotEmptyAmt && billDate != null && billDate.length() > 0) {
                    isValidDate = CommonValidation.isValidDate(billDate, (validDays * -1));
                }
                
                boolean isWdaSfcValid = true;
                if(creditMemo.getBshMappingValid().equalsIgnoreCase(Constants.WDA_SFC_INVALID)){
                    isWdaSfcValid = false;
                }

                boolean isNotEmptyVoucherRef = true;
                //customer should be available in fusion validation
                if (isValidAmt && isNotEmptyAmt && isNotEmptyBillingHeaderStatus && isNotEmptyPaymentMode && isValidDate 
                		&& isNotEmptyRevenueAccntCode && isWdaSfcValid && isValidTransactionTypeLineItem){
                    //isCustomerAvailable = true;
                    //isCreditMemoAvailable = false;
                	CreditMemoSingleton singleton = CreditMemoSingleton.getInstance();
                	if (!singleton.isCustomerAvailable(creditMemo.getCustomerId())) {
                		isCustomerAvailable = reportRunHelper.checkCustomerAvailable(creditMemo.getCustomerId());
                		singleton.addCustomer(creditMemo.getCustomerId(), isCustomerAvailable);
                	} else {
                            isCustomerAvailable = singleton.getCustomerStatus(creditMemo.getCustomerId());
                    	}
                    
                	if (isCustomerAvailable) {
                		if (!singleton.isCreditMemoAvailable(creditMemo.getCreditNoteNumber())) {
                			isCreditMemoAvailable = reportRunHelper.checkInvoiceAvailability(creditMemo.getCustomerId(),
                                creditMemo.getCreditNoteNumber());
                			singleton.addCreditMemo(creditMemo.getCreditNoteNumber(), isCreditMemoAvailable);
                        }else {
                        	isCreditMemoAvailable = singleton.getCreditMemoStatus(creditMemo.getCreditNoteNumber());
                        }
                    }
                	
                	if (isCustomerAvailable && !isCreditMemoAvailable) {
                		if(creditMemo.getTransTypeName().equalsIgnoreCase(Constants.REGULAR_CM)) {
                			isNotEmptyVoucherRef = CommonValidation.stringNotEmpty(creditMemo.getVoucherRefNumber());
                			
                			if(isNotEmptyVoucherRef) {
                				isInvoiceAvailable = reportRunHelper.checkInvoiceAvailability(creditMemo.getCustomerId(),
                                        creditMemo.getVoucherRefNumber());
                				
                				if (isInvoiceAvailable) {
                					BigDecimal allocatedRevAmt = new BigDecimal(creditMemo.getAllocatedRevAmt());
                					BigDecimal discount = (!(creditMemo.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
		                            			new BigDecimal(creditMemo.getDiscountAmt()) : new BigDecimal(0));        			
                					LOG.info("DISCOUNT: "+discount);
                					BigDecimal forfeitedAmt = (!(creditMemo.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
	                                 		new BigDecimal(creditMemo.getForfeitedAmt()) : new BigDecimal(0)) ;
	             			       	BigDecimal couponAmt = (!(creditMemo.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
	             			        		new BigDecimal(creditMemo.getCouponAmt()) : new BigDecimal(0)) ;
	             			       	BigDecimal a = new BigDecimal(0);
	             			       	if((allocatedRevAmt.abs().compareTo(discount.abs()) == 0) || (allocatedRevAmt.abs().compareTo(forfeitedAmt.abs()) == 0) ||
                        				(allocatedRevAmt.abs().compareTo(couponAmt.abs()) == 0))
	             			       		a = new BigDecimal(0);
	             			       	else
	             			       		a = allocatedRevAmt.multiply(new BigDecimal(-1)).subtract(discount.add(forfeitedAmt).add(couponAmt));
	             			       
	             			       String refAmount = (df.format(a));
	             			       LOG.info("Reference amount: "+refAmount);
             			            
	             			       invoiceRef = reportRunHelper.getInvoiceLineReference(creditMemo.getCustomerId(),
    	                				creditMemo.getVoucherRefNumber(), refAmount);
		                			
	             			       for (int j = -1; (j = invoiceRef.indexOf(Constants.INTERFACE_LINE_CONTEXT, j + 1)) != -1;) {
	             			    	   if(!singleton.isInvoiceRefAvailable(invoiceRef.substring(j,invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END, j)))) {
	             			    		   LOG.info("Invoice Ref available");
	             			    		   singleton.addInvoiceRef(invoiceRef.substring(j,invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END, j)));
	             			    		   invoiceRef = invoiceRef.substring(j,invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END, j) + 28);
	             			    		   break;
	                          	    } else {
	                          	    	continue;
	                          	    	}
	             			    	}
	             			     
		             			     String[] arr = new String[3];
		             			     if (invoiceRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) >= 0) {
		             			    	 arr[0] = invoiceRef.substring(invoiceRef.indexOf(Constants.INTERFACE_LINE_CONTEXT) + 24, invoiceRef.indexOf(Constants.INTERFACE_LINE_CONTEXT_END));
		                             }
		                             if (invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) > 0) {
		                            	 arr[1] = invoiceRef.substring(invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1) + 27, invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE1_END));
		                             }
		                             if (invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) > 0) {
		                            	 arr[2] = invoiceRef.substring(invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2) + 27, invoiceRef.indexOf(Constants.INTERFACE_LINE_ATTRIBUTE2_END));
		                             }
                					if (arr[0] != null && arr[1] != null && arr[2] != null) {	
			        					creditMemo.setRefTransactionFlexfieldCntxt(arr[0]);
			        					creditMemo.setRefTransactionFlexfieldSegment1(arr[1]);
			        					creditMemo.setRefTransactionFlexfieldSegment2(arr[2]);
			        				} else {
			        					creditMemo.setRefTransactionFlexfieldCntxt(Constants.EMTPY);
			        					creditMemo.setRefTransactionFlexfieldSegment1(Constants.EMTPY);
			        					creditMemo.setRefTransactionFlexfieldSegment2(Constants.EMTPY);
			        					creditMemo.setCreditMtdUsedRevenueSchedulingRules(Constants.EMTPY);
			        				}
                				}
                			}
                		} else {
                			LOG.info("Refund");
                			creditMemo.setRefTransactionFlexfieldCntxt(Constants.EMTPY);
        					creditMemo.setRefTransactionFlexfieldSegment1(Constants.EMTPY);
        					creditMemo.setRefTransactionFlexfieldSegment2(Constants.EMTPY);
        					creditMemo.setCreditMtdUsedRevenueSchedulingRules(Constants.EMTPY);
                    	}
                	} 
                } 

                if (isCustomerAvailable && !isCreditMemoAvailable && isInvoiceAvailable) {
                    creditMemo.setGrossTotalAmt(df.format(new BigDecimal(creditMemo.getGrossTotalAmt())));
                    creditMemo.setAllocatedRevAmt(df.format(new BigDecimal(creditMemo.getAllocatedRevAmt())));
                    creditMemo.setValid(true);
                } else {
                    if (!isNotEmptyBillDate){
                        creditMemo.setErrorMsg(Constants.BILLDATE_EMPTY_ERROR_MSG);
                    } else if(!isNotEmptyAmt){
                        creditMemo.setErrorMsg(Constants.AMT_EMPTY_ERROR_MSG);
                    } else if(!isValidAmt){
                        creditMemo.setErrorMsg(Constants.INVALID_AMT_ERROR_MSG);
                    } else if(!isNotEmptyBillingHeaderStatus){
                        creditMemo.setErrorMsg(Constants.CM_STATUS_EMPTY_ERROR_MSG);
                    } else if(!isNotEmptyPaymentMode){
                        creditMemo.setErrorMsg(Constants.CM_PAYMENT_MODE_EMPTY_ERROR_MSG);
                    }else if (!isValidDate) {
                        creditMemo.setErrorMsg(Constants.BILL_DATE_ERROR_MSG);
                    } else if (!isNotEmptyRevenueAccntCode) {
                        creditMemo.setErrorMsg(Constants.REVENUEACCOUNTCODE_EMPTY_ERROR_MSG);
                    } else if (!isValidTransactionTypeLineItem) {
                        creditMemo.setErrorMsg(Constants.TRANSACTION_TYPE_LINEITEM_EMPTY_ERROR_MSG);
                    } else if(!isWdaSfcValid){
                        creditMemo.setErrorMsg(Constants.WDA_SFC_ERROR_MSG);
                    } else if (!isNotEmptyVoucherRef) {
                        creditMemo.setErrorMsg(Constants.INVOICE_REF_EMPTY);
                    } else if (!isCustomerAvailable) {
                        creditMemo.setErrorMsg(Constants.CUSTOMER_NOTAVAILABLE_ERROR_MSG);
                    } else if (isCreditMemoAvailable){
                        creditMemo.setErrorMsg(Constants.CREDITMEMO_AVAILABLE_ERROR_MSG);
                    } else {
                        creditMemo.setErrorMsg(Constants.INVOICE_NOTAVAILABLE_ERROR_MSG);
                    } 
                }
                //validation ends
                
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
            LOG.error("Exception message : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;

        }

        return creditMemo;
    }
}
