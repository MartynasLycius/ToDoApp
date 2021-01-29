package com.mir00r.todoapp.domains.todo.models.dtos;

import com.mir00r.todoapp.commons.utils.DateUtil;
import com.mir00r.todoapp.domains.common.models.dtos.BaseDto;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public class ToDoItemDto extends BaseDto {

    @Size(min = 3, max = 255)
    private String name;

    private String description;

    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescriptionShort() {
        if (description == null) return "";
        return description.substring(0, Math.min(50, description.length())) + "..";
    }

    public String getReadableDateTime() {
        return DateUtil.getReadableDateTime(this.date);
    }

    public String getHtmlDateTime() {
        return DateUtil.getHtmlDateFormat().format(this.date);
    }


}
