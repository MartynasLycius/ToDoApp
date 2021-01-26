package com.proit.todo.data.dto.request;

import com.proit.todo.data.entity.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String todoTitle;
    private String todoDescription;
    private TodoStatus status;
}
