package com.app.todo.frontend;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("save-todo")
public class SaveTodoView extends TodoView{
	
	public SaveTodoView(TodoService todoService) {
		super(todoService,"Add TODO");
		
		Todo todo = new Todo();						
		setForm(todo);
		
		add(h1, form);
	}
	
	
	
	
}
