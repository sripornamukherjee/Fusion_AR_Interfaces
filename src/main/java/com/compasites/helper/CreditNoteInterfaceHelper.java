package com.compasites.helper;

import com.compasites.pojo.CreditNote;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.oracle.xmlns.adf.svc.types.AmountType;
import com.oracle.xmlns.apps.financials.receivables.receipts.shared.standardreceiptservice.commonservice.ObjectFactory;
import com.oracle.xmlns.apps.financials.receivables.receipts.shared.standardreceiptservice.commonservice.StandardReceipt;
import com.oracle.xmlns.apps.financials.receivables.receipts.shared.standardreceiptservice.commonservice.StandardReceiptService;
import com.oracle.xmlns.apps.financials.receivables.receipts.shared.standardreceiptservice.commonservice.StandardReceiptService_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sobhan Babu on 27-05-2016.
 */
public class CreditNoteInterfaceHelper {

    static Logger LOG = LoggerFactory.getLogger(CreditNoteInterfaceHelper.class);

    public void createCreditNote(CreditNote creditnote) throws Exception{
        StandardReceiptService_Service service = new StandardReceiptService_Service();
        StandardReceiptService creditnoteService = service.getStandardReceiptServiceSoapHttpPort();
        ObjectFactory objFactory = new ObjectFactory();

        Map<String, Object> requestContext = ((BindingProvider)creditnoteService).getRequestContext();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, "fin_final_impl");
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "Welcome12345");
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://echg-test.fin.ap2.oraclecloud.com/finArRcptsSharedCommonService/StandardReceiptService");

        AmountType amount = new AmountType();
        amount.setValue(new BigDecimal(500.00));
        amount.setCurrencyCode("SGD");

        AmountType discountAmt = new AmountType();
        //amount.setValue(new BigDecimal(500.00));
        amount.setCurrencyCode("SGD");

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());

        DatatypeFactory df1 = DatatypeFactory.newInstance();
        XMLGregorianCalendar cal1 = df1.newXMLGregorianCalendar(c);
        JAXBElement<XMLGregorianCalendar> anticipatedClrDt = objFactory.createStandardReceiptAnticipatedClearingDate(cal1);
        JAXBElement<String> comments = objFactory.createStandardReceiptComments("Rejected");
        JAXBElement<Long> custBankAccntId = objFactory.createStandardReceiptCustomerBankAccountId(10011l);
        JAXBElement<String> cusReceiptRef = objFactory.createStandardReceiptCustomerReceiptReference("10021");
        JAXBElement<Long> custId = objFactory.createStandardReceiptCustomerId(26007l);
        JAXBElement<AmountType> factorDiscAmt = objFactory.createStandardReceiptFactorDiscountAmount(discountAmt);

        StandardReceipt standardReceipt = new StandardReceipt();
        standardReceipt.setAmount(amount);
        standardReceipt.setAnticipatedClearingDate(anticipatedClrDt);
        standardReceipt.setCashReceiptId(10021l);
        standardReceipt.setComments(comments);
        standardReceipt.setCustomerBankAccountId(custBankAccntId);
        standardReceipt.setCustomerReceiptReference(cusReceiptRef);
        standardReceipt.setDepositDate(anticipatedClrDt);
        standardReceipt.setCurrencyCode("SGD");
        standardReceipt.setCustomerId(custId);
        standardReceipt.setFactorDiscountAmount(factorDiscAmt);
        standardReceipt.setOrgId(300000001919104l);
        standardReceipt.setReceiptMethodId(10021l);
        standardReceipt.setReceiptDate(cal1);

        creditnoteService.createStandardReceipt(standardReceipt);

    }
}