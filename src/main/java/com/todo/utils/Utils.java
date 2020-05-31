package com.todo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {
    private static final Logger logger= LogManager.getLogger(Utils.class);
    private Utils(){}

    public static String getRelativePath(String uri){
        if (isEmpty(uri))return null;
        return uri.startsWith("/")?uri.substring(1):uri;
    }

    public static boolean isEmptyObjList(Collection<?> value) {
        return value == null || value.isEmpty();
    }
    public static boolean isEmptyObjMap(Map<?,?> value) {
        return value == null || value.isEmpty();
    }
    public static boolean isEmpty(List list)  {
        return list==null || list.size()==0;
    }
    public static boolean isEmpty(String str)  {
        return str==null || str.length()==0;
    }
    public static boolean isEmptyTrimString(String str)  {
        return str==null || str.trim().length()==0;
    }
}
