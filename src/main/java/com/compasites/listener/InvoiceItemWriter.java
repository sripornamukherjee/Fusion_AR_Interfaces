package com.compasites.listener;

import com.compasites.pojo.Customer;
import com.compasites.pojo.Invoice;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * Created by Sobhan Babu on 17-05-2016.
 */
public class InvoiceItemWriter implements ItemWriteListener<Invoice> {
    @Override
    public void beforeWrite(List<? extends Invoice> list) {
        System.out.println("ItemReadListener - onReadError");

    }

    @Override
    public void afterWrite(java.util.List<? extends Invoice> list) {}

    @Override
    public void onWriteError(Exception ex, List<? extends Invoice> list) {
        System.out.println("ItemReadListener - onReadError");
    }
}
