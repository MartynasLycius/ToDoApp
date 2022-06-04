package com.zahidul.frontend.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.ToDoItemService;

@PageTitle("Add New Item")
@Route(value = "add", layout = MainLayout.class)
public class ItemAddFormView extends VerticalLayout {

	private ToDoItemService toDoItemService;
	private Binder<ToDoItem> binder = new Binder<>(ToDoItem.class);
	private ToDoItem item = new ToDoItem();

	@Autowired
	public ItemAddFormView(ToDoItemService toDoItemService) {

		this.toDoItemService = toDoItemService;
		binder.setBean(item);

		binder.bindInstanceFields(this);
	}
}