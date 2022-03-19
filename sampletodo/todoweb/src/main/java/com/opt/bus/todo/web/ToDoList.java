/*****************************************************************************************************************
 *
 *	 File			 : ToDoList.java
 *
  *****************************************************************************************************************/


package com.opt.bus.todo.web;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.opt.bus.dto.ToDoDto;
import com.opt.bus.service.IToDoService;
import com.opt.exception.BusinessException;
import com.opt.exception.NoDataException;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/*
 * This class is  ToDoList class   which is  used for showing todo list & deleting todo item .
 * 
 */
@Route(value = "toDoItemList")
public class ToDoList extends VerticalLayout {

	private static final long serialVersionUID = 136546464828996327L;

	@Inject
	private IToDoService iToDoService;
	
	private static final Logger lOGGER = Logger.getLogger(ToDoList.class.getName());

	private final Grid<ToDoDto> todos = new Grid<>(ToDoDto.class);

	@PostConstruct
	public void ToDoListView() {
		setHeight("700px");
		setClassName("auto-margin");

		H3 heading = new H3("Todo Item List");
		Button refreshBtn = new Button(VaadinIcon.REFRESH.create());
		RouterLink addNewToDoLink = new RouterLink("New ToDo", ToDoNew.class);
		add(heading, addNewToDoLink, refreshBtn, todos);

		todos.setColumns("id", "itemName", "productionDate", "itemDescription", "delete", "edit");

		todos.addComponentColumn(todoDto -> {
			Button deleteBtn = new Button(VaadinIcon.TRASH.create());
			deleteBtn.addClickListener(e -> {

				try {
					iToDoService.delete(todoDto.getId());
					lOGGER.log(Level.INFO, "toDo deleted."+todoDto.getId());
				} catch (BusinessException be) {
					lOGGER.log(Level.SEVERE, "Error occurd in delete.", be.getMessage());

				}
				getToDoList();
			});
			return deleteBtn;
		});

		todos.addComponentColumn(todoDto -> {
			Button editBtn = new Button(VaadinIcon.EDIT.create());
			editBtn.addClickListener(e -> {
				UI.getCurrent().navigate(ToDoEdit.class, todoDto.getId());
			});
			return editBtn;
		});
		getToDoList();
		refreshBtn.addClickListener(e -> getToDoList());
	}

	public void getToDoList() {

		try {
			List<ToDoDto> toDoDtos = iToDoService.getToDoList();
			todos.setItems(toDoDtos);
		} catch (NoDataException ne) {
			lOGGER.log(Level.SEVERE, "No toDo found.", ne.getMessage());
		}
	}

}
