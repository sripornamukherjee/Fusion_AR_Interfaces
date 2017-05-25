package com.compasites.listener;

import com.compasites.constants.Constants;
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
 * Created by Sobhan Babu on 23-06-2016.
 */
public class PartyCustomWriter implements ItemWriter<Customer> {

    static Logger LOG = LoggerFactory.getLogger(PartyCustomWriter.class);

    @Value("${customer.output.folder}")
    private String outputFolder;

    @Value("${customer.output.partiesfile}")
    private String partiesFile;

    @Override
    public void write(List<? extends Customer> items) throws IOException {
        FileWriter writer = new FileWriter(outputFolder + partiesFile, true);
        BufferedWriter bw = new BufferedWriter(writer);
        try {
            for(Customer customer : items) {
                /*if (customer.getContactPersonName() == null || "".equalsIgnoreCase(customer.getContactPersonName())){
                    customer.setContactPersonName(customer.getCustomerName());
                }*/

                if (customer.getCustomerType().equalsIgnoreCase(Constants.PERSON)) {
                    bw.write(customer.getPersonDetailsPerson());
                    bw.write(customer.getPersonDetails());
                }else {
                    bw.write(customer.getOrganizationDetails());
                    bw.write(customer.getPersonDetails());
                }
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
