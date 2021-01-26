package com.proit.todo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends AbstractEntity {

    private String todoId;
    @NotNull(message = "Title must not be null.")
    private String todoTitle;
    private String todoDescription;
    private TodoStatus todoStatus = TodoStatus.ACTIVE;
    private Date dateCreated = new Date();
    private Date dateUpdated;
}
