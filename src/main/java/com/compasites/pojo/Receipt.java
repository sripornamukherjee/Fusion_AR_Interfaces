package com.compasites.pojo;

import com.compasites.constants.Constants;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public class Receipt {
    //input
    private String invoiceSerialLine;
    private String invoiceHeader;
    private String recordType;
    private String customerId;
    private String accounttype;
    private String invoiceNumber;
    private String billDate;

    private String docType;
    private String grossTotAmt;
    private String payMode;
    private String receiptSerialLine;
    private String collection;
    private String collectionNumber;
    private String collectionDate;
    private String collectionAmt;
    private String collectionPayMode;
    private String businessUnitName;
    private String methodName;
    private String currencyCode;

    private boolean isValid;
    private String errorMsg;

    public String getLine1Content(){
        StringBuilder sb = new StringBuilder();
        sb.append(invoiceSerialLine).append(Constants.CVS_SEPERATOR)
                .append(invoiceHeader).append(Constants.CVS_SEPERATOR)
                .append(recordType).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(accounttype).append(Constants.CVS_SEPERATOR)
                .append(invoiceNumber).append(Constants.CVS_SEPERATOR)
                .append(billDate).append(Constants.CVS_SEPERATOR)
                .append(docType).append(Constants.CVS_SEPERATOR)
                .append(grossTotAmt).append(Constants.CVS_SEPERATOR)
                .append(payMode)
                .append("\r\n");

        return sb.toString();
    }

    public String getLine2Content(){
        StringBuilder sb = new StringBuilder();
        sb.append(receiptSerialLine).append(Constants.CVS_SEPERATOR)
                .append(collection).append(Constants.CVS_SEPERATOR)
                .append(collectionNumber).append(Constants.CVS_SEPERATOR)
                .append(collectionDate).append(Constants.CVS_SEPERATOR)
                .append(collectionAmt).append(Constants.CVS_SEPERATOR)
                .append(collectionPayMode)
                .append("\r\n");

        return sb.toString();
    }

    public String getInvoiceSerialLine() {
        return invoiceSerialLine;
    }
    public void setInvoiceSerialLine(String invoiceSerialLine) {
        this.invoiceSerialLine = invoiceSerialLine;
    }
    public String getInvoiceHeader() {
        return invoiceHeader;
    }
    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }
    public String getRecordType() {
        return recordType;
    }
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getAccounttype() {
        return accounttype;
    }
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getBillDate() {
        return billDate;
    }
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
    public String getDocType() {
        return docType;
    }
    public void setDocType(String docType) {
        this.docType = docType;
    }
    public String getGrossTotAmt() {
        return grossTotAmt;
    }
    public void setGrossTotAmt(String grossTotAmt) {
        this.grossTotAmt = grossTotAmt;
    }
    public String getPayMode() {
        return payMode;
    }
    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
    public String getReceiptSerialLine() {
        return receiptSerialLine;
    }
    public void setReceiptSerialLine(String receiptSerialLine) {
        this.receiptSerialLine = receiptSerialLine;
    }
    public String getCollection() {
        return collection;
    }
    public void setCollection(String collection) {
        this.collection = collection;
    }
    public String getCollectionNumber() {
        return collectionNumber;
    }
    public void setCollectionNumber(String collectionNumber) {
        this.collectionNumber = collectionNumber;
    }
    public String getCollectionDate() {
        return collectionDate;
    }
    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }
    public String getCollectionAmt() {
        return collectionAmt;
    }
    public void setCollectionAmt(String collectionAmt) {
        this.collectionAmt = collectionAmt;
    }
    public String getCollectionPayMode() {
        return collectionPayMode;
    }
    public void setCollectionPayMode(String collectionPayMode) {
        this.collectionPayMode = collectionPayMode;
    }
    public String getBusinessUnitName() {
        return businessUnitName;
    }
    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public boolean isValid() {
        return isValid;
    }
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "CustId : " + customerId + " BusinessUnitName: " + businessUnitName +
                " MethodName: " + methodName + " Custome Id : " + customerId +
                " CurrencyCode: " + currencyCode + "ReceiptNumber: " + collectionNumber;
    }
}
