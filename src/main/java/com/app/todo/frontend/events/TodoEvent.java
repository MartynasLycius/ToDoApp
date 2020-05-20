package com.app.todo.frontend.events;

import com.app.todo.backend.entity.Todo;
import com.app.todo.frontend.components.TodoForm;
import com.vaadin.flow.component.ComponentEvent;

public abstract class TodoEvent extends ComponentEvent<TodoForm> {
	private static final long serialVersionUID = 1L;
	
	private Todo todo;

	protected TodoEvent(TodoForm source, Todo todo) {
		super(source, false);
		this.todo = todo;
	}
	
	public Todo getTodo() {
		return todo;
	}

}


