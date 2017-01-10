package com.example.jon_thornburg.secondituneshitter.activities.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jon_thornburg on 1/10/17.
 */

public class DateConverter {
    public static String makeDate(String input) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(input);
            String formattedDate = new SimpleDateFormat("MM-dd-yyyy").format(date);
            return formattedDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
