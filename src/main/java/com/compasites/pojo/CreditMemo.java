package com.compasites.pojo;

import com.compasites.constants.Constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 31-05-2016.
 */
public class CreditMemo {

    public static String creditmemoFile;
    public static String errorTime;

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
    private String creditNoteNumber;
    private String billDate;
    private String paymentMode;
    private String billingHeaderStatus;
    private String grossTotalAmt;
    private String gstAmount;
    private String fmsTransactionType;
    private String advanceAccntCode;
    private BigDecimal headerAmt;

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
    private String errorMsg;
    private String bshMappingValid;

    //interface lines
    private String businessUnitName;
    private String transBtchSrcName;
    private String transTypeName;
    private String paymentTerms;
    private String transactionDate;
    private String accountingDt;
    private String transactionNumber;
    private String originalSysBillToCustRef;
    private String origSysBillToCustomerAddRef;
    private String origSysBillToCustomerContactRef;
    private String origSysShipToCustomerRef;
    private String origSysShipToCustomerAddrRef;
    private String origSysShipToCustomerContactRef;
    private String origSysShipToCustomerAccntRef;
    private String origSysShipToCustomerAccntAddrRef;
    private String origSysShipToCustomerAccntContactRef;
    private String origSysSoldToCustomerRef;
    private String origSysSoldToCustomerAccntRef;
    private String billToCustomerAccntNumber;
    private String billToCustomerSiteNumber;
    private String billToContactPartyNumbr;
    private String shipToCustomerAccountNumber;
    private String shipToCustomerSiteNumber;
    private String shipToContactPartyNumber;
    private String soldToCustomerAccntNumber;
    private String transactionLineType;
    private String transactionLineDescr;
    private String currencyCode;
    private String currencyConversionType;
    private String currecnyConversionDt;
    private String currecnyConversionRate;
    private String transactionLineAmt;
    private String transactionLineQty;
    private String customerOrderedQty;
    private String unitSellingPrice;
    private String unitStandardPrice;
    private String lineTransactionFlexfieldContxt;
    private String lineTransactionFlexfieldSeg1;
    private String lineTransactionFlexfieldSeg2;
    private String lineTransactionFlexfieldSeg3;
    private String lineTransactionFlexfieldSeg4;
    private String lineTransactionFlexfieldSeg5;
    private String lineTransactionFlexfieldSeg6;
    private String lineTransactionFlexfieldSeg7;
    private String lineTransactionFlexfieldSeg8;
    private String lineTransactionFlexfieldSeg9;
    private String lineTransactionFlexfieldSeg10;
    private String lineTransactionFlexfieldSeg11;
    private String lineTransactionFlexfieldSeg12;
    private String lineTransactionFlexfieldSeg13;
    private String lineTransactionFlexfieldSeg14;
    private String lineTransactionFlexfieldSeg15;
    private String primarySalespersonNumber;
    private String taxClassificationCode;
    private String legalEntityIdentifier;
    private String accntedAmtInLedgerCurrency;
    private String salesOrderNumber;
    private String salesOrderdDt;
    private String actualShipDt;
    private String warehouseCode;
    private String unitOfMeasureCode;
    private String unitOfMeasureName;
    private String invoicingRuleName;
    private String revenueSchedulingRuleName;
    private String noOfRevenuePeriods;
    private String revenuedSchedulingRuleStrtDt;
    private String revenueSchedulingRuleEndDt;
    private String reasonCodeMeaning;
    private String lastPeriodToCredit;
    private String transactionBusinessCategoryCode;
    private String prodFiscalClassificationCode;
    private String prodCategoryCode;
    private String prodType;
    private String lineIntendedUseCode;
    private String assembleCode;
    private String docSubType;
    private String defaultTaxationCntry;
    private String userDefinedFiscalClassification;
    private String taxInvoiceNumber;
    private String taxInvoiceDate;
    private String taxRegimeCode;
    private String tax;
    private String taxStatusCode;
    private String taxRateCode;
    private String taxJurisdicationCode;
    private String firstPartyRegNumber;
    private String thirdPartyRegistrationNumber;
    private String finalDischargeLocation;
    private String taxableAmt;
    private String taxableFlag;
    private String taxExemptionFlag;
    private String taxExemptionReasonCode;
    private String taxExemptionReasonCodeMeaning;
    private String taxExemptionCertifiedNumber;
    private String lineAmtIncludesTaxFlag;
    private String taxPrecedence;
    private String creditMtdUsedRevenueSchedulingRules;
    private String creditMtdUsedSplitPaymentTerms;
    private String reasonCode;
    private String taxRate;
    private String fobPoint;
    private String carrier;
    private String shippingRef;
    private String salesOrderLineNumber;
    private String salesOrderSrc;
    private String salesOrderRevisonNumber;
    private String purchaseOrderNumber;
    private String purchaseOrderRevisionNumber;
    private String purchaseOrderDate;
    private String agreementName;
    private String memoLineName;
    private String documentNumber;
    private String originalSysBatchName;
    private String linkToTransactionsFlexfieldCntxt;
    private String linkToTransactionsFlexfieldSegment1;
    private String linkToTransactionsFlexfieldSegment2;
    private String linkToTransactionsFlexfieldSegment3;
    private String linkToTransactionsFlexfieldSegment4;
    private String linkToTransactionsFlexfieldSegment5;
    private String linkToTransactionsFlexfieldSegment6;
    private String linkToTransactionsFlexfieldSegment7;
    private String linkToTransactionsFlexfieldSegment8;
    private String linkToTransactionsFlexfieldSegment9;
    private String linkToTransactionsFlexfieldSegment10;
    private String linkToTransactionsFlexfieldSegment11;
    private String linkToTransactionsFlexfieldSegment12;
    private String linkToTransactionsFlexfieldSegment13;
    private String linkToTransactionsFlexfieldSegment14;
    private String linkToTransactionsFlexfieldSegment15;
    private String refTransactionFlexfieldCntxt;
    private String refTransactionFlexfieldSegment1;
    private String refTransactionFlexfieldSegment2;
    private String refTransactionFlexfieldSegment3;
    private String refTransactionFlexfieldSegment4;
    private String refTransactionFlexfieldSegment5;
    private String refTransactionFlexfieldSegment6;
    private String refTransactionFlexfieldSegment7;
    private String refTransactionFlexfieldSegment8;
    private String refTransactionFlexfieldSegment9;
    private String refTransactionFlexfieldSegment10;
    private String refTransactionFlexfieldSegment11;
    private String refTransactionFlexfieldSegment12;
    private String refTransactionFlexfieldSegment13;
    private String refTransactionFlexfieldSegment14;
    private String refTransactionFlexfieldSegment15;
    private String linkToParentLineCntxt;
    private String linkToParentLineSegment1;
    private String linkToParentLineSegment2;
    private String linkToParentLineSegment3;
    private String linkToParentLineSegment4;
    private String linkToParentLineSegment5;
    private String linkToParentLineSegment6;
    private String linkToParentLineSegment7;
    private String linkToParentLineSegment8;
    private String linkToParentLineSegment9;
    private String linkToParentLineSegment10;
    private String linkToParentLineSegment11;
    private String linkToParentLineSegment12;
    private String linkToParentLineSegment13;
    private String linkToParentLineSegment14;
    private String linkToParentLineSegment15;
    private String receiptMethodName;
    private String printingOption;
    private String relatedBatchSrcName;
    private String relatedTransactionNumber;
    private String inventoryItemNumber;
    private String inventoryItemSegment2;
    private String inventoryItemSegment3;
    private String inventoryItemSegment4;
    private String inventoryItemSegment5;
    private String inventoryItemSegment6;
    private String inventoryItemSegment7;
    private String inventoryItemSegment8;
    private String inventoryItemSegment9;
    private String inventoryItemSegment10;
    private String inventoryItemSegment11;
    private String inventoryItemSegment12;
    private String inventoryItemSegment13;
    private String inventoryItemSegment14;
    private String inventoryItemSegment15;
    private String inventoryItemSegment16;
    private String inventoryItemSegment17;
    private String inventoryItemSegment18;
    private String inventoryItemSegment19;
    private String inventoryItemSegment20;
    private String billToCustomerBankAccntName;
    private String resetTransactionDtFlag;
    private String paymentServerOrderNumber;
    private String lastTransactionDebitAuth;
    private String approvalCode;
    private String addressVerificationCode;
    private String transactionLineTranslatedDesc;
    private String consolidatedBillingNumber;
    private String promisedCommitmentAmt;
    private String paymentSetIdentifier;
    private String origAccntingDt;
    private String invoiceLineAccntingLvl;
    private String overrideAutoAcntingFlag;
    private String historicalFlag;
    private String deferralExclusionFlag;
    private String paymentAttributes;
    private String invoiceBillingDt;
    private String invoiceLineFlexfieldCntxt;
    private String invoiceLineFlexfieldSeg1;
    private String invoiceLineFlexfieldSeg2;
    private String invoiceLineFlexfieldSeg3;
    private String invoiceLineFlexfieldSeg4;
    private String invoiceLineFlexfieldSeg5;
    private String invoiceLineFlexfieldSeg6;
    private String invoiceLineFlexfieldSeg7;
    private String invoiceLineFlexfieldSeg8;
    private String invoiceLineFlexfieldSeg9;
    private String invoiceLineFlexfieldSeg10;
    private String invoiceLineFlexfieldSeg11;
    private String invoiceLineFlexfieldSeg12;
    private String invoiceLineFlexfieldSeg13;
    private String invoiceLineFlexfieldSeg14;
    private String invoiceLineFlexfieldSeg15;
    private String invoiceTransactionFlexfieldCntxt;
    private String invoiceTransFlexfieldSeg1;
    private String invoiceTransFlexfieldSeg2;
    private String invoiceTransFlexfieldSeg3;
    private String invoiceTransFlexfieldSeg4;
    private String invoiceTransFlexfieldSeg5;
    private String invoiceTransFlexfieldSeg6;
    private String invoiceTransFlexfieldSeg7;
    private String invoiceTransFlexfieldSeg8;
    private String invoiceTransFlexfieldSeg9;
    private String invoiceTransFlexfieldSeg10;
    private String invoiceTransFlexfieldSeg11;
    private String invoiceTransFlexfieldSeg12;
    private String invoiceTransFlexfieldSeg13;
    private String invoiceTransFlexfieldSeg14;
    private String invoiceTransFlexfieldSeg15;
    private String receivablesTransRegionInfoFlexfieldCntxt;
    private String recvTransRegInfoFlexSeg1;
    private String recvTransRegInfoFlexSeg2;
    private String recvTransRegInfoFlexSeg3;
    private String recvTransRegInfoFlexSeg4;
    private String recvTransRegInfoFlexSeg5;
    private String recvTransRegInfoFlexSeg6;
    private String recvTransRegInfoFlexSeg7;
    private String recvTransRegInfoFlexSeg8;
    private String recvTransRegInfoFlexSeg9;
    private String recvTransRegInfoFlexSeg10;
    private String recvTransRegInfoFlexSeg11;
    private String recvTransRegInfoFlexSeg12;
    private String recvTransRegInfoFlexSeg13;
    private String recvTransRegInfoFlexSeg14;
    private String recvTransRegInfoFlexSeg15;
    private String recvTransRegInfoFlexSeg16;
    private String recvTransRegInfoFlexSeg17;
    private String recvTransRegInfoFlexSeg18;
    private String recvTransRegInfoFlexSeg19;
    private String recvTransRegInfoFlexSeg20;
    private String recvTransRegInfoFlexSeg21;
    private String recvTransRegInfoFlexSeg22;
    private String recvTransRegInfoFlexSeg23;
    private String recvTransRegInfoFlexSeg24;
    private String recvTransRegInfoFlexSeg25;
    private String recvTransRegInfoFlexSeg26;
    private String recvTransRegInfoFlexSeg27;
    private String recvTransRegInfoFlexSeg28;
    private String recvTransRegInfoFlexSeg29;
    private String recvTransRegInfoFlexSeg30;
    private String lineGlblDecrFlexAttribCateg;
    private String lineGlblDecrFlexSeg1;
    private String lineGlblDecrFlexSeg2;
    private String lineGlblDecrFlexSeg3;
    private String lineGlblDecrFlexSeg4;
    private String lineGlblDecrFlexSeg5;
    private String lineGlblDecrFlexSeg6;
    private String lineGlblDecrFlexSeg7;
    private String lineGlblDecrFlexSeg8;
    private String lineGlblDecrFlexSeg9;
    private String lineGlblDecrFlexSeg10;
    private String lineGlblDecrFlexSeg11;
    private String lineGlblDecrFlexSeg12;
    private String lineGlblDecrFlexSeg13;
    private String lineGlblDecrFlexSeg14;
    private String lineGlblDecrFlexSeg15;
    private String lineGlblDecrFlexSeg16;
    private String lineGlblDecrFlexSeg17;
    private String lineGlblDecrFlexSeg18;
    private String lineGlblDecrFlexSeg19;
    private String lineGlblDecrFlexSeg20;

