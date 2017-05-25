package com.compasites.pojo;

import com.compasites.constants.Constants;

import java.math.BigDecimal;

/**
 * Created by Sobhan Babu on 07-06-2016.
 */
public class Journal {

    public static String journalFile;

    //input header
    private String headerSerialNumber;
    private String srcSysId;
    private String recordType;
    private String customerId;
    private String customerName;
    private String accountType;
    private String memClass;
    private String memType;
    private String documentType;
    private String transactionType;
    private String invoiceNumber;
    private String billDate;
    private String paymentMode;
    private String billingHeaderStatus;
    private String grossTotalAmt;
    private String gstAmount;
    private String fmsTransactionType;
    private String advanceAccntCode;

    //input line item
    private String lineSerialNumber;
    private String transactionTypeLineItem;
    private String netTotalAmt;
    private String revenueAcCode;
    private String discountAmt;
    private String forfeitedAmt;
    private String couponAmt;
    private String gstPercent;
    private String wdaFundAmt;
    private String sfcFundedAmt;
    private String allocatedRevAmt;
    private String halfYear;
    private String fullYear;
    private String courseStartDt;
    private String wdasfcRefNumber;
    private String voucherRefNumber;
    private String renewalYear;
    private String status;

    private boolean isValid;
    private String errorMsg = Constants.EMTPY;
    private BigDecimal headerAmt;

    private String statusCode;
    private String ledgerId;
    private String effectiveDateOfTrans;
    private String journalSrc;
    private String journalCategory;
    private String currencyCode;
    private String journalEntryCreationDt;
    private String actualFlag;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private String segment6;
    private String segment7;
    private String segment8;
    private String segment9;
    private String segment10;
    private String segment11;
    private String segment12;
    private String segment13;
    private String segment14;
    private String segment15;
    private String segment16;
    private String segment17;
    private String segment18;
    private String segment19;
    private String segment20;
    private String segment21;
    private String segment22;
    private String segment23;
    private String segment24;
    private String segment25;
    private String segment26;
    private String segment27;
    private String segment28;
    private String segment29;
    private String segment30;
    private String enteredDebitAmt;
    private String enteredCreditAmt;
    private String convertedDebitAmt;
    private String convertedCrediAmt;
    private String reference1BatchNm;
    private String reference2BatchDes;
    private String reference3;
    private String reference4JournalEntryNm;
    private String reference5;
    private String reference6;
    private String reference7;
    private String reference8;
    private String reference9;
    private String reference10;
    private String refCol1;
    private String refCol2;
    private String refCol3;
    private String refCol4;
    private String refCol5;
    private String refCol6;
    private String refCol7;
    private String refCol8;
    private String refCol9;
    private String refCol110;
    private String statsAmt;
    private String currecnyConvType;
    private String currencyConvDate;
    private String currecnyConvRate;
    private String interfaceGrpIdentifier;
    private String cntxtFieldForJrnlEntryLineDiff;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private String cntxtFieldForCapturedInfoDiff;
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private String attribute16;
    private String attribute17;
    private String attribute18;
    private String attribute19;
    private String attribute20;
    private String clringCompany;
    private String debitAccount;
    private String creditAccount;

