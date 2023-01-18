package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Utility {

    public static String unixTimestamp() {
        long unix = new Date().getTime();
        return String.valueOf(unix);
    }

    public static LocalDate daysToAdd(String date, int day) {
        LocalDate newDate = LocalDate.parse(date);
        return newDate.plusDays(day);
    }

    // chage date format from 2022-12-31 to 221231
    public static String dateFormat(String date) throws ParseException {
        String newDate;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = df.parse(date);
        df.applyPattern("yyMMdd");
        newDate = df.format(d);

        return newDate;
    }


}
