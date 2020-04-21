package com.kids.crm.todo.service;

import com.kids.crm.todo.model.Todo;

import java.util.Arrays;
import java.util.List;

public interface TodoService {
    void create(String name);

    List<Todo> fetch();

}
