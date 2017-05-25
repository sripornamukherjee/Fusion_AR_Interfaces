package com.compasites.pojo;

/**
 * Created by Sobhan Babu on 26-05-2016.
 */
public class CreditNote {

    private String customerId;
    private String creditAmount;
    private String collectionId;
    private String comments;
    private String collectionDate;
    private String custBankAccntId;
    private String billRefNumber;

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCreditAmount() {
        return creditAmount;
    }
    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }
    public String getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getCollectionDate() {
        return collectionDate;
    }
    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }
    public String getCustBankAccntId() {
        return custBankAccntId;
    }
    public void setCustBankAccntId(String custBankAccntId) {
        this.custBankAccntId = custBankAccntId;
    }
    public String getBillRefNumber() {
        return billRefNumber;
    }
    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
    }
}
