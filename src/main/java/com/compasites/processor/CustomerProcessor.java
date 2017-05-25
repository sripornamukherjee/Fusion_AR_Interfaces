package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.Constants;
import com.compasites.constants.CustomerEnum;
import com.compasites.helper.ReportRunHelper;
import com.compasites.pojo.Customer;
import com.compasites.utils.DateUtil;
import com.compasites.utils.FileUtil;
import com.compasites.validations.CommonValidation;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sobhan Babu on 21-04-2016.
 */
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    static Logger LOG = LoggerFactory.getLogger(CustomerProcessor.class);

    @Autowired
    private ReportRunHelper reportRunHelper;

    @Value("${customer.batchIdentifierfromProperties}")
    private String batchIdentifierFromProperties;

    @Value("${customer.batchIdentifier}")
    private String batchIdentifier;

    @Value("${bsh.mappingfile.location}")
    private String mappingFile;

    @Value("${output.dir}")
    private String outputDir;

    @Value("${customer.errorfolder}")
    private String errorFolder;

    @Value("${customer.errorfile}")
    private String filename;

    @Value("${customer.doCustomerValidation}")
    private String doCustomerAvailableValidation;

    @Override
    public Customer process(final Customer customer) throws Exception {

        final Customer transformedCustomer = customer;

        try {
            String custName = transformedCustomer.getCustomerName();
            String nameAsPerId = transformedCustomer.getContactPersonName();
            if(!nameAsPerId.equalsIgnoreCase(Constants.EMTPY) && !(nameAsPerId==null)) {
            	custName = nameAsPerId;
            }
 
            if (custName != null){
                custName = custName.replaceAll(Constants.COMMA, "");
            }

            custName = CommonValidation.replaceDoubleQuote(custName);
            transformedCustomer.setContactPersonName(CommonValidation.removeComma(transformedCustomer.getContactPersonName()));
            transformedCustomer.setContactPersonName(CommonValidation.replaceDoubleQuote(transformedCustomer.getContactPersonName()));
            transformedCustomer.setBillingAddr1(CommonValidation.removeComma(transformedCustomer.getBillingAddr1()));
            transformedCustomer.setBillingAddr2(CommonValidation.removeComma(transformedCustomer.getBillingAddr2()));
            
            //Added on 15/11 to replace double quotes and remove extra spaces in the addresses
            transformedCustomer.setBillingAddr1(CommonValidation.replaceDoubleQuote(transformedCustomer.getBillingAddr1()));
            transformedCustomer.setBillingAddr2(CommonValidation.replaceDoubleQuote(transformedCustomer.getBillingAddr2()));
            transformedCustomer.setBillingAddr1(CommonValidation.removeExtraSpace(transformedCustomer.getBillingAddr1()));
            transformedCustomer.setBillingAddr2(CommonValidation.removeExtraSpace(transformedCustomer.getBillingAddr2()));
            
            /* changed on 8/11 - due to no billing address line 3 in input
            transformedCustomer.setBillingAddr3(CommonValidation.removeComma(transformedCustomer.getBillingAddr3()));*/

            transformedCustomer.setContactPersonName1(Constants.EMTPY);

            String accntNumber = Constants.EMTPY;
            if (custName.length() > 3){
                accntNumber = custName.substring(0,3);
                accntNumber = accntNumber + System.currentTimeMillis();
            }
            transformedCustomer.setAccntNum(accntNumber);
            transformedCustomer.setCustomerName(custName);

            //setting batch id
            if (Customer.batchId == null){
                if (Constants.YES.equalsIgnoreCase(batchIdentifierFromProperties)){
                    //from properties
                    Customer.batchId = batchIdentifier;
                }else {
                    //from web service
                    Customer.batchId = reportRunHelper.getBatchIdentifier();
                }
            }

            //Not get batch identifier then throw error
            if (Customer.batchId == null || Constants.EMTPY.equalsIgnoreCase(Customer.batchId)){
                throw new RuntimeException("Batch identifier is not found. Please set batch identifier.");
            }

            transformedCustomer.setBatchIdentifier(Customer.batchId);
            /*transformedCustomer.setInsertUpdateIndicator(insertUpdateIndicator);
             
             */
            Interpreter i = new Interpreter(); // Construct an interpreter
            i.source(mappingFile);

            Map<String, String> map = new HashMap<String, String>();
            CustomerEnum.setValuesMap(transformedCustomer, map);

            i.set("map", map);
            i.eval("mapCustomerValues(map)");
            map = (Map) i.get("map");

            CustomerEnum.setValuesCustomer(transformedCustomer, map);

            String customerStatus = transformedCustomer.getCustomerStatus();
            String customerType = transformedCustomer.getCustomerType();

            if(!Constants.AFLAG.equalsIgnoreCase(customerStatus) && !Constants.IFLAG.equalsIgnoreCase(customerStatus)) {
                if (Customer.errorTime == null){
                    Customer.errorTime = DateUtil.getDateTime();
                }
                customer.setErrorMsg(Constants.CUSTOMER_STATUS_ERROR_MSG);
                writeToErrorFile(customer);
                return null;
            }else if(!Constants.PERSON.equalsIgnoreCase(customerType) && !Constants.ORGANIZATION.equalsIgnoreCase(customerType)){
                if (Customer.errorTime == null){
                    Customer.errorTime = DateUtil.getDateTime();
                }
                customer.setErrorMsg(Constants.CUSTOMER_TYPE_ERROR_MSG);
                writeToErrorFile(customer);
                return null;
            }else if(Constants.YES.equalsIgnoreCase(doCustomerAvailableValidation)){
                if(reportRunHelper.checkCustomerAvailable(transformedCustomer.getCustomerId())) {
                    if (Customer.errorTime == null) {
                        Customer.errorTime = DateUtil.getDateTime();
                    }
                    customer.setErrorMsg(Constants.CUSTOMER_ID_EXIST_ERROR_MSG);
                    writeToErrorFile(customer);
                    return null;
                }
            }
            /* 
             * else if(Constants.YES.equalsIgnoreCase(doCustomerAvailableValidation)){
              	boolean customerAvailable = reportRunHelper.checkCustomerAvailable(transformedCustomer.getCustomerId());
                if(customerAvailable && insertUpdateIndicator.equalsIgnoreCase("I")) {
                	if (Customer.errorTime == null) {
                    	Customer.errorTime = DateUtil.getDateTime();
                	}
                	customer.setErrorMsg(Constants.CUSTOMER_ID_EXIST_ERROR_MSG);
                    writeToErrorFile(customer);
                    return null;
                } else if (!customerAvailable && insertUpdateIndicator.equalsIgnoreCase("U"){
                	if (Customer.errorTime == null) {
                    	Customer.errorTime = DateUtil.getDateTime();
                	}
                	customer.setErrorMsg(Constants.CUSTOMER_ID_DOES_NOT_EXIST_ERROR_MSG);
                	writeToErrorFile(customer);
                    return null;
                }         
                
            }*/
        } catch (IOException ioe){
            LOG.error("IOException message : " + ioe.getMessage());
            LOG.error("IOException : ", ioe);
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("EvalError message  : " + evlError.getMessage());
            LOG.error("EvalError : ", evlError);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception message : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }

        return transformedCustomer;
    }

    private void writeToErrorFile(Customer customer) throws IOException {
        String fileName = errorFolder + filename + Customer.errorTime + Constants.CSV;
        FileWriter erroFileWriter = new FileWriter(fileName, true);
        BufferedWriter errorFileBw = new BufferedWriter(erroFileWriter);
        try {
            errorFileBw.write(customer.getErrorLine());
        } catch (Exception e){
            LOG.error("Exception message : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }finally {
            erroFileWriter.flush();
            errorFileBw.close();
        }
    }
}
