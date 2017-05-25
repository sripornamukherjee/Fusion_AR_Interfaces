package com.compasites.constants;

import com.compasites.pojo.Customer;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import java.util.Map;

/**
 * Created by Sobhan Babu on 25-04-2016.
 */
public enum CustomerEnum {

    BATCH_INDENTIFIER("batchIdentifier"),
    IDENTIFYING_ADDRESS("identifyingAddr"),
    INSERT_UPDATE_INDICATOR("insertUpdateIndicator"),
    SUBJECT_REL_PARTY_ORIG_SYSTEM_REF("subRelPrtyOrigSystemRef"),
    REL_SRC_SYSTEM_REF("relSrcSystemRef"),
    CONTACT_ROLE_ORIGINAL_SYS_REF("contctRoleOrigSysRef"),
    ACCNT_ROLE_ORIG_SYS_REF("accntSiteSrcSysRef"),
    ORGANIZATION_ID("orgId"),
    CUSTOMER_ID("customerId"),
    CUSTOMER_ID1("customerId1"),
    CUSTOMER_ID_PHONE("customerIdPhone"),       //MN200-1-1phone
    CUSTOMER_ID_EMAIL("customerIdEmail"),       //MN200-1-1email
    CUSTOMER_ID_BILL_TO_RESPONSIBILITY("customerIdBillToResponsibiltiy"),       //MN200-1-1
    CUSTOMER_ID_STMT_RESPONSIBILITY("customerIdStmtResponsibiltiy"),            //MN200-1-2
    CUSTOMER_TYPE("customerType"),
    CUSTOMER_STATUS("customerStatus"),
    CUSTOMER_TYPE_INPUT("customerTypeInput"),
    CUSTOMER_STATUS_INPUT("customerStatusInput"),
    COUNTRY("country"),
    COUNTY1("county1"),
    COUNTY("county"),
    EMAIL_ADDRESS("emailAddress"),
    MAPPED_EMAIL_ADDRESS("mappedEmailAddr"),
    CONTACT_PERSON_NAME("contactPersonName"),
    CUSTOMER_NAME("customerName"),
    END("end");

    String enumVal;

    CustomerEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesCustomer(Customer customer, Map map) throws InvocationTargetException, IllegalAccessException {

        for(CustomerEnum enumVal : CustomerEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Customer.class, methodName,String.class);
            method.setAccessible(true);
            method.invoke(customer, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(Customer customer, Map map) throws InvocationTargetException, IllegalAccessException {

        for(CustomerEnum enumVal : CustomerEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Customer.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(customer, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
