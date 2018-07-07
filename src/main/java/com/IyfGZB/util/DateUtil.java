package com.IyfGZB.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static String toDate(Date date) {
        Timestamp ts=new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return  formatter.format(ts);
    }


    public static String getDateInString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String day=simpleDateFormat.format(date).toUpperCase();

        System.out.println("DAY "+simpleDateFormat.format(date).toUpperCase());

        simpleDateFormat = new SimpleDateFormat("MMMM");
        String month = simpleDateFormat.format(date).toUpperCase();
        System.out.println("MONTH "+simpleDateFormat.format(date).toUpperCase());

        simpleDateFormat = new SimpleDateFormat("YYYY");
        String year= simpleDateFormat.format(date).toUpperCase();
        System.out.println("YEAR "+simpleDateFormat.format(date).toUpperCase());

        return day;
    }
}
