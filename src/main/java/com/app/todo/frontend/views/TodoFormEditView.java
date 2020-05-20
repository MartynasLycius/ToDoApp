package com.app.todo.frontend.views;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.frontend.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

@Route(value="edit-todo", layout=MainLayout.class)
public class TodoFormEditView extends TodoFormView{

	public TodoFormEditView(TodoService todoService) {
		super(todoService, "Edit TODO");
		
		Todo todo = (Todo) ComponentUtil.getData(UI.getCurrent(), "todo");
		setForm(todo);
		
		add(form);
	}
}
