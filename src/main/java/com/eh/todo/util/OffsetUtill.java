package com.eh.todo.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public class OffsetUtill {

    private static Logger LOG = LoggerFactory.getLogger(OffsetUtill.class);

    public static Map<String, Object> response(Object data, int total) {
        Map<String, Object> response = new HashMap<String, Object>();
        int pages = getPageCount(total, 10);

        response.put("total", total);
        response.put("pages", pages);
        response.put("data", data);
        response.put("success", total == 0 ? PropertyUtil.FALSE : PropertyUtil.TRUE);
        response.put("message", total == 0 ? PropertyUtil.RESPONSE_MASSAGE_FALSE : PropertyUtil.RESPONSE_MASSAGE_TRUE);

        return response;
    }

    public static int getPageCount(int noOfRecords, int pageSize) {
        int pages = noOfRecords / pageSize;
        if (noOfRecords % pageSize > 0) {
            pages++;
        }
        return pages;
    }

    public static Long castLongObject(Object object) {
        Long result = 0l;
        try {
            if (object instanceof Long)
                result = ((Long) object).longValue();
            else if (object instanceof Integer) {
                result = ((Integer) object).longValue();
            } else if (object instanceof String) {
                result = Long.valueOf((String) object);
            }
        } catch (Exception e) {
            LOG.error("Can Not Cust Object to Long");
        }
        return result;
    }

    public static Map<String, Object> offsetifyByPageAndSize(int page, int size) {
        page = page - 1;
        page = (page < 0) ? 0 : page;
        size = (size <= 0) ? 100 : ((size >= 100) ? 100 : size);

        int iniOffset = (page * size) + 1;
        int endOffset = (page * size) + size;

        Map<String,Object> param = new HashMap<>();
        param.put("iniOffset", iniOffset);
        param.put("endOffset", endOffset);
        return param;
    }
}
