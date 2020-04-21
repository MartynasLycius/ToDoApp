package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;
import com.kids.crm.todo.repository.TodoRepositoryImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TodoServiceImpl implements TodoService {

    @Inject TodoRepository todoRepository2;

    @Override
    public void create(String name) {
        Todo todo = new Todo();
        todo.setDescription("df");
        todo.setTitle(name);


        TodoRepositoryImpl todoRepository = new TodoRepositoryImpl();
        todoRepository.createTodo(todo);
    }

    @Override
    public List<Todo> fetch() {

        return todoRepository2.fetch();
    }
}
