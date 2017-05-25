package com.compasites.constants;

import com.compasites.pojo.CreditMemo;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 02-06-2016.
 */
public enum CreditMemoEnum {

    RECORD_TYPE("recordType"),
    CUSTOMER_ID("customerId"),
    ACCOUNT_TYPE("accountType"),
    CREDIT_MEMO_NUMBER("creditNoteNumber"),
    BILL_DATE("billDate"),
    DOCUMENT_TYPE("documentType"),
    GROSS_TOTAL_AMOUNT("grossTotalAmt"),
    PAYMENT_MODE("paymentMode"),
    BILLING_HEADER_STATUS("billingHeaderStatus"),
    REVENUE_ACCOUNT_CODE("revenueAcCode"),
    NET_TOTAL_AMOUNT("netTotalAmt"),
    ALLOCATED_REVENUE_AMOUNT("allocatedRevAmt"),
    HALF_YEAR("halfYear"),
    FULL_YEAR("fullYear"),
    COURSE_START_DATE("courseStartDt"),
    WDA_SFC_REFERENCE_NUMBER("wdasfcRefNumber"),
    TRANSACTION_TYPE("transactionType"),
    TRANSACTION_TYPE_LINE_ITEM("transactionTypeLineItem"),
    FMS_TRANSACTION_TYPE("fmsTransactionType"),
    GST_PERCENT("gstPercent"),
    WDA_FUNDED_AMT("wdaFundAmt"),
    SFC_FUNDED_AMT("sfcFundedAmt"),

    BUSINESS_UNIT_NAME("businessUnitName"),
    TRANSACTION_BATCH_SOURCE_NAME("transBtchSrcName"),
    TRANSACTION_TYPE_NAME("transTypeName"),
    PAYMENT_TERMS("paymentTerms"),
    TRANSACTION_DATE("transactionDate"),
    ACCOUNTING_DATE("accountingDt"),
    TRANSACTION_NUMBER("transactionNumber"),
    BILL_CUSTOMER_ACCNT_NUMBER("billToCustomerAccntNumber"),
    BILL_CUSTOMER_SITE_NUMBER("billToCustomerSiteNumber"),
    TRANSACTION_LINE_TYPE("transactionLineType"),
    TRANSACTION_LINE_DESCRIPTION("transactionLineDescr"),
    CURRENCY_CODE("currencyCode"),
    CURRENCY_CONVERSION_TYPE("currencyConversionType"),
    CURRENCY_CONVERSION_RATE("currecnyConversionRate"),
    TRANSACTION_LINE_AMOUNT("transactionLineAmt"),
    TRANSACTION_LINE_QUANTITY("transactionLineQty"),
    UNIT_SELLING_PRICE("unitSellingPrice"),
    LINE_TRANSACTION_FLEX_FIEDL_CONTEXT("lineTransactionFlexfieldContxt"),
    INVOICING_RULE_NAME("invoicingRuleName"),
    CREDIT_MTD_USED_REVENUE_SCHEDULING_RULES("creditMtdUsedRevenueSchedulingRules"),
    MEMO_LINE_NAME("memoLineName"),
    REF_TRANSACTION_FLEXFIELD_CONTEXT("refTransactionFlexfieldCntxt"),
    REF_TRANSACTION_FLEXFIELD_SEG1("refTransactionFlexfieldSegment1"),
	REF_TRANSACTION_FLEXFIELD_SEG2("refTransactionFlexfieldSegment2"),
    PRINTING_OPTION("printingOption"),
    TAX_CLASSIFICATION_CODE("taxClassificationCode"),
    IS_BSH_MAPPING_VALID("bshMappingValid"),
    INVOICE_TRANSACTION_FLEXFIELD_SEG1("invoiceTransFlexfieldSeg1");


    String enumVal;

    CreditMemoEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesCreditMemo(CreditMemo creditMemo, Map map) throws InvocationTargetException, IllegalAccessException {

        for(CreditMemoEnum enumVal : CreditMemoEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(CreditMemo.class, methodName, String.class);
            method.setAccessible(true);
            method.invoke(creditMemo, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(CreditMemo creditMemo, Map map) throws InvocationTargetException, IllegalAccessException {
        for(CreditMemoEnum enumVal : CreditMemoEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(CreditMemo.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(creditMemo, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
