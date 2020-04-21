package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@TransactionAttribute
@Local
public interface TodoRepository {

    void createTodo(Todo todo);
    List<Todo> fetch();


}
