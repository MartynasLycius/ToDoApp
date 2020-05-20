package com.app.todo.frontend.views;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.frontend.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="save-todo", layout=MainLayout.class)
public class TodoFormSaveView extends TodoFormView{
	
	public TodoFormSaveView(TodoService todoService) {
		super(todoService,"Add TODO");
		
		Todo todo = new Todo();						
		setForm(todo);
		
		add(form);
	}
	
	
	
	
}
