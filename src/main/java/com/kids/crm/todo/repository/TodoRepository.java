package com.kids.crm.todo.repository;

import com.kids.crm.todo.model.Todo;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;

@TransactionAttribute
@Local
public interface TodoRepository {

    void createTodo(Todo todo);
    List<Todo> fetch();


    void delete(Todo todo);

    Optional<Todo> findById(Long id);

    Todo update(Todo todo);
}
