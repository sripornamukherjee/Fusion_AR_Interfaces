package com.compasites.constants;

import com.compasites.pojo.CreditNote;
import com.compasites.pojo.Receipt;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Sobhan Babu on 26-05-2016.
 */
public enum CreditnoteEnum {

    COMMENTS("comments");

    String enumVal;

    CreditnoteEnum(String value){
        this.enumVal = value;
    }

    public String getEnumVal() {
        return enumVal;
    }

    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }

    public static void setValuesCreditnote(CreditNote creditnote, Map map) throws InvocationTargetException, IllegalAccessException {

        for(CreditnoteEnum enumVal : CreditnoteEnum.values()){
            String methodName = Constants.SET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(CreditNote.class, methodName, String.class);
            method.setAccessible(true);
            method.invoke(creditnote, map.get(enumVal.getEnumVal()));
        }
    }

    public static void setValuesMap(CreditNote creditnote, Map map) throws InvocationTargetException, IllegalAccessException {
        for(CreditnoteEnum enumVal : CreditnoteEnum.values()){
            String methodName = Constants.GET + StringUtils.capitalize(enumVal.getEnumVal());
            Method method = ReflectionUtils.findMethod(CreditNote.class, methodName);
            method.setAccessible(true);
            String propertyValue = (String) method.invoke(creditnote, null);
            map.put(enumVal.getEnumVal(), propertyValue);
        }
    }
}
