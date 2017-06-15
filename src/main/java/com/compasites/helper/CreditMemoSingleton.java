package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.listener.CreditMemoCustomWriter;
import com.compasites.pojo.CreditMemo;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.CreditMemo;
import com.compasites.pojo.LineSegmentHolder;
import com.oracle.xmlns.oxp.service.v2.ReportService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sobhan Babu on 12-06-2016.
 */
public class CreditMemoSingleton {
	static Logger LOG = LoggerFactory.getLogger(CreditMemoSingleton.class);

    private static volatile CreditMemoSingleton singleton;

    private Map<String, ArrayList<CreditMemo>> errorMap = new HashMap<String, ArrayList<CreditMemo>>();

    private Map<String, ArrayList<CreditMemo>> validMap = new HashMap<String, ArrayList<CreditMemo>>();

    private ArrayList<String> invoiceRef = new ArrayList<String>();
    
    private Map<String, LineSegmentHolder> mapCache = new HashMap<String, LineSegmentHolder>();

    private Map<String, Boolean> customerMap = new HashMap<String, Boolean>();

    private Map<String, Boolean> CreditMemoMap = new HashMap<String, Boolean>();
    
    private Map<String, Boolean> InvoiceMap = new HashMap<String, Boolean>();

    private String errorFileDateTime;

    private ReportService reportService;

    //private constructor
    private  CreditMemoSingleton(){}

