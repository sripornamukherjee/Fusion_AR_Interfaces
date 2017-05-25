package com.compasites.utils;

import com.compasites.constants.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sobhan Babu on 01-06-2016.
 */
public class DateUtil {

    public static String getDateTime(){
        String datetime = new SimpleDateFormat(Constants.FILE_DATE_FORMAT).format(Calendar.getInstance().getTime());
        return datetime;
    }

    public static Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_NEW);
        return sdf.parse(date);
    }

    public static Date getFormatedDate(Date date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String formatedDate = sdf.format(date);
        return sdf.parse(formatedDate);
    }

    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.parse(date);
    }

    public static String getDateyyMMdd(){
        String datetime = new SimpleDateFormat(Constants.RECEIPT_DT_FORMAT).format(Calendar.getInstance().getTime());
        return datetime;
    }
}
