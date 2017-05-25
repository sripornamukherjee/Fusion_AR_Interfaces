package com.compasites.constants;

import com.compasites.pojo.Receipt;
import com.compasites.pojo.ReceiptLock;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 21-06-2016.
 */
public enum ReceiptLockEnum {

    CUSTOMER_ID("customerId"),
    COLLECTION_DATE("collectionDate"),
    FORMATTED_COLLECTION_DATE("formatedCollectionDate"),
    TRANSACTION_TYPE("transactionType"),
    RECEIPT_NUMBER("receiptNumber"),
    PAYMENT_MODE("paymentMode"),
    INVOICE_NUMBER("invoiceNumber"),
    INVOICE_AMOUNT("invoiceAmount"),
    RECEIPT_AMOUNT("receiptAmount"),
    PAYMENT_AMOUNT("paymentAmount"),

    RECEIPT_METHOD("receiptMethod"),
    DESTINATION_BANK_ACCOUNT("destBankAccount"),
    BANK_ORIG_NUMBER("bankOrigNumber"),
    DEPOSIT_DT("depositDt"),
    DEPOSIT_DATE("depositDate"),
    SERVICE_HEADER_BANK_ORG_HEADER("serviceHdrBankOrgNumber"),
    LOCKBOX_NUMBER("lockBoxNumber"),
    LOCKBOX_HEADER_DEPOSIT_DATE("lockBoxHdrDepositDt"),
    LOCKBOX_HEADER_BANK_ORIGINATION_NUMBER("lockBoxHdrBankOriginationNumber"),
    BATCHBOX_HEADER_DEPOSIT_DATE("batchHdrDepositDt"),
    PAYMENT_RECEIPT_METHOD("paymentReceiptMethod"),
    PAYMENT_LOCKBOX_NUMBER("paymentLockBoxNumber"),
    PAYMENT_REMITTANCE_AMOUNT("paymentRemittanceAmt"),
    PAYMENT_RECEIPT_NUMBER("paymentReceiptNumber"),
    PAY_RECEIPT_DATE("paymentReceiptDate"),
    CURRENCY("currency"),
    PAYMENT_CUSTOMER_ACCOUNT_NUMBER("paymentCustomerAccntNumber"),
    PAYMENT_DEPOSIT_DATE("paymentDepositDate"),
    PAYMENT_TRANSACTION_REF1("paymentTransactionRef1"),
    PAYMENT_APPLIED_AMOUNT1("paymentAppliedAmt1"),
    PAYMENT_TRANSACTION_REF2("paymentTransactionRef2"),
    PAYMENT_APPLIED_AMOUNT2("paymentAppliedAmt2"),
    PAYMENT_TRANSACTION_REF3("paymentTransactionRef3"),
    PAYMENT_APPLIED_AMOUNT3("paymentAppliedAmt3"),
    PAYMENT_TRANSACTION_REF4("paymentTransactionRef4"),
    PAYMENT_APPLIED_AMOUNT4("paymentAppliedAmt4"),
    BATCH_NAME("batchName")
    ;

    String enumVal;

    ReceiptLockEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesReceipt(ReceiptLock receipt, Map map) throws InvocationTargetException, IllegalAccessException {

        for(ReceiptLockEnum enumVal : ReceiptLockEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(ReceiptLock.class, methodName, String.class);
            method.setAccessible(true);
            method.invoke(receipt, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(ReceiptLock receipt, Map map) throws InvocationTargetException, IllegalAccessException {
        for(ReceiptLockEnum enumVal : ReceiptLockEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(ReceiptLock.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(receipt, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
