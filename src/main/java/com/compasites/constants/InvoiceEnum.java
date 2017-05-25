package com.compasites.constants;

import com.compasites.pojo.Invoice;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public enum InvoiceEnum {

    //input file properties
    RECORD_TYPE("recordType"),
    CUSTOMER_ID("customerId"),
    ACCOUNT_TYPE("accountType"),
    INVOICE_NUMBER("invoiceNumber"),
    BILL_DATE("billDate"),
    DOCUMENT_TYPE("documentType"),
    GROSS_TOTAL_AMOUNT("grossTotalAmt"),
    PAYMENT_MODE("paymentMode"),
    LINE_ITEM("lineItem"),
    NET_TOTAL_AMT("netTotalAmt"),
    ALLOCATED_REVENUE_AMOUNT("allocatedRevAmt"),
    MEMBERSHIP_YEAR("memYear"),
    HALF_YEAR("halfYear"),
    FULL_YEAR("fullYear"),
    COURSE_START_DATE("courseStartDt"),
    WDA_SFC_REFERENCE_NUMBER("wdasfcRefNumber"),
    TRANSACTION_TYPE("transactionType"),
    TRANSACTION_TYPE_LINE_ITEM("transactionTypeLineItem"),
    FMS_TRANSACTION_TYPE("fmsTransactionType"),
    WDA_FUNDED_AMT("wdaFundedAmt"),
    SFC_FUNDED_AMT("sfcFundedAmt"),

     //output output properties
    BUSINESS_UNIT_NAME("businessUnitName"),
    TRANSACTION_BATCH_SOURCE_NAME("transBtchSrcName"),
    TRANSACTION_TYPE_NAME("transTypeName"),
    PAYMENT_TERMS("paymentTerms"),
    TRANSACTION_DATE("transactionDate"),
    ACCOUNTING_DATE("accountingDt"),
    TRANSACTION_NUMBER("transactionNumber"),
    ORIGINAL_SYSTEM_BILL_TO_CUSTOMER_REFERENCE("originalSysBillToCustRef"),
    ORIGINAL_SYSTEM_SHIP_TO_CUSTOMER_ACCOUNTD_REFERENCE("origSysShipToCustomerAccntRef"),
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
    PURCHASE_ORDER_DATE("purchaseOrderDate"),
    MEMO_LINE_NAME("memoLineName"),
    PRINTING_OPTION("printingOption"),
    TRANSACTION_LINE_TRANSLATED_DESCRIPTION("transactionLineTranslatedDesc"),
    CONSOLIDATED_BILLING_NUMBER("consolidatedBillingNumber"),
    REVENUE_SCHEDULING_RULE_NAME("revenueSchedulingRuleName"),
    INVOICE_BILLING_DATE("invoiceBillingDt"),
    REVENUESCDHEDULING_START_DATE("revenuedSchedulingRuleStrtDt"),
    NO_OF_REVENUE_PERIODS("noOfRevenuePeriods"),
    TAX_CLASSIFICATION_CODE("taxClassificationCode"),
    PRIMARY_SALES_PERSON_NUMBER("primarySalespersonNumber"),
    REVENUE_ACCOUNT_CODE("revenueAcCode"),
    BILL_MONTH("billMonth"),
    BILL_YEAR("billYear"),
    GST_PERCENT("gstPercent"),
    RENEWAL_YEAR("renewalYear"),
    RECEIPT_METHOD_NAME("receiptMethodName"),
    IS_BSH_MAPPING_VALID("bshMappingValid"),
    INVOICE_TRANSACTION_FLEXFIELD_SEG1("invoiceTransFlexfieldSeg1");

    String enumVal;

    InvoiceEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesInvoice(Invoice invoice, Map map) throws InvocationTargetException, IllegalAccessException {

        for(InvoiceEnum enumVal : InvoiceEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Invoice.class, methodName,String.class);
            method.setAccessible(true);
            method.invoke(invoice, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(Invoice invoice, Map map) throws InvocationTargetException, IllegalAccessException {
        for(InvoiceEnum enumVal : InvoiceEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Invoice.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(invoice, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
