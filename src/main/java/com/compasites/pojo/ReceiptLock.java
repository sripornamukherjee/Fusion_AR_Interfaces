package com.compasites.pojo;

import com.compasites.constants.Constants;

/**
 * Created by Sobhan Babu on 21-06-2016.
 */
public class ReceiptLock {

    public static String receiptFile;
    public static String lockboxAccountingDt;

    //header
    private String billHeaderIdentifier;
    private String srcSysId;
    private String customerId;
    private String customerName;
    private String accountType;
    private String memClass;
    private String memType;
    private String transactionType;
    private String invoiceNumber;
    private String receiptNumber;
    private String collectionDate;
    private String accountingDate;
    
   

    private String formatedCollectionDate;

    //line item
    private String billingLineItemIdentifier;
    private String paymentMode;
    private String collectionStatus;
    private String invoiceAmount;
    private String receiptAmount;
    private String paymentRefNumber;
    private String paymentAmount;
    private String voucherRefNumber;
    private String coupenAmt;

    private String businessUnitName;
    private String methodName;
    private String currencyCode;

    private String receiptMethod;
    private String lockBoxNumber;
    private String batchName;

    private String status;

    //Record 1
    private String sheet1RecordType;
    private String destBankAccount;
    private String bankOrigNumber;
    private String depositDt;
    private String tranHderDepositTime;
    private String transmissionRecCount;
    private String transmissionAmount;
    private String payInterfaceFlexFieldSeg1;
    private String payInterfaceFlexFieldSeg2;
    private String payInterfaceFlexFieldSeg3;
    private String payInterfaceFlexFieldSeg4;
    private String payInterfaceFlexFieldSeg5;
    private String payInterfaceFlexFieldSeg6;
    private String payInterfaceFlexFieldSeg7;
    private String payInterfaceFlexFieldSeg8;
    private String payInterfaceFlexFieldSeg9;
    private String payInterfaceFlexFieldSeg10;
    private String payInterfaceFlexFieldSeg11;
    private String payInterfaceFlexFieldSeg12;
    private String payInterfaceFlexFieldSeg13;
    private String payInterfaceFlexFieldSeg14;
    private String payInterfaceFlexFieldSeg15;

    //Record 2
    private String serviceHdrRecordType;
    private String depositDate;
    private String depositTime;
    private String serviceHdrBankOrgNumber;

    //Record 5
    private String lockBoxHdrRecordType;
    private String currency;
    private String lockBoxHdrReceiptMethod;
    private String lockBoxHdrLockBoxNumber;
    private String lockBoxHdrDepositDt;
    private String lockBoxHdrDepositTime;
    private String lockBoxHdrLockBoxBatchCount;
    private String lockBoxRecordCount;
    private String lockBoxAmount;
    private String lockBoxHdrBankOriginationNumber;

    //Record 3
    private String batchHdrRecordType;
    private String batchHdrBatchName;
    private String batchHdrConversionRateType;
    private String batchHdrConversionRate;
    private String batchHdrReceiptMethod;
    private String batchHdrLockboxNumber;
    private String batchHdrDepositDt;
    private String batchHdrDepositTime;
    private String batchHdrBatchRecordCnt;
    private String batchHdrBatchAmt;
    private String batchHdrComments;

    //Record 6
    private String paymentRecordType;
    private String paymentBatchName;
    private String paymentItemNumber;
    private String paymentRemittanceAmt;
    private String paymentTransitRoutingNumber;
    private String paymentCustomerBankAccnt;
    private String paymentReceiptNumber;
    private String paymentReceiptDate;
    private String paymentConversionRateType;
    private String paymentConversionRate;
    private String paymentCustomerAccntNumber;
    private String paymentCustomerSite;
    private String paymentCustomerBankBranch;
    private String paymentCustomerBank;
    private String paymentReceiptMethod;
    private String paymentRemitanceBank;
    private String paymentLockBoxNumber;
    private String paymentDepositDate;
    private String paymentDepositTime;
    private String paymentAnticipatedClringDt;
    private String paymentTransactionRef1;
    private String paymentTransactionInst1;
    private String paymentTransactionRefDate1;
    private String paymentTransactionCur1;
    private String paymentCrossCurrencyRate1;
    private String paymentAppliedAmt1;
    private String paymentAllocatedReceiptAmt1;
    private String paymentCustomerRef1;


    private String paymentTransactionRef2;
    private String paymentTransactionInst2;
    private String paymentTransactionRefDate2;
    private String paymentTransactionCur2;
    private String paymentCrossCurrencyRate2;
    private String paymentAppliedAmt2;
    private String paymentAllocatedReceiptAmt2;
    private String paymentCustomerRef2;

    private String paymentTransactionRef3;
    private String paymentTransactionInst3;
    private String paymentTransactionRefDate3;
    private String paymentTransactionCur3;
    private String paymentCrossCurrencyRate3;
    private String paymentAppliedAmt3;
    private String paymentAllocatedReceiptAmt3;
    private String paymentCustomerRef3;

    private String paymentTransactionRef4;
    private String paymentTransactionInst4;
    private String paymentTransactionRefDate4;
    private String paymentTransactionCur4;
    private String paymentCrossCurrencyRate4;
    private String paymentAppliedAmt4;
    private String paymentAllocatedReceiptAmt4;
    private String paymentCustomerRef4;

    private String paymentTransactionRef5;
    private String paymentTransactionInst5;
    private String paymentTransactionRefDate5;
    private String paymentTransactionCur5;
    private String paymentCrossCurrencyRate5;
    private String paymentAppliedAmt5;
    private String paymentAllocatedReceiptAmt5;
    private String paymentCustomerRef5;

    private String paymentTransactionRef6;
    private String paymentTransactionInst6;
    private String paymentTransactionRefDate6;
    private String paymentTransactionCur6;
    private String paymentCrossCurrencyRate6;
    private String paymentAppliedAmt6;
    private String paymentAllocatedReceiptAmt6;
    private String paymentCustomerRef6;

    private String paymentTransactionRef7;
    private String paymentTransactionInst7;
    private String paymentTransactionRefDate7;
    private String paymentTransactionCur7;
    private String paymentCrossCurrencyRate77;
    private String paymentAppliedAmt7;
    private String paymentAllocatedReceiptAmt7;
    private String paymentCustomerRef7;

    private String paymentComments;

    //Record 7
    private String btchTrlrRecordType;
    private String btchTrlrBatchName;
    private String btchTrlrConversionRateTyp;
    private String btchTrlrConversionRate;
    private String btchTrlrReceiptMethod;
    private String btchTrlrLockBoxNumber;
    private String btchTrlrDepositDate;
    private String btchTrlrDepositTime;
    private String btchTrlrBatchRecordCount;
    private String btchTrlrBatchAmount;
    private String btchTrlrComments;

