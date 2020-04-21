package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TodoServiceImpl implements TodoService {

    @Override
    public void create(String name) {
        Todo todo = new Todo();
        todo.setDescription("df");
        todo.setTitle(name);


        TodoRepository todoRepository = new TodoRepository();
        todoRepository.createTodo(todo);
    }

    @Override
    public List<Todo> fetch() {
        TodoRepository todoRepository = new TodoRepository();
        return todoRepository.fetch();
    }
}
