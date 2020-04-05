package com.rali.dto.transformer;


import com.rali.dto.TodoDto;
import com.rali.entity.Todo;
import com.rali.exception.ApiException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class TodoDtoTransformer {

    public TodoDto getToDoDto(Todo todo) {

        TodoDto todoDto = new TodoDto();
        try {
            BeanUtils.copyProperties(todoDto, todo);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return todoDto;
    }

    public List<TodoDto> getToDoDos(List<Todo> todoList) {

        List<TodoDto> todoDtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            todoDtoList.add(getToDoDto(todo));
        }
        return todoDtoList;
    }

    public Todo getToDo(TodoDto todoDto) {

        Todo todo = new Todo();
        try {
            BeanUtils.copyProperties(todo, todoDto);

        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return todo;
    }

}
