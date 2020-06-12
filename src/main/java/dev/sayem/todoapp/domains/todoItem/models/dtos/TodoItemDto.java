package dev.sayem.todoapp.domains.todoItem.models.dtos;

import dev.sayem.todoapp.commons.utils.DateUtil;
import dev.sayem.todoapp.domains.common.base.models.dtos.BaseDto;

import javax.validation.constraints.Size;
import java.util.Date;

public class TodoItemDto extends BaseDto {
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

    public String getDescriptionShort() {
        if (description == null) return "";
        return description.substring(0, Math.min(50, description.length()))+"..";
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

    public String getReadableDateTime() {
        return DateUtil.getReadableDateTime(this.date);
    }

    public String getHtmlDateTime() {
        return DateUtil.getHtmlDateFormat().format(this.date);
    }

}
