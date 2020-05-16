package com.proit.todo.core.helper;

import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;



public class Sanitizer {
    /**
     * Trim white space from left and right
     * Escape html tags to avoid CSRF
     * */
    public static String basic(String value){
        if(value==null)return null;

        value = value.trim();
        value = StringEscapeUtils.escapeJavaScript(value);

        return value;
    }

}