    public String getContentDebit(){
        String replacedDebitAccount = debitAccount.replaceAll(Constants.MINUS, Constants.COMMA);
        StringBuilder sb = new StringBuilder();
        sb.append(statusCode).append(Constants.CVS_SEPERATOR)
                .append(ledgerId).append(Constants.CVS_SEPERATOR)
                .append(effectiveDateOfTrans).append(Constants.CVS_SEPERATOR)
                .append(journalSrc).append(Constants.CVS_SEPERATOR)
                .append(journalCategory).append(Constants.CVS_SEPERATOR)
                .append(currencyCode).append(Constants.CVS_SEPERATOR)
                .append(journalEntryCreationDt).append(Constants.CVS_SEPERATOR)
                .append(actualFlag).append(Constants.CVS_SEPERATOR)
                .append(replacedDebitAccount).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,")
                //.append(enteredDebitAmt).append(Constants.CVS_SEPERATOR)
                .append(headerAmt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(reference1BatchNm).append(Constants.CVS_SEPERATOR)
                .append(reference2BatchDes).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(reference4JournalEntryNm).append(Constants.CVS_SEPERATOR)
                .append(reference5).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,")
                .append(interfaceGrpIdentifier).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,")
                .append("\r\n");

        return sb.toString();
    }

    public String getContentCredit(){
        String replacedCreditAccount = creditAccount.replaceAll(Constants.MINUS, Constants.COMMA);
        StringBuilder sb = new StringBuilder();
        sb.append(statusCode).append(Constants.CVS_SEPERATOR)
                .append(ledgerId).append(Constants.CVS_SEPERATOR)
                .append(effectiveDateOfTrans).append(Constants.CVS_SEPERATOR)
                .append(journalSrc).append(Constants.CVS_SEPERATOR)
                .append(journalCategory).append(Constants.CVS_SEPERATOR)
                .append(currencyCode).append(Constants.CVS_SEPERATOR)
                .append(journalEntryCreationDt).append(Constants.CVS_SEPERATOR)
                .append(actualFlag).append(Constants.CVS_SEPERATOR)
                .append(replacedCreditAccount).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,")
                .append(Constants.CVS_SEPERATOR)
                .append(enteredCreditAmt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(reference1BatchNm).append(Constants.CVS_SEPERATOR)
                .append(reference2BatchDes).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(reference4JournalEntryNm).append(Constants.CVS_SEPERATOR)
                .append(reference5).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,")
                .append(interfaceGrpIdentifier).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,")
                .append("\r\n");

        return sb.toString();
    }

    public String getErrorLine(){
        StringBuilder sb = new StringBuilder();
        sb.append(srcSysId).append(Constants.CVS_SEPERATOR)
                .append(headerSerialNumber).append(Constants.CVS_SEPERATOR)
                .append(recordType).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(customerName).append(Constants.CVS_SEPERATOR)
                .append(accountType).append(Constants.CVS_SEPERATOR)
                .append(memClass).append(Constants.CVS_SEPERATOR)
                .append(memType).append(Constants.CVS_SEPERATOR)
                .append(documentType).append(Constants.CVS_SEPERATOR)
                .append(transactionType).append(Constants.CVS_SEPERATOR)
                .append(invoiceNumber).append(Constants.CVS_SEPERATOR)
                .append(billDate).append(Constants.CVS_SEPERATOR)
                .append(paymentMode).append(Constants.CVS_SEPERATOR)
                .append(grossTotalAmt).append(Constants.CVS_SEPERATOR)
                .append(gstAmount).append(Constants.CVS_SEPERATOR)
                .append(fmsTransactionType).append(Constants.CVS_SEPERATOR)
                .append(advanceAccntCode).append(Constants.CVS_SEPERATOR)
                .append(lineSerialNumber).append(Constants.CVS_SEPERATOR)
                .append(transactionTypeLineItem).append(Constants.CVS_SEPERATOR)
                .append(netTotalAmt).append(Constants.CVS_SEPERATOR)
                .append(revenueAcCode).append(Constants.CVS_SEPERATOR)
                .append(discountAmt).append(Constants.CVS_SEPERATOR)
                .append(forfeitedAmt).append(Constants.CVS_SEPERATOR)
                .append(couponAmt).append(Constants.CVS_SEPERATOR)
                .append(gstPercent).append(Constants.CVS_SEPERATOR)
                .append(wdaFundAmt).append(Constants.CVS_SEPERATOR)
                .append(sfcFundedAmt).append(Constants.CVS_SEPERATOR)
                .append(allocatedRevAmt).append(Constants.CVS_SEPERATOR)
                .append(halfYear).append(Constants.CVS_SEPERATOR)
                .append(fullYear).append(Constants.CVS_SEPERATOR)
                .append(renewalYear).append(Constants.CVS_SEPERATOR)
                .append(courseStartDt).append(Constants.CVS_SEPERATOR)
                .append(wdasfcRefNumber).append(Constants.CVS_SEPERATOR)
                .append(voucherRefNumber).append(Constants.CVS_SEPERATOR)
                .append(Constants.ERROR_FLAG).append(Constants.CVS_SEPERATOR)
                .append(errorMsg).append("\r\n");
        return sb.toString();
    }

    public boolean isLastLine(){
        if ((invoiceNumber == null || Constants.EMTPY.equals(invoiceNumber)) &&
                (billDate == null || Constants.EMTPY.equals(billDate)) &&
                (transactionType == null || Constants.EMTPY.equals(transactionType))) {
            return true;
        }

        return false;
    }

    public String getHeaderSerialNumber() {
        return headerSerialNumber;
    }
    public void setHeaderSerialNumber(String headerSerialNumber) {
        this.headerSerialNumber = headerSerialNumber;
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
    public String getDocumentType() {
        return documentType;
    }
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public String getBillingHeaderStatus() {
        return billingHeaderStatus;
    }
    public void setBillingHeaderStatus(String billingHeaderStatus) {
        this.billingHeaderStatus = billingHeaderStatus;
    }
    public String getGrossTotalAmt() {
        return grossTotalAmt;
    }
    public void setGrossTotalAmt(String grossTotalAmt) {
        this.grossTotalAmt = grossTotalAmt;
    }
    public String getGstAmount() {
        return gstAmount;
    }
    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }
    public String getFmsTransactionType() {
        return fmsTransactionType;
    }
    public void setFmsTransactionType(String fmsTransactionType) {
        this.fmsTransactionType = fmsTransactionType;
    }
    public String getAdvanceAccntCode() {
        return advanceAccntCode;
    }
    public void setAdvanceAccntCode(String advanceAccntCode) {
        this.advanceAccntCode = advanceAccntCode;
    }
    public String getLineSerialNumber() {
        return lineSerialNumber;
    }
    public void setLineSerialNumber(String lineSerialNumber) {
        this.lineSerialNumber = lineSerialNumber;
    }
    public String getTransactionTypeLineItem() {
        return transactionTypeLineItem;
    }
    public void setTransactionTypeLineItem(String transactionTypeLineItem) {
        this.transactionTypeLineItem = transactionTypeLineItem;
    }
    public String getNetTotalAmt() {
        return netTotalAmt;
    }
    public void setNetTotalAmt(String netTotalAmt) {
        this.netTotalAmt = netTotalAmt;
    }
    public String getRevenueAcCode() {
        return revenueAcCode;
    }
    public void setRevenueAcCode(String revenueAcCode) {
        this.revenueAcCode = revenueAcCode;
    }
    public String getDiscountAmt() {
        return discountAmt;
    }
    public void setDiscountAmt(String discountAmt) {
        this.discountAmt = discountAmt;
    }
    public String getForfeitedAmt() {
        return forfeitedAmt;
    }
    public void setForfeitedAmt(String forfeitedAmt) {
        this.forfeitedAmt = forfeitedAmt;
    }
    public String getCouponAmt() {
        return couponAmt;
    }
    public void setCouponAmt(String couponAmt) {
        this.couponAmt = couponAmt;
    }
    public String getGstPercent() {
        return gstPercent;
    }
    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }
    public String getWdaFundAmt() {
        return wdaFundAmt;
    }
    public void setWdaFundAmt(String wdaFundAmt) {
        this.wdaFundAmt = wdaFundAmt;
    }
    public String getSfcFundedAmt() {
        return sfcFundedAmt;
    }
    public void setSfcFundedAmt(String sfcFundedAmt) {
        this.sfcFundedAmt = sfcFundedAmt;
    }
    public String getAllocatedRevAmt() {
        return allocatedRevAmt;
    }
    public void setAllocatedRevAmt(String allocatedRevAmt) {
        this.allocatedRevAmt = allocatedRevAmt;
    }
    public String getHalfYear() {
        return halfYear;
    }
    public void setHalfYear(String halfYear) {
        this.halfYear = halfYear;
    }
    public String getFullYear() {
        return fullYear;
    }
    public void setFullYear(String fullYear) {
        this.fullYear = fullYear;
    }
    public String getCourseStartDt() {
        return courseStartDt;
    }
    public void setCourseStartDt(String courseStartDt) {
        this.courseStartDt = courseStartDt;
    }
    public String getWdasfcRefNumber() {
        return wdasfcRefNumber;
    }
    public void setWdasfcRefNumber(String wdasfcRefNumber) {
        this.wdasfcRefNumber = wdasfcRefNumber;
    }
    public String getVoucherRefNumber() {
        return voucherRefNumber;
    }
    public void setVoucherRefNumber(String voucherRefNumber) {
        this.voucherRefNumber = voucherRefNumber;
    }
    public String getRenewalYear() {
        return renewalYear;
    }
    public void setRenewalYear(String renewalYear) {
        this.renewalYear = renewalYear;
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
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getLedgerId() {
        return ledgerId;
    }
    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }
    public String getEffectiveDateOfTrans() {
        return effectiveDateOfTrans;
    }
    public void setEffectiveDateOfTrans(String effectiveDateOfTrans) {
        this.effectiveDateOfTrans = effectiveDateOfTrans;
    }
    public String getJournalSrc() {
        return journalSrc;
    }
    public void setJournalSrc(String journalSrc) {
        this.journalSrc = journalSrc;
    }
    public String getJournalCategory() {
        return journalCategory;
    }
    public void setJournalCategory(String journalCategory) {
        this.journalCategory = journalCategory;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getJournalEntryCreationDt() {
        return journalEntryCreationDt;
    }
    public void setJournalEntryCreationDt(String journalEntryCreationDt) {
        this.journalEntryCreationDt = journalEntryCreationDt;
    }
    public String getActualFlag() {
        return actualFlag;
    }
    public void setActualFlag(String actualFlag) {
        this.actualFlag = actualFlag;
    }
    public String getSegment1() {
        return segment1;
    }
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }
    public String getSegment2() {
        return segment2;
    }
    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }
    public String getSegment3() {
        return segment3;
    }
    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }
    public String getSegment4() {
        return segment4;
    }
    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }
    public String getSegment5() {
        return segment5;
    }
    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }
    public String getSegment6() {
        return segment6;
    }
    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }
    public String getSegment7() {
        return segment7;
    }
    public void setSegment7(String segment7) {
        this.segment7 = segment7;
    }
    public String getSegment8() {
        return segment8;
    }
    public void setSegment8(String segment8) {
        this.segment8 = segment8;
    }
    public String getSegment9() {
        return segment9;
    }
    public void setSegment9(String segment9) {
        this.segment9 = segment9;
    }
    public String getSegment10() {
        return segment10;
    }
    public void setSegment10(String segment10) {
        this.segment10 = segment10;
    }
    public String getSegment11() {
        return segment11;
    }
    public void setSegment11(String segment11) {
        this.segment11 = segment11;
    }
    public String getSegment12() {
        return segment12;
    }
    public void setSegment12(String segment12) {
        this.segment12 = segment12;
    }
    public String getSegment13() {
        return segment13;
    }
    public void setSegment13(String segment13) {
        this.segment13 = segment13;
    }
    public String getSegment14() {
        return segment14;
    }
    public void setSegment14(String segment14) {
        this.segment14 = segment14;
    }
    public String getSegment15() {
        return segment15;
    }
    public void setSegment15(String segment15) {
        this.segment15 = segment15;
    }
    public String getSegment16() {
        return segment16;
    }
    public void setSegment16(String segment16) {
        this.segment16 = segment16;
    }
    public String getSegment17() {
        return segment17;
    }
    public void setSegment17(String segment17) {
        this.segment17 = segment17;
    }
    public String getSegment18() {
        return segment18;
    }
    public void setSegment18(String segment18) {
        this.segment18 = segment18;
    }
    public String getSegment19() {
        return segment19;
    }
    public void setSegment19(String segment19) {
        this.segment19 = segment19;
    }
    public String getSegment20() {
        return segment20;
    }
    public void setSegment20(String segment20) {
        this.segment20 = segment20;
    }
    public String getSegment21() {
        return segment21;
    }
    public void setSegment21(String segment21) {
        this.segment21 = segment21;
    }
    public String getSegment22() {
        return segment22;
    }
    public void setSegment22(String segment22) {
        this.segment22 = segment22;
    }
    public String getSegment23() {
        return segment23;
    }
    public void setSegment23(String segment23) {
        this.segment23 = segment23;
    }
    public String getSegment24() {
        return segment24;
    }
    public void setSegment24(String segment24) {
        this.segment24 = segment24;
    }
    public String getSegment25() {
        return segment25;
    }
    public void setSegment25(String segment25) {
        this.segment25 = segment25;
    }
    public String getSegment26() {
        return segment26;
    }
    public void setSegment26(String segment26) {
        this.segment26 = segment26;
    }
    public String getSegment27() {
        return segment27;
    }
    public void setSegment27(String segment27) {
        this.segment27 = segment27;
    }
    public String getSegment28() {
        return segment28;
    }
    public void setSegment28(String segment28) {
        this.segment28 = segment28;
    }
    public String getSegment29() {
        return segment29;
    }
    public void setSegment29(String segment29) {
        this.segment29 = segment29;
    }
    public String getSegment30() {
        return segment30;
    }
    public void setSegment30(String segment30) {
        this.segment30 = segment30;
    }
    public String getEnteredDebitAmt() {
        return enteredDebitAmt;
    }
    public void setEnteredDebitAmt(String enteredDebitAmt) {
        this.enteredDebitAmt = enteredDebitAmt;
    }
    public String getEnteredCreditAmt() {
        return enteredCreditAmt;
    }
    public void setEnteredCreditAmt(String enteredCreditAmt) {
        this.enteredCreditAmt = enteredCreditAmt;
    }
    public String getConvertedDebitAmt() {
        return convertedDebitAmt;
    }
    public void setConvertedDebitAmt(String convertedDebitAmt) {
        this.convertedDebitAmt = convertedDebitAmt;
    }
    public String getConvertedCrediAmt() {
        return convertedCrediAmt;
    }
    public void setConvertedCrediAmt(String convertedCrediAmt) {
        this.convertedCrediAmt = convertedCrediAmt;
    }
    public String getReference1BatchNm() {
        return reference1BatchNm;
    }
    public void setReference1BatchNm(String reference1BatchNm) {
        this.reference1BatchNm = reference1BatchNm;
    }
    public String getReference2BatchDes() {
        return reference2BatchDes;
    }
    public void setReference2BatchDes(String reference2BatchDes) {
        this.reference2BatchDes = reference2BatchDes;
    }
    public String getReference4JournalEntryNm() {
        return reference4JournalEntryNm;
    }
    public void setReference4JournalEntryNm(String reference4JournalEntryNm) {
        this.reference4JournalEntryNm = reference4JournalEntryNm;
    }
    public String getReference5() {
        return reference5;
    }
    public void setReference5(String reference5) {
        this.reference5 = reference5;
    }
    public String getReference6() {
        return reference6;
    }
    public void setReference6(String reference6) {
        this.reference6 = reference6;
    }
    public String getReference7() {
        return reference7;
    }
    public void setReference7(String reference7) {
        this.reference7 = reference7;
    }
    public String getReference8() {
        return reference8;
    }
    public void setReference8(String reference8) {
        this.reference8 = reference8;
    }
    public String getReference9() {
        return reference9;
    }
    public void setReference9(String reference9) {
        this.reference9 = reference9;
    }
    public String getReference10() {
        return reference10;
    }
    public void setReference10(String reference10) {
        this.reference10 = reference10;
    }
    public String getStatsAmt() {
        return statsAmt;
    }
    public void setStatsAmt(String statsAmt) {
        this.statsAmt = statsAmt;
    }
    public String getCurrecnyConvType() {
        return currecnyConvType;
    }
    public void setCurrecnyConvType(String currecnyConvType) {
        this.currecnyConvType = currecnyConvType;
    }
    public String getCurrencyConvDate() {
        return currencyConvDate;
    }
    public void setCurrencyConvDate(String currencyConvDate) {
        this.currencyConvDate = currencyConvDate;
    }
    public String getCurrecnyConvRate() {
        return currecnyConvRate;
    }
    public void setCurrecnyConvRate(String currecnyConvRate) {
        this.currecnyConvRate = currecnyConvRate;
    }
    public String getInterfaceGrpIdentifier() {
        return interfaceGrpIdentifier;
    }
    public void setInterfaceGrpIdentifier(String interfaceGrpIdentifier) {
        this.interfaceGrpIdentifier = interfaceGrpIdentifier;
    }
    public String getCntxtFieldForJrnlEntryLineDiff() {
        return cntxtFieldForJrnlEntryLineDiff;
    }
    public void setCntxtFieldForJrnlEntryLineDiff(String cntxtFieldForJrnlEntryLineDiff) {
        this.cntxtFieldForJrnlEntryLineDiff = cntxtFieldForJrnlEntryLineDiff;
    }
    public String getAttribute1() {
        return attribute1;
    }
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }
    public String getAttribute2() {
        return attribute2;
    }
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
    public String getAttribute3() {
        return attribute3;
    }
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }
    public String getAttribute4() {
        return attribute4;
    }
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }
    public String getAttribute5() {
        return attribute5;
    }
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }
    public String getAttribute6() {
        return attribute6;
    }
    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }
    public String getAttribute7() {
        return attribute7;
    }
    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }
    public String getAttribute8() {
        return attribute8;
    }
    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }
    public String getAttribute9() {
        return attribute9;
    }
    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }
    public String getAttribute10() {
        return attribute10;
    }
    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }
    public String getCntxtFieldForCapturedInfoDiff() {
        return cntxtFieldForCapturedInfoDiff;
    }
    public void setCntxtFieldForCapturedInfoDiff(String cntxtFieldForCapturedInfoDiff) {
        this.cntxtFieldForCapturedInfoDiff = cntxtFieldForCapturedInfoDiff;
    }
    public String getAttribute11() {
        return attribute11;
    }
    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }
    public String getAttribute12() {
        return attribute12;
    }
    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }
    public String getAttribute13() {
        return attribute13;
    }
    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }
    public String getAttribute14() {
        return attribute14;
    }
    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }
    public String getAttribute15() {
        return attribute15;
    }
    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }
    public String getAttribute16() {
        return attribute16;
    }
    public void setAttribute16(String attribute16) {
        this.attribute16 = attribute16;
    }
    public String getAttribute17() {
        return attribute17;
    }
    public void setAttribute17(String attribute17) {
        this.attribute17 = attribute17;
    }
    public String getAttribute18() {
        return attribute18;
    }
    public void setAttribute18(String attribute18) {
        this.attribute18 = attribute18;
    }
    public String getAttribute19() {
        return attribute19;
    }
    public void setAttribute19(String attribute19) {
        this.attribute19 = attribute19;
    }
    public String getAttribute20() {
        return attribute20;
    }
    public void setAttribute20(String attribute20) {
        this.attribute20 = attribute20;
    }
    public String getClringCompany() {
        return clringCompany;
    }
    public void setClringCompany(String clringCompany) {
        this.clringCompany = clringCompany;
    }
    public String getDebitAccount() {
        return debitAccount;
    }
    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }
    public String getCreditAccount() {
        return creditAccount;
    }
    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }
    public String getReference3() {
        return reference3;
    }

    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }
    public String getRefCol1() {
        return refCol1;
    }
    public void setRefCol1(String refCol1) {
        this.refCol1 = refCol1;
    }
    public String getRefCol2() {
        return refCol2;
    }
    public void setRefCol2(String refCol2) {
        this.refCol2 = refCol2;
    }
    public String getRefCol3() {
        return refCol3;
    }
    public void setRefCol3(String refCol3) {
        this.refCol3 = refCol3;
    }
    public String getRefCol4() {
        return refCol4;
    }
    public void setRefCol4(String refCol4) {
        this.refCol4 = refCol4;
    }
    public String getRefCol5() {
        return refCol5;
    }
    public void setRefCol5(String refCol5) {
        this.refCol5 = refCol5;
    }
    public String getRefCol6() {
        return refCol6;
    }
    public void setRefCol6(String refCol6) {
        this.refCol6 = refCol6;
    }
    public String getRefCol7() {
        return refCol7;
    }
    public void setRefCol7(String refCol7) {
        this.refCol7 = refCol7;
    }
    public String getRefCol8() {
        return refCol8;
    }
    public void setRefCol8(String refCol8) {
        this.refCol8 = refCol8;
    }
    public String getRefCol9() {
        return refCol9;
    }
    public void setRefCol9(String refCol9) {
        this.refCol9 = refCol9;
    }
    public String getRefCol110() {
        return refCol110;
    }
    public void setRefCol110(String refCol110) {
        this.refCol110 = refCol110;
    }
    public BigDecimal getHeaderAmt() {
        return headerAmt;
    }
    public void setHeaderAmt(BigDecimal headerAmt) {
        this.headerAmt = headerAmt;
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

