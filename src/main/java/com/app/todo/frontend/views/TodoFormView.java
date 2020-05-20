package com.app.todo.frontend.views;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.frontend.components.TodoForm;
import com.app.todo.frontend.components.TodoForm.CancelEvent;
import com.app.todo.frontend.components.TodoForm.SaveEvent;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TodoFormView extends VerticalLayout{
	protected TodoForm form;
	
	private TodoService todoService;
	
	public TodoFormView(TodoService todoService, String header) {
		this.todoService = todoService;
		
		add(new H2(header));
	}
	
	protected void setForm(Todo todo) {
		form = new TodoForm();
		form.addListener(TodoForm.SaveEvent.class, this::saveTodo);
		form.addListener(TodoForm.CancelEvent.class, this::cancelTodo);
		form.setTodo(todo);
	}
	
	private void saveTodo(TodoForm.SaveEvent event) {
		todoService.save(event.getTodo());
		getUI().ifPresent(ui -> ui.navigate(TodoListView.class));
	}
	
	private void cancelTodo(TodoForm.CancelEvent event) {
		getUI().ifPresent(ui -> ui.navigate(TodoListView.class));
	}

}
