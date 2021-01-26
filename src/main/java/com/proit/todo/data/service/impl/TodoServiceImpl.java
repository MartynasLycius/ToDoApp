package com.proit.todo.data.service.impl;

import com.proit.todo.data.utils.TodoUtils;
import com.proit.todo.data.dto.request.TodoRequest;
import com.proit.todo.data.dto.response.TodoResponse;
import com.proit.todo.data.entity.Todo;
import com.proit.todo.data.exception.ApiException;
import com.proit.todo.data.repository.TodoRepository;
import com.proit.todo.data.service.contact.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    TodoRepository todoRepository;

    public TodoServiceImpl(@Autowired TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoResponse> getTodoList() {
        List<TodoResponse> result = new ArrayList<>();
        for (int i = 0; i < todoRepository.findAll().size(); i++) {
            TodoResponse todoResponse = new TodoResponse();
            BeanUtils.copyProperties(todoRepository.findAll().get(i), todoResponse);
            result.add(todoResponse);
        }
        return result;
    }

    @Override
    public TodoResponse save(TodoRequest payload) {
        log.info("Todo Save");
        Todo todo = new Todo();
        BeanUtils.copyProperties(payload, todo);
        todo.setTodoId(TodoUtils.todoIdGenerator());
        Todo result = todoRepository.save(todo);
        TodoResponse response = new TodoResponse();
        BeanUtils.copyProperties(result, response);
        return response;
    }

    @Override
    public TodoResponse update(String todoId, TodoRequest payload) {
        log.info("Todo Update");
        Todo todo = getTodo(todoId);
        BeanUtils.copyProperties(payload, todo);
        todo.setTodoStatus(payload.getStatus());
        Todo result = todoRepository.save(todo);
        TodoResponse response = new TodoResponse();
        BeanUtils.copyProperties(result, response);
        return response;
    }

    @Override
    public void deleteTodoItem(String id) {
        log.info("Todo Delete");
        Todo todo = getTodo(id);
        todoRepository.delete(todo);
    }

    public TodoResponse getTodoById(String id) {
        log.info("fetching todo with id: " + id);
        Todo todo = todoRepository.getTodoByTodoId(id).orElseThrow(() -> new ApiException(HttpStatus.NO_CONTENT));
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    private Todo getTodo(String id) {
        return todoRepository.getTodoByTodoId(id).orElseThrow(() -> new ApiException(HttpStatus.NO_CONTENT));
    }
}