    //Record 8
    private String lokbxTrlrRecordType;
    private String lokbxTrlrReceiptMethod;
    private String lokbxTrlrLockBoxNumber;
    private String lokbxTrlrDepositDt;
    private String lokbxTrlrDepositTime;
    private String lokbxTrlrLockBoxBtchCount;
    private String lokbxTrlrLockBoxRecordCount;
    private String lokbxTrlrLockBoxAmt;
    private String lokbxTrlrBankOriginationNumber;

    //Record 9
    private String transTrlrRecordType;
    private String transTrlrBankOrigNumber;
    private String transTrlrDepositDate;
    private String transTrlrDepositTime;
    private String transTrlrTransmissionRecrdCount;
    private String transTrlrTransmissionAmt;

    private boolean isValid;
    private String errorMsg = Constants.EMTPY;

    public boolean isLastLine(){
        if ((invoiceNumber == null || Constants.EMTPY.equals(invoiceNumber)) &&
                (receiptNumber == null || Constants.EMTPY.equals(receiptNumber)) &&
                (transactionType == null || Constants.EMTPY.equals(transactionType))) {
            return true;
        }

        return false;
    }

    public String getLineData(){
        StringBuilder sb = new StringBuilder();
        sb.append(srcSysId).append(Constants.CVS_SEPERATOR)
                .append(billHeaderIdentifier).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(customerName).append(Constants.CVS_SEPERATOR)
                .append(accountType).append(Constants.CVS_SEPERATOR)
                .append(memClass).append(Constants.CVS_SEPERATOR)
                .append(memType).append(Constants.CVS_SEPERATOR)
                .append(transactionType).append(Constants.CVS_SEPERATOR)
                .append(invoiceNumber).append(Constants.CVS_SEPERATOR)
                .append(receiptNumber).append(Constants.CVS_SEPERATOR)
                .append(collectionDate).append(Constants.CVS_SEPERATOR)
                .append(paymentMode).append(Constants.CVS_SEPERATOR)
                .append(collectionStatus).append(Constants.CVS_SEPERATOR)
                .append(billingLineItemIdentifier).append(Constants.CVS_SEPERATOR)
                .append(invoiceAmount).append(Constants.CVS_SEPERATOR)
                .append(receiptAmount).append(Constants.CVS_SEPERATOR)
                .append(paymentRefNumber).append(Constants.CVS_SEPERATOR)
                .append(paymentAmount).append(Constants.CVS_SEPERATOR)
                .append(voucherRefNumber).append(Constants.CVS_SEPERATOR)
                .append(coupenAmt).append(Constants.CVS_SEPERATOR)
                .append(Constants.ERROR_FLAG).append(Constants.CVS_SEPERATOR)
                .append(errorMsg)
                .append("\r\n");

        return sb.toString();
    }

