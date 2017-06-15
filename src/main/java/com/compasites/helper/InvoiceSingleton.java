package com.compasites.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compasites.constants.Constants;
import com.compasites.listener.InvoiceCustomWriter;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.LineSegmentHolder;
import com.oracle.xmlns.oxp.service.v2.ReportService;

/**
 * Created by Sobhan Babu on 12-06-2016.
 */
public class InvoiceSingleton {
	static Logger LOG = LoggerFactory.getLogger(InvoiceSingleton.class);
	
    private static volatile InvoiceSingleton singleton;
    
    private int lineCount;

    private Map<String, ArrayList<Invoice>> errorMap = new HashMap<String, ArrayList<Invoice>>();

    private Map<String, ArrayList<Invoice>> validMap = new HashMap<String, ArrayList<Invoice>>();

    private Map<String, LineSegmentHolder> mapCache = new HashMap<String, LineSegmentHolder>();

    private Map<String, Boolean> customerMap = new HashMap<String, Boolean>();

    private Map<String, Boolean> invoiceMap = new HashMap<String, Boolean>();

    private String errorFileDateTime;

    private ReportService reportService;

    //private constructor
    private  InvoiceSingleton(){}

    public static InvoiceSingleton getInstance(){
        if (singleton == null) { // first time lock
            synchronized (InvoiceSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new InvoiceSingleton();
                }
            }
        }
        return singleton;
    }
    
    public int getLineCount() {
    	return lineCount;
    }
    
    public void setLineCount(int lineCount) {
    	this.lineCount = lineCount;
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

    public void addInvoice(String invoiceNumber, Boolean available){
        invoiceMap.put(invoiceNumber, available);
    }

    public boolean isInvoiceAvailable(String invoiceNumber){
        boolean retVal = false;
        if (invoiceMap.containsKey(invoiceNumber)){
            retVal = true;
        }
        return retVal;
    }

    public boolean getInvoiceStatus(String invoiceNumber){
        return invoiceMap.get(invoiceNumber);
    }

    public ArrayList<Invoice> getValidList(Invoice invoice){
        ArrayList<Invoice> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(invoice.getInvoiceNumber()) &&
                !validMap.containsKey(invoice.getInvoiceNumber())){
            if (!validMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<Invoice>>> entryset = validMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<Invoice>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                validMap.remove(key);
            }
        }

        return returnVal;
    }

    public ArrayList<Invoice> getErrorList(Invoice invoice){
        ArrayList<Invoice> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(invoice.getInvoiceNumber()) &&
                !validMap.containsKey(invoice.getInvoiceNumber())){
            if (!errorMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<Invoice>>> entryset = errorMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<Invoice>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                errorMap.remove(key);
            }
        }

        return returnVal;
    }

    public void putInvoice(Invoice invoice, boolean isValid){
    	DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
        if (isValid){
            //valid case
            if (errorMap.containsKey(invoice.getInvoiceNumber())){
                ArrayList<Invoice> list = errorMap.get(invoice.getInvoiceNumber());
                list.add(invoice);
                Invoice inv = list.get(0);
                for (Invoice invice : list){
                    invice.setErrorMsg(inv.getErrorMsg());
                }
            }else {
            	BigDecimal headerAmtSum = new BigDecimal(0);
                ArrayList<Invoice> list = validMap.get(invoice.getInvoiceNumber());
                
                if (list == null){
                    list = new ArrayList<Invoice>();
                    list.add(invoice);
                    String grossTotal = invoice.getGrossTotalAmt();
                    BigDecimal allocatedRevAmt = new BigDecimal(invoice.getAllocatedRevAmt());
                    BigDecimal sfcFunding = (!(invoice.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    					new BigDecimal(invoice.getSfcFundedAmt()) : new BigDecimal(0)) ;
                    //LOG.info("SFC: "+sfcFunding);
                    BigDecimal wdaFunding = (!(invoice.getWdaFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                            			new BigDecimal(invoice.getWdaFundedAmt()) : new BigDecimal(0)) ;
                    //LOG.info("WDA: "+wdaFunding);
                    BigDecimal discount = (!(invoice.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                                    			new BigDecimal(invoice.getDiscountAmt()) : new BigDecimal(0)) ;        			
 
                    BigDecimal forfeitedAmt = (!(invoice.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                			new BigDecimal(invoice.getForfeitedAmt()) : new BigDecimal(0)) ;
		            BigDecimal couponAmt = (!(invoice.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                			new BigDecimal(invoice.getCouponAmt()) : new BigDecimal(0)) ;
		            
		            String memoLineName = invoice.getMemoLineName();
                    
		            // Set header amount to 0 when memoline is for SFC/ WDA
                    if(((memoLineName.equals(Constants.SFC_MEMOLINE) || memoLineName.equals(Constants.WDA_MEMOLINE)) && 
                    		((allocatedRevAmt.abs().compareTo(sfcFunding.abs()) == 0) || (allocatedRevAmt.abs().compareTo(wdaFunding.abs()) == 0))) 
                    		|| ((allocatedRevAmt.abs().compareTo(discount.abs()) == 0) || (allocatedRevAmt.abs().compareTo(forfeitedAmt.abs()) == 0) ||
                    				(allocatedRevAmt.abs().compareTo(couponAmt.abs()) == 0)))
                    	invoice.setHeaderAmt(new BigDecimal(0));
                    else {
	                    allocatedRevAmt = allocatedRevAmt.subtract(getDiscountAmount(invoice)); 
	                    headerAmtSum = allocatedRevAmt;
	                    
	                    /* Removed on 01/06/17
	                     * BigDecimal gstAmount = new BigDecimal(df.format(getGstAmount(invoice)));     
	                    headerAmtSum = headerAmtSum.subtract(getTotalFundingAmount(invoice));
	                    headerAmtSum = headerAmtSum.add(gstAmount);*/
	                    
	                    headerAmtSum = headerAmtSum.subtract(getTotalFundingAmount(invoice));
	                    invoice.setHeaderAmt(new BigDecimal(invoice.getAllocatedRevAmt()));
                    }
                    validMap.put(invoice.getInvoiceNumber(), list);
                }else {
                    list.add(invoice);
                    for (Invoice invice : list){                       
                        BigDecimal temp = new BigDecimal(0);
                        BigDecimal allocatedRevAmt = new BigDecimal(invice.getAllocatedRevAmt());
                        BigDecimal sfcFunding = (!(invice.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
            					new BigDecimal(invice.getSfcFundedAmt()) : new BigDecimal(0)) ;
			            
			            BigDecimal wdaFunding = (!(invice.getWdaFundedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
			                    			new BigDecimal(invice.getWdaFundedAmt()) : new BigDecimal(0)) ;
			            //LOG.info("WDA: "+wdaFunding);
			            BigDecimal discount = (!(invice.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
			                            			new BigDecimal(invice.getDiscountAmt()) : new BigDecimal(0)) ;
			            BigDecimal forfeitedAmt = (!(invice.getForfeitedAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    			new BigDecimal(invice.getForfeitedAmt()) : new BigDecimal(0)) ;
			            BigDecimal couponAmt = (!(invice.getCouponAmt().equalsIgnoreCase(Constants.EMTPY)) ? 
                    			new BigDecimal(invice.getCouponAmt()) : new BigDecimal(0)) ;
			            
			            String memoLineName = invice.getMemoLineName();
	                   
			            // Set header amount to 0 when memoline is for SFC/ WDA or if it's a discount line
                        if(((memoLineName.equals(Constants.SFC_MEMOLINE) || memoLineName.equals(Constants.WDA_MEMOLINE)) && 
                        		((allocatedRevAmt.abs().compareTo(sfcFunding.abs()) == 0) || (allocatedRevAmt.abs().compareTo(wdaFunding.abs()) == 0))) 
                        		|| ((allocatedRevAmt.abs().compareTo(discount.abs()) == 0) || (allocatedRevAmt.abs().compareTo(forfeitedAmt.abs()) == 0) ||
                        				(allocatedRevAmt.abs().compareTo(couponAmt.abs()) == 0))) {
                        	LOG.info("Funding/ Discount line");
                        } else {
                        	temp = allocatedRevAmt.subtract(getDiscountAmount(invice));
                        	
    	                    /* Removed on 01/06/17
    	                     * BigDecimal gstAmount = new BigDecimal(df.format(getGstAmount(invice)));
    	                    temp = temp.subtract(getTotalFundingAmount(invice));
    	                    headerAmtSum = headerAmtSum.add(temp);
    	                    headerAmtSum = headerAmtSum.add(gstAmount);*/
                        	
                        	temp = temp.subtract(getTotalFundingAmount(invice));
                        	headerAmtSum = headerAmtSum.add(temp); 
                        }
                    }
                }

                for (Invoice i : list){
                	LOG.info("Setting header amount sum to: "+headerAmtSum);
                    i.setHeaderAmt(headerAmtSum);
                }
            }
        }else {
            //invalid case
            if (validMap.containsKey(invoice.getInvoiceNumber())){
                ArrayList<Invoice> list = validMap.get(invoice.getInvoiceNumber());
                list.add(invoice);
                for (Invoice invice : list){
                    invice.setErrorMsg(invoice.getErrorMsg());
                }
                validMap.remove(invoice.getInvoiceNumber());
                errorMap.put(invoice.getInvoiceNumber(), list);
            }else {
                ArrayList<Invoice> list = errorMap.get(invoice.getInvoiceNumber());
                if (list == null){
                    list = new ArrayList<Invoice>();
                }
                list.add(invoice);
                errorMap.put(invoice.getInvoiceNumber(), list);
            }
        }
    }
    
    public BigDecimal getDiscountAmount(Invoice i) {
    	String discount = i.getDiscountAmt();
    	String forfeited = i.getForfeitedAmt();
    	String coupon = i.getCouponAmt();
        BigDecimal discountAmt = new BigDecimal(0);
        BigDecimal forfeitedAmt = new BigDecimal(0);
        BigDecimal couponAmt = new BigDecimal(0);
        
        if(discountAmt.signum() == -1)
        	discountAmt = discountAmt.multiply(new BigDecimal(-1));
        if(forfeitedAmt.signum() == -1)
        	forfeitedAmt = forfeitedAmt.multiply(new BigDecimal(-1));
        if(couponAmt.signum() == -1)
        	couponAmt = couponAmt.multiply(new BigDecimal(-1));
        
        if(!discount.equalsIgnoreCase(Constants.EMTPY))
        	discountAmt = new BigDecimal(discount);
        if(!forfeited.equalsIgnoreCase(Constants.EMTPY))
        	forfeitedAmt = new BigDecimal(forfeited);
        if(!coupon.equalsIgnoreCase(Constants.EMTPY))
        	couponAmt = new BigDecimal(coupon);
        
        return discountAmt.add(forfeitedAmt).add(couponAmt);
    }
    
    public BigDecimal getTotalFundingAmount(Invoice i) {
    	String sfcFunding = i.getSfcFundedAmt();
        BigDecimal sfcFundedAmt = new BigDecimal(0);
        
        String wdaFunding = i.getWdaFundedAmt();
        BigDecimal wdaFundedAmt = new BigDecimal(0);
        
        if(!sfcFunding.equalsIgnoreCase(Constants.EMTPY)) {
        	sfcFundedAmt = new BigDecimal(sfcFunding);
        }
        if(!wdaFunding.equalsIgnoreCase(Constants.EMTPY)) {
        	wdaFundedAmt = new BigDecimal(wdaFunding);
        }
        
        return sfcFundedAmt.add(wdaFundedAmt);
    }
    
    public BigDecimal getGstAmount(Invoice i) {
    	 /*String gstPercent = i.getGstPercent();
    	 BigDecimal gstAmount = new BigDecimal(0);
         BigDecimal allocatedRevAmt = new BigDecimal(i.getAllocatedRevAmt());
         BigDecimal discount = getDiscountAmount(i);
         allocatedRevAmt = allocatedRevAmt.subtract(discount);
         // IF GST amount = 0, then no GST on allocated rev
         if (gstPercent.length() > 0 && gstPercent.indexOf(Constants.PERCENT_SIGN) > -1 && !(i.getGstAmount().equals("0.00"))){
         	gstPercent = gstPercent.substring(0, gstPercent.length() - 1);
         	// Calculate GST only when allocated rev +ve or when SFC,WDA,Discount are all empty
         	if(!(allocatedRevAmt.signum() == -1) || (i.getSfcFundedAmt().equalsIgnoreCase(Constants.EMTPY) &
            		i.getWdaFundedAmt().equalsIgnoreCase(Constants.EMTPY) & 
            		i.getDiscountAmt().equalsIgnoreCase(Constants.EMTPY))) {
         		gstAmount = allocatedRevAmt.multiply(new BigDecimal(gstPercent));
             	gstAmount = gstAmount.divide(new BigDecimal(100));
         	} 
         	
         }*/
    	BigDecimal gstAmount = new BigDecimal(i.getGstAmount());
        return gstAmount;
         	
    }

    public void setLineSegment(Invoice invoice){
        boolean isContains = mapCache.containsKey(invoice.getInvoiceNumber());
        if (!isContains){
            String lineSeg = Constants.LINE_TRANSACTION_FLEX_FIELD + GregorianCalendar.getInstance().getTimeInMillis();
            LineSegmentHolder lineSegment = new LineSegmentHolder();
            lineSegment.setLineSeg1(lineSeg);
            lineSegment.setLineSeg2(1);
            invoice.setLineTransactionFlexfieldSeg1(lineSeg);
            invoice.setLineTransactionFlexfieldSeg2(String.valueOf(lineSegment.getLineSeg2()));
            mapCache.put(invoice.getInvoiceNumber(), lineSegment);
        }else {
            LineSegmentHolder lineSegment = mapCache.get(invoice.getInvoiceNumber());
            lineSegment.setLineSeg2(lineSegment.getLineSeg2() + 1);
            invoice.setLineTransactionFlexfieldSeg1(lineSegment.getLineSeg1());
            invoice.setLineTransactionFlexfieldSeg2(String.valueOf(lineSegment.getLineSeg2()));
        }
    }
    

    public ArrayList<Invoice> getValidList(){
        ArrayList<Invoice> returnVal = null;
        Set<Map.Entry<String, ArrayList<Invoice>>> entryset = validMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<Invoice>> entry : entryset){
            key = entry.getKey();
            returnVal = entry.getValue();
        }
        errorMap.remove(key);

        return returnVal;
    }

    public ArrayList<Invoice> getErrorList(){
        ArrayList<Invoice> returnVal = null;
        Set<Map.Entry<String, ArrayList<Invoice>>> entryset = errorMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<Invoice>> entry : entryset){
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