    public boolean isLastLine(){
        if ((creditNoteNumber == null || Constants.EMTPY.equals(creditNoteNumber)) &&
                (billDate == null || Constants.EMTPY.equals(billDate)) &&
                (transactionType == null || Constants.EMTPY.equals(transactionType))) {
            return true;
        }

        return false;
    }

    public String getContent(){
        StringBuilder sb = new StringBuilder();
        //sb.append(businessUnitName).append(Constants.CVS_SEPERATOR)
        sb.append(Constants.CVS_SEPERATOR)
                .append(transBtchSrcName).append(Constants.CVS_SEPERATOR)
                .append(transTypeName).append(Constants.CVS_SEPERATOR)
                .append(paymentTerms).append(Constants.CVS_SEPERATOR)
                .append(transactionDate).append(Constants.CVS_SEPERATOR)
                .append(accountingDt).append(Constants.CVS_SEPERATOR)
                .append(transactionNumber).append(Constants.CVS_SEPERATOR)
                //.append(originalSysBillToCustRef)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                //.append(origSysShipToCustomerAccntRef)
                .append(",,,,,")
                .append(billToCustomerAccntNumber).append(Constants.CVS_SEPERATOR)
                .append(billToCustomerSiteNumber).append(Constants.CVS_SEPERATOR)
                .append(",,,,,")
                .append(transactionLineType).append(Constants.CVS_SEPERATOR)
                .append(transactionLineDescr).append(Constants.CVS_SEPERATOR)
                .append(currencyCode).append(Constants.CVS_SEPERATOR)
                .append(currencyConversionType).append(Constants.CVS_SEPERATOR)
                .append(currecnyConversionDt).append(Constants.CVS_SEPERATOR)
                .append(currecnyConversionRate).append(Constants.CVS_SEPERATOR)
                .append(transactionLineAmt).append(Constants.CVS_SEPERATOR)
                .append(transactionLineQty).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(unitSellingPrice)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(lineTransactionFlexfieldContxt).append(Constants.CVS_SEPERATOR)
                .append(lineTransactionFlexfieldSeg1).append(Constants.CVS_SEPERATOR)
                .append(lineTransactionFlexfieldSeg2).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,")
                .append(taxClassificationCode).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,")
                .append(invoicingRuleName).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,").append(lineAmtIncludesTaxFlag)
                .append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(creditMtdUsedRevenueSchedulingRules)
                .append(",,,,,,,,,,,,,,")
                /*.append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)*/
                .append(memoLineName)
                .append(",,,,,,,,,,,,,,,,,,,")
                .append(refTransactionFlexfieldCntxt).append(Constants.CVS_SEPERATOR)
                .append(refTransactionFlexfieldSegment1).append(Constants.CVS_SEPERATOR)
                .append(refTransactionFlexfieldSegment2).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                .append(printingOption)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,")
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,")
                .append(invoiceTransFlexfieldSeg1)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                .append(businessUnitName).append(Constants.CVS_SEPERATOR).append("END").append("\r\n");

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
                .append(creditNoteNumber).append(Constants.CVS_SEPERATOR)
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
    public String getCreditNoteNumber() {
        return creditNoteNumber;
    }
    public void setCreditNoteNumber(String creditNoteNumber) {
        this.creditNoteNumber = creditNoteNumber;
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
    public String getBusinessUnitName() {
        return businessUnitName;
    }
    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }
    public String getTransBtchSrcName() {
        return transBtchSrcName;
    }
    public void setTransBtchSrcName(String transBtchSrcName) {
        this.transBtchSrcName = transBtchSrcName;
    }
    public String getTransTypeName() {
        return transTypeName;
    }
    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName;
    }
    public String getPaymentTerms() {
        return paymentTerms;
    }
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getAccountingDt() {
        return accountingDt;
    }
    public void setAccountingDt(String accountingDt) {
        this.accountingDt = accountingDt;
    }
    public String getTransactionNumber() {
        return transactionNumber;
    }
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
    public String getOriginalSysBillToCustRef() {
        return originalSysBillToCustRef;
    }
    public void setOriginalSysBillToCustRef(String originalSysBillToCustRef) {
        this.originalSysBillToCustRef = originalSysBillToCustRef;
    }
    public String getOrigSysBillToCustomerAddRef() {
        return origSysBillToCustomerAddRef;
    }
    public void setOrigSysBillToCustomerAddRef(String origSysBillToCustomerAddRef) {
        this.origSysBillToCustomerAddRef = origSysBillToCustomerAddRef;
    }
    public String getOrigSysBillToCustomerContactRef() {
        return origSysBillToCustomerContactRef;
    }
    public void setOrigSysBillToCustomerContactRef(String origSysBillToCustomerContactRef) {
        this.origSysBillToCustomerContactRef = origSysBillToCustomerContactRef;
    }
    public String getOrigSysShipToCustomerRef() {
        return origSysShipToCustomerRef;
    }
    public void setOrigSysShipToCustomerRef(String origSysShipToCustomerRef) {
        this.origSysShipToCustomerRef = origSysShipToCustomerRef;
    }
    public String getOrigSysShipToCustomerAddrRef() {
        return origSysShipToCustomerAddrRef;
    }
    public void setOrigSysShipToCustomerAddrRef(String origSysShipToCustomerAddrRef) {
        this.origSysShipToCustomerAddrRef = origSysShipToCustomerAddrRef;
    }
    public String getOrigSysShipToCustomerContactRef() {
        return origSysShipToCustomerContactRef;
    }
    public void setOrigSysShipToCustomerContactRef(String origSysShipToCustomerContactRef) {
        this.origSysShipToCustomerContactRef = origSysShipToCustomerContactRef;
    }
    public String getOrigSysShipToCustomerAccntRef() {
        return origSysShipToCustomerAccntRef;
    }
    public void setOrigSysShipToCustomerAccntRef(String origSysShipToCustomerAccntRef) {
        this.origSysShipToCustomerAccntRef = origSysShipToCustomerAccntRef;
    }
    public String getOrigSysShipToCustomerAccntAddrRef() {
        return origSysShipToCustomerAccntAddrRef;
    }
    public void setOrigSysShipToCustomerAccntAddrRef(String origSysShipToCustomerAccntAddrRef) {
        this.origSysShipToCustomerAccntAddrRef = origSysShipToCustomerAccntAddrRef;
    }
    public String getOrigSysShipToCustomerAccntContactRef() {
        return origSysShipToCustomerAccntContactRef;
    }
    public void setOrigSysShipToCustomerAccntContactRef(String origSysShipToCustomerAccntContactRef) {
        this.origSysShipToCustomerAccntContactRef = origSysShipToCustomerAccntContactRef;
    }
    public String getOrigSysSoldToCustomerRef() {
        return origSysSoldToCustomerRef;
    }
    public void setOrigSysSoldToCustomerRef(String origSysSoldToCustomerRef) {
        this.origSysSoldToCustomerRef = origSysSoldToCustomerRef;
    }
    public String getOrigSysSoldToCustomerAccntRef() {
        return origSysSoldToCustomerAccntRef;
    }
    public void setOrigSysSoldToCustomerAccntRef(String origSysSoldToCustomerAccntRef) {
        this.origSysSoldToCustomerAccntRef = origSysSoldToCustomerAccntRef;
    }
    public String getBillToCustomerAccntNumber() {
        return billToCustomerAccntNumber;
    }
    public void setBillToCustomerAccntNumber(String billToCustomerAccntNumber) {
        this.billToCustomerAccntNumber = billToCustomerAccntNumber;
    }
    public String getBillToCustomerSiteNumber() {
        return billToCustomerSiteNumber;
    }
    public void setBillToCustomerSiteNumber(String billToCustomerSiteNumber) {
        this.billToCustomerSiteNumber = billToCustomerSiteNumber;
    }
    public String getBillToContactPartyNumbr() {
        return billToContactPartyNumbr;
    }
    public void setBillToContactPartyNumbr(String billToContactPartyNumbr) {
        this.billToContactPartyNumbr = billToContactPartyNumbr;
    }
    public String getShipToCustomerAccountNumber() {
        return shipToCustomerAccountNumber;
    }
    public void setShipToCustomerAccountNumber(String shipToCustomerAccountNumber) {
        this.shipToCustomerAccountNumber = shipToCustomerAccountNumber;
    }
    public String getShipToCustomerSiteNumber() {
        return shipToCustomerSiteNumber;
    }
    public void setShipToCustomerSiteNumber(String shipToCustomerSiteNumber) {
        this.shipToCustomerSiteNumber = shipToCustomerSiteNumber;
    }
    public String getShipToContactPartyNumber() {
        return shipToContactPartyNumber;
    }
    public void setShipToContactPartyNumber(String shipToContactPartyNumber) {
        this.shipToContactPartyNumber = shipToContactPartyNumber;
    }
    public String getSoldToCustomerAccntNumber() {
        return soldToCustomerAccntNumber;
    }
    public void setSoldToCustomerAccntNumber(String soldToCustomerAccntNumber) {
        this.soldToCustomerAccntNumber = soldToCustomerAccntNumber;
    }
    public String getTransactionLineType() {
        return transactionLineType;
    }
    public void setTransactionLineType(String transactionLineType) {
        this.transactionLineType = transactionLineType;
    }
    public String getTransactionLineDescr() {
        return transactionLineDescr;
    }
    public void setTransactionLineDescr(String transactionLineDescr) {
        this.transactionLineDescr = transactionLineDescr;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getCurrencyConversionType() {
        return currencyConversionType;
    }
    public void setCurrencyConversionType(String currencyConversionType) {
        this.currencyConversionType = currencyConversionType;
    }
    public String getCurrecnyConversionDt() {
        return currecnyConversionDt;
    }
    public void setCurrecnyConversionDt(String currecnyConversionDt) {
        this.currecnyConversionDt = currecnyConversionDt;
    }
    public String getCurrecnyConversionRate() {
        return currecnyConversionRate;
    }
    public void setCurrecnyConversionRate(String currecnyConversionRate) {
        this.currecnyConversionRate = currecnyConversionRate;
    }
    public String getTransactionLineAmt() {
        return transactionLineAmt;
    }
    public void setTransactionLineAmt(String transactionLineAmt) {
        this.transactionLineAmt = transactionLineAmt;
    }
    public String getTransactionLineQty() {
        return transactionLineQty;
    }
    public void setTransactionLineQty(String transactionLineQty) {
        this.transactionLineQty = transactionLineQty;
    }
    public String getCustomerOrderedQty() {
        return customerOrderedQty;
    }
    public void setCustomerOrderedQty(String customerOrderedQty) {
        this.customerOrderedQty = customerOrderedQty;
    }
    public String getUnitSellingPrice() {
        return unitSellingPrice;
    }
    public void setUnitSellingPrice(String unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }
    public String getUnitStandardPrice() {
        return unitStandardPrice;
    }
    public void setUnitStandardPrice(String unitStandardPrice) {
        this.unitStandardPrice = unitStandardPrice;
    }
    public String getLineTransactionFlexfieldContxt() {
        return lineTransactionFlexfieldContxt;
    }
    public void setLineTransactionFlexfieldContxt(String lineTransactionFlexfieldContxt) {
        this.lineTransactionFlexfieldContxt = lineTransactionFlexfieldContxt;
    }
    public String getLineTransactionFlexfieldSeg1() {
        return lineTransactionFlexfieldSeg1;
    }
    public void setLineTransactionFlexfieldSeg1(String lineTransactionFlexfieldSeg1) {
        this.lineTransactionFlexfieldSeg1 = lineTransactionFlexfieldSeg1;
    }
    public String getLineTransactionFlexfieldSeg2() {
        return lineTransactionFlexfieldSeg2;
    }
    public void setLineTransactionFlexfieldSeg2(String lineTransactionFlexfieldSeg2) {
        this.lineTransactionFlexfieldSeg2 = lineTransactionFlexfieldSeg2;
    }
    public String getLineTransactionFlexfieldSeg3() {
        return lineTransactionFlexfieldSeg3;
    }
    public void setLineTransactionFlexfieldSeg3(String lineTransactionFlexfieldSeg3) {
        this.lineTransactionFlexfieldSeg3 = lineTransactionFlexfieldSeg3;
    }
    public String getLineTransactionFlexfieldSeg4() {
        return lineTransactionFlexfieldSeg4;
    }
    public void setLineTransactionFlexfieldSeg4(String lineTransactionFlexfieldSeg4) {
        this.lineTransactionFlexfieldSeg4 = lineTransactionFlexfieldSeg4;
    }
    public String getLineTransactionFlexfieldSeg5() {
        return lineTransactionFlexfieldSeg5;
    }
    public void setLineTransactionFlexfieldSeg5(String lineTransactionFlexfieldSeg5) {
        this.lineTransactionFlexfieldSeg5 = lineTransactionFlexfieldSeg5;
    }
    public String getLineTransactionFlexfieldSeg6() {
        return lineTransactionFlexfieldSeg6;
    }
    public void setLineTransactionFlexfieldSeg6(String lineTransactionFlexfieldSeg6) {
        this.lineTransactionFlexfieldSeg6 = lineTransactionFlexfieldSeg6;
    }
    public String getLineTransactionFlexfieldSeg7() {
        return lineTransactionFlexfieldSeg7;
    }
    public void setLineTransactionFlexfieldSeg7(String lineTransactionFlexfieldSeg7) {
        this.lineTransactionFlexfieldSeg7 = lineTransactionFlexfieldSeg7;
    }
    public String getLineTransactionFlexfieldSeg8() {
        return lineTransactionFlexfieldSeg8;
    }
    public void setLineTransactionFlexfieldSeg8(String lineTransactionFlexfieldSeg8) {
        this.lineTransactionFlexfieldSeg8 = lineTransactionFlexfieldSeg8;
    }
    public String getLineTransactionFlexfieldSeg9() {
        return lineTransactionFlexfieldSeg9;
    }
    public void setLineTransactionFlexfieldSeg9(String lineTransactionFlexfieldSeg9) {
        this.lineTransactionFlexfieldSeg9 = lineTransactionFlexfieldSeg9;
    }
    public String getLineTransactionFlexfieldSeg10() {
        return lineTransactionFlexfieldSeg10;
    }
    public void setLineTransactionFlexfieldSeg10(String lineTransactionFlexfieldSeg10) {
        this.lineTransactionFlexfieldSeg10 = lineTransactionFlexfieldSeg10;
    }
    public String getLineTransactionFlexfieldSeg11() {
        return lineTransactionFlexfieldSeg11;
    }
    public void setLineTransactionFlexfieldSeg11(String lineTransactionFlexfieldSeg11) {
        this.lineTransactionFlexfieldSeg11 = lineTransactionFlexfieldSeg11;
    }
    public String getLineTransactionFlexfieldSeg12() {
        return lineTransactionFlexfieldSeg12;
    }
    public void setLineTransactionFlexfieldSeg12(String lineTransactionFlexfieldSeg12) {
        this.lineTransactionFlexfieldSeg12 = lineTransactionFlexfieldSeg12;
    }
    public String getLineTransactionFlexfieldSeg13() {
        return lineTransactionFlexfieldSeg13;
    }
    public void setLineTransactionFlexfieldSeg13(String lineTransactionFlexfieldSeg13) {
        this.lineTransactionFlexfieldSeg13 = lineTransactionFlexfieldSeg13;
    }
    public String getLineTransactionFlexfieldSeg14() {
        return lineTransactionFlexfieldSeg14;
    }
    public void setLineTransactionFlexfieldSeg14(String lineTransactionFlexfieldSeg14) {
        this.lineTransactionFlexfieldSeg14 = lineTransactionFlexfieldSeg14;
    }
    public String getLineTransactionFlexfieldSeg15() {
        return lineTransactionFlexfieldSeg15;
    }
    public void setLineTransactionFlexfieldSeg15(String lineTransactionFlexfieldSeg15) {
        this.lineTransactionFlexfieldSeg15 = lineTransactionFlexfieldSeg15;
    }
    public String getPrimarySalespersonNumber() {
        return primarySalespersonNumber;
    }
    public void setPrimarySalespersonNumber(String primarySalespersonNumber) {
        this.primarySalespersonNumber = primarySalespersonNumber;
    }
    public String getTaxClassificationCode() {
        return taxClassificationCode;
    }
    public void setTaxClassificationCode(String taxClassificationCode) {
        this.taxClassificationCode = taxClassificationCode;
    }
    public String getLegalEntityIdentifier() {
        return legalEntityIdentifier;
    }
    public void setLegalEntityIdentifier(String legalEntityIdentifier) {
        this.legalEntityIdentifier = legalEntityIdentifier;
    }
    public String getAccntedAmtInLedgerCurrency() {
        return accntedAmtInLedgerCurrency;
    }
    public void setAccntedAmtInLedgerCurrency(String accntedAmtInLedgerCurrency) {
        this.accntedAmtInLedgerCurrency = accntedAmtInLedgerCurrency;
    }
    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }
    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }
    public String getSalesOrderdDt() {
        return salesOrderdDt;
    }
    public void setSalesOrderdDt(String salesOrderdDt) {
        this.salesOrderdDt = salesOrderdDt;
    }
    public String getActualShipDt() {
        return actualShipDt;
    }
    public void setActualShipDt(String actualShipDt) {
        this.actualShipDt = actualShipDt;
    }
    public String getWarehouseCode() {
        return warehouseCode;
    }
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    public String getUnitOfMeasureCode() {
        return unitOfMeasureCode;
    }
    public void setUnitOfMeasureCode(String unitOfMeasureCode) {
        this.unitOfMeasureCode = unitOfMeasureCode;
    }
    public String getUnitOfMeasureName() {
        return unitOfMeasureName;
    }
    public void setUnitOfMeasureName(String unitOfMeasureName) {
        this.unitOfMeasureName = unitOfMeasureName;
    }
    public String getInvoicingRuleName() {
        return invoicingRuleName;
    }
    public void setInvoicingRuleName(String invoicingRuleName) {
        this.invoicingRuleName = invoicingRuleName;
    }
    public String getRevenueSchedulingRuleName() {
        return revenueSchedulingRuleName;
    }
    public void setRevenueSchedulingRuleName(String revenueSchedulingRuleName) {
        this.revenueSchedulingRuleName = revenueSchedulingRuleName;
    }
    public String getNoOfRevenuePeriods() {
        return noOfRevenuePeriods;
    }
    public void setNoOfRevenuePeriods(String noOfRevenuePeriods) {
        this.noOfRevenuePeriods = noOfRevenuePeriods;
    }
    public String getRevenuedSchedulingRuleStrtDt() {
        return revenuedSchedulingRuleStrtDt;
    }
    public void setRevenuedSchedulingRuleStrtDt(String revenuedSchedulingRuleStrtDt) {
        this.revenuedSchedulingRuleStrtDt = revenuedSchedulingRuleStrtDt;
    }
    public String getRevenueSchedulingRuleEndDt() {
        return revenueSchedulingRuleEndDt;
    }
    public void setRevenueSchedulingRuleEndDt(String revenueSchedulingRuleEndDt) {
        this.revenueSchedulingRuleEndDt = revenueSchedulingRuleEndDt;
    }
    public String getReasonCodeMeaning() {
        return reasonCodeMeaning;
    }
    public void setReasonCodeMeaning(String reasonCodeMeaning) {
        this.reasonCodeMeaning = reasonCodeMeaning;
    }
    public String getLastPeriodToCredit() {
        return lastPeriodToCredit;
    }
    public void setLastPeriodToCredit(String lastPeriodToCredit) {
        this.lastPeriodToCredit = lastPeriodToCredit;
    }
    public String getTransactionBusinessCategoryCode() {
        return transactionBusinessCategoryCode;
    }
    public void setTransactionBusinessCategoryCode(String transactionBusinessCategoryCode) {
        this.transactionBusinessCategoryCode = transactionBusinessCategoryCode;
    }
    public String getProdFiscalClassificationCode() {
        return prodFiscalClassificationCode;
    }
    public void setProdFiscalClassificationCode(String prodFiscalClassificationCode) {
        this.prodFiscalClassificationCode = prodFiscalClassificationCode;
    }
    public String getProdCategoryCode() {
        return prodCategoryCode;
    }
    public void setProdCategoryCode(String prodCategoryCode) {
        this.prodCategoryCode = prodCategoryCode;
    }
    public String getProdType() {
        return prodType;
    }
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
    public String getLineIntendedUseCode() {
        return lineIntendedUseCode;
    }
    public void setLineIntendedUseCode(String lineIntendedUseCode) {
        this.lineIntendedUseCode = lineIntendedUseCode;
    }
    public String getAssembleCode() {
        return assembleCode;
    }
    public void setAssembleCode(String assembleCode) {
        this.assembleCode = assembleCode;
    }
    public String getDocSubType() {
        return docSubType;
    }
    public void setDocSubType(String docSubType) {
        this.docSubType = docSubType;
    }
    public String getDefaultTaxationCntry() {
        return defaultTaxationCntry;
    }
    public void setDefaultTaxationCntry(String defaultTaxationCntry) {
        this.defaultTaxationCntry = defaultTaxationCntry;
    }
    public String getUserDefinedFiscalClassification() {
        return userDefinedFiscalClassification;
    }
    public void setUserDefinedFiscalClassification(String userDefinedFiscalClassification) {
        this.userDefinedFiscalClassification = userDefinedFiscalClassification;
    }
    public String getTaxInvoiceNumber() {
        return taxInvoiceNumber;
    }
    public void setTaxInvoiceNumber(String taxInvoiceNumber) {
        this.taxInvoiceNumber = taxInvoiceNumber;
    }
    public String getTaxInvoiceDate() {
        return taxInvoiceDate;
    }
    public void setTaxInvoiceDate(String taxInvoiceDate) {
        this.taxInvoiceDate = taxInvoiceDate;
    }
    public String getTaxRegimeCode() {
        return taxRegimeCode;
    }
    public void setTaxRegimeCode(String taxRegimeCode) {
        this.taxRegimeCode = taxRegimeCode;
    }
    public String getTax() {
        return tax;
    }
    public void setTax(String tax) {
        this.tax = tax;
    }
    public String getTaxStatusCode() {
        return taxStatusCode;
    }
    public void setTaxStatusCode(String taxStatusCode) {
        this.taxStatusCode = taxStatusCode;
    }
    public String getTaxRateCode() {
        return taxRateCode;
    }
    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }
    public String getTaxJurisdicationCode() {
        return taxJurisdicationCode;
    }
    public void setTaxJurisdicationCode(String taxJurisdicationCode) {
        this.taxJurisdicationCode = taxJurisdicationCode;
    }
    public String getFirstPartyRegNumber() {
        return firstPartyRegNumber;
    }
    public void setFirstPartyRegNumber(String firstPartyRegNumber) {
        this.firstPartyRegNumber = firstPartyRegNumber;
    }
    public String getThirdPartyRegistrationNumber() {
        return thirdPartyRegistrationNumber;
    }
    public void setThirdPartyRegistrationNumber(String thirdPartyRegistrationNumber) {
        this.thirdPartyRegistrationNumber = thirdPartyRegistrationNumber;
    }
    public String getFinalDischargeLocation() {
        return finalDischargeLocation;
    }
    public void setFinalDischargeLocation(String finalDischargeLocation) {
        this.finalDischargeLocation = finalDischargeLocation;
    }
    public String getTaxableAmt() {
        return taxableAmt;
    }
    public void setTaxableAmt(String taxableAmt) {
        this.taxableAmt = taxableAmt;
    }
    public String getTaxableFlag() {
        return taxableFlag;
    }
    public void setTaxableFlag(String taxableFlag) {
        this.taxableFlag = taxableFlag;
    }
    public String getTaxExemptionFlag() {
        return taxExemptionFlag;
    }
    public void setTaxExemptionFlag(String taxExemptionFlag) {
        this.taxExemptionFlag = taxExemptionFlag;
    }
    public String getTaxExemptionReasonCode() {
        return taxExemptionReasonCode;
    }
    public void setTaxExemptionReasonCode(String taxExemptionReasonCode) {
        this.taxExemptionReasonCode = taxExemptionReasonCode;
    }
    public String getTaxExemptionReasonCodeMeaning() {
        return taxExemptionReasonCodeMeaning;
    }
    public void setTaxExemptionReasonCodeMeaning(String taxExemptionReasonCodeMeaning) {
        this.taxExemptionReasonCodeMeaning = taxExemptionReasonCodeMeaning;
    }
    public String getTaxExemptionCertifiedNumber() {
        return taxExemptionCertifiedNumber;
    }
    public void setTaxExemptionCertifiedNumber(String taxExemptionCertifiedNumber) {
        this.taxExemptionCertifiedNumber = taxExemptionCertifiedNumber;
    }
    public String getLineAmtIncludesTaxFlag() {
        return lineAmtIncludesTaxFlag;
    }
    public void setLineAmtIncludesTaxFlag(String lineAmtIncludesTaxFlag) {
        this.lineAmtIncludesTaxFlag = lineAmtIncludesTaxFlag;
    }
    public String getTaxPrecedence() {
        return taxPrecedence;
    }
    public void setTaxPrecedence(String taxPrecedence) {
        this.taxPrecedence = taxPrecedence;
    }
    public String getCreditMtdUsedRevenueSchedulingRules() {
        return creditMtdUsedRevenueSchedulingRules;
    }
    public void setCreditMtdUsedRevenueSchedulingRules(String creditMtdUsedRevenueSchedulingRules) {
        this.creditMtdUsedRevenueSchedulingRules = creditMtdUsedRevenueSchedulingRules;
    }
    public String getCreditMtdUsedSplitPaymentTerms() {
        return creditMtdUsedSplitPaymentTerms;
    }
    public void setCreditMtdUsedSplitPaymentTerms(String creditMtdUsedSplitPaymentTerms) {
        this.creditMtdUsedSplitPaymentTerms = creditMtdUsedSplitPaymentTerms;
    }
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    public String getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }
    public String getFobPoint() {
        return fobPoint;
    }
    public void setFobPoint(String fobPoint) {
        this.fobPoint = fobPoint;
    }
    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public String getShippingRef() {
        return shippingRef;
    }
    public void setShippingRef(String shippingRef) {
        this.shippingRef = shippingRef;
    }
    public String getSalesOrderLineNumber() {
        return salesOrderLineNumber;
    }
    public void setSalesOrderLineNumber(String salesOrderLineNumber) {
        this.salesOrderLineNumber = salesOrderLineNumber;
    }
    public String getSalesOrderSrc() {
        return salesOrderSrc;
    }
    public void setSalesOrderSrc(String salesOrderSrc) {
        this.salesOrderSrc = salesOrderSrc;
    }
    public String getSalesOrderRevisonNumber() {
        return salesOrderRevisonNumber;
    }
    public void setSalesOrderRevisonNumber(String salesOrderRevisonNumber) {
        this.salesOrderRevisonNumber = salesOrderRevisonNumber;
    }
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }
    public String getPurchaseOrderRevisionNumber() {
        return purchaseOrderRevisionNumber;
    }
    public void setPurchaseOrderRevisionNumber(String purchaseOrderRevisionNumber) {
        this.purchaseOrderRevisionNumber = purchaseOrderRevisionNumber;
    }
    public String getPurchaseOrderDate() {
        return purchaseOrderDate;
    }
    public void setPurchaseOrderDate(String purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }
    public String getAgreementName() {
        return agreementName;
    }
    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }
    public String getMemoLineName() {
        return memoLineName;
    }
    public void setMemoLineName(String memoLineName) {
        this.memoLineName = memoLineName;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getOriginalSysBatchName() {
        return originalSysBatchName;
    }
    public void setOriginalSysBatchName(String originalSysBatchName) {
        this.originalSysBatchName = originalSysBatchName;
    }
    public String getLinkToTransactionsFlexfieldCntxt() {
        return linkToTransactionsFlexfieldCntxt;
    }
    public void setLinkToTransactionsFlexfieldCntxt(String linkToTransactionsFlexfieldCntxt) {
        this.linkToTransactionsFlexfieldCntxt = linkToTransactionsFlexfieldCntxt;
    }
    public String getLinkToTransactionsFlexfieldSegment1() {
        return linkToTransactionsFlexfieldSegment1;
    }
    public void setLinkToTransactionsFlexfieldSegment1(String linkToTransactionsFlexfieldSegment1) {
        this.linkToTransactionsFlexfieldSegment1 = linkToTransactionsFlexfieldSegment1;
    }
    public String getLinkToTransactionsFlexfieldSegment2() {
        return linkToTransactionsFlexfieldSegment2;
    }
    public void setLinkToTransactionsFlexfieldSegment2(String linkToTransactionsFlexfieldSegment2) {
        this.linkToTransactionsFlexfieldSegment2 = linkToTransactionsFlexfieldSegment2;
    }
    public String getLinkToTransactionsFlexfieldSegment3() {
        return linkToTransactionsFlexfieldSegment3;
    }
    public void setLinkToTransactionsFlexfieldSegment3(String linkToTransactionsFlexfieldSegment3) {
        this.linkToTransactionsFlexfieldSegment3 = linkToTransactionsFlexfieldSegment3;
    }
    public String getLinkToTransactionsFlexfieldSegment4() {
        return linkToTransactionsFlexfieldSegment4;
    }
    public void setLinkToTransactionsFlexfieldSegment4(String linkToTransactionsFlexfieldSegment4) {
        this.linkToTransactionsFlexfieldSegment4 = linkToTransactionsFlexfieldSegment4;
    }
    public String getLinkToTransactionsFlexfieldSegment5() {
        return linkToTransactionsFlexfieldSegment5;
    }
    public void setLinkToTransactionsFlexfieldSegment5(String linkToTransactionsFlexfieldSegment5) {
        this.linkToTransactionsFlexfieldSegment5 = linkToTransactionsFlexfieldSegment5;
    }
    public String getLinkToTransactionsFlexfieldSegment6() {
        return linkToTransactionsFlexfieldSegment6;
    }
    public void setLinkToTransactionsFlexfieldSegment6(String linkToTransactionsFlexfieldSegment6) {
        this.linkToTransactionsFlexfieldSegment6 = linkToTransactionsFlexfieldSegment6;
    }
    public String getLinkToTransactionsFlexfieldSegment7() {
        return linkToTransactionsFlexfieldSegment7;
    }
    public void setLinkToTransactionsFlexfieldSegment7(String linkToTransactionsFlexfieldSegment7) {
        this.linkToTransactionsFlexfieldSegment7 = linkToTransactionsFlexfieldSegment7;
    }
    public String getLinkToTransactionsFlexfieldSegment8() {
        return linkToTransactionsFlexfieldSegment8;
    }
    public void setLinkToTransactionsFlexfieldSegment8(String linkToTransactionsFlexfieldSegment8) {
        this.linkToTransactionsFlexfieldSegment8 = linkToTransactionsFlexfieldSegment8;
    }
    public String getLinkToTransactionsFlexfieldSegment9() {
        return linkToTransactionsFlexfieldSegment9;
    }
    public void setLinkToTransactionsFlexfieldSegment9(String linkToTransactionsFlexfieldSegment9) {
        this.linkToTransactionsFlexfieldSegment9 = linkToTransactionsFlexfieldSegment9;
    }
    public String getLinkToTransactionsFlexfieldSegment10() {
        return linkToTransactionsFlexfieldSegment10;
    }
    public void setLinkToTransactionsFlexfieldSegment10(String linkToTransactionsFlexfieldSegment10) {
        this.linkToTransactionsFlexfieldSegment10 = linkToTransactionsFlexfieldSegment10;
    }
    public String getLinkToTransactionsFlexfieldSegment11() {
        return linkToTransactionsFlexfieldSegment11;
    }
    public void setLinkToTransactionsFlexfieldSegment11(String linkToTransactionsFlexfieldSegment11) {
        this.linkToTransactionsFlexfieldSegment11 = linkToTransactionsFlexfieldSegment11;
    }
    public String getLinkToTransactionsFlexfieldSegment12() {
        return linkToTransactionsFlexfieldSegment12;
    }
    public void setLinkToTransactionsFlexfieldSegment12(String linkToTransactionsFlexfieldSegment12) {
        this.linkToTransactionsFlexfieldSegment12 = linkToTransactionsFlexfieldSegment12;
    }
    public String getLinkToTransactionsFlexfieldSegment13() {
        return linkToTransactionsFlexfieldSegment13;
    }
    public void setLinkToTransactionsFlexfieldSegment13(String linkToTransactionsFlexfieldSegment13) {
        this.linkToTransactionsFlexfieldSegment13 = linkToTransactionsFlexfieldSegment13;
    }
    public String getLinkToTransactionsFlexfieldSegment14() {
        return linkToTransactionsFlexfieldSegment14;
    }
    public void setLinkToTransactionsFlexfieldSegment14(String linkToTransactionsFlexfieldSegment14) {
        this.linkToTransactionsFlexfieldSegment14 = linkToTransactionsFlexfieldSegment14;
    }
    public String getLinkToTransactionsFlexfieldSegment15() {
        return linkToTransactionsFlexfieldSegment15;
    }
    public void setLinkToTransactionsFlexfieldSegment15(String linkToTransactionsFlexfieldSegment15) {
        this.linkToTransactionsFlexfieldSegment15 = linkToTransactionsFlexfieldSegment15;
    }
    public String getRefTransactionFlexfieldCntxt() {
        return refTransactionFlexfieldCntxt;
    }
    public void setRefTransactionFlexfieldCntxt(String refTransactionFlexfieldCntxt) {
        this.refTransactionFlexfieldCntxt = refTransactionFlexfieldCntxt;
    }
    public String getRefTransactionFlexfieldSegment1() {
        return refTransactionFlexfieldSegment1;
    }
    public void setRefTransactionFlexfieldSegment1(String refTransactionFlexfieldSegment1) {
        this.refTransactionFlexfieldSegment1 = refTransactionFlexfieldSegment1;
    }
    public String getRefTransactionFlexfieldSegment2() {
        return refTransactionFlexfieldSegment2;
    }
    public void setRefTransactionFlexfieldSegment2(String refTransactionFlexfieldSegment2) {
        this.refTransactionFlexfieldSegment2 = refTransactionFlexfieldSegment2;
    }
    public String getRefTransactionFlexfieldSegment3() {
        return refTransactionFlexfieldSegment3;
    }
    public void setRefTransactionFlexfieldSegment3(String refTransactionFlexfieldSegment3) {
        this.refTransactionFlexfieldSegment3 = refTransactionFlexfieldSegment3;
    }
    public String getRefTransactionFlexfieldSegment4() {
        return refTransactionFlexfieldSegment4;
    }
    public void setRefTransactionFlexfieldSegment4(String refTransactionFlexfieldSegment4) {
        this.refTransactionFlexfieldSegment4 = refTransactionFlexfieldSegment4;
    }
    public String getRefTransactionFlexfieldSegment5() {
        return refTransactionFlexfieldSegment5;
    }
    public void setRefTransactionFlexfieldSegment5(String refTransactionFlexfieldSegment5) {
        this.refTransactionFlexfieldSegment5 = refTransactionFlexfieldSegment5;
    }
    public String getRefTransactionFlexfieldSegment6() {
        return refTransactionFlexfieldSegment6;
    }
    public void setRefTransactionFlexfieldSegment6(String refTransactionFlexfieldSegment6) {
        this.refTransactionFlexfieldSegment6 = refTransactionFlexfieldSegment6;
    }
    public String getRefTransactionFlexfieldSegment7() {
        return refTransactionFlexfieldSegment7;
    }
    public void setRefTransactionFlexfieldSegment7(String refTransactionFlexfieldSegment7) {
        this.refTransactionFlexfieldSegment7 = refTransactionFlexfieldSegment7;
    }
    public String getRefTransactionFlexfieldSegment8() {
        return refTransactionFlexfieldSegment8;
    }
    public void setRefTransactionFlexfieldSegment8(String refTransactionFlexfieldSegment8) {
        this.refTransactionFlexfieldSegment8 = refTransactionFlexfieldSegment8;
    }
    public String getRefTransactionFlexfieldSegment9() {
        return refTransactionFlexfieldSegment9;
    }
    public void setRefTransactionFlexfieldSegment9(String refTransactionFlexfieldSegment9) {
        this.refTransactionFlexfieldSegment9 = refTransactionFlexfieldSegment9;
    }
    public String getRefTransactionFlexfieldSegment10() {
        return refTransactionFlexfieldSegment10;
    }
    public void setRefTransactionFlexfieldSegment10(String refTransactionFlexfieldSegment10) {
        this.refTransactionFlexfieldSegment10 = refTransactionFlexfieldSegment10;
    }
    public String getRefTransactionFlexfieldSegment11() {
        return refTransactionFlexfieldSegment11;
    }
    public void setRefTransactionFlexfieldSegment11(String refTransactionFlexfieldSegment11) {
        this.refTransactionFlexfieldSegment11 = refTransactionFlexfieldSegment11;
    }
    public String getRefTransactionFlexfieldSegment12() {
        return refTransactionFlexfieldSegment12;
    }
    public void setRefTransactionFlexfieldSegment12(String refTransactionFlexfieldSegment12) {
        this.refTransactionFlexfieldSegment12 = refTransactionFlexfieldSegment12;
    }
    public String getRefTransactionFlexfieldSegment13() {
        return refTransactionFlexfieldSegment13;
    }
    public void setRefTransactionFlexfieldSegment13(String refTransactionFlexfieldSegment13) {
        this.refTransactionFlexfieldSegment13 = refTransactionFlexfieldSegment13;
    }
    public String getRefTransactionFlexfieldSegment14() {
        return refTransactionFlexfieldSegment14;
    }
    public void setRefTransactionFlexfieldSegment14(String refTransactionFlexfieldSegment14) {
        this.refTransactionFlexfieldSegment14 = refTransactionFlexfieldSegment14;
    }
    public String getRefTransactionFlexfieldSegment15() {
        return refTransactionFlexfieldSegment15;
    }
    public void setRefTransactionFlexfieldSegment15(String refTransactionFlexfieldSegment15) {
        this.refTransactionFlexfieldSegment15 = refTransactionFlexfieldSegment15;
    }
    public String getLinkToParentLineCntxt() {
        return linkToParentLineCntxt;
    }
    public void setLinkToParentLineCntxt(String linkToParentLineCntxt) {
        this.linkToParentLineCntxt = linkToParentLineCntxt;
    }
    public String getLinkToParentLineSegment1() {
        return linkToParentLineSegment1;
    }
    public void setLinkToParentLineSegment1(String linkToParentLineSegment1) {
        this.linkToParentLineSegment1 = linkToParentLineSegment1;
    }
    public String getLinkToParentLineSegment2() {
        return linkToParentLineSegment2;
    }
    public void setLinkToParentLineSegment2(String linkToParentLineSegment2) {
        this.linkToParentLineSegment2 = linkToParentLineSegment2;
    }
    public String getLinkToParentLineSegment3() {
        return linkToParentLineSegment3;
    }
    public void setLinkToParentLineSegment3(String linkToParentLineSegment3) {
        this.linkToParentLineSegment3 = linkToParentLineSegment3;
    }
    public String getLinkToParentLineSegment4() {
        return linkToParentLineSegment4;
    }
    public void setLinkToParentLineSegment4(String linkToParentLineSegment4) {
        this.linkToParentLineSegment4 = linkToParentLineSegment4;
    }
    public String getLinkToParentLineSegment5() {
        return linkToParentLineSegment5;
    }
    public void setLinkToParentLineSegment5(String linkToParentLineSegment5) {
        this.linkToParentLineSegment5 = linkToParentLineSegment5;
    }
    public String getLinkToParentLineSegment6() {
        return linkToParentLineSegment6;
    }
    public void setLinkToParentLineSegment6(String linkToParentLineSegment6) {
        this.linkToParentLineSegment6 = linkToParentLineSegment6;
    }
    public String getLinkToParentLineSegment7() {
        return linkToParentLineSegment7;
    }
    public void setLinkToParentLineSegment7(String linkToParentLineSegment7) {
        this.linkToParentLineSegment7 = linkToParentLineSegment7;
    }
    public String getLinkToParentLineSegment8() {
        return linkToParentLineSegment8;
    }
    public void setLinkToParentLineSegment8(String linkToParentLineSegment8) {
        this.linkToParentLineSegment8 = linkToParentLineSegment8;
    }
    public String getLinkToParentLineSegment9() {
        return linkToParentLineSegment9;
    }
    public void setLinkToParentLineSegment9(String linkToParentLineSegment9) {
        this.linkToParentLineSegment9 = linkToParentLineSegment9;
    }
    public String getLinkToParentLineSegment10() {
        return linkToParentLineSegment10;
    }
    public void setLinkToParentLineSegment10(String linkToParentLineSegment10) {
        this.linkToParentLineSegment10 = linkToParentLineSegment10;
    }
    public String getLinkToParentLineSegment11() {
        return linkToParentLineSegment11;
    }
    public void setLinkToParentLineSegment11(String linkToParentLineSegment11) {
        this.linkToParentLineSegment11 = linkToParentLineSegment11;
    }
    public String getLinkToParentLineSegment12() {
        return linkToParentLineSegment12;
    }
    public void setLinkToParentLineSegment12(String linkToParentLineSegment12) {
        this.linkToParentLineSegment12 = linkToParentLineSegment12;
    }
    public String getLinkToParentLineSegment13() {
        return linkToParentLineSegment13;
    }
    public void setLinkToParentLineSegment13(String linkToParentLineSegment13) {
        this.linkToParentLineSegment13 = linkToParentLineSegment13;
    }
    public String getLinkToParentLineSegment14() {
        return linkToParentLineSegment14;
    }
    public void setLinkToParentLineSegment14(String linkToParentLineSegment14) {
        this.linkToParentLineSegment14 = linkToParentLineSegment14;
    }
    public String getLinkToParentLineSegment15() {
        return linkToParentLineSegment15;
    }
    public void setLinkToParentLineSegment15(String linkToParentLineSegment15) {
        this.linkToParentLineSegment15 = linkToParentLineSegment15;
    }
    public String getReceiptMethodName() {
        return receiptMethodName;
    }
    public void setReceiptMethodName(String receiptMethodName) {
        this.receiptMethodName = receiptMethodName;
    }
    public String getPrintingOption() {
        return printingOption;
    }
    public void setPrintingOption(String printingOption) {
        this.printingOption = printingOption;
    }
    public String getRelatedBatchSrcName() {
        return relatedBatchSrcName;
    }
    public void setRelatedBatchSrcName(String relatedBatchSrcName) {
        this.relatedBatchSrcName = relatedBatchSrcName;
    }
    public String getRelatedTransactionNumber() {
        return relatedTransactionNumber;
    }
    public void setRelatedTransactionNumber(String relatedTransactionNumber) {
        this.relatedTransactionNumber = relatedTransactionNumber;
    }
    public String getInventoryItemNumber() {
        return inventoryItemNumber;
    }
    public void setInventoryItemNumber(String inventoryItemNumber) {
        this.inventoryItemNumber = inventoryItemNumber;
    }
    public String getInventoryItemSegment2() {
        return inventoryItemSegment2;
    }
    public void setInventoryItemSegment2(String inventoryItemSegment2) {
        this.inventoryItemSegment2 = inventoryItemSegment2;
    }
    public String getInventoryItemSegment3() {
        return inventoryItemSegment3;
    }
    public void setInventoryItemSegment3(String inventoryItemSegment3) {
        this.inventoryItemSegment3 = inventoryItemSegment3;
    }
    public String getInventoryItemSegment4() {
        return inventoryItemSegment4;
    }
    public void setInventoryItemSegment4(String inventoryItemSegment4) {
        this.inventoryItemSegment4 = inventoryItemSegment4;
    }
    public String getInventoryItemSegment5() {
        return inventoryItemSegment5;
    }
    public void setInventoryItemSegment5(String inventoryItemSegment5) {
        this.inventoryItemSegment5 = inventoryItemSegment5;
    }
    public String getInventoryItemSegment6() {
        return inventoryItemSegment6;
    }
    public void setInventoryItemSegment6(String inventoryItemSegment6) {
        this.inventoryItemSegment6 = inventoryItemSegment6;
    }
    public String getInventoryItemSegment7() {
        return inventoryItemSegment7;
    }
    public void setInventoryItemSegment7(String inventoryItemSegment7) {
        this.inventoryItemSegment7 = inventoryItemSegment7;
    }
    public String getInventoryItemSegment8() {
        return inventoryItemSegment8;
    }
    public void setInventoryItemSegment8(String inventoryItemSegment8) {
        this.inventoryItemSegment8 = inventoryItemSegment8;
    }
    public String getInventoryItemSegment9() {
        return inventoryItemSegment9;
    }
    public void setInventoryItemSegment9(String inventoryItemSegment9) {
        this.inventoryItemSegment9 = inventoryItemSegment9;
    }
    public String getInventoryItemSegment10() {
        return inventoryItemSegment10;
    }
    public void setInventoryItemSegment10(String inventoryItemSegment10) {
        this.inventoryItemSegment10 = inventoryItemSegment10;
    }
    public String getInventoryItemSegment11() {
        return inventoryItemSegment11;
    }
    public void setInventoryItemSegment11(String inventoryItemSegment11) {
        this.inventoryItemSegment11 = inventoryItemSegment11;
    }
    public String getInventoryItemSegment12() {
        return inventoryItemSegment12;
    }
    public void setInventoryItemSegment12(String inventoryItemSegment12) {
        this.inventoryItemSegment12 = inventoryItemSegment12;
    }
    public String getInventoryItemSegment13() {
        return inventoryItemSegment13;
    }
    public void setInventoryItemSegment13(String inventoryItemSegment13) {
        this.inventoryItemSegment13 = inventoryItemSegment13;
    }
    public String getInventoryItemSegment14() {
        return inventoryItemSegment14;
    }
    public void setInventoryItemSegment14(String inventoryItemSegment14) {
        this.inventoryItemSegment14 = inventoryItemSegment14;
    }
    public String getInventoryItemSegment15() {
        return inventoryItemSegment15;
    }
    public void setInventoryItemSegment15(String inventoryItemSegment15) {
        this.inventoryItemSegment15 = inventoryItemSegment15;
    }
    public String getInventoryItemSegment16() {
        return inventoryItemSegment16;
    }
    public void setInventoryItemSegment16(String inventoryItemSegment16) {
        this.inventoryItemSegment16 = inventoryItemSegment16;
    }
    public String getInventoryItemSegment17() {
        return inventoryItemSegment17;
    }
    public void setInventoryItemSegment17(String inventoryItemSegment17) {
        this.inventoryItemSegment17 = inventoryItemSegment17;
    }
    public String getInventoryItemSegment18() {
        return inventoryItemSegment18;
    }
    public void setInventoryItemSegment18(String inventoryItemSegment18) {
        this.inventoryItemSegment18 = inventoryItemSegment18;
    }
    public String getInventoryItemSegment19() {
        return inventoryItemSegment19;
    }
    public void setInventoryItemSegment19(String inventoryItemSegment19) {
        this.inventoryItemSegment19 = inventoryItemSegment19;
    }
    public String getInventoryItemSegment20() {
        return inventoryItemSegment20;
    }
    public void setInventoryItemSegment20(String inventoryItemSegment20) {
        this.inventoryItemSegment20 = inventoryItemSegment20;
    }
    public String getBillToCustomerBankAccntName() {
        return billToCustomerBankAccntName;
    }
    public void setBillToCustomerBankAccntName(String billToCustomerBankAccntName) {
        this.billToCustomerBankAccntName = billToCustomerBankAccntName;
    }
    public String getResetTransactionDtFlag() {
        return resetTransactionDtFlag;
    }
    public void setResetTransactionDtFlag(String resetTransactionDtFlag) {
        this.resetTransactionDtFlag = resetTransactionDtFlag;
    }
    public String getPaymentServerOrderNumber() {
        return paymentServerOrderNumber;
    }
    public void setPaymentServerOrderNumber(String paymentServerOrderNumber) {
        this.paymentServerOrderNumber = paymentServerOrderNumber;
    }
    public String getLastTransactionDebitAuth() {
        return lastTransactionDebitAuth;
    }
    public void setLastTransactionDebitAuth(String lastTransactionDebitAuth) {
        this.lastTransactionDebitAuth = lastTransactionDebitAuth;
    }
    public String getApprovalCode() {
        return approvalCode;
    }
    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
    public String getAddressVerificationCode() {
        return addressVerificationCode;
    }
    public void setAddressVerificationCode(String addressVerificationCode) {
        this.addressVerificationCode = addressVerificationCode;
    }
    public String getTransactionLineTranslatedDesc() {
        return transactionLineTranslatedDesc;
    }
    public void setTransactionLineTranslatedDesc(String transactionLineTranslatedDesc) {
        this.transactionLineTranslatedDesc = transactionLineTranslatedDesc;
    }
    public String getConsolidatedBillingNumber() {
        return consolidatedBillingNumber;
    }
    public void setConsolidatedBillingNumber(String consolidatedBillingNumber) {
        this.consolidatedBillingNumber = consolidatedBillingNumber;
    }
    public String getPromisedCommitmentAmt() {
        return promisedCommitmentAmt;
    }
    public void setPromisedCommitmentAmt(String promisedCommitmentAmt) {
        this.promisedCommitmentAmt = promisedCommitmentAmt;
    }
    public String getPaymentSetIdentifier() {
        return paymentSetIdentifier;
    }
    public void setPaymentSetIdentifier(String paymentSetIdentifier) {
        this.paymentSetIdentifier = paymentSetIdentifier;
    }
    public String getOrigAccntingDt() {
        return origAccntingDt;
    }
    public void setOrigAccntingDt(String origAccntingDt) {
        this.origAccntingDt = origAccntingDt;
    }
    public String getInvoiceLineAccntingLvl() {
        return invoiceLineAccntingLvl;
    }
    public void setInvoiceLineAccntingLvl(String invoiceLineAccntingLvl) {
        this.invoiceLineAccntingLvl = invoiceLineAccntingLvl;
    }
    public String getOverrideAutoAcntingFlag() {
        return overrideAutoAcntingFlag;
    }
    public void setOverrideAutoAcntingFlag(String overrideAutoAcntingFlag) {
        this.overrideAutoAcntingFlag = overrideAutoAcntingFlag;
    }
    public String getHistoricalFlag() {
        return historicalFlag;
    }
    public void setHistoricalFlag(String historicalFlag) {
        this.historicalFlag = historicalFlag;
    }
    public String getDeferralExclusionFlag() {
        return deferralExclusionFlag;
    }
    public void setDeferralExclusionFlag(String deferralExclusionFlag) {
        this.deferralExclusionFlag = deferralExclusionFlag;
    }
    public String getPaymentAttributes() {
        return paymentAttributes;
    }
    public void setPaymentAttributes(String paymentAttributes) {
        this.paymentAttributes = paymentAttributes;
    }
    public String getInvoiceBillingDt() {
        return invoiceBillingDt;
    }
    public void setInvoiceBillingDt(String invoiceBillingDt) {
        this.invoiceBillingDt = invoiceBillingDt;
    }
    public String getInvoiceLineFlexfieldCntxt() {
        return invoiceLineFlexfieldCntxt;
    }
    public void setInvoiceLineFlexfieldCntxt(String invoiceLineFlexfieldCntxt) {
        this.invoiceLineFlexfieldCntxt = invoiceLineFlexfieldCntxt;
    }
    public String getInvoiceLineFlexfieldSeg1() {
        return invoiceLineFlexfieldSeg1;
    }
    public void setInvoiceLineFlexfieldSeg1(String invoiceLineFlexfieldSeg1) {
        this.invoiceLineFlexfieldSeg1 = invoiceLineFlexfieldSeg1;
    }
    public String getInvoiceLineFlexfieldSeg2() {
        return invoiceLineFlexfieldSeg2;
    }
    public void setInvoiceLineFlexfieldSeg2(String invoiceLineFlexfieldSeg2) {
        this.invoiceLineFlexfieldSeg2 = invoiceLineFlexfieldSeg2;
    }
    public String getInvoiceLineFlexfieldSeg3() {
        return invoiceLineFlexfieldSeg3;
    }
    public void setInvoiceLineFlexfieldSeg3(String invoiceLineFlexfieldSeg3) {
        this.invoiceLineFlexfieldSeg3 = invoiceLineFlexfieldSeg3;
    }
    public String getInvoiceLineFlexfieldSeg4() {
        return invoiceLineFlexfieldSeg4;
    }
    public void setInvoiceLineFlexfieldSeg4(String invoiceLineFlexfieldSeg4) {
        this.invoiceLineFlexfieldSeg4 = invoiceLineFlexfieldSeg4;
    }
    public String getInvoiceLineFlexfieldSeg5() {
        return invoiceLineFlexfieldSeg5;
    }
    public void setInvoiceLineFlexfieldSeg5(String invoiceLineFlexfieldSeg5) {
        this.invoiceLineFlexfieldSeg5 = invoiceLineFlexfieldSeg5;
    }
    public String getInvoiceLineFlexfieldSeg6() {
        return invoiceLineFlexfieldSeg6;
    }
    public void setInvoiceLineFlexfieldSeg6(String invoiceLineFlexfieldSeg6) {
        this.invoiceLineFlexfieldSeg6 = invoiceLineFlexfieldSeg6;
    }
    public String getInvoiceLineFlexfieldSeg7() {
        return invoiceLineFlexfieldSeg7;
    }
    public void setInvoiceLineFlexfieldSeg7(String invoiceLineFlexfieldSeg7) {
        this.invoiceLineFlexfieldSeg7 = invoiceLineFlexfieldSeg7;
    }
    public String getInvoiceLineFlexfieldSeg8() {
        return invoiceLineFlexfieldSeg8;
    }
    public void setInvoiceLineFlexfieldSeg8(String invoiceLineFlexfieldSeg8) {
        this.invoiceLineFlexfieldSeg8 = invoiceLineFlexfieldSeg8;
    }
    public String getInvoiceLineFlexfieldSeg9() {
        return invoiceLineFlexfieldSeg9;
    }
    public void setInvoiceLineFlexfieldSeg9(String invoiceLineFlexfieldSeg9) {
        this.invoiceLineFlexfieldSeg9 = invoiceLineFlexfieldSeg9;
    }
    public String getInvoiceLineFlexfieldSeg10() {
        return invoiceLineFlexfieldSeg10;
    }
    public void setInvoiceLineFlexfieldSeg10(String invoiceLineFlexfieldSeg10) {
        this.invoiceLineFlexfieldSeg10 = invoiceLineFlexfieldSeg10;
    }
    public String getInvoiceLineFlexfieldSeg11() {
        return invoiceLineFlexfieldSeg11;
    }
    public void setInvoiceLineFlexfieldSeg11(String invoiceLineFlexfieldSeg11) {
        this.invoiceLineFlexfieldSeg11 = invoiceLineFlexfieldSeg11;
    }
    public String getInvoiceLineFlexfieldSeg12() {
        return invoiceLineFlexfieldSeg12;
    }
    public void setInvoiceLineFlexfieldSeg12(String invoiceLineFlexfieldSeg12) {
        this.invoiceLineFlexfieldSeg12 = invoiceLineFlexfieldSeg12;
    }
    public String getInvoiceLineFlexfieldSeg13() {
        return invoiceLineFlexfieldSeg13;
    }
    public void setInvoiceLineFlexfieldSeg13(String invoiceLineFlexfieldSeg13) {
        this.invoiceLineFlexfieldSeg13 = invoiceLineFlexfieldSeg13;
    }
    public String getInvoiceLineFlexfieldSeg14() {
        return invoiceLineFlexfieldSeg14;
    }
    public void setInvoiceLineFlexfieldSeg14(String invoiceLineFlexfieldSeg14) {
        this.invoiceLineFlexfieldSeg14 = invoiceLineFlexfieldSeg14;
    }
    public String getInvoiceLineFlexfieldSeg15() {
        return invoiceLineFlexfieldSeg15;
    }
    public void setInvoiceLineFlexfieldSeg15(String invoiceLineFlexfieldSeg15) {
        this.invoiceLineFlexfieldSeg15 = invoiceLineFlexfieldSeg15;
    }
    public String getInvoiceTransactionFlexfieldCntxt() {
        return invoiceTransactionFlexfieldCntxt;
    }
    public void setInvoiceTransactionFlexfieldCntxt(String invoiceTransactionFlexfieldCntxt) {
        this.invoiceTransactionFlexfieldCntxt = invoiceTransactionFlexfieldCntxt;
    }
    public String getInvoiceTransFlexfieldSeg1() {
        return invoiceTransFlexfieldSeg1;
    }
    public void setInvoiceTransFlexfieldSeg1(String invoiceTransFlexfieldSeg1) {
        this.invoiceTransFlexfieldSeg1 = invoiceTransFlexfieldSeg1;
    }
    public String getInvoiceTransFlexfieldSeg2() {
        return invoiceTransFlexfieldSeg2;
    }
    public void setInvoiceTransFlexfieldSeg2(String invoiceTransFlexfieldSeg2) {
        this.invoiceTransFlexfieldSeg2 = invoiceTransFlexfieldSeg2;
    }
    public String getInvoiceTransFlexfieldSeg3() {
        return invoiceTransFlexfieldSeg3;
    }
    public void setInvoiceTransFlexfieldSeg3(String invoiceTransFlexfieldSeg3) {
        this.invoiceTransFlexfieldSeg3 = invoiceTransFlexfieldSeg3;
    }
    public String getInvoiceTransFlexfieldSeg4() {
        return invoiceTransFlexfieldSeg4;
    }
    public void setInvoiceTransFlexfieldSeg4(String invoiceTransFlexfieldSeg4) {
        this.invoiceTransFlexfieldSeg4 = invoiceTransFlexfieldSeg4;
    }
    public String getInvoiceTransFlexfieldSeg5() {
        return invoiceTransFlexfieldSeg5;
    }
    public void setInvoiceTransFlexfieldSeg5(String invoiceTransFlexfieldSeg5) {
        this.invoiceTransFlexfieldSeg5 = invoiceTransFlexfieldSeg5;
    }
    public String getInvoiceTransFlexfieldSeg6() {
        return invoiceTransFlexfieldSeg6;
    }
    public void setInvoiceTransFlexfieldSeg6(String invoiceTransFlexfieldSeg6) {
        this.invoiceTransFlexfieldSeg6 = invoiceTransFlexfieldSeg6;
    }
    public String getInvoiceTransFlexfieldSeg7() {
        return invoiceTransFlexfieldSeg7;
    }
    public void setInvoiceTransFlexfieldSeg7(String invoiceTransFlexfieldSeg7) {
        this.invoiceTransFlexfieldSeg7 = invoiceTransFlexfieldSeg7;
    }
    public String getInvoiceTransFlexfieldSeg8() {
        return invoiceTransFlexfieldSeg8;
    }
    public void setInvoiceTransFlexfieldSeg8(String invoiceTransFlexfieldSeg8) {
        this.invoiceTransFlexfieldSeg8 = invoiceTransFlexfieldSeg8;
    }
    public String getInvoiceTransFlexfieldSeg9() {
        return invoiceTransFlexfieldSeg9;
    }
    public void setInvoiceTransFlexfieldSeg9(String invoiceTransFlexfieldSeg9) {
        this.invoiceTransFlexfieldSeg9 = invoiceTransFlexfieldSeg9;
    }
    public String getInvoiceTransFlexfieldSeg10() {
        return invoiceTransFlexfieldSeg10;
    }
    public void setInvoiceTransFlexfieldSeg10(String invoiceTransFlexfieldSeg10) {
        this.invoiceTransFlexfieldSeg10 = invoiceTransFlexfieldSeg10;
    }
    public String getInvoiceTransFlexfieldSeg11() {
        return invoiceTransFlexfieldSeg11;
    }
    public void setInvoiceTransFlexfieldSeg11(String invoiceTransFlexfieldSeg11) {
        this.invoiceTransFlexfieldSeg11 = invoiceTransFlexfieldSeg11;
    }
    public String getInvoiceTransFlexfieldSeg12() {
        return invoiceTransFlexfieldSeg12;
    }
    public void setInvoiceTransFlexfieldSeg12(String invoiceTransFlexfieldSeg12) {
        this.invoiceTransFlexfieldSeg12 = invoiceTransFlexfieldSeg12;
    }
    public String getInvoiceTransFlexfieldSeg13() {
        return invoiceTransFlexfieldSeg13;
    }
    public void setInvoiceTransFlexfieldSeg13(String invoiceTransFlexfieldSeg13) {
        this.invoiceTransFlexfieldSeg13 = invoiceTransFlexfieldSeg13;
    }
    public String getInvoiceTransFlexfieldSeg14() {
        return invoiceTransFlexfieldSeg14;
    }
    public void setInvoiceTransFlexfieldSeg14(String invoiceTransFlexfieldSeg14) {
        this.invoiceTransFlexfieldSeg14 = invoiceTransFlexfieldSeg14;
    }
    public String getInvoiceTransFlexfieldSeg15() {
        return invoiceTransFlexfieldSeg15;
    }
    public void setInvoiceTransFlexfieldSeg15(String invoiceTransFlexfieldSeg15) {
        this.invoiceTransFlexfieldSeg15 = invoiceTransFlexfieldSeg15;
    }
    public String getReceivablesTransRegionInfoFlexfieldCntxt() {
        return receivablesTransRegionInfoFlexfieldCntxt;
    }
    public void setReceivablesTransRegionInfoFlexfieldCntxt(String receivablesTransRegionInfoFlexfieldCntxt) {
        this.receivablesTransRegionInfoFlexfieldCntxt = receivablesTransRegionInfoFlexfieldCntxt;
    }
    public String getRecvTransRegInfoFlexSeg1() {
        return recvTransRegInfoFlexSeg1;
    }
    public void setRecvTransRegInfoFlexSeg1(String recvTransRegInfoFlexSeg1) {
        this.recvTransRegInfoFlexSeg1 = recvTransRegInfoFlexSeg1;
    }
    public String getRecvTransRegInfoFlexSeg2() {
        return recvTransRegInfoFlexSeg2;
    }
    public void setRecvTransRegInfoFlexSeg2(String recvTransRegInfoFlexSeg2) {
        this.recvTransRegInfoFlexSeg2 = recvTransRegInfoFlexSeg2;
    }
    public String getRecvTransRegInfoFlexSeg3() {
        return recvTransRegInfoFlexSeg3;
    }
    public void setRecvTransRegInfoFlexSeg3(String recvTransRegInfoFlexSeg3) {
        this.recvTransRegInfoFlexSeg3 = recvTransRegInfoFlexSeg3;
    }
    public String getRecvTransRegInfoFlexSeg4() {
        return recvTransRegInfoFlexSeg4;
    }
    public void setRecvTransRegInfoFlexSeg4(String recvTransRegInfoFlexSeg4) {
        this.recvTransRegInfoFlexSeg4 = recvTransRegInfoFlexSeg4;
    }
    public String getRecvTransRegInfoFlexSeg5() {
        return recvTransRegInfoFlexSeg5;
    }
    public void setRecvTransRegInfoFlexSeg5(String recvTransRegInfoFlexSeg5) {
        this.recvTransRegInfoFlexSeg5 = recvTransRegInfoFlexSeg5;
    }
    public String getRecvTransRegInfoFlexSeg6() {
        return recvTransRegInfoFlexSeg6;
    }
    public void setRecvTransRegInfoFlexSeg6(String recvTransRegInfoFlexSeg6) {
        this.recvTransRegInfoFlexSeg6 = recvTransRegInfoFlexSeg6;
    }
    public String getRecvTransRegInfoFlexSeg7() {
        return recvTransRegInfoFlexSeg7;
    }
    public void setRecvTransRegInfoFlexSeg7(String recvTransRegInfoFlexSeg7) {
        this.recvTransRegInfoFlexSeg7 = recvTransRegInfoFlexSeg7;
    }
    public String getRecvTransRegInfoFlexSeg8() {
        return recvTransRegInfoFlexSeg8;
    }
    public void setRecvTransRegInfoFlexSeg8(String recvTransRegInfoFlexSeg8) {
        this.recvTransRegInfoFlexSeg8 = recvTransRegInfoFlexSeg8;
    }
    public String getRecvTransRegInfoFlexSeg9() {
        return recvTransRegInfoFlexSeg9;
    }
    public void setRecvTransRegInfoFlexSeg9(String recvTransRegInfoFlexSeg9) {
        this.recvTransRegInfoFlexSeg9 = recvTransRegInfoFlexSeg9;
    }
    public String getRecvTransRegInfoFlexSeg10() {
        return recvTransRegInfoFlexSeg10;
    }
    public void setRecvTransRegInfoFlexSeg10(String recvTransRegInfoFlexSeg10) {
        this.recvTransRegInfoFlexSeg10 = recvTransRegInfoFlexSeg10;
    }
    public String getRecvTransRegInfoFlexSeg11() {
        return recvTransRegInfoFlexSeg11;
    }
    public void setRecvTransRegInfoFlexSeg11(String recvTransRegInfoFlexSeg11) {
        this.recvTransRegInfoFlexSeg11 = recvTransRegInfoFlexSeg11;
    }
    public String getRecvTransRegInfoFlexSeg12() {
        return recvTransRegInfoFlexSeg12;
    }
    public void setRecvTransRegInfoFlexSeg12(String recvTransRegInfoFlexSeg12) {
        this.recvTransRegInfoFlexSeg12 = recvTransRegInfoFlexSeg12;
    }
    public String getRecvTransRegInfoFlexSeg13() {
        return recvTransRegInfoFlexSeg13;
    }
    public void setRecvTransRegInfoFlexSeg13(String recvTransRegInfoFlexSeg13) {
        this.recvTransRegInfoFlexSeg13 = recvTransRegInfoFlexSeg13;
    }
    public String getRecvTransRegInfoFlexSeg14() {
        return recvTransRegInfoFlexSeg14;
    }
    public void setRecvTransRegInfoFlexSeg14(String recvTransRegInfoFlexSeg14) {
        this.recvTransRegInfoFlexSeg14 = recvTransRegInfoFlexSeg14;
    }
    public String getRecvTransRegInfoFlexSeg15() {
        return recvTransRegInfoFlexSeg15;
    }
    public void setRecvTransRegInfoFlexSeg15(String recvTransRegInfoFlexSeg15) {
        this.recvTransRegInfoFlexSeg15 = recvTransRegInfoFlexSeg15;
    }
    public String getRecvTransRegInfoFlexSeg16() {
        return recvTransRegInfoFlexSeg16;
    }
    public void setRecvTransRegInfoFlexSeg16(String recvTransRegInfoFlexSeg16) {
        this.recvTransRegInfoFlexSeg16 = recvTransRegInfoFlexSeg16;
    }
    public String getRecvTransRegInfoFlexSeg17() {
        return recvTransRegInfoFlexSeg17;
    }
    public void setRecvTransRegInfoFlexSeg17(String recvTransRegInfoFlexSeg17) {
        this.recvTransRegInfoFlexSeg17 = recvTransRegInfoFlexSeg17;
    }
    public String getRecvTransRegInfoFlexSeg18() {
        return recvTransRegInfoFlexSeg18;
    }
    public void setRecvTransRegInfoFlexSeg18(String recvTransRegInfoFlexSeg18) {
        this.recvTransRegInfoFlexSeg18 = recvTransRegInfoFlexSeg18;
    }
    public String getRecvTransRegInfoFlexSeg19() {
        return recvTransRegInfoFlexSeg19;
    }
    public void setRecvTransRegInfoFlexSeg19(String recvTransRegInfoFlexSeg19) {
        this.recvTransRegInfoFlexSeg19 = recvTransRegInfoFlexSeg19;
    }
    public String getRecvTransRegInfoFlexSeg20() {
        return recvTransRegInfoFlexSeg20;
    }
    public void setRecvTransRegInfoFlexSeg20(String recvTransRegInfoFlexSeg20) {
        this.recvTransRegInfoFlexSeg20 = recvTransRegInfoFlexSeg20;
    }
    public String getRecvTransRegInfoFlexSeg21() {
        return recvTransRegInfoFlexSeg21;
    }
    public void setRecvTransRegInfoFlexSeg21(String recvTransRegInfoFlexSeg21) {
        this.recvTransRegInfoFlexSeg21 = recvTransRegInfoFlexSeg21;
    }
    public String getRecvTransRegInfoFlexSeg22() {
        return recvTransRegInfoFlexSeg22;
    }
    public void setRecvTransRegInfoFlexSeg22(String recvTransRegInfoFlexSeg22) {
        this.recvTransRegInfoFlexSeg22 = recvTransRegInfoFlexSeg22;
    }
    public String getRecvTransRegInfoFlexSeg23() {
        return recvTransRegInfoFlexSeg23;
    }
    public void setRecvTransRegInfoFlexSeg23(String recvTransRegInfoFlexSeg23) {
        this.recvTransRegInfoFlexSeg23 = recvTransRegInfoFlexSeg23;
    }
    public String getRecvTransRegInfoFlexSeg24() {
        return recvTransRegInfoFlexSeg24;
    }
    public void setRecvTransRegInfoFlexSeg24(String recvTransRegInfoFlexSeg24) {
        this.recvTransRegInfoFlexSeg24 = recvTransRegInfoFlexSeg24;
    }
    public String getRecvTransRegInfoFlexSeg25() {
        return recvTransRegInfoFlexSeg25;
    }
    public void setRecvTransRegInfoFlexSeg25(String recvTransRegInfoFlexSeg25) {
        this.recvTransRegInfoFlexSeg25 = recvTransRegInfoFlexSeg25;
    }
    public String getRecvTransRegInfoFlexSeg26() {
        return recvTransRegInfoFlexSeg26;
    }
    public void setRecvTransRegInfoFlexSeg26(String recvTransRegInfoFlexSeg26) {
        this.recvTransRegInfoFlexSeg26 = recvTransRegInfoFlexSeg26;
    }
    public String getRecvTransRegInfoFlexSeg27() {
        return recvTransRegInfoFlexSeg27;
    }
    public void setRecvTransRegInfoFlexSeg27(String recvTransRegInfoFlexSeg27) {
        this.recvTransRegInfoFlexSeg27 = recvTransRegInfoFlexSeg27;
    }
    public String getRecvTransRegInfoFlexSeg28() {
        return recvTransRegInfoFlexSeg28;
    }
    public void setRecvTransRegInfoFlexSeg28(String recvTransRegInfoFlexSeg28) {
        this.recvTransRegInfoFlexSeg28 = recvTransRegInfoFlexSeg28;
    }
    public String getRecvTransRegInfoFlexSeg29() {
        return recvTransRegInfoFlexSeg29;
    }
    public void setRecvTransRegInfoFlexSeg29(String recvTransRegInfoFlexSeg29) {
        this.recvTransRegInfoFlexSeg29 = recvTransRegInfoFlexSeg29;
    }
    public String getRecvTransRegInfoFlexSeg30() {
        return recvTransRegInfoFlexSeg30;
    }
    public void setRecvTransRegInfoFlexSeg30(String recvTransRegInfoFlexSeg30) {
        this.recvTransRegInfoFlexSeg30 = recvTransRegInfoFlexSeg30;
    }
    public String getLineGlblDecrFlexAttribCateg() {
        return lineGlblDecrFlexAttribCateg;
    }
    public void setLineGlblDecrFlexAttribCateg(String lineGlblDecrFlexAttribCateg) {
        this.lineGlblDecrFlexAttribCateg = lineGlblDecrFlexAttribCateg;
    }
    public String getLineGlblDecrFlexSeg1() {
        return lineGlblDecrFlexSeg1;
    }
    public void setLineGlblDecrFlexSeg1(String lineGlblDecrFlexSeg1) {
        this.lineGlblDecrFlexSeg1 = lineGlblDecrFlexSeg1;
    }
    public String getLineGlblDecrFlexSeg2() {
        return lineGlblDecrFlexSeg2;
    }
    public void setLineGlblDecrFlexSeg2(String lineGlblDecrFlexSeg2) {
        this.lineGlblDecrFlexSeg2 = lineGlblDecrFlexSeg2;
    }
    public String getLineGlblDecrFlexSeg3() {
        return lineGlblDecrFlexSeg3;
    }
    public void setLineGlblDecrFlexSeg3(String lineGlblDecrFlexSeg3) {
        this.lineGlblDecrFlexSeg3 = lineGlblDecrFlexSeg3;
    }
    public String getLineGlblDecrFlexSeg4() {
        return lineGlblDecrFlexSeg4;
    }
    public void setLineGlblDecrFlexSeg4(String lineGlblDecrFlexSeg4) {
        this.lineGlblDecrFlexSeg4 = lineGlblDecrFlexSeg4;
    }
    public String getLineGlblDecrFlexSeg5() {
        return lineGlblDecrFlexSeg5;
    }
    public void setLineGlblDecrFlexSeg5(String lineGlblDecrFlexSeg5) {
        this.lineGlblDecrFlexSeg5 = lineGlblDecrFlexSeg5;
    }
    public String getLineGlblDecrFlexSeg6() {
        return lineGlblDecrFlexSeg6;
    }
    public void setLineGlblDecrFlexSeg6(String lineGlblDecrFlexSeg6) {
        this.lineGlblDecrFlexSeg6 = lineGlblDecrFlexSeg6;
    }
    public String getLineGlblDecrFlexSeg7() {
        return lineGlblDecrFlexSeg7;
    }
    public void setLineGlblDecrFlexSeg7(String lineGlblDecrFlexSeg7) {
        this.lineGlblDecrFlexSeg7 = lineGlblDecrFlexSeg7;
    }
    public String getLineGlblDecrFlexSeg8() {
        return lineGlblDecrFlexSeg8;
    }
    public void setLineGlblDecrFlexSeg8(String lineGlblDecrFlexSeg8) {
        this.lineGlblDecrFlexSeg8 = lineGlblDecrFlexSeg8;
    }
    public String getLineGlblDecrFlexSeg9() {
        return lineGlblDecrFlexSeg9;
    }
    public void setLineGlblDecrFlexSeg9(String lineGlblDecrFlexSeg9) {
        this.lineGlblDecrFlexSeg9 = lineGlblDecrFlexSeg9;
    }
    public String getLineGlblDecrFlexSeg10() {
        return lineGlblDecrFlexSeg10;
    }
    public void setLineGlblDecrFlexSeg10(String lineGlblDecrFlexSeg10) {
        this.lineGlblDecrFlexSeg10 = lineGlblDecrFlexSeg10;
    }
    public String getLineGlblDecrFlexSeg11() {
        return lineGlblDecrFlexSeg11;
    }
    public void setLineGlblDecrFlexSeg11(String lineGlblDecrFlexSeg11) {
        this.lineGlblDecrFlexSeg11 = lineGlblDecrFlexSeg11;
    }
    public String getLineGlblDecrFlexSeg12() {
        return lineGlblDecrFlexSeg12;
    }
    public void setLineGlblDecrFlexSeg12(String lineGlblDecrFlexSeg12) {
        this.lineGlblDecrFlexSeg12 = lineGlblDecrFlexSeg12;
    }
    public String getLineGlblDecrFlexSeg13() {
        return lineGlblDecrFlexSeg13;
    }
    public void setLineGlblDecrFlexSeg13(String lineGlblDecrFlexSeg13) {
        this.lineGlblDecrFlexSeg13 = lineGlblDecrFlexSeg13;
    }
    public String getLineGlblDecrFlexSeg14() {
        return lineGlblDecrFlexSeg14;
    }
    public void setLineGlblDecrFlexSeg14(String lineGlblDecrFlexSeg14) {
        this.lineGlblDecrFlexSeg14 = lineGlblDecrFlexSeg14;
    }
    public String getLineGlblDecrFlexSeg15() {
        return lineGlblDecrFlexSeg15;
    }
    public void setLineGlblDecrFlexSeg15(String lineGlblDecrFlexSeg15) {
        this.lineGlblDecrFlexSeg15 = lineGlblDecrFlexSeg15;
    }
    public String getLineGlblDecrFlexSeg16() {
        return lineGlblDecrFlexSeg16;
    }
    public void setLineGlblDecrFlexSeg16(String lineGlblDecrFlexSeg16) {
        this.lineGlblDecrFlexSeg16 = lineGlblDecrFlexSeg16;
    }
    public String getLineGlblDecrFlexSeg17() {
        return lineGlblDecrFlexSeg17;
    }
    public void setLineGlblDecrFlexSeg17(String lineGlblDecrFlexSeg17) {
        this.lineGlblDecrFlexSeg17 = lineGlblDecrFlexSeg17;
    }
    public String getLineGlblDecrFlexSeg18() {
        return lineGlblDecrFlexSeg18;
    }
    public void setLineGlblDecrFlexSeg18(String lineGlblDecrFlexSeg18) {
        this.lineGlblDecrFlexSeg18 = lineGlblDecrFlexSeg18;
    }
    public String getLineGlblDecrFlexSeg19() {
        return lineGlblDecrFlexSeg19;
    }
    public void setLineGlblDecrFlexSeg19(String lineGlblDecrFlexSeg19) {
        this.lineGlblDecrFlexSeg19 = lineGlblDecrFlexSeg19;
    }
    public String getLineGlblDecrFlexSeg20() {
        return lineGlblDecrFlexSeg20;
    }
    public void setLineGlblDecrFlexSeg20(String lineGlblDecrFlexSeg20) {
        this.lineGlblDecrFlexSeg20 = lineGlblDecrFlexSeg20;
    }
    public String getRenewalYear() {
        return renewalYear;
    }
    public void setRenewalYear(String renewalYear) {
        this.renewalYear = renewalYear;
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
    public String getBshMappingValid() {
        return bshMappingValid;
    }
    public void setBshMappingValid(String bshMappingValid) {
        this.bshMappingValid = bshMappingValid;
    }
    public BigDecimal getHeaderAmt() {
        return headerAmt;
    }
    public void setHeaderAmt(BigDecimal headerAmt) {
        this.headerAmt = headerAmt;
    }
}
