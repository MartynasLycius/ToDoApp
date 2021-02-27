package com.opt.bus.todo.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.dto.ToDoDto;
import com.opt.bus.service.IToDoService;
import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class ToDoList extends VerticalLayout {

	private static final long serialVersionUID = 136546464828996327L;

	@Inject
	private IToDoService  iToDoService;

	private final Grid<ToDoDto> todos = new Grid<>(ToDoDto.class);

	@PostConstruct
	public void ToDoListView() {
	
		H1 heading = new H1("Todo Item List");
		Button update = new Button(VaadinIcon.REFRESH.create());
		RouterLink customerView = new RouterLink("Submit new Customer", ToDoNew.class);
		add(heading, update, todos, customerView);
		
		todos.setColumns("id", "name", "date","description","delete","edit");
		
		
		  todos.addComponentColumn(todoDto -> { Button deleteBtn = new
		  Button(VaadinIcon.TRASH.create()); deleteBtn.addClickListener(e -> {
		  
		  try { iToDoService.delete(todoDto.getId()); } catch (BusinessException e1) {
		  
		  
		  }
		  
		  getToDoList(); }); return deleteBtn; });
		  
		  
		  todos.addComponentColumn(todoDto -> { Button editBtn = new
		  Button(VaadinIcon.EDIT.create()); editBtn.addClickListener(e -> {
		  
		  UI.getCurrent().navigate(ToDoEdit.class, todoDto.getId().toString());
		  
		  }); return editBtn; });
		 
		
		getToDoList();

		//update.addClickListener(e -> getToDoList());

	}

	public void getToDoList() {

		try {
			List<ToDoDto> toDoDtos = iToDoService.getToDoList();
			
			todos.setItems(toDoDtos);
		} catch (NoDataException e) {
			
		}
	}

	
}
