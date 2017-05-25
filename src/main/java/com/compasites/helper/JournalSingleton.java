package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.Journal;
import com.compasites.pojo.LineSegmentHolder;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sobhan Babu on 12-07-2016.
 */
public class JournalSingleton {

    private static volatile JournalSingleton singleton;

    private Map<String, ArrayList<Journal>> errorMap = new HashMap<String, ArrayList<Journal>>();

    private Map<String, ArrayList<Journal>> validMap = new HashMap<String, ArrayList<Journal>>();

    private Map<String, LineSegmentHolder> mapCache = new HashMap<String, LineSegmentHolder>();

    private String errorFileDateTime;

    //private constructor
    private  JournalSingleton(){}

    public static JournalSingleton getInstance(){
        if (singleton == null) { // first time lock
            synchronized (JournalSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new JournalSingleton();
                }
            }
        }
        return singleton;
    }

    public ArrayList<Journal> getValidList(Journal journal){
        ArrayList<Journal> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(journal.getInvoiceNumber()) &&
                !validMap.containsKey(journal.getInvoiceNumber())){
            if (!validMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<Journal>>> entryset = validMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<Journal>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                validMap.remove(key);
            }
        }

        return returnVal;
    }

    public ArrayList<Journal> getErrorList(Journal journal){
        ArrayList<Journal> returnVal = null;
        if (errorMap.isEmpty() && validMap.isEmpty()){
            return null;
        }

        if (!errorMap.containsKey(journal.getInvoiceNumber()) &&
                !validMap.containsKey(journal.getInvoiceNumber())){
            if (!errorMap.isEmpty()){
                Set<Map.Entry<String, ArrayList<Journal>>> entryset = errorMap.entrySet();
                String key = null;
                for(Map.Entry<String, ArrayList<Journal>> entry : entryset){
                    key = entry.getKey();
                    returnVal = entry.getValue();
                }
                errorMap.remove(key);
            }
        }

        return returnVal;
    }

    public void putInvoice(Journal journal, boolean isValid){
        if (isValid){
            //valid case
            if (errorMap.containsKey(journal.getInvoiceNumber())){
                ArrayList<Journal> list = errorMap.get(journal.getInvoiceNumber());
                list.add(journal);
                Journal jrnl = list.get(0);
                for (Journal jornal : list){
                    jornal.setErrorMsg(jrnl.getErrorMsg());
                }
            }else {
                ArrayList<Journal> list = validMap.get(journal.getInvoiceNumber());
                if (list == null){
                    list = new ArrayList<Journal>();
                    list.add(journal);
                    //journal.setHeaderAmt(new BigDecimal(journal.getNetTotalAmt()));
                    journal.setHeaderAmt(new BigDecimal(journal.getAllocatedRevAmt()));
                    validMap.put(journal.getInvoiceNumber(), list);
                }else {
                    BigDecimal headerAmtSum = new BigDecimal(0);
                    list.add(journal);
                    for (Journal jornal : list){
                        //headerAmtSum = headerAmtSum.add(new BigDecimal(jornal.getNetTotalAmt()));
                        headerAmtSum = headerAmtSum.add(new BigDecimal(jornal.getAllocatedRevAmt()));
                    }

                    for (Journal jornal : list){
                        jornal.setHeaderAmt(headerAmtSum);
                    }
                }

            }
        }else {
            //invalid case
            if (validMap.containsKey(journal.getInvoiceNumber())){
                ArrayList<Journal> list = validMap.get(journal.getInvoiceNumber());
                list.add(journal);
                for (Journal jornal : list){
                    jornal.setErrorMsg(journal.getErrorMsg());
                }
                validMap.remove(journal.getInvoiceNumber());
                errorMap.put(journal.getInvoiceNumber(), list);
            }else {
                ArrayList<Journal> list = errorMap.get(journal.getInvoiceNumber());
                if (list == null){
                    list = new ArrayList<Journal>();
                }
                list.add(journal);
                errorMap.put(journal.getInvoiceNumber(), list);
            }
        }
    }

    public ArrayList<Journal> getValidList(){
        ArrayList<Journal> returnVal = null;
        Set<Map.Entry<String, ArrayList<Journal>>> entryset = validMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<Journal>> entry : entryset){
            key = entry.getKey();
            returnVal = entry.getValue();
        }
        errorMap.remove(key);

        return returnVal;
    }

    public ArrayList<Journal> getErrorList(){
        ArrayList<Journal> returnVal = null;
        Set<Map.Entry<String, ArrayList<Journal>>> entryset = errorMap.entrySet();
        String key = null;
        for(Map.Entry<String, ArrayList<Journal>> entry : entryset){
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
}
