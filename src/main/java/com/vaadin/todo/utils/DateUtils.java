package com.vaadin.todo.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


}
