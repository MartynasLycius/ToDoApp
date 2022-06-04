package com.zahidul.frontend.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.ToDoItemService;

@PageTitle("Edit Item")
@Route(value = "edit/:itemId", layout = MainLayout.class)
public class ItemEditFormView extends VerticalLayout implements BeforeEnterObserver {

	private String itemId;

	@Autowired
	private ToDoItemService toDoItemService;

	private Binder<ToDoItem> binder = new Binder<>(ToDoItem.class);
	private ToDoItem item;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		itemId = event.getRouteParameters().get("itemId").get();
		item = toDoItemService.getItemById(Long.parseLong(itemId));
		binder.setBean(item);

		binder.bindInstanceFields(this);
	}
}
