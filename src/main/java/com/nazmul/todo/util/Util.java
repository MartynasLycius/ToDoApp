/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nazmul
 */
public class Util {
    
    /**
     * Convert a {@code  LocalDate} to {@code java.util.Date}
     * @param localDate {@code  LocalDate}
     * @return {@see java.util.Date}
     */
    public static Date convertTo(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }
    
    /**
     * Converts a date {@code  LocalDate} into 'E, dd MMM' pattern
     * @param date {@code  LocalDate}
     * @return String representation of the date in specific pattern
     */
    public static String getChartFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("E, dd MMM"));
    }
    
    /**
     * Converts a date {@code  LocalDate} into 'YYYY-MM-dd' pattern
     * @param date
     * @return 
     */
    public static String formatLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    /**
     * Gets all the dates from the given date upto last previous past days.
     * @param startDate date {@code  LocalDate}
     * @param pastNumOfDays num of last prev days
     * @return list of datess {@code  LocalDate}
     */
    public static List<LocalDate> getPastDaysDates(LocalDate startDate, int pastNumOfDays) {
        List<LocalDate> pastWeeksDate = new ArrayList<>();
        
        //get past days date in descending order.
        for(int i = pastNumOfDays ; i > 0 ; i--) {
            pastWeeksDate.add(startDate.minusDays(Long.valueOf(i)));
        }
        
        pastWeeksDate.add(startDate);
        return pastWeeksDate;
    }
}