package com.pathan.todo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pathan on 30-Jan-21.
 */

@Entity @Table(name="todos")
@NoArgsConstructor @Setter @Getter @ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String taskName;
    private String description;
    private Date targetDate;
    @Transient
    private String targateDateStr;
    private  String createdBy;
    private Date created;

    public Todo(String taskName, String description, Date targetDate, String createdBy, Date created) {
        this.taskName = taskName;
        this.description = description;
        this.targetDate = targetDate;
        this.createdBy = createdBy;
        this.created = created;
    }
}
