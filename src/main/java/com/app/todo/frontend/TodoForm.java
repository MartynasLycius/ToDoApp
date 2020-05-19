package com.app.todo.frontend;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.backend.service.impl.TodoServiceImpl;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TodoForm extends FormLayout{
	private static final long serialVersionUID = 1L;
	
	TextField title = new TextField("Title");
	TextField description = new TextField("Description");
	
	Binder<Todo> todoBinder = new Binder<>(Todo.class);
	
	Button save = new Button("Save");
	Button cancel = new Button("Cancel");
	
	TodoService service;
	
	public TodoForm(TodoService service) {
		this.service = service;
		todoBinder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		
		add(title,
			description,
			createButtonLayout());
	}
	
	public void setTodo(Todo todo) {
		todoBinder.setBean(todo);
	}
	
	private HorizontalLayout createButtonLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

	    save.addClickShortcut(Key.ENTER); 
	    cancel.addClickShortcut(Key.ESCAPE);
		
		return new HorizontalLayout(save, cancel);
		
	}
	
	private void save() {
		Todo todo = todoBinder.getBean();
		System.out.println(this);
		System.out.println(todo);

		service.save(todo);
	}
}
