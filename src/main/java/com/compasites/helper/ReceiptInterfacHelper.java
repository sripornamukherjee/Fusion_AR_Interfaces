package com.compasites.helper;

import com.compasites.constants.Constants;
import com.compasites.pojo.Receipt;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.oracle.xmlns.adf.svc.types.AmountType;
import com.oracle.xmlns.apps.financials.receivables.receipts.shared.createandapplyreceiptservice.commonservice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Sobhan Babu on 25-05-2016.
 */
public class ReceiptInterfacHelper {

    static Logger LOG = LoggerFactory.getLogger(ReceiptInterfacHelper.class);

    private CreateAndApplyReceiptService receiptService = null;

    @Value("${oraclefusion.username}")
    private String fusionUsername;

    @Value("${oraclefusion.password}")
    private String fusionPassword;

    @Value("${receipt.endpointaddress}")
    private String receiptEndpointAddress;

    @PostConstruct
    public void init(){
        LOG.debug("Receipt service init method called.");
    }

    public void createReceipt(Receipt receipt) throws Exception{
        if (receiptService == null) {
            CreateAndApplyReceiptService_Service service = new CreateAndApplyReceiptService_Service();
            MyHandlerResolver myHandlerResolver = new MyHandlerResolver();
            service.setHandlerResolver(myHandlerResolver);
            receiptService = service.getCreateAndApplyReceiptServiceSoapHttpPort();

            Map<String, Object> requestContext = ((BindingProvider) receiptService).getRequestContext();
            requestContext.put(BindingProvider.USERNAME_PROPERTY, fusionUsername);
            requestContext.put(BindingProvider.PASSWORD_PROPERTY, fusionPassword);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, receiptEndpointAddress);
        }

        ObjectFactory objFactory = new ObjectFactory();
        JAXBElement<String> businessUnitName = objFactory.createCreateAndApplyReceiptBusinessUnitName(receipt.getBusinessUnitName());
        JAXBElement<String> receiptMethodName = objFactory.createCreateAndApplyReceiptReceiptMethodName(receipt.getMethodName());
        JAXBElement<String> receiptNumber = objFactory.createCreateAndApplyReceiptReceiptNumber(receipt.getCollectionNumber());

        //JAXBElement<String> customerName = objFactory.createCreateAndApplyReceiptCustomerName("WEB Services");
        JAXBElement<String> customerAccountNumber = objFactory.createCreateAndApplyReceiptCustomerAccountNumber(receipt.getCustomerId());
        JAXBElement<String> transactionNumber = objFactory.createCreateAndApplyReceiptTransactionNumber(receipt.getInvoiceNumber());
        //JAXBElement<Long> trxId = objFactory.createCreateAndApplyReceiptCustomerTrxId(0l);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date dt = sdf.parse(receipt.getCollectionDate());


        GregorianCalendar c = new GregorianCalendar();
        c.setTime(dt);

        DatatypeFactory df1 = DatatypeFactory.newInstance();
        XMLGregorianCalendar cal1 = df1.newXMLGregorianCalendar(c);
        JAXBElement<XMLGregorianCalendar> glDate = objFactory.createCreateAndApplyReceiptGlDate(cal1);

        AmountType amount = new AmountType();
        amount.setValue(new BigDecimal(receipt.getCollectionAmt()));
        amount.setCurrencyCode(receipt.getCurrencyCode());

        XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        CreateAndApplyReceipt createAndApplyReceipt = new CreateAndApplyReceipt();
        createAndApplyReceipt.setBusinessUnitName(businessUnitName);
        createAndApplyReceipt.setReceiptMethodName(receiptMethodName);
        createAndApplyReceipt.setReceiptNumber(receiptNumber);
        createAndApplyReceipt.setReceiptDate(cal);
        createAndApplyReceipt.setGlDate(glDate);
        createAndApplyReceipt.setAmount(amount);
        //createAndApplyReceipt.setCustomerName(customerName);
        createAndApplyReceipt.setCustomerAccountNumber(customerAccountNumber);
        createAndApplyReceipt.setCurrencyCode(receipt.getCurrencyCode());
        createAndApplyReceipt.setTransactionNumber(transactionNumber);

        CreateAndApplyReceiptResult response = receiptService.createAndApplyReceipt(createAndApplyReceipt);

        LOG.debug("Response : " + response.getValue().get(0).getBusinessUnitName());

    }

    @PreDestroy
    public void destory(){
        LOG.debug("Receipt service destroy method called.");
        receiptService = null;
    }
}
