package com.vaadin.todo.entity;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "todo_item")
public class ToDoItem extends AbstractEntity implements Cloneable {

    @NotNull
    @NotEmpty
    @SafeHtml
    private String itemName = "";

    @NotNull
    @NotEmpty
    @SafeHtml
    @Column(columnDefinition="TEXT")
    private String description = "";


    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