    public static CreditMemoSingleton getInstance(){
        if (singleton == null) { // first time lock
            synchronized (CreditMemoSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new CreditMemoSingleton();
                }
            }
        }
        return singleton;
    }

    public void addInvoiceRef(String invoiceLineRef) {
    	invoiceRef.add(invoiceLineRef);
    }
    
    public boolean isInvoiceRefAvailable(String invoiceLineRef) {
    	boolean retVal = false;
    	if(invoiceRef.contains(invoiceLineRef))
    		retVal = true;
    	
    	return retVal;
    }
    
    public void addCustomer(String customerId, Boolean available){
        customerMap.put(customerId, available);
    }

    public boolean isCustomerAvailable(String customerId){
        boolean retVal = false;
        if (customerMap.containsKey(customerId)){
            retVal = true;
        }
        return retVal;
    }

    public boolean getCustomerStatus(String customerId){
        return customerMap.get(customerId);
    }

    public void addCreditMemo(String CreditMemoNumber, Boolean available){
        CreditMemoMap.put(CreditMemoNumber, available);
    }

    public boolean isCreditMemoAvailable(String CreditMemoNumber){
        boolean retVal = false;
        if (CreditMemoMap.containsKey(CreditMemoNumber)){
            retVal = true;
        }
        return retVal;
    }

    public boolean getCreditMemoStatus(String CreditMemoNumber){
        return CreditMemoMap.get(CreditMemoNumber);
    }
    
    public void addInvoice(String InvoiceNumber, Boolean available){
        InvoiceMap.put(InvoiceNumber, available);
    }

    public boolean isInvoiceAvailable(String InvoiceNumber){
        boolean retVal = false;
        if (InvoiceMap.containsKey(InvoiceNumber)){
            retVal = true;
        }
        return retVal;
    }

    public boolean getInvoiceStatus(String InvoiceNumber){
        return InvoiceMap.get(InvoiceNumber);
    }

    public ArrayList<CreditMemo> getValidList(CreditMemo CreditMemo){
        ArrayList<CreditMemo> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(CreditMemo.getCreditNoteNumber()) &&
                !validMap.containsKey(CreditMemo.getCreditNoteNumber())){
            if (!validMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<CreditMemo>>> entryset = validMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<CreditMemo>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                validMap.remove(key);
            }
        }

        return returnVal;
    }

    public ArrayList<CreditMemo> getErrorList(CreditMemo CreditMemo){
        ArrayList<CreditMemo> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(CreditMemo.getCreditNoteNumber()) &&
                !validMap.containsKey(CreditMemo.getCreditNoteNumber())){
            if (!errorMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<CreditMemo>>> entryset = errorMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<CreditMemo>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                errorMap.remove(key);
            }
        }

        return returnVal;
    }

    public void putCreditMemo(CreditMemo CreditMemo, boolean isValid){
    	DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        if (isValid){
            //valid case
            if (errorMap.containsKey(CreditMemo.getCreditNoteNumber())){
            	LOG.info("Error Map contains "+CreditMemo.getCreditNoteNumber());
                ArrayList<CreditMemo> list = errorMap.get(CreditMemo.getCreditNoteNumber());
                list.add(CreditMemo);
                CreditMemo inv = list.get(0);
                for (CreditMemo invice : list){
                    invice.setErrorMsg(inv.getErrorMsg());
                }
            }else {
            	BigDecimal headerAmtSum = new BigDecimal(0);
                ArrayList<CreditMemo> list = validMap.get(CreditMemo.getCreditNoteNumber());
                
                if (list == null){
                    list = new ArrayList<CreditMemo>();
                    list.add(CreditMemo);
                    BigDecimal allocatedRevAmt = new BigDecimal(CreditMemo.getAllocatedRevAmt());
                    BigDecimal sfcFunding = (!(CreditMemo.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    					new BigDecimal(CreditMemo.getSfcFundedAmt()) : new BigDecimal(0)) ;
                   
                    BigDecimal wdaFunding = (!(CreditMemo.getWdaFundAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                            			new BigDecimal(CreditMemo.getWdaFundAmt()) : new BigDecimal(0)) ;
                    
                    BigDecimal discount = (!(CreditMemo.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                                    			new BigDecimal(CreditMemo.getDiscountAmt()) : new BigDecimal(0)) ;        			
                    BigDecimal forfeitedAmt = (!(CreditMemo.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                			new BigDecimal(CreditMemo.getForfeitedAmt()) : new BigDecimal(0)) ;
		            BigDecimal couponAmt = (!(CreditMemo.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                			new BigDecimal(CreditMemo.getCouponAmt()) : new BigDecimal(0)) ;
                    
                    if((allocatedRevAmt.abs().compareTo(sfcFunding.abs()) == 0) || (allocatedRevAmt.abs().compareTo(wdaFunding.abs()) == 0) ||
                    		(allocatedRevAmt.abs().compareTo(discount.abs()) == 0) || (allocatedRevAmt.abs().compareTo(forfeitedAmt.abs()) == 0) ||
            				(allocatedRevAmt.abs().compareTo(couponAmt.abs()) == 0))
                    	CreditMemo.setHeaderAmt(new BigDecimal(0));
                    else {
                    	allocatedRevAmt = allocatedRevAmt.add(getDiscountAmount(CreditMemo));
	                    headerAmtSum = allocatedRevAmt;
	                    
	                    /*BigDecimal gstAmount = new BigDecimal(df.format(getGstAmount(CreditMemo)));
	                    LOG.info("GST "+gstAmount);*/
	                    headerAmtSum = headerAmtSum.add(getTotalFundingAmount(CreditMemo));
	                    LOG.info("(HEADER AMOUNT SUM+Funding) w/o GST "+headerAmtSum.toString());
	                    
	                    /*headerAmtSum = headerAmtSum.add(gstAmount);
	                    LOG.info("HEADER AMOUNT + GST "+headerAmtSum.toString());*/
	                    
	                    CreditMemo.setHeaderAmt(new BigDecimal(CreditMemo.getAllocatedRevAmt()));
                    }
                  
                    validMap.put(CreditMemo.getCreditNoteNumber(), list);
                }else {
                    list.add(CreditMemo);
                    for (CreditMemo cm : list){                       
                    	BigDecimal temp = new BigDecimal(0);
                        BigDecimal allocatedRevAmt = new BigDecimal(cm.getAllocatedRevAmt());
                        BigDecimal sfcFunding = (!(cm.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
            					new BigDecimal(cm.getSfcFundedAmt()) : new BigDecimal(0)) ;
			            BigDecimal wdaFunding = (!(cm.getWdaFundAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
			                    			new BigDecimal(cm.getWdaFundAmt()) : new BigDecimal(0)) ;
			            BigDecimal discount = (!(cm.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
			                            			new BigDecimal(cm.getDiscountAmt()) : new BigDecimal(0)) ;        			
			            BigDecimal forfeitedAmt = (!(cm.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    			new BigDecimal(cm.getForfeitedAmt()) : new BigDecimal(0)) ;
			            BigDecimal couponAmt = (!(cm.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    			new BigDecimal(cm.getCouponAmt()) : new BigDecimal(0)) ;
                        
			            if((allocatedRevAmt.abs().compareTo(sfcFunding.abs()) == 0) || (allocatedRevAmt.abs().compareTo(wdaFunding.abs()) == 0) ||
                        		(allocatedRevAmt.abs().compareTo(discount.abs()) == 0) || (allocatedRevAmt.abs().compareTo(forfeitedAmt.abs()) == 0) ||
                        				(allocatedRevAmt.abs().compareTo(couponAmt.abs()) == 0)) {
                        	LOG.info("Funding/ Discount line");
                        } else {
                        	temp = allocatedRevAmt.add(getDiscountAmount(cm));
    	                    /*BigDecimal gstAmount = new BigDecimal(df.format(getGstAmount(cm)));*/
    	                    
                        	temp = temp.add(getTotalFundingAmount(cm));
    	                    headerAmtSum = headerAmtSum.add(temp);
    	                    
    	                    /*headerAmtSum = headerAmtSum.add(gstAmount);*/
                        }
                    }
                }

                for (CreditMemo invice : list){
                    LOG.info("Header Amount being set to "+headerAmtSum.toString());
                    invice.setHeaderAmt(headerAmtSum);
                }

            }
        }else {
            //invalid case
            if (validMap.containsKey(CreditMemo.getCreditNoteNumber())){
                ArrayList<CreditMemo> list = validMap.get(CreditMemo.getCreditNoteNumber());
                list.add(CreditMemo);
                for (CreditMemo invice : list){
                    invice.setErrorMsg(CreditMemo.getErrorMsg());
                }
                validMap.remove(CreditMemo.getCreditNoteNumber());
                errorMap.put(CreditMemo.getCreditNoteNumber(), list);
            }else {
                ArrayList<CreditMemo> list = errorMap.get(CreditMemo.getCreditNoteNumber());
                if (list == null){
                    list = new ArrayList<CreditMemo>();
                }
                list.add(CreditMemo);
                errorMap.put(CreditMemo.getCreditNoteNumber(), list);
            }
        }
    }
    
    public BigDecimal getTotalFundingAmount(CreditMemo i) {
    	String sfcFunding = i.getSfcFundedAmt();
        BigDecimal sfcFundedAmt = new BigDecimal(0);
        String wdaFunding = i.getWdaFundAmt();
        BigDecimal wdaFundedAmt = new BigDecimal(0);
        if(!sfcFunding.equalsIgnoreCase(Constants.EMTPY))
        	sfcFundedAmt = new BigDecimal(sfcFunding);
        if(!wdaFunding.equalsIgnoreCase(Constants.EMTPY))
        	wdaFundedAmt = new BigDecimal(wdaFunding);
        
        return sfcFundedAmt.add(wdaFundedAmt);
    }
    
    public BigDecimal getDiscountAmount(CreditMemo c) {
    	String discount = c.getDiscountAmt();
    	String forfeited = c.getForfeitedAmt();
    	String coupon = c.getCouponAmt();
    	
        BigDecimal discountAmt = new BigDecimal(0);
        BigDecimal forfeitedAmt = new BigDecimal(0);
        BigDecimal couponAmt = new BigDecimal(0);
        
        if(!discount.equalsIgnoreCase(Constants.EMTPY))
        	discountAmt = new BigDecimal(discount);
        if(!forfeited.equalsIgnoreCase(Constants.EMTPY))
        	forfeitedAmt = new BigDecimal(forfeited);
        if(!coupon.equalsIgnoreCase(Constants.EMTPY))
        	couponAmt = new BigDecimal(coupon);
        
        return discountAmt.add(forfeitedAmt).add(couponAmt);
    }
    
    public BigDecimal getGstAmount(CreditMemo i) {
    	/*BigDecimal funding = getTotalFundingAmount(i);
    	 String gstPercent = i.getGstPercent();
    	 BigDecimal gstAmount = new BigDecimal(0);
    	 BigDecimal invoiceLineGstAmt = new BigDecimal(i.getGstAmount());
         BigDecimal allocatedRevAmt = new BigDecimal(i.getAllocatedRevAmt());
         BigDecimal discount = getDiscountAmount(i);
         allocatedRevAmt = allocatedRevAmt.add(discount);
           
         if (gstPercent.length() > 0 && gstPercent.indexOf(Constants.PERCENT_SIGN) > -1 && 
        		 invoiceLineGstAmt.compareTo(BigDecimal.ZERO) != 0){
         	gstPercent = gstPercent.substring(0, gstPercent.length() - 1);
         	if(allocatedRevAmt.signum() == 1 && funding.compareTo(BigDecimal.ZERO) != 0) {
         		gstAmount = BigDecimal.ZERO;
         	} else {
         		LOG.info("Calculating GST for amount: "+allocatedRevAmt);
         		gstAmount = allocatedRevAmt.multiply(new BigDecimal(gstPercent));
             	gstAmount = gstAmount.divide(new BigDecimal(100));
         	} 
         }*/
         BigDecimal gstAmount = new BigDecimal(i.getGstAmount());
         return gstAmount;
         	
    }

    public void setLineSegment(CreditMemo CreditMemo){
        boolean isContains = mapCache.containsKey(CreditMemo.getCreditNoteNumber());
        if (!isContains){
            String lineSeg = Constants.CM_LINE_TRANSACTION_FLEX_FIELD + GregorianCalendar.getInstance().getTimeInMillis();
            LineSegmentHolder lineSegment = new LineSegmentHolder();
            lineSegment.setLineSeg1(lineSeg);
            lineSegment.setLineSeg2(1);
            CreditMemo.setLineTransactionFlexfieldSeg1(lineSeg);
            CreditMemo.setLineTransactionFlexfieldSeg2(String.valueOf(lineSegment.getLineSeg2()));
            mapCache.put(CreditMemo.getCreditNoteNumber(), lineSegment);
        }else {
            LineSegmentHolder lineSegment = mapCache.get(CreditMemo.getCreditNoteNumber());
            lineSegment.setLineSeg2(lineSegment.getLineSeg2() + 1);
            CreditMemo.setLineTransactionFlexfieldSeg1(lineSegment.getLineSeg1());
            CreditMemo.setLineTransactionFlexfieldSeg2(String.valueOf(lineSegment.getLineSeg2()));
        }
    }

    public ArrayList<CreditMemo> getValidList(){
        ArrayList<CreditMemo> returnVal = null;
        Set<Map.Entry<String, ArrayList<CreditMemo>>> entryset = validMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<CreditMemo>> entry : entryset){
            key = entry.getKey();
            returnVal = entry.getValue();
        }
        errorMap.remove(key);

        return returnVal;
    }

    public ArrayList<CreditMemo> getErrorList(){
        ArrayList<CreditMemo> returnVal = null;
        Set<Map.Entry<String, ArrayList<CreditMemo>>> entryset = errorMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<CreditMemo>> entry : entryset){
            key = entry.getKey();
            returnVal = entry.getValue();
        }
        errorMap.remove(key);

        return returnVal;
    }

    public String getErrorFileDateTime() {
        return errorFileDateTime;
    }

    public void setErrorFileDateTime(String errorFileDateTime) {
        this.errorFileDateTime = errorFileDateTime;
    }

    public void removeInstance(){
        singleton = null;
    }

    public ReportService getReportService() {
        return reportService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }
}


