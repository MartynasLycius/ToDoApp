package com.todo.web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the todo_item database table.
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 2020-20-05
 */
@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", length = 200, nullable = false)
    private String itemName;

    @Column(name = "description", length = 300)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "target_date", nullable = false)
    private Date targetDate;

    public TodoItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}