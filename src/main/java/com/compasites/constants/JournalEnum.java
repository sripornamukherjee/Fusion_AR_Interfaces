package com.compasites.constants;

import com.compasites.pojo.Journal;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 07-06-2016.
 */
public enum JournalEnum {

    BILL_DATE("billDate"),
    NET_TOTAL_AMOUNT("netTotalAmt"),
    ALLOCATED_REVENUE_AMOUNT("allocatedRevAmt"),
    REVENUE_ACCOUNT_CODE("revenueAcCode"),
    ADVANCE_ACCOUNT_CODE("advanceAccntCode"),
    INVOICE_NUMBER("invoiceNumber"),
    CUSTOMER_NAME("customerName"),
    COURSE_START_DT("courseStartDt"),

    STATUS_CODE("statusCode"),
    LEDGER_ID("ledgerId"),
    JOURNAL_SOURCE("journalSrc"),
    JOURNAL_CATEGORY("journalCategory"),
    CURRECNY_CODE("currencyCode"),
    ACTUAL_FLAG("actualFlag"),
    DEBIT_ACCOUNT("debitAccount"),
    CREDIT_ACCOUNT("creditAccount"),
    INTERFACE_GROUP_IDENTIFIER("interfaceGrpIdentifier"),
    EFFECTIVE_DATE_OF_TRANSACTION("effectiveDateOfTrans"),
    JOUNRAL_ENTRY_CREATION_DATE("journalEntryCreationDt"),
    ENTERED_DEBIT_AMOUNT("enteredDebitAmt"),
    ENTERED_CREDIT_AMOUNT("enteredCreditAmt"),
    REFERENCE1_BATCH_NAME("reference1BatchNm"),
    REFERENCE2_BATCH_DESCRIPTION("reference2BatchDes"),
    REFERENCE4_JOUNRAL_ENTRY_NAME("reference4JournalEntryNm"),
    REFERENCE5_JOURNAL_ENTRY_DESC("reference5");

    String enumVal;

    JournalEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesJournal(Journal journal, Map map) throws InvocationTargetException, IllegalAccessException {

        for(JournalEnum enumVal : JournalEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Journal.class, methodName, String.class);
            method.setAccessible(true);
            method.invoke(journal, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(Journal journal, Map map) throws InvocationTargetException, IllegalAccessException {
        for(JournalEnum enumVal : JournalEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            //System.out.println("method name : " + methodName);
            Method method = ReflectionUtils.findMethod(Journal.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(journal, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
