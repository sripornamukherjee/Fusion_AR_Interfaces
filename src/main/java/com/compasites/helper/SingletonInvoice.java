package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.pojo.Invoice;
import com.compasites.pojo.LineSegmentHolder;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 27-06-2016.
 */
public class SingletonInvoice {

    private static volatile SingletonInvoice singleton;

    //private int count;
    private Map<String, LineSegmentHolder> mapCache = new HashMap<String, LineSegmentHolder>();

    //private constructor
    private  SingletonInvoice(){}

    public static SingletonInvoice getInstance(){
        if (singleton == null) { // first time lock
            synchronized (ReceiptSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new SingletonInvoice();
                }
            }
        }
        return singleton;
    }

    public void setLineSegment(Invoice invoice){
        boolean isContains = mapCache.containsKey(invoice.getHeaderSerialNumber());
        if (!isContains){
            String lineSeg = Constants.LINE_TRANSACTION_FLEX_FIELD + GregorianCalendar.getInstance().getTimeInMillis();
            LineSegmentHolder lineSegment = new LineSegmentHolder();
            lineSegment.setLineSeg1(lineSeg);
            lineSegment.setLineSeg2(1);
            invoice.setLineTransactionFlexfieldSeg1(lineSeg);
            invoice.setLineTransactionFlexfieldSeg2("1");
            mapCache.put(invoice.getHeaderSerialNumber(), lineSegment);
        }else {
            LineSegmentHolder lineSegment = mapCache.get(invoice.getHeaderSerialNumber());
            lineSegment.setLineSeg2(lineSegment.getLineSeg2() + 1);
            invoice.setLineTransactionFlexfieldSeg1(lineSegment.getLineSeg1());
            invoice.setLineTransactionFlexfieldSeg2(String.valueOf(lineSegment.getLineSeg2()));
        }
    }

    public void removeInstance(){
        singleton = null;
    }
}