    //Record 1
    public String getTransmissionHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(Constants.CVS_SEPERATOR)
                .append(destBankAccount).append(Constants.CVS_SEPERATOR)
                .append(bankOrigNumber).append(Constants.CVS_SEPERATOR)
                .append(depositDt)
                .append(",,,,,,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 2
    public String getServiceHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(2).append(Constants.CVS_SEPERATOR)
                .append(depositDate).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(destBankAccount).append(Constants.CVS_SEPERATOR)
                .append(serviceHdrBankOrgNumber).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 5
    public String getLockBoxHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(5).append(Constants.CVS_SEPERATOR)
                .append(currency).append(Constants.CVS_SEPERATOR)
                .append(receiptMethod).append(Constants.CVS_SEPERATOR)
                .append(lockBoxNumber).append(Constants.CVS_SEPERATOR)
                .append(lockBoxHdrDepositDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(destBankAccount).append(Constants.CVS_SEPERATOR)
                .append(lockBoxHdrBankOriginationNumber).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 3
    public String getBatchHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(3).append(Constants.CVS_SEPERATOR)
                .append(batchName).append(Constants.CVS_SEPERATOR)
                .append(currency).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(receiptMethod).append(Constants.CVS_SEPERATOR)
                .append(lockBoxNumber).append(Constants.CVS_SEPERATOR)
                .append(batchHdrDepositDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 6
    public String getPayment(){
        StringBuilder sb = new StringBuilder();
        sb.append(6).append(Constants.CVS_SEPERATOR)
                .append(batchName).append(Constants.CVS_SEPERATOR)
                .append(paymentItemNumber).append(Constants.CVS_SEPERATOR)
                .append(paymentRemittanceAmt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentReceiptNumber).append(Constants.CVS_SEPERATOR)
                .append(paymentReceiptDate).append(Constants.CVS_SEPERATOR)
                .append(currency).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentCustomerAccntNumber).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(paymentReceiptMethod).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(paymentLockBoxNumber).append(Constants.CVS_SEPERATOR)
                .append(paymentDepositDate).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentTransactionRef1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentAppliedAmt1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentTransactionRef2).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentAppliedAmt2).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentTransactionRef3).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentAppliedAmt3).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentTransactionRef4).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(paymentAppliedAmt4).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 7
    public String getBatchTrailer(){
        StringBuilder sb = new StringBuilder();
        sb.append(7).append(Constants.CVS_SEPERATOR)
                .append(batchName).append(Constants.CVS_SEPERATOR)
                .append(currency).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(receiptMethod).append(Constants.CVS_SEPERATOR)
                .append(lockBoxNumber).append(Constants.CVS_SEPERATOR)
                .append(batchHdrDepositDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 8
    public String getLockBoxTrailer(){
        StringBuilder sb = new StringBuilder();
        sb.append(8).append(Constants.CVS_SEPERATOR)
                .append(currency).append(Constants.CVS_SEPERATOR)
                .append(receiptMethod).append(Constants.CVS_SEPERATOR)
                .append(lockBoxNumber).append(Constants.CVS_SEPERATOR)
                .append(lockBoxHdrDepositDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(destBankAccount).append(Constants.CVS_SEPERATOR)
                .append(lockBoxHdrBankOriginationNumber).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    //Record 9
    public String getTransmissionTrailer(){
        StringBuilder sb = new StringBuilder();
        sb.append(9).append(Constants.CVS_SEPERATOR)
                .append(destBankAccount).append(Constants.CVS_SEPERATOR)
                .append(bankOrigNumber).append(Constants.CVS_SEPERATOR)
                .append(depositDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,END")
                .append("\r\n");

        return sb.toString();
    }

    public String getBillHeaderIdentifier() {
        return billHeaderIdentifier;
    }
    public void setBillHeaderIdentifier(String billHeaderIdentifier) {
        this.billHeaderIdentifier = billHeaderIdentifier;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getMemClass() {
        return memClass;
    }
    public void setMemClass(String memClass) {
        this.memClass = memClass;
    }
    public String getMemType() {
        return memType;
    }
    public void setMemType(String memType) {
        this.memType = memType;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getReceiptNumber() {
        return receiptNumber;
    }
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
    public String getCollectionDate() {
        return collectionDate;
    }
    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }
    public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public String getCollectionStatus() {
        return collectionStatus;
    }
    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    public String getBillingLineItemIdentifier() {
        return billingLineItemIdentifier;
    }
    public void setBillingLineItemIdentifier(String billingLineItemIdentifier) {
        this.billingLineItemIdentifier = billingLineItemIdentifier;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public String getInvoiceAmount() {
        return invoiceAmount;
    }
    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
    public String getReceiptAmount() {
        return receiptAmount;
    }
    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
    public String getPaymentRefNumber() {
		return paymentRefNumber;
	}

	public void setPaymentRefNumber(String paymentRefNumber) {
		this.paymentRefNumber = paymentRefNumber;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getVoucherRefNumber() {
        return voucherRefNumber;
    }
    public void setVoucherRefNumber(String voucherRefNumber) {
        this.voucherRefNumber = voucherRefNumber;
    }
    public String getCoupenAmt() {
        return coupenAmt;
    }
    public void setCoupenAmt(String coupenAmt) {
        this.coupenAmt = coupenAmt;
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


    public String getSheet1RecordType() {
        return sheet1RecordType;
    }

    public void setSheet1RecordType(String sheet1RecordType) {
        this.sheet1RecordType = sheet1RecordType;
    }
    public String getDestBankAccount() {
        return destBankAccount;
    }
    public void setDestBankAccount(String destBankAccount) {
        this.destBankAccount = destBankAccount;
    }
    public String getBankOrigNumber() {
        return bankOrigNumber;
    }
    public void setBankOrigNumber(String bankOrigNumber) {
        this.bankOrigNumber = bankOrigNumber;
    }
    public String getDepositDt() {
        return depositDt;
    }
    public void setDepositDt(String depositDt) {
        this.depositDt = depositDt;
    }
    public String getTransmissionRecCount() {
        return transmissionRecCount;
    }
    public void setTransmissionRecCount(String transmissionRecCount) {
        this.transmissionRecCount = transmissionRecCount;
    }
    public String getTransmissionAmount() {
        return transmissionAmount;
    }
    public void setTransmissionAmount(String transmissionAmount) {
        this.transmissionAmount = transmissionAmount;
    }
    public String getPayInterfaceFlexFieldSeg1() {
        return payInterfaceFlexFieldSeg1;
    }
    public void setPayInterfaceFlexFieldSeg1(String payInterfaceFlexFieldSeg1) {
        this.payInterfaceFlexFieldSeg1 = payInterfaceFlexFieldSeg1;
    }
    public String getPayInterfaceFlexFieldSeg2() {
        return payInterfaceFlexFieldSeg2;
    }
    public void setPayInterfaceFlexFieldSeg2(String payInterfaceFlexFieldSeg2) {
        this.payInterfaceFlexFieldSeg2 = payInterfaceFlexFieldSeg2;
    }
    public String getPayInterfaceFlexFieldSeg3() {
        return payInterfaceFlexFieldSeg3;
    }
    public void setPayInterfaceFlexFieldSeg3(String payInterfaceFlexFieldSeg3) {
        this.payInterfaceFlexFieldSeg3 = payInterfaceFlexFieldSeg3;
    }
    public String getPayInterfaceFlexFieldSeg4() {
        return payInterfaceFlexFieldSeg4;
    }
    public void setPayInterfaceFlexFieldSeg4(String payInterfaceFlexFieldSeg4) {
        this.payInterfaceFlexFieldSeg4 = payInterfaceFlexFieldSeg4;
    }
    public String getPayInterfaceFlexFieldSeg5() {
        return payInterfaceFlexFieldSeg5;
    }
    public void setPayInterfaceFlexFieldSeg5(String payInterfaceFlexFieldSeg5) {
        this.payInterfaceFlexFieldSeg5 = payInterfaceFlexFieldSeg5;
    }
    public String getPayInterfaceFlexFieldSeg6() {
        return payInterfaceFlexFieldSeg6;
    }
    public void setPayInterfaceFlexFieldSeg6(String payInterfaceFlexFieldSeg6) {
        this.payInterfaceFlexFieldSeg6 = payInterfaceFlexFieldSeg6;
    }
    public String getPayInterfaceFlexFieldSeg7() {
        return payInterfaceFlexFieldSeg7;
    }
    public void setPayInterfaceFlexFieldSeg7(String payInterfaceFlexFieldSeg7) {
        this.payInterfaceFlexFieldSeg7 = payInterfaceFlexFieldSeg7;
    }
    public String getPayInterfaceFlexFieldSeg8() {
        return payInterfaceFlexFieldSeg8;
    }
    public void setPayInterfaceFlexFieldSeg8(String payInterfaceFlexFieldSeg8) {
        this.payInterfaceFlexFieldSeg8 = payInterfaceFlexFieldSeg8;
    }
    public String getPayInterfaceFlexFieldSeg9() {
        return payInterfaceFlexFieldSeg9;
    }
    public void setPayInterfaceFlexFieldSeg9(String payInterfaceFlexFieldSeg9) {
        this.payInterfaceFlexFieldSeg9 = payInterfaceFlexFieldSeg9;
    }
    public String getPayInterfaceFlexFieldSeg10() {
        return payInterfaceFlexFieldSeg10;
    }
    public void setPayInterfaceFlexFieldSeg10(String payInterfaceFlexFieldSeg10) {
        this.payInterfaceFlexFieldSeg10 = payInterfaceFlexFieldSeg10;
    }
    public String getPayInterfaceFlexFieldSeg11() {
        return payInterfaceFlexFieldSeg11;
    }
    public void setPayInterfaceFlexFieldSeg11(String payInterfaceFlexFieldSeg11) {
        this.payInterfaceFlexFieldSeg11 = payInterfaceFlexFieldSeg11;
    }
    public String getPayInterfaceFlexFieldSeg12() {
        return payInterfaceFlexFieldSeg12;
    }
    public void setPayInterfaceFlexFieldSeg12(String payInterfaceFlexFieldSeg12) {
        this.payInterfaceFlexFieldSeg12 = payInterfaceFlexFieldSeg12;
    }
    public String getPayInterfaceFlexFieldSeg13() {
        return payInterfaceFlexFieldSeg13;
    }
    public void setPayInterfaceFlexFieldSeg13(String payInterfaceFlexFieldSeg13) {
        this.payInterfaceFlexFieldSeg13 = payInterfaceFlexFieldSeg13;
    }
    public String getPayInterfaceFlexFieldSeg14() {
        return payInterfaceFlexFieldSeg14;
    }
    public void setPayInterfaceFlexFieldSeg14(String payInterfaceFlexFieldSeg14) {
        this.payInterfaceFlexFieldSeg14 = payInterfaceFlexFieldSeg14;
    }
    public String getPayInterfaceFlexFieldSeg15() {
        return payInterfaceFlexFieldSeg15;
    }
    public void setPayInterfaceFlexFieldSeg15(String payInterfaceFlexFieldSeg15) {
        this.payInterfaceFlexFieldSeg15 = payInterfaceFlexFieldSeg15;
    }
    public String getServiceHdrRecordType() {
        return serviceHdrRecordType;
    }
    public void setServiceHdrRecordType(String serviceHdrRecordType) {
        this.serviceHdrRecordType = serviceHdrRecordType;
    }
    public String getDepositDate() {
        return depositDate;
    }
    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }
    public String getDepositTime() {
        return depositTime;
    }
    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }
    public String getServiceHdrBankOrgNumber() {
        return serviceHdrBankOrgNumber;
    }
    public void setServiceHdrBankOrgNumber(String serviceHdrBankOrgNumber) {
        this.serviceHdrBankOrgNumber = serviceHdrBankOrgNumber;
    }
    public String getLockBoxHdrRecordType() {
        return lockBoxHdrRecordType;
    }
    public void setLockBoxHdrRecordType(String lockBoxHdrRecordType) {
        this.lockBoxHdrRecordType = lockBoxHdrRecordType;
    }
    public String getLockBoxHdrReceiptMethod() {
        return lockBoxHdrReceiptMethod;
    }
    public void setLockBoxHdrReceiptMethod(String lockBoxHdrReceiptMethod) {
        this.lockBoxHdrReceiptMethod = lockBoxHdrReceiptMethod;
    }
    public String getLockBoxHdrLockBoxNumber() {
        return lockBoxHdrLockBoxNumber;
    }
    public void setLockBoxHdrLockBoxNumber(String lockBoxHdrLockBoxNumber) {
        this.lockBoxHdrLockBoxNumber = lockBoxHdrLockBoxNumber;
    }
    public String getLockBoxHdrDepositDt() {
        return lockBoxHdrDepositDt;
    }
    public void setLockBoxHdrDepositDt(String lockBoxHdrDepositDt) {
        this.lockBoxHdrDepositDt = lockBoxHdrDepositDt;
    }
    public String getLockBoxHdrDepositTime() {
        return lockBoxHdrDepositTime;
    }
    public void setLockBoxHdrDepositTime(String lockBoxHdrDepositTime) {
        this.lockBoxHdrDepositTime = lockBoxHdrDepositTime;
    }
    public String getLockBoxHdrLockBoxBatchCount() {
        return lockBoxHdrLockBoxBatchCount;
    }
    public void setLockBoxHdrLockBoxBatchCount(String lockBoxHdrLockBoxBatchCount) {
        this.lockBoxHdrLockBoxBatchCount = lockBoxHdrLockBoxBatchCount;
    }
    public String getLockBoxRecordCount() {
        return lockBoxRecordCount;
    }
    public void setLockBoxRecordCount(String lockBoxRecordCount) {
        this.lockBoxRecordCount = lockBoxRecordCount;
    }
    public String getLockBoxAmount() {
        return lockBoxAmount;
    }
    public void setLockBoxAmount(String lockBoxAmount) {
        this.lockBoxAmount = lockBoxAmount;
    }
    public String getLockBoxHdrBankOriginationNumber() {
        return lockBoxHdrBankOriginationNumber;
    }
    public void setLockBoxHdrBankOriginationNumber(String lockBoxHdrBankOriginationNumber) {
        this.lockBoxHdrBankOriginationNumber = lockBoxHdrBankOriginationNumber;
    }
    public String getBatchHdrRecordType() {
        return batchHdrRecordType;
    }
    public void setBatchHdrRecordType(String batchHdrRecordType) {
        this.batchHdrRecordType = batchHdrRecordType;
    }
    public String getBatchHdrBatchName() {
        return batchHdrBatchName;
    }
    public void setBatchHdrBatchName(String batchHdrBatchName) {
        this.batchHdrBatchName = batchHdrBatchName;
    }
    public String getBatchHdrConversionRateType() {
        return batchHdrConversionRateType;
    }
    public void setBatchHdrConversionRateType(String batchHdrConversionRateType) {
        this.batchHdrConversionRateType = batchHdrConversionRateType;
    }
    public String getBatchHdrConversionRate() {
        return batchHdrConversionRate;
    }
    public void setBatchHdrConversionRate(String batchHdrConversionRate) {
        this.batchHdrConversionRate = batchHdrConversionRate;
    }
    public String getBatchHdrReceiptMethod() {
        return batchHdrReceiptMethod;
    }
    public void setBatchHdrReceiptMethod(String batchHdrReceiptMethod) {
        this.batchHdrReceiptMethod = batchHdrReceiptMethod;
    }
    public String getBatchHdrLockboxNumber() {
        return batchHdrLockboxNumber;
    }
    public void setBatchHdrLockboxNumber(String batchHdrLockboxNumber) {
        this.batchHdrLockboxNumber = batchHdrLockboxNumber;
    }
    public String getBatchHdrDepositDt() {
        return batchHdrDepositDt;
    }
    public void setBatchHdrDepositDt(String batchHdrDepositDt) {
        this.batchHdrDepositDt = batchHdrDepositDt;
    }
    public String getBatchHdrDepositTime() {
        return batchHdrDepositTime;
    }
    public void setBatchHdrDepositTime(String batchHdrDepositTime) {
        this.batchHdrDepositTime = batchHdrDepositTime;
    }
    public String getBatchHdrBatchRecordCnt() {
        return batchHdrBatchRecordCnt;
    }
    public void setBatchHdrBatchRecordCnt(String batchHdrBatchRecordCnt) {
        this.batchHdrBatchRecordCnt = batchHdrBatchRecordCnt;
    }
    public String getBatchHdrBatchAmt() {
        return batchHdrBatchAmt;
    }
    public void setBatchHdrBatchAmt(String batchHdrBatchAmt) {
        this.batchHdrBatchAmt = batchHdrBatchAmt;
    }
    public String getBatchHdrComments() {
        return batchHdrComments;
    }
    public void setBatchHdrComments(String batchHdrComments) {
        this.batchHdrComments = batchHdrComments;
    }
    public String getPaymentRecordType() {
        return paymentRecordType;
    }

    public void setPaymentRecordType(String paymentRecordType) {
        this.paymentRecordType = paymentRecordType;
    }

    public String getPaymentBatchName() {
        return paymentBatchName;
    }

    public void setPaymentBatchName(String paymentBatchName) {
        this.paymentBatchName = paymentBatchName;
    }

    public String getPaymentItemNumber() {
        return paymentItemNumber;
    }

    public void setPaymentItemNumber(String paymentItemNumber) {
        this.paymentItemNumber = paymentItemNumber;
    }

    public String getPaymentRemittanceAmt() {
        return paymentRemittanceAmt;
    }

    public void setPaymentRemittanceAmt(String paymentRemittanceAmt) {
        this.paymentRemittanceAmt = paymentRemittanceAmt;
    }

    public String getPaymentTransitRoutingNumber() {
        return paymentTransitRoutingNumber;
    }

    public void setPaymentTransitRoutingNumber(String paymentTransitRoutingNumber) {
        this.paymentTransitRoutingNumber = paymentTransitRoutingNumber;
    }

    public String getPaymentCustomerBankAccnt() {
        return paymentCustomerBankAccnt;
    }

    public void setPaymentCustomerBankAccnt(String paymentCustomerBankAccnt) {
        this.paymentCustomerBankAccnt = paymentCustomerBankAccnt;
    }

    public String getPaymentReceiptNumber() {
        return paymentReceiptNumber;
    }

    public void setPaymentReceiptNumber(String paymentReceiptNumber) {
        this.paymentReceiptNumber = paymentReceiptNumber;
    }

    public String getPaymentReceiptDate() {
        return paymentReceiptDate;
    }

    public void setPaymentReceiptDate(String paymentReceiptDate) {
        this.paymentReceiptDate = paymentReceiptDate;
    }

    public String getPaymentConversionRateType() {
        return paymentConversionRateType;
    }

    public void setPaymentConversionRateType(String paymentConversionRateType) {
        this.paymentConversionRateType = paymentConversionRateType;
    }

    public String getPaymentConversionRate() {
        return paymentConversionRate;
    }

    public void setPaymentConversionRate(String paymentConversionRate) {
        this.paymentConversionRate = paymentConversionRate;
    }

    public String getPaymentCustomerAccntNumber() {
        return paymentCustomerAccntNumber;
    }

    public void setPaymentCustomerAccntNumber(String paymentCustomerAccntNumber) {
        this.paymentCustomerAccntNumber = paymentCustomerAccntNumber;
    }

    public String getPaymentCustomerSite() {
        return paymentCustomerSite;
    }

    public void setPaymentCustomerSite(String paymentCustomerSite) {
        this.paymentCustomerSite = paymentCustomerSite;
    }

    public String getPaymentCustomerBankBranch() {
        return paymentCustomerBankBranch;
    }

    public void setPaymentCustomerBankBranch(String paymentCustomerBankBranch) {
        this.paymentCustomerBankBranch = paymentCustomerBankBranch;
    }

    public String getPaymentCustomerBank() {
        return paymentCustomerBank;
    }

    public void setPaymentCustomerBank(String paymentCustomerBank) {
        this.paymentCustomerBank = paymentCustomerBank;
    }

    public String getPaymentReceiptMethod() {
        return paymentReceiptMethod;
    }

    public void setPaymentReceiptMethod(String paymentReceiptMethod) {
        this.paymentReceiptMethod = paymentReceiptMethod;
    }

    public String getPaymentRemitanceBank() {
        return paymentRemitanceBank;
    }

    public void setPaymentRemitanceBank(String paymentRemitanceBank) {
        this.paymentRemitanceBank = paymentRemitanceBank;
    }

    public String getPaymentLockBoxNumber() {
        return paymentLockBoxNumber;
    }

    public void setPaymentLockBoxNumber(String paymentLockBoxNumber) {
        this.paymentLockBoxNumber = paymentLockBoxNumber;
    }

    public String getPaymentDepositDate() {
        return paymentDepositDate;
    }

    public void setPaymentDepositDate(String paymentDepositDate) {
        this.paymentDepositDate = paymentDepositDate;
    }

    public String getPaymentDepositTime() {
        return paymentDepositTime;
    }

    public void setPaymentDepositTime(String paymentDepositTime) {
        this.paymentDepositTime = paymentDepositTime;
    }

    public String getPaymentAnticipatedClringDt() {
        return paymentAnticipatedClringDt;
    }

    public void setPaymentAnticipatedClringDt(String paymentAnticipatedClringDt) {
        this.paymentAnticipatedClringDt = paymentAnticipatedClringDt;
    }

    public String getPaymentTransactionRef1() {
        return paymentTransactionRef1;
    }

    public void setPaymentTransactionRef1(String paymentTransactionRef1) {
        this.paymentTransactionRef1 = paymentTransactionRef1;
    }

    public String getPaymentTransactionInst1() {
        return paymentTransactionInst1;
    }

    public void setPaymentTransactionInst1(String paymentTransactionInst1) {
        this.paymentTransactionInst1 = paymentTransactionInst1;
    }

    public String getPaymentTransactionRefDate1() {
        return paymentTransactionRefDate1;
    }

    public void setPaymentTransactionRefDate1(String paymentTransactionRefDate1) {
        this.paymentTransactionRefDate1 = paymentTransactionRefDate1;
    }

    public String getPaymentTransactionCur1() {
        return paymentTransactionCur1;
    }

    public void setPaymentTransactionCur1(String paymentTransactionCur1) {
        this.paymentTransactionCur1 = paymentTransactionCur1;
    }

    public String getPaymentCrossCurrencyRate1() {
        return paymentCrossCurrencyRate1;
    }

    public void setPaymentCrossCurrencyRate1(String paymentCrossCurrencyRate1) {
        this.paymentCrossCurrencyRate1 = paymentCrossCurrencyRate1;
    }

    public String getPaymentAppliedAmt1() {
        return paymentAppliedAmt1;
    }

    public void setPaymentAppliedAmt1(String paymentAppliedAmt1) {
        this.paymentAppliedAmt1 = paymentAppliedAmt1;
    }

    public String getPaymentAllocatedReceiptAmt1() {
        return paymentAllocatedReceiptAmt1;
    }

    public void setPaymentAllocatedReceiptAmt1(String paymentAllocatedReceiptAmt1) {
        this.paymentAllocatedReceiptAmt1 = paymentAllocatedReceiptAmt1;
    }

    public String getPaymentCustomerRef1() {
        return paymentCustomerRef1;
    }

    public void setPaymentCustomerRef1(String paymentCustomerRef1) {
        this.paymentCustomerRef1 = paymentCustomerRef1;
    }

    public String getPaymentTransactionRef2() {
        return paymentTransactionRef2;
    }

    public void setPaymentTransactionRef2(String paymentTransactionRef2) {
        this.paymentTransactionRef2 = paymentTransactionRef2;
    }

    public String getPaymentTransactionInst2() {
        return paymentTransactionInst2;
    }

    public void setPaymentTransactionInst2(String paymentTransactionInst2) {
        this.paymentTransactionInst2 = paymentTransactionInst2;
    }

    public String getPaymentTransactionRefDate2() {
        return paymentTransactionRefDate2;
    }

    public void setPaymentTransactionRefDate2(String paymentTransactionRefDate2) {
        this.paymentTransactionRefDate2 = paymentTransactionRefDate2;
    }

    public String getPaymentTransactionCur2() {
        return paymentTransactionCur2;
    }

    public void setPaymentTransactionCur2(String paymentTransactionCur2) {
        this.paymentTransactionCur2 = paymentTransactionCur2;
    }

    public String getPaymentCrossCurrencyRate2() {
        return paymentCrossCurrencyRate2;
    }

    public void setPaymentCrossCurrencyRate2(String paymentCrossCurrencyRate2) {
        this.paymentCrossCurrencyRate2 = paymentCrossCurrencyRate2;
    }

    public String getPaymentAppliedAmt2() {
        return paymentAppliedAmt2;
    }

    public void setPaymentAppliedAmt2(String paymentAppliedAmt2) {
        this.paymentAppliedAmt2 = paymentAppliedAmt2;
    }

    public String getPaymentAllocatedReceiptAmt2() {
        return paymentAllocatedReceiptAmt2;
    }

    public void setPaymentAllocatedReceiptAmt2(String paymentAllocatedReceiptAmt2) {
        this.paymentAllocatedReceiptAmt2 = paymentAllocatedReceiptAmt2;
    }

    public String getPaymentCustomerRef2() {
        return paymentCustomerRef2;
    }

    public void setPaymentCustomerRef2(String paymentCustomerRef2) {
        this.paymentCustomerRef2 = paymentCustomerRef2;
    }

    public String getPaymentTransactionRef3() {
        return paymentTransactionRef3;
    }

    public void setPaymentTransactionRef3(String paymentTransactionRef3) {
        this.paymentTransactionRef3 = paymentTransactionRef3;
    }

    public String getPaymentTransactionInst3() {
        return paymentTransactionInst3;
    }

    public void setPaymentTransactionInst3(String paymentTransactionInst3) {
        this.paymentTransactionInst3 = paymentTransactionInst3;
    }

    public String getPaymentTransactionRefDate3() {
        return paymentTransactionRefDate3;
    }

    public void setPaymentTransactionRefDate3(String paymentTransactionRefDate3) {
        this.paymentTransactionRefDate3 = paymentTransactionRefDate3;
    }

    public String getPaymentTransactionCur3() {
        return paymentTransactionCur3;
    }

    public void setPaymentTransactionCur3(String paymentTransactionCur3) {
        this.paymentTransactionCur3 = paymentTransactionCur3;
    }

    public String getPaymentCrossCurrencyRate3() {
        return paymentCrossCurrencyRate3;
    }

    public void setPaymentCrossCurrencyRate3(String paymentCrossCurrencyRate3) {
        this.paymentCrossCurrencyRate3 = paymentCrossCurrencyRate3;
    }

    public String getPaymentAppliedAmt3() {
        return paymentAppliedAmt3;
    }

    public void setPaymentAppliedAmt3(String paymentAppliedAmt3) {
        this.paymentAppliedAmt3 = paymentAppliedAmt3;
    }

    public String getPaymentAllocatedReceiptAmt3() {
        return paymentAllocatedReceiptAmt3;
    }

    public void setPaymentAllocatedReceiptAmt3(String paymentAllocatedReceiptAmt3) {
        this.paymentAllocatedReceiptAmt3 = paymentAllocatedReceiptAmt3;
    }

    public String getPaymentCustomerRef3() {
        return paymentCustomerRef3;
    }

    public void setPaymentCustomerRef3(String paymentCustomerRef3) {
        this.paymentCustomerRef3 = paymentCustomerRef3;
    }

    public String getPaymentTransactionRef4() {
        return paymentTransactionRef4;
    }

    public void setPaymentTransactionRef4(String paymentTransactionRef4) {
        this.paymentTransactionRef4 = paymentTransactionRef4;
    }

    public String getPaymentTransactionInst4() {
        return paymentTransactionInst4;
    }

    public void setPaymentTransactionInst4(String paymentTransactionInst4) {
        this.paymentTransactionInst4 = paymentTransactionInst4;
    }

    public String getPaymentTransactionRefDate4() {
        return paymentTransactionRefDate4;
    }

    public void setPaymentTransactionRefDate4(String paymentTransactionRefDate4) {
        this.paymentTransactionRefDate4 = paymentTransactionRefDate4;
    }

    public String getPaymentTransactionCur4() {
        return paymentTransactionCur4;
    }

    public void setPaymentTransactionCur4(String paymentTransactionCur4) {
        this.paymentTransactionCur4 = paymentTransactionCur4;
    }

    public String getPaymentCrossCurrencyRate4() {
        return paymentCrossCurrencyRate4;
    }

    public void setPaymentCrossCurrencyRate4(String paymentCrossCurrencyRate4) {
        this.paymentCrossCurrencyRate4 = paymentCrossCurrencyRate4;
    }

    public String getPaymentAppliedAmt4() {
        return paymentAppliedAmt4;
    }

    public void setPaymentAppliedAmt4(String paymentAppliedAmt4) {
        this.paymentAppliedAmt4 = paymentAppliedAmt4;
    }

    public String getPaymentAllocatedReceiptAmt4() {
        return paymentAllocatedReceiptAmt4;
    }

    public void setPaymentAllocatedReceiptAmt4(String paymentAllocatedReceiptAmt4) {
        this.paymentAllocatedReceiptAmt4 = paymentAllocatedReceiptAmt4;
    }

    public String getPaymentCustomerRef4() {
        return paymentCustomerRef4;
    }

    public void setPaymentCustomerRef4(String paymentCustomerRef4) {
        this.paymentCustomerRef4 = paymentCustomerRef4;
    }

    public String getPaymentTransactionRef15() {
        return paymentTransactionRef5;
    }

    public void setPaymentTransactionRef15(String paymentTransactionRef15) {
        this.paymentTransactionRef5 = paymentTransactionRef15;
    }

    public String getPaymentTransactionInst5() {
        return paymentTransactionInst5;
    }

    public void setPaymentTransactionInst5(String paymentTransactionInst5) {
        this.paymentTransactionInst5 = paymentTransactionInst5;
    }

    public String getPaymentTransactionRefDate5() {
        return paymentTransactionRefDate5;
    }

    public void setPaymentTransactionRefDate5(String paymentTransactionRefDate5) {
        this.paymentTransactionRefDate5 = paymentTransactionRefDate5;
    }

    public String getPaymentTransactionCur5() {
        return paymentTransactionCur5;
    }

    public void setPaymentTransactionCur5(String paymentTransactionCur5) {
        this.paymentTransactionCur5 = paymentTransactionCur5;
    }

    public String getPaymentCrossCurrencyRate5() {
        return paymentCrossCurrencyRate5;
    }

    public void setPaymentCrossCurrencyRate5(String paymentCrossCurrencyRate5) {
        this.paymentCrossCurrencyRate5 = paymentCrossCurrencyRate5;
    }

    public String getPaymentAppliedAmt5() {
        return paymentAppliedAmt5;
    }

    public void setPaymentAppliedAmt5(String paymentAppliedAmt5) {
        this.paymentAppliedAmt5 = paymentAppliedAmt5;
    }

    public String getPaymentAllocatedReceiptAmt5() {
        return paymentAllocatedReceiptAmt5;
    }

    public void setPaymentAllocatedReceiptAmt5(String paymentAllocatedReceiptAmt5) {
        this.paymentAllocatedReceiptAmt5 = paymentAllocatedReceiptAmt5;
    }

    public String getPaymentCustomerRef5() {
        return paymentCustomerRef5;
    }

    public void setPaymentCustomerRef5(String paymentCustomerRef5) {
        this.paymentCustomerRef5 = paymentCustomerRef5;
    }

    public String getPaymentTransactionRef6() {
        return paymentTransactionRef6;
    }

    public void setPaymentTransactionRef6(String paymentTransactionRef6) {
        this.paymentTransactionRef6 = paymentTransactionRef6;
    }

    public String getPaymentTransactionInst6() {
        return paymentTransactionInst6;
    }

    public void setPaymentTransactionInst6(String paymentTransactionInst6) {
        this.paymentTransactionInst6 = paymentTransactionInst6;
    }

    public String getPaymentTransactionRefDate6() {
        return paymentTransactionRefDate6;
    }

    public void setPaymentTransactionRefDate6(String paymentTransactionRefDate6) {
        this.paymentTransactionRefDate6 = paymentTransactionRefDate6;
    }

    public String getPaymentTransactionCur6() {
        return paymentTransactionCur6;
    }

    public void setPaymentTransactionCur6(String paymentTransactionCur6) {
        this.paymentTransactionCur6 = paymentTransactionCur6;
    }

    public String getPaymentCrossCurrencyRate6() {
        return paymentCrossCurrencyRate6;
    }

    public void setPaymentCrossCurrencyRate6(String paymentCrossCurrencyRate6) {
        this.paymentCrossCurrencyRate6 = paymentCrossCurrencyRate6;
    }

    public String getPaymentAppliedAmt6() {
        return paymentAppliedAmt6;
    }

    public void setPaymentAppliedAmt6(String paymentAppliedAmt6) {
        this.paymentAppliedAmt6 = paymentAppliedAmt6;
    }

    public String getPaymentAllocatedReceiptAmt6() {
        return paymentAllocatedReceiptAmt6;
    }

    public void setPaymentAllocatedReceiptAmt6(String paymentAllocatedReceiptAmt6) {
        this.paymentAllocatedReceiptAmt6 = paymentAllocatedReceiptAmt6;
    }

    public String getPaymentCustomerRef6() {
        return paymentCustomerRef6;
    }

    public void setPaymentCustomerRef6(String paymentCustomerRef6) {
        this.paymentCustomerRef6 = paymentCustomerRef6;
    }

    public String getPaymentTransactionRef7() {
        return paymentTransactionRef7;
    }

    public void setPaymentTransactionRef7(String paymentTransactionRef7) {
        this.paymentTransactionRef7 = paymentTransactionRef7;
    }

    public String getPaymentTransactionInst7() {
        return paymentTransactionInst7;
    }

    public void setPaymentTransactionInst7(String paymentTransactionInst7) {
        this.paymentTransactionInst7 = paymentTransactionInst7;
    }

    public String getPaymentTransactionRefDate7() {
        return paymentTransactionRefDate7;
    }

    public void setPaymentTransactionRefDate7(String paymentTransactionRefDate7) {
        this.paymentTransactionRefDate7 = paymentTransactionRefDate7;
    }

    public String getPaymentTransactionCur7() {
        return paymentTransactionCur7;
    }

    public void setPaymentTransactionCur7(String paymentTransactionCur7) {
        this.paymentTransactionCur7 = paymentTransactionCur7;
    }

    public String getPaymentCrossCurrencyRate77() {
        return paymentCrossCurrencyRate77;
    }

    public void setPaymentCrossCurrencyRate77(String paymentCrossCurrencyRate77) {
        this.paymentCrossCurrencyRate77 = paymentCrossCurrencyRate77;
    }

    public String getPaymentAppliedAmt7() {
        return paymentAppliedAmt7;
    }

    public void setPaymentAppliedAmt7(String paymentAppliedAmt7) {
        this.paymentAppliedAmt7 = paymentAppliedAmt7;
    }

    public String getPaymentAllocatedReceiptAmt7() {
        return paymentAllocatedReceiptAmt7;
    }

    public void setPaymentAllocatedReceiptAmt7(String paymentAllocatedReceiptAmt7) {
        this.paymentAllocatedReceiptAmt7 = paymentAllocatedReceiptAmt7;
    }

    public String getPaymentCustomerRef7() {
        return paymentCustomerRef7;
    }

    public void setPaymentCustomerRef7(String paymentCustomerRef7) {
        this.paymentCustomerRef7 = paymentCustomerRef7;
    }

    public String getPaymentComments() {
        return paymentComments;
    }

    public void setPaymentComments(String paymentComments) {
        this.paymentComments = paymentComments;
    }

    public String getBtchTrlrRecordType() {
        return btchTrlrRecordType;
    }
    public void setBtchTrlrRecordType(String btchTrlrRecordType) {
        this.btchTrlrRecordType = btchTrlrRecordType;
    }
    public String getBtchTrlrBatchName() {
        return btchTrlrBatchName;
    }
    public void setBtchTrlrBatchName(String btchTrlrBatchName) {
        this.btchTrlrBatchName = btchTrlrBatchName;
    }
    public String getBtchTrlrConversionRateTyp() {
        return btchTrlrConversionRateTyp;
    }
    public void setBtchTrlrConversionRateTyp(String btchTrlrConversionRateTyp) {
        this.btchTrlrConversionRateTyp = btchTrlrConversionRateTyp;
    }
    public String getBtchTrlrConversionRate() {
        return btchTrlrConversionRate;
    }
    public void setBtchTrlrConversionRate(String btchTrlrConversionRate) {
        this.btchTrlrConversionRate = btchTrlrConversionRate;
    }
    public String getBtchTrlrReceiptMethod() {
        return btchTrlrReceiptMethod;
    }
    public void setBtchTrlrReceiptMethod(String btchTrlrReceiptMethod) {
        this.btchTrlrReceiptMethod = btchTrlrReceiptMethod;
    }
    public String getBtchTrlrLockBoxNumber() {
        return btchTrlrLockBoxNumber;
    }
    public void setBtchTrlrLockBoxNumber(String btchTrlrLockBoxNumber) {
        this.btchTrlrLockBoxNumber = btchTrlrLockBoxNumber;
    }
    public String getBtchTrlrDepositDate() {
        return btchTrlrDepositDate;
    }
    public void setBtchTrlrDepositDate(String btchTrlrDepositDate) {
        this.btchTrlrDepositDate = btchTrlrDepositDate;
    }
    public String getBtchTrlrDepositTime() {
        return btchTrlrDepositTime;
    }
    public void setBtchTrlrDepositTime(String btchTrlrDepositTime) {
        this.btchTrlrDepositTime = btchTrlrDepositTime;
    }
    public String getBtchTrlrBatchRecordCount() {
        return btchTrlrBatchRecordCount;
    }
    public void setBtchTrlrBatchRecordCount(String btchTrlrBatchRecordCount) {
        this.btchTrlrBatchRecordCount = btchTrlrBatchRecordCount;
    }
    public String getBtchTrlrBatchAmount() {
        return btchTrlrBatchAmount;
    }
    public void setBtchTrlrBatchAmount(String btchTrlrBatchAmount) {
        this.btchTrlrBatchAmount = btchTrlrBatchAmount;
    }
    public String getBtchTrlrComments() {
        return btchTrlrComments;
    }
    public void setBtchTrlrComments(String btchTrlrComments) {
        this.btchTrlrComments = btchTrlrComments;
    }
    public String getLokbxTrlrRecordType() {
        return lokbxTrlrRecordType;
    }
    public void setLokbxTrlrRecordType(String lokbxTrlrRecordType) {
        this.lokbxTrlrRecordType = lokbxTrlrRecordType;
    }
    public String getLokbxTrlrReceiptMethod() {
        return lokbxTrlrReceiptMethod;
    }
    public void setLokbxTrlrReceiptMethod(String lokbxTrlrReceiptMethod) {
        this.lokbxTrlrReceiptMethod = lokbxTrlrReceiptMethod;
    }
    public String getLokbxTrlrLockBoxNumber() {
        return lokbxTrlrLockBoxNumber;
    }
    public void setLokbxTrlrLockBoxNumber(String lokbxTrlrLockBoxNumber) {
        this.lokbxTrlrLockBoxNumber = lokbxTrlrLockBoxNumber;
    }
    public String getLokbxTrlrDepositDt() {
        return lokbxTrlrDepositDt;
    }
    public void setLokbxTrlrDepositDt(String lokbxTrlrDepositDt) {
        this.lokbxTrlrDepositDt = lokbxTrlrDepositDt;
    }
    public String getLokbxTrlrDepositTime() {
        return lokbxTrlrDepositTime;
    }
    public void setLokbxTrlrDepositTime(String lokbxTrlrDepositTime) {
        this.lokbxTrlrDepositTime = lokbxTrlrDepositTime;
    }
    public String getLokbxTrlrLockBoxBtchCount() {
        return lokbxTrlrLockBoxBtchCount;
    }
    public void setLokbxTrlrLockBoxBtchCount(String lokbxTrlrLockBoxBtchCount) {
        this.lokbxTrlrLockBoxBtchCount = lokbxTrlrLockBoxBtchCount;
    }
    public String getLokbxTrlrLockBoxRecordCount() {
        return lokbxTrlrLockBoxRecordCount;
    }
    public void setLokbxTrlrLockBoxRecordCount(String lokbxTrlrLockBoxRecordCount) {
        this.lokbxTrlrLockBoxRecordCount = lokbxTrlrLockBoxRecordCount;
    }
    public String getLokbxTrlrLockBoxAmt() {
        return lokbxTrlrLockBoxAmt;
    }
    public void setLokbxTrlrLockBoxAmt(String lokbxTrlrLockBoxAmt) {
        this.lokbxTrlrLockBoxAmt = lokbxTrlrLockBoxAmt;
    }

    public String getLokbxTrlrBankOriginationNumber() {
        return lokbxTrlrBankOriginationNumber;
    }
    public void setLokbxTrlrBankOriginationNumber(String lokbxTrlrBankOriginationNumber) {
        this.lokbxTrlrBankOriginationNumber = lokbxTrlrBankOriginationNumber;
    }
    public String getTransTrlrRecordType() {
        return transTrlrRecordType;
    }
    public void setTransTrlrRecordType(String transTrlrRecordType) {
        this.transTrlrRecordType = transTrlrRecordType;
    }

    public String getTransTrlrBankOrigNumber() {
        return transTrlrBankOrigNumber;
    }
    public void setTransTrlrBankOrigNumber(String transTrlrBankOrigNumber) {
        this.transTrlrBankOrigNumber = transTrlrBankOrigNumber;
    }
    public String getTransTrlrDepositDate() {
        return transTrlrDepositDate;
    }
    public void setTransTrlrDepositDate(String transTrlrDepositDate) {
        this.transTrlrDepositDate = transTrlrDepositDate;
    }
    public String getTransTrlrDepositTime() {
        return transTrlrDepositTime;
    }
    public void setTransTrlrDepositTime(String transTrlrDepositTime) {
        this.transTrlrDepositTime = transTrlrDepositTime;
    }
    public String getTransTrlrTransmissionRecrdCount() {
        return transTrlrTransmissionRecrdCount;
    }
    public void setTransTrlrTransmissionRecrdCount(String transTrlrTransmissionRecrdCount) {
        this.transTrlrTransmissionRecrdCount = transTrlrTransmissionRecrdCount;
    }
    public String getTransTrlrTransmissionAmt() {
        return transTrlrTransmissionAmt;
    }
    public void setTransTrlrTransmissionAmt(String transTrlrTransmissionAmt) {
        this.transTrlrTransmissionAmt = transTrlrTransmissionAmt;
    }
    public String getTranHderDepositTime() {
        return tranHderDepositTime;
    }
    public void setTranHderDepositTime(String tranHderDepositTime) {
        this.tranHderDepositTime = tranHderDepositTime;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getReceiptMethod() {
        return receiptMethod;
    }

    public void setReceiptMethod(String receiptMethod) {
        this.receiptMethod = receiptMethod;
    }
    public String getLockBoxNumber() {
        return lockBoxNumber;
    }

    public void setLockBoxNumber(String lockBoxNumber) {
        this.lockBoxNumber = lockBoxNumber;
    }
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    public String getFormatedCollectionDate() {
        return formatedCollectionDate;
    }
    public void setFormatedCollectionDate(String formatedCollectionDate) {
        this.formatedCollectionDate = formatedCollectionDate;
    }
    public String getSrcSysId() {
        return srcSysId;
    }
    public void setSrcSysId(String srcSysId) {
        this.srcSysId = srcSysId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
