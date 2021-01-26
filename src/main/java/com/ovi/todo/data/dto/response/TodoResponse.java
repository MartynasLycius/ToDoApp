package com.ovi.todo.data.dto.response;

import com.ovi.todo.data.entity.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {

    private String todoId;
    private String todoTitle;
    private String todoDescription;
    private TodoStatus todoStatus;
    private Date dateCreated = new Date();
    private Date dateUpdated;
}
