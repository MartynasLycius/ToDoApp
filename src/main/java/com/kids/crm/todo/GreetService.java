package com.kids.crm.todo;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.repository.TodoRepository;
import com.vaadin.cdi.annotation.VaadinSessionScoped;

/**
 * Data provider bean scoped for each user session.
 */
@VaadinSessionScoped
public class GreetService {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            Todo todo = new Todo();
            todo.setDescription("df");
            todo.setTitle(name);


            TodoRepository todoRepository = new TodoRepository();
            todoRepository.createTodo(todo);
            return "Hello " + name;
        }
    }
}
