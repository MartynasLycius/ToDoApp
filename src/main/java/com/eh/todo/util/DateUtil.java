package com.eh.todo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public class DateUtil {

    private static Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    public static final String YYYY_MM_DD             = "yyyy-MM-dd";
    public static final String YYYY_M_D               = "yyyy-M-d";
    public static final String YY_M_D                 = "yy-M-d";
    public static final String YYYYMMDD               = "yyyyMMdd";

    public static final String DD_MM_YYYY             = "dd/MM/yyyy";
    public static final String D_M_YYYY               = "d/M/yyyy";
    public static final String D_M_YY                 = "d/M/yy";
    public static final String DDMMYYYY               = "ddMMyyyy";
    public static final String TIME_ZONE              = "GMT";
    public static final String DATE_EXP               = "EEE, d MMM yyyy HH:mm:ss z";

    public static final String ISO_DATE               = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String HL7_DATE_FORMAT        = "yyyyMMddHHmmss.SSSZ";
    public static final String HL7v2_DATE_FORMAT      = "yyyyMMddHHmmss";

    /**
     * This method is used to format date
     *
     * @return  : This method returns string type date.
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return date != null ? formatter.format(date) : null;
    }

    /**
     * This method is used to check date with another date.
     *
     * @return  : This method returns boolean.
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    public static boolean isEqual(Date refDate, Date date) {
        long refTime = refDate != null ? refDate.getTime() : 0;
        long time    = date != null ? date.getTime(): 0;
        return refTime == time;
    }

    /**
     * This method is used to parse a date to another pattern.
     *
     * @return  : This method returns parsed Date.
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    public static Date parse(String date, String pattern) {
        Date retDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            retDate = formatter.parse(date);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return retDate;
    }

    /**
     * This method is used to parse a string date to date format.
     *
     * @return  : This method returns parsed Date.
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    public static Date parse(String date) {
        Date javaDate    = null;
        boolean notParse = true;
        int index        = 0;
        
        String[] formats = new String[] { "yyyyMMddHHmmss.SSSZ",
                "yyyyMMddHHmmss.SSS", "yyyyMMddHHmmss.SS", 
                "yyyyMMddHHmmss.S", "yyyyMMddHHmmss", 
                "yyyyMMddHHmm", "yyyyMMddHH", 
                "yyyyMMdd", "yyyyMM", "yyyy" };

        while(notParse && index < formats.length){
            try {
                String format = formats[index];
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                javaDate = formatter.parse(date);
                notParse = false;
            } catch (ParseException e) {
                index++;
            }
        }

        return javaDate;
    }
}
