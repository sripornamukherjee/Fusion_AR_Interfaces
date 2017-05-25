package com.compasites.constants;

import com.compasites.pojo.Receipt;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 20-05-2016.
 */
public enum ReceiptEnum {

    COLLECTION_PAY_MODE("collectionPayMode"),
    BUSINESS_UNIT_NAME("businessUnitName"),
    METHOD_NAME("methodName"),
    CURRECNY_CODE("currencyCode");

    String enumVal;

    ReceiptEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesReceipt(Receipt receipt, Map map) throws InvocationTargetException, IllegalAccessException {

        for(ReceiptEnum enumVal : ReceiptEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Receipt.class, methodName, String.class);
            method.setAccessible(true);
            method.invoke(receipt, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(Receipt receipt, Map map) throws InvocationTargetException, IllegalAccessException {
        for(ReceiptEnum enumVal : ReceiptEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(Receipt.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(receipt, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
