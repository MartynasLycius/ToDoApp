package com.zahidul.frontend.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.LabelByLocaleService;
import com.zahidul.backend.service.ToDoItemService;
import com.zahidul.frontend.util.LabelKey;
import com.zahidul.frontend.util.Settings;

@PageTitle("Item List")
@Route(value = "/", layout = MainLayout.class)
public class ItemListView extends VerticalLayout {

	private ToDoItemService toDoItemService;
	private Grid<ToDoItem> grid = new Grid<>(ToDoItem.class);
	private ToDoItem selectedItem;
	private List<ToDoItem> items;
	private LabelByLocaleService lableService;

	@Autowired
	public ItemListView(ToDoItemService toDoItemService, LabelByLocaleService lableService) {

		this.toDoItemService = toDoItemService;
		this.items = toDoItemService.getAllItem();
		this.lableService = lableService;

		addClassName("list-view");
		setSizeFull();

		configureGrid();

		add(getActionLayout(), grid);
		setAlignItems(Alignment.CENTER);
	}

	private void configureGrid() {
		grid.addClassNames("contact-grid");
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumns("itemId", "itemName", "itemDescription", "itemDate");
		grid.setHeight("300px");
		grid.setItems(items);

		grid.setPageSize(1);

		SingleSelect<Grid<ToDoItem>, ToDoItem> personSelect = grid.asSingleSelect();

		personSelect.addValueChangeListener(e -> {
			selectedItem = e.getValue();
		});
	}

	private HorizontalLayout getActionLayout() {
		Button addButton = new Button(lableService.getMessage(LabelKey.BUTTON_ADD_NEW), VaadinIcon.PLUS.create());
		addButton.addClickListener(e -> addButton.getUI().ifPresent(ui -> ui.navigate(ItemAddFormView.class)));

		Button editButton = new Button(lableService.getMessage(LabelKey.BUTTON_EDIT), VaadinIcon.EDIT.create());
		editButton.addClickListener(e -> {

			if (selectedItem == null) {
				showMessage();
			} else {

				String selectedItemId = selectedItem.getItemId().toString();
				editButton.getUI().ifPresent(
						ui -> ui.navigate(ItemEditFormView.class, new RouteParameters("itemId", selectedItemId)));
			}
		});

		Button deleteButton = new Button(lableService.getMessage(LabelKey.BUTTON_DELETE), VaadinIcon.TRASH.create());
		deleteButton.addClickListener(e -> delete());

		HorizontalLayout action = new HorizontalLayout(addButton, editButton, deleteButton);
		return action;
	}

	void delete() {
		if (selectedItem == null) {
			showMessage();
		} else {
			toDoItemService.deleteItem(selectedItem);
			items.remove(selectedItem);
			grid.setItems(items);
		}
	}

	private void showMessage() {
		String errorMessage = lableService.getMessage(LabelKey.ERROR_MESSAGE_ITEM_SELECTION);
		Notification.show(errorMessage, Settings.NOTIFICATION_DEFAULT_DURATION, Position.MIDDLE);
	}

}