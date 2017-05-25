package com.compasites.validations;

import com.compasites.constants.Constants;
import com.compasites.utils.DateUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sobhan Babu on 03-06-2016.
 */
public class CommonValidation {

    public static boolean amtDecimalLen(String amt){
        boolean isValidAmt = true;
        if (amt != null && amt.length() > 0) {
            String amtSplit[] = amt.split("\\.");
            if (amtSplit.length == 2 && amtSplit[1].length() > 2){
                isValidAmt = false;
            }
        }

        return isValidAmt;
    }

    public static boolean stringNotEmpty(String str){
        boolean isValidAmt = false;
        if (str != null && str.length() > 0) {
            isValidAmt = true;
        }

        return isValidAmt;
    }

    public static boolean isValidDate(String billDate, int validDays) throws ParseException {
        boolean isValidDate = false;
        Date billDt = DateUtil.getDateFromString(billDate);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, validDays);
        Date currentDt = DateUtil.getFormatedDate(cal.getTime());
        if (billDt.compareTo(currentDt) >= 0) {
            isValidDate = true;
        }

        return isValidDate;
    }

    public static boolean isAmountValid(String allocatedRevAmt, String grossTotalAmt){
        boolean isAmountValid = true;
        BigDecimal allRevAmt = new BigDecimal(allocatedRevAmt);
        BigDecimal grossTotAmt = new BigDecimal(grossTotalAmt);
        //if((allRevAmt.compareTo(new BigDecimal(0)) == 0 && grossTotAmt.compareTo(new BigDecimal(0)) != 0)
        //        || (allRevAmt.compareTo(new BigDecimal(0)) != 0 && grossTotAmt.compareTo(new BigDecimal(0)) == 0)) {
        if((allRevAmt.compareTo(new BigDecimal(0)) == 0 && grossTotAmt.compareTo(new BigDecimal(0)) != 0)){
            isAmountValid = false;
        }

        return isAmountValid;
    }

    public static String removeComma(String str){
        String retString = str;
        if (str != null){
            if (str.indexOf(Constants.COMMA) > -1) {
                retString = str.replaceAll(Constants.COMMA, "");
            }
        }
        return retString;
    }

    public static String replaceDoubleQuote(String str){
        String retString = str;
        if (str != null){
            if (str.indexOf(Constants.DOUBLE_QUOTE) > -1) {
                retString = str.replaceAll(Constants.DOUBLE_QUOTE, Constants.TWO_DOUBLE_QUOTE);
            }
        }
        return retString;
    }

    public static String removeDoubleQuote(String str){
        String retString = str;
        if (str != null){
            if (str.indexOf(Constants.DOUBLE_QUOTE) > -1) {
                retString = str.replaceAll(Constants.DOUBLE_QUOTE, "");
            }
        }
        return retString;
    }
    
   public static String removeExtraSpace(String str) {
	   String retString = str;
       if (str != null){
           retString = str.replaceAll(Constants.REGEX_ONE_OR_MORE_SPACE, " ");
       }
       return retString;
   }
}
