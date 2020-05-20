package com.app.todo.frontend;


import java.time.format.DateTimeFormatter;

import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private Grid<Todo> grid = new Grid<>(Todo.class);
	private Button newButton = new Button("Add new todo");
	
	private TodoService todoService;

	public MainView(TodoService todoService) {
		this.todoService = todoService;
		
		setSizeFull();
		configureGrid();
		
		newButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		newButton.addClickListener(event -> newButton.getUI().ifPresent(ui -> ui.navigate(TodoSaveView.class)));
				
		add(newButton, grid);
		
		updateList();
        
    }
	
	private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title", "description");
        grid.addColumn(this::getCreatedTime).setHeader("Created");
        grid.addComponentColumn(this::buildDeleteButton);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(event -> {
        	ComponentUtil.setData(UI.getCurrent(), "todo", event.getValue());
        	this.getUI().ifPresent(ui -> ui.navigate(TodoEditView.class));
        });
    }
	
	private void updateList() {
        grid.setItems(todoService.getAll());
    }
	
	private void delete(Todo todo) {
		todoService.delete(todo);
		updateList();
	}
	
	private Button buildDeleteButton(Todo todo) {
		Button button = new Button("Delete");
		button.addThemeVariants(ButtonVariant.LUMO_ERROR);
		button.addClickListener(event -> delete(todo));
		
		return button;
	}
	
	private String getCreatedTime(Todo todo) {
		return todo.getDatetime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));		
	}
}
