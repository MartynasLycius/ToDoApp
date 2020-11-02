package com.seal.todoapp.com.seal.todoapp.service;

import com.seal.todoapp.com.seal.todoapp.entity.Todo;
import com.seal.todoapp.com.seal.todoapp.model.TodoListResponse;
import com.seal.todoapp.com.seal.todoapp.model.TodoRequest;
import com.seal.todoapp.com.seal.todoapp.model.TodoResponse;
import com.seal.todoapp.com.seal.todoapp.repo.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TodoAppService {

    private final TodoRepository todoRepository;

    public List<TodoListResponse> list() {
        return todoRepository.findAll()
                .stream()
                .map(TodoListResponse::new)
                .collect(toList());
    }

    public TodoResponse getOne(Long id) {
        return getTodo(id, TodoResponse::new);
    }

    public TodoResponse create(TodoRequest request) {
        Todo todo = TodoMapper.todoRequestToTodo(request, new Todo());
        Todo save = todoRepository.save(todo);
        return new TodoResponse(save);
    }

    public TodoResponse edit(TodoRequest request) {
        return updateTodo(request, todo -> TodoMapper.todoRequestToTodo(request, todo));
    }

    public TodoResponse markComplete(TodoRequest request) {
        return updateTodo(request, this::markComplete);
    }

    private TodoResponse updateTodo(TodoRequest request, Function<Todo, Todo> mapper) {
        Todo todo = getTodo(request.getId(), mapper);
        Todo update = todoRepository.save(todo);
        return new TodoResponse(update);
    }

    private <T> T getTodo(Long id, Function<Todo, T> mapper) {
        return todoRepository.findById(id)
                .map(mapper)
                .orElseThrow(EntityNotFoundException::new);
    }

    private Todo markComplete(Todo todo) {
        todo.setComplete(true);
        return todo;
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

}
