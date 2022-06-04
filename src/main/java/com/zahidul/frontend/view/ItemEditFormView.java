package com.zahidul.frontend.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.LabelByLocaleService;
import com.zahidul.backend.service.ToDoItemService;
import com.zahidul.frontend.util.FormFieldValidator;
import com.zahidul.frontend.util.LabelKey;
import com.zahidul.frontend.util.Settings;

@PageTitle("Edit Item")
@Route(value = "edit/:itemId", layout = MainLayout.class)
public class ItemEditFormView extends VerticalLayout implements BeforeEnterObserver {

	private String itemId;

	@Autowired
	private ToDoItemService toDoItemService;

	@Autowired
	private LabelByLocaleService lableService;

	private TextField itemName;
	private TextArea itemDescription;
	private DatePicker itemDate;

	private Binder<ToDoItem> binder = new Binder<>(ToDoItem.class);
	private ToDoItem item;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		itemId = event.getRouteParameters().get("itemId").get();
		item = toDoItemService.getItemById(Long.parseLong(itemId));
		binder.setBean(item);

		add(createTitle());
		add(createFormLayout());
		add(createActionButtonLayout());
		setAlignItems(Alignment.CENTER);
		setWidth("50%");

		binder.bindInstanceFields(this);
	}

	private Component createTitle() {
		return new H3(lableService.getMessage(LabelKey.HEADER_ITEM_EDIT_PAGE));
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();

		itemName = new TextField(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_NAME));
		itemDescription = new TextArea(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_DESCRIPTION));
		itemDate = new DatePicker(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_DATE));

		formLayout.add(itemName, itemDescription, itemDate);
		return formLayout;
	}

	private Component createActionButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addClassName("button-layout");

		Button updateButton = new Button(lableService.getMessage(LabelKey.BUTTON_UPDATE), VaadinIcon.CHECK.create());
		updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		updateButton.addClickListener(e -> {
			boolean update = updateItem();
			if (update) {
				updateButton.getUI().ifPresent(ui -> ui.navigate(ItemListView.class));
				setVisible(false);
			}
		});

		Button cancelButton = new Button(lableService.getMessage(LabelKey.BUTTON_CANCEL),
				VaadinIcon.FILE_REMOVE.create());
		cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancelButton.addClickListener(e -> cancelButton.getUI().ifPresent(ui -> ui.navigate(ItemListView.class)));

		buttonLayout.add(updateButton);
		buttonLayout.add(cancelButton);

		return buttonLayout;
	}

	private boolean updateItem() {
		ToDoItem item = binder.getBean();
		if (item == null || FormFieldValidator.hasEmptyField(item)) {
			String errorMessage = lableService.getMessage(LabelKey.ERROR_MESSAGE_EMPTY_FIELD);
			Notification.show(errorMessage, Settings.NOTIFICATION_DEFAULT_DURATION, Position.MIDDLE);
			return Boolean.FALSE;
		} else {
			toDoItemService.saveItem(item);
			return Boolean.TRUE;
		}
	}
}
