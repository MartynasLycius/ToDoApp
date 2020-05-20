package com.app.todo.frontend.views;


import java.time.format.DateTimeFormatter;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.app.todo.frontend.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="", layout=MainLayout.class)
public class TodoListView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private Grid<Todo> grid;
	
	private TodoService todoService;

	public TodoListView(TodoService todoService) {
		this.todoService = todoService;
				
		setSizeFull();
		add(buildAddButton(), buildGrid());
		
		updateList();        
    }
	
	private Button buildAddButton() {
		Button button = new Button("Add new todo");
		button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		button.addClickListener(event -> button.getUI().ifPresent(ui -> ui.navigate(TodoFormSaveView.class)));
		
		return button;
	}
	
	private Grid<Todo> buildGrid() {
		grid = new Grid<>(Todo.class);
        grid.setSizeFull();
        grid.setColumns("title", "description");
        grid.addColumn(this::getFormattedTime).setHeader("Created");
        grid.addComponentColumn(this::configureDeleteButton);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(event -> {
        	ComponentUtil.setData(UI.getCurrent(), "todo", event.getValue());
        	this.getUI().ifPresent(ui -> ui.navigate(TodoFormEditView.class));
        });
        
        return grid;
    }
	
	private void updateList() {
        grid.setItems(todoService.getAll());
    }
	
	private void deleteItem(Todo todo) {
		todoService.delete(todo);
		updateList();
	}
	
	private Button configureDeleteButton(Todo todo) {
		Button button = new Button("Delete");
		button.addThemeVariants(ButtonVariant.LUMO_ERROR);
		button.addClickListener(event -> deleteItem(todo));
		
		return button;
	}
	
	private String getFormattedTime(Todo todo) {
		return todo.getDatetime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));		
	}
}
