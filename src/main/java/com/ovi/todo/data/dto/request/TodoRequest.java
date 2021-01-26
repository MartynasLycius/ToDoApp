package com.ovi.todo.data.dto.request;

import com.ovi.todo.data.entity.TodoStatus;
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
