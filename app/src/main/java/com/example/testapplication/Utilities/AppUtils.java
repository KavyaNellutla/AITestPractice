package com.example.testapplication.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {
    public static String getFormattedDate(String actualdate){
        String convertedDate="";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.'SSS'Z'", Locale.getDefault());
        DateFormat convertTo = new SimpleDateFormat("dd-MM-yyyy hh:mm a",Locale.getDefault());
        Date date = null;
        try{
            date = dateFormat.parse(actualdate);
        }catch (Exception e){

        }
        convertedDate=convertTo.format(date);
        return convertedDate;
    }
}
