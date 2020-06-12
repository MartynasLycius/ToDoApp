package dev.sayem.todoapp.commons.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String DATE_PATTERN_READABLE = "MMM dd, YYYY";

    // Formats
    public static DateFormat getReadableDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN_READABLE);
    }


    // Impl
    public static String getReadableDate(Date date) {
        return getReadableDateFormat().format(date);
    }
}
