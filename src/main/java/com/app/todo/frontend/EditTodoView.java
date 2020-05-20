package com.app.todo.frontend;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

@Route("edit-todo")
public class EditTodoView extends TodoView{

	public EditTodoView(TodoService todoService) {
		super(todoService, "Edit TODO");
		
		Todo todo = (Todo) ComponentUtil.getData(UI.getCurrent(), "todo");
		setForm(todo);
		
		add(h1, form);
	}
}
