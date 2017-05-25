package com.compasites.listener;

import com.compasites.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sobhan Babu on 24-06-2016.
 */
public class CustomerAccountContactResponsibilities implements ItemWriter<Customer> {

    static Logger LOG = LoggerFactory.getLogger(ContactPointWriter.class);

    @Value("${customer.output.folder}")
    private String outputFolder;

    @Value("${customer.output.contactResponsibilities}")
    private String cntctResponsibilitiesFile;

    @Override
    public void write(List<? extends Customer> items) throws IOException {
        FileWriter writer = new FileWriter(outputFolder + cntctResponsibilitiesFile, true);
        BufferedWriter bw = new BufferedWriter(writer);
        try {
            for(Customer customer : items) {
                bw.write(customer.getAccountBillToDetails());
                bw.write(customer.getAccountStmtDetails());
            }
        }catch (Exception e){
            LOG.error("Exception message  : " + e.getMessage());
            LOG.error("Exception : ", e);
        }finally {
            bw.flush();
            bw.close();
        }
    }
}
