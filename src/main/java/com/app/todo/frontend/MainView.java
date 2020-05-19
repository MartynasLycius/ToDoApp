package com.app.todo.frontend;


import com.app.todo.backend.entity.Todo;
import com.app.todo.backend.service.TodoService;
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
	private TodoForm form;
	private TodoService todoService;

	public MainView(TodoService todoService) {
		this.todoService = todoService;
		
		setSizeFull();
		configureGrid();
		
		newButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		form = new TodoForm(todoService);
		form.setTodo(new Todo());
		
		add(newButton, new Div(grid, form));
		
		updateList();
        
    }
	
	private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("title", "description", "datetime");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
//        grid.asSingleSelect().addValueChangeListener(event -> 
//        editTodo(event.getValue()));
    }
	
	private void updateList() {
		System.out.println(todoService.getAll().size());
        grid.setItems(todoService.getAll());
    }
	
//	private void editTodo(Todo todo) {
//		form.setTodo(todo);
//	}

}
