package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;
import com.kids.crm.todo.repository.TodoRepositoryImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TodoServiceImpl implements TodoService {

    @Inject TodoRepository todoRepository;

    @Override
    public List<Todo> fetch() {
        return todoRepository.fetch();
    }

    @Override
    public void create(Todo todo) {
        todoRepository.createTodo(todo);
    }
}
