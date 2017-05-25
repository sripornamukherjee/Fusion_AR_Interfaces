package com.compasites.helper;

import com.compasites.pojo.Invoice;
import com.compasites.pojo.ReceiptLock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 22-06-2016.
 */
public class ReceiptSingleton {

    private static volatile ReceiptSingleton singleton;

    private ReceiptLock receiptLock;
    private int count;

    //private constructor
    private  ReceiptSingleton(){}

    public static ReceiptSingleton getInstance(){
        if (singleton == null) { // first time lock
            synchronized (ReceiptSingleton.class) {
                if (singleton == null) {  // second time lock
                    singleton = new ReceiptSingleton();
                }
            }
        }
        return singleton;
    }

    public void setReceiptLock(ReceiptLock receipt){
        receiptLock = receipt;
    }

    public ReceiptLock getReceiptLock(){
        return receiptLock;
    }

    public void removeInstance(){
        singleton = null;
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
}
