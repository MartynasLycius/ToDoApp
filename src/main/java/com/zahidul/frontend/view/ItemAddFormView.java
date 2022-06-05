package com.zahidul.frontend.view;

import org.apache.commons.lang3.StringUtils;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.zahidul.backend.model.ToDoItem;
import com.zahidul.backend.service.LabelByLocaleService;
import com.zahidul.backend.service.ToDoItemService;
import com.zahidul.frontend.util.FormFieldValidator;
import com.zahidul.frontend.util.LabelKey;
import com.zahidul.frontend.util.Settings;

@PageTitle("Add New Item")
@Route(value = "add", layout = MainLayout.class)
public class ItemAddFormView extends VerticalLayout {

	private ToDoItemService toDoItemService;
	private LabelByLocaleService lableService;
	private Binder<ToDoItem> binder = new Binder<>(ToDoItem.class);
	private ToDoItem item = new ToDoItem();

	private TextField itemName;
	private TextArea itemDescription;
	private DatePicker itemDate;

	@Autowired
	public ItemAddFormView(ToDoItemService toDoItemService, LabelByLocaleService lableService) {

		this.toDoItemService = toDoItemService;
		this.lableService = lableService;
		binder.setBean(item);

		add(createTitle());
		add(createFormLayout());
		add(createActionButtonLayout());
		setAlignItems(Alignment.CENTER);
		setWidth("50%");

		binder.bindInstanceFields(this);
	}

	private Component createTitle() {
		return new H3(lableService.getMessage(LabelKey.HEADER_ITEM_ADD_PAGE));
	}

	private Component createFormLayout() {
		FormLayout formLayout = new FormLayout();

		itemName = new TextField(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_NAME));
		itemDescription = new TextArea(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_DESCRIPTION));
		itemDate = new DatePicker(lableService.getMessage(LabelKey.TEXT_FIELD_ITEM_DATE));

		formLayout.add(itemName);
		formLayout.add(itemDescription);
		formLayout.add(itemDate);

		return formLayout;
	}

	private Component createActionButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addClassName("button-layout");

		Button saveButton = new Button(lableService.getMessage(LabelKey.BUTTON_SAVE), VaadinIcon.CHECK.create());
		saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveButton.addClickListener(e -> {
			boolean save = saveNewItem();
			if (save) {
				saveButton.getUI().ifPresent(ui -> ui.navigate(ItemListView.class));
				setVisible(false);
			}
		});

		Button resetButton = new Button(lableService.getMessage(LabelKey.BUTTON_RESET), VaadinIcon.REFRESH.create());
		resetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		resetButton.addClickListener(e -> clearForm());

		Button cancelButton = new Button(lableService.getMessage(LabelKey.BUTTON_CANCEL),
				VaadinIcon.FILE_REMOVE.create());
		cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancelButton.addClickListener(e -> cancelButton.getUI().ifPresent(ui -> ui.navigate(ItemListView.class)));

		buttonLayout.add(saveButton);
		buttonLayout.add(resetButton);
		buttonLayout.add(cancelButton);
		return buttonLayout;
	}

	private boolean saveNewItem() {
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

	private void clearForm() {
		itemName.setValue(StringUtils.EMPTY);
		itemDescription.setValue(StringUtils.EMPTY);
		itemDate.setValue(null);
	}
}