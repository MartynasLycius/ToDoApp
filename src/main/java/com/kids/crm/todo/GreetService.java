package com.kids.crm.todo;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;
import com.kids.crm.todo.service.TodoService;
import com.vaadin.cdi.annotation.VaadinSessionScoped;

import javax.inject.Inject;

/**
 * Data provider bean scoped for each user session.
 */
@VaadinSessionScoped
public class GreetService {

    @Inject
    TodoService todoService;

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {

            return "Hello " + name;
        }
    }
}
