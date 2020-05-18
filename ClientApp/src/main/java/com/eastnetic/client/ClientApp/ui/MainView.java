package com.eastnetic.client.ClientApp.ui;


import com.eastnetic.client.ClientApp.model.ToDo;
import com.eastnetic.client.ClientApp.repo.ToDoRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringView(name = MainView.VIEW_NAME)
public class MainView extends CustomComponent implements  View {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "crud";
	
	private final ToDoEditor editor;
	
	final Grid<ToDo> grid;

	final TextField filter;

	private final Button addNewBtn;
	
	private final ToDoRepository todoRepository;
	
	@Autowired
	public MainView(ToDoRepository todoRepository, ToDoEditor editor) {
		this.todoRepository = todoRepository;
		this.editor = editor;
		this.grid = new Grid<>(ToDo.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New ToDo", FontAwesome.PLUS);

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "firstName", "lastName", "task", "phone", "eMail", "creation date");

		filter.setPlaceholder("Filter by task");

		setCompositionRoot(mainLayout);

		// Connect selected Student to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.edittodo(e.getValue());
		});

		// Instantiate and edit new Student the new button is clicked
		addNewBtn.addClickListener(e -> editor.edittodo(new ToDo("", "", "","", "", null)));
		
		editor.save.addClickListener(e -> {
			todoRepository.save(editor.todo);
			listStudents(null);
			editor.setVisible(false);
		});
		
		editor.delete.addClickListener(e -> {
			todoRepository.delete(editor.todo);
			listStudents(null);
			editor.setVisible(false);
		});
		
		editor.cancel.addClickListener(e -> {
			editor.edittodo(editor.todo);
			listStudents(null);
			editor.setVisible(false);
		});

		// Listen changes made by the filter textbox, refresh data from backend
		filter.addValueChangeListener(event -> {
			editor.setVisible(false);
			listStudents(event.getValue());
		});
		// Initialize listing
		listStudents(null);

	}
	
    void listStudents(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(todoRepository.findAll());
		}
		else {
			grid.setItems(todoRepository.findByTaskStartsWithIgnoreCase(filterText));
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
