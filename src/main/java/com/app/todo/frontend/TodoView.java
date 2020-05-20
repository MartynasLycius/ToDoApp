package com.app.todo.frontend;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TodoView extends VerticalLayout{
	protected TodoForm form;
	protected H1 h1;
	
	private TodoService todoService;
	
	public TodoView(TodoService todoService, String header) {
		this.todoService = todoService;
		
		h1 = new H1(header);
	}
	
	protected void setForm(Todo todo) {
		form = new TodoForm();
		form.addListener(TodoForm.SaveEvent.class, this::saveTodo);
		form.addListener(TodoForm.CancelEvent.class, this::cancelTodo);
		form.setTodo(todo);
	}
	
	private void saveTodo(TodoForm.SaveEvent event) {
		todoService.save(event.getTodo());
		getUI().ifPresent(ui -> ui.navigate(MainView.class));
	}
	
	private void cancelTodo(TodoForm.CancelEvent event) {
		getUI().ifPresent(ui -> ui.navigate(MainView.class));
	}

}
