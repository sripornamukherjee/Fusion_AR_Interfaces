package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.LineSegmentHolder;
import com.compasites.pojo.Receipt;
import com.compasites.pojo.ReceiptLock;

import java.util.*;

/**
 * Created by Sobhan Babu on 04-07-2016.
 */
public class ReceiptLockSingleton {

	private int lineCount;
    private static volatile ReceiptLockSingleton singleton;
    private static String trailerReceiptMethod;
    private static String trailerBankOrigNum;
    private static String trailerLockboxNum;
    private static String trailerDestBankAc;
    private static String trailerDate;
    

    private Map<String, ArrayList<ReceiptLock>> errorMap = new HashMap<String, ArrayList<ReceiptLock>>();

    private Map<String, ArrayList<ReceiptLock>> validMap = new HashMap<String, ArrayList<ReceiptLock>>();

    private String errorFileDateTime;

    private int count;

    private String batchName;

    private  ReceiptLockSingleton(){}

    public static ReceiptLockSingleton getInstance(){
        if (singleton == null) { // first time lock
            synchronized (ReceiptLockSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new ReceiptLockSingleton();
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

    public ArrayList<ReceiptLock> getValidList(ReceiptLock receipt){
        ArrayList<ReceiptLock> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(receipt.getReceiptNumber()) &&
                !validMap.containsKey(receipt.getReceiptNumber())){
            if (!validMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<ReceiptLock>>> entryset = validMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<ReceiptLock>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                validMap.remove(key);
            }
        }

        return returnVal;
    }

    public ArrayList<ReceiptLock> getErrorList(ReceiptLock receipt){
        ArrayList<ReceiptLock> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(receipt.getReceiptNumber()) &&
                !validMap.containsKey(receipt.getReceiptNumber())){
            if (!errorMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<ReceiptLock>>> entryset = errorMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<ReceiptLock>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                errorMap.remove(key);
            }
        }

        return returnVal;
    }

    public void putReceipt(ReceiptLock receipt, boolean isValid){
        if (isValid){
            //valid case
            if (errorMap.containsKey(receipt.getReceiptNumber())){
                ArrayList<ReceiptLock> list = errorMap.get(receipt.getReceiptNumber());
                list.add(receipt);
            }else {
                ArrayList<ReceiptLock> list = validMap.get(receipt.getReceiptNumber());
                if (list == null){
                    list = new ArrayList<ReceiptLock>();
                    list.add(receipt);
                    validMap.put(receipt.getReceiptNumber(), list);
                }else {
                    ReceiptLock receiptLock = list.get(0);
                    if (receiptLock.getPaymentTransactionRef2().equals(Constants.EMTPY) && receiptLock.getPaymentAppliedAmt2().equals(Constants.EMTPY)) {
                        receiptLock.setPaymentTransactionRef2(receipt.getPaymentTransactionRef1());
                        receiptLock.setPaymentAppliedAmt2(receipt.getPaymentAppliedAmt1());
                    } else if (receiptLock.getPaymentTransactionRef3().equals(Constants.EMTPY) && receiptLock.getPaymentAppliedAmt3().equals(Constants.EMTPY)) {
                        receiptLock.setPaymentTransactionRef3(receipt.getPaymentTransactionRef1());
                        receiptLock.setPaymentAppliedAmt3(receipt.getPaymentAppliedAmt1());
                    } else if (receiptLock.getPaymentTransactionRef4().equals(Constants.EMTPY) && receiptLock.getPaymentAppliedAmt4().equals(Constants.EMTPY)) {
                        receiptLock.setPaymentTransactionRef3(receipt.getPaymentTransactionRef1());
                        receiptLock.setPaymentAppliedAmt3(receipt.getPaymentAppliedAmt1());
                    }
                    list.add(receipt);
                }

            }
        }else {
            //invalid case
            if (validMap.containsKey(receipt.getReceiptNumber())){
                ArrayList<ReceiptLock> list = validMap.get(receipt.getReceiptNumber());
                list.add(receipt);
                validMap.remove(receipt.getReceiptNumber());
                errorMap.put(receipt.getReceiptNumber(), list);
            }else {
                ArrayList<ReceiptLock> list = errorMap.get(receipt.getReceiptNumber());
                if (list == null){
                    list = new ArrayList<ReceiptLock>();
                }
                list.add(receipt);
                errorMap.put(receipt.getReceiptNumber(), list);
            }
        }
    }

    public ArrayList<ReceiptLock> getValidList(){
        ArrayList<ReceiptLock> returnVal = null;
        Set<Map.Entry<String, ArrayList<ReceiptLock>>> entryset = validMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<ReceiptLock>> entry : entryset){
            key = entry.getKey();
            returnVal = entry.getValue();
        }
        errorMap.remove(key);

        return returnVal;
    }

    public ArrayList<ReceiptLock> getErrorList(){
        ArrayList<ReceiptLock> returnVal = null;
        Set<Map.Entry<String, ArrayList<ReceiptLock>>> entryset = errorMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<ReceiptLock>> entry : entryset){
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

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public void incrementCount(){
        count++;
    }

    public void removeInstance(){
        singleton = null;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

	public String getTrailerReceiptMethod() {
		return trailerReceiptMethod;
	}

	public void setTrailerReceiptMethod(String trailerReceiptMethod) {
		ReceiptLockSingleton.trailerReceiptMethod = trailerReceiptMethod;
	}

	public String getTrailerBankOrigNum() {
		return trailerBankOrigNum;
	}

	public void setTrailerBankOrigNum(String trailerBankOrigNum) {
		ReceiptLockSingleton.trailerBankOrigNum = trailerBankOrigNum;
	}

	public String getTrailerLockboxNum() {
		return trailerLockboxNum;
	}

	public void setTrailerLockboxNum(String trailerLockboxNum) {
		ReceiptLockSingleton.trailerLockboxNum = trailerLockboxNum;
	}

	public String getTrailerDestBankAc() {
		return trailerDestBankAc;
	}

	public void setTrailerDestBankAc(String trailerDestBankAc) {
		ReceiptLockSingleton.trailerDestBankAc = trailerDestBankAc;
	}

	public String getTrailerDate() {
		return trailerDate;
	}

	public void setTrailerDate(String trailerDate) {
		ReceiptLockSingleton.trailerDate = trailerDate;
	}
}
