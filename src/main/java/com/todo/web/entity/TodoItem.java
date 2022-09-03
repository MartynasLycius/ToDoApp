package com.todo.web.entity;

import lombok.Data;

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
@Data
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
}