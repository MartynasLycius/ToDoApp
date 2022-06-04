package com.zahidul.frontend.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.ToDoItemService;

@PageTitle("Item List")
@Route(value = "items", layout = MainLayout.class)
public class ItemListView extends VerticalLayout {

	private ToDoItemService toDoItemService;
	private Grid<ToDoItem> grid = new Grid<>(ToDoItem.class);
	private ToDoItem selectedItem;
	private List<ToDoItem> items;

	@Autowired
	public ItemListView(ToDoItemService toDoItemService) {

		this.toDoItemService = toDoItemService;
		this.items = toDoItemService.getAllItem();

	}
}