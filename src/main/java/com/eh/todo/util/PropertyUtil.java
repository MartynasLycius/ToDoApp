package com.eh.todo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public class PropertyUtil {

    @SuppressWarnings("unused")
    private static Logger LOG = LoggerFactory.getLogger(PropertyUtil.class);

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    public static final String RESPONSE_MASSAGE_TRUE = "Records Found";
    public static final String RESPONSE_MASSAGE_FALSE = "No Records Found";

    public static final String WELCOME = "Welcome !!! Project Running : ";
    public static final String URL = "http://localhost:9191/todo";

    public static final String RECORD = "##### Record : ";
    public static final String SAVED = "Saved";
    public static final String NOT_SAVED = "Not Saved";
    public static final String FOUND = "Found";
    public static final String NOT_FOUND = "Not Found";
    public static final String UPDETED = "Updeted";
    public static final String NOT_UPDETED = "Not Updeted";

    public static String Record(String massage) {
        return RECORD + massage;
    }
}