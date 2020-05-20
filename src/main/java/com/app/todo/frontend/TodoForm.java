package com.app.todo.frontend;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.frontend.events.TodoEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class TodoForm extends FormLayout{
	private static final long serialVersionUID = 1L;

	TextField title = new TextField("Title");
	TextField description = new TextField("Description");	
	Binder<Todo> todoBinder = new Binder<>(Todo.class);

	public TodoForm() {
		todoBinder.bindInstanceFields(this);
		
		HorizontalLayout buttonLayout = createButtonLayout();

		add(title, description, buttonLayout);
	}

	public void setTodo(Todo todo) {
		todoBinder.setBean(todo);
	}

	private HorizontalLayout createButtonLayout() {
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");
		
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER); 
		cancel.addClickShortcut(Key.ESCAPE);
		
		save.addClickListener(event -> validateAndSave());
		cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));

		return new HorizontalLayout(save, cancel);		
	}

	private void validateAndSave() {
		if(todoBinder.isValid()) {
			fireEvent(new SaveEvent(this, todoBinder.getBean()));
		}
	}

	public static class SaveEvent extends TodoEvent {

		protected SaveEvent(TodoForm source, Todo todo) {
			super(source, todo);
		}	
	}

	public static class CancelEvent extends TodoEvent {
		CancelEvent(TodoForm source) {
			super(source, null);
		}
	}
	
	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
}


