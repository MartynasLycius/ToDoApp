package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Stateless
public class TodoServiceImpl implements TodoService {

    @Inject TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.fetch();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void create(Todo todo) {
        if (StringUtils.isEmpty(todo.getTitle())) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        todoRepository.createTodo(todo);
    }

    @Override
    public void delete(Todo todo) {
        findById(todo.getId())
                .ifPresent(todo1 -> todoRepository.delete(todo1));

    }

    @Override
    public void update(Todo todo) {
        if (todo.getCompletionDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Future date has to be selected");
        }
        todoRepository.update(todo);
    }
}
