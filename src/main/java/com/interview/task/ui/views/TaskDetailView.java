package com.interview.task.ui.views;

import org.springframework.core.env.Environment;
import org.vaadin.klaudeta.PaginatedGrid;

import com.interview.task.models.TodoItem;
import com.interview.task.services.TodoServices;
import com.interview.task.ui.customizedComponents.TinyButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;

public class TaskDetailView extends VerticalLayout {

	TodoServices todoServices;
	TextField taskName;
	TextArea description;
	DateTimePicker tobePerformedDateTime, createdDateTime;
	ComboBox<String> completeCombo;
	Dialog formDialog;
	Button save, close;
	TinyButton edit;
	Icon editIcon, readIcon;
	TodoItem todoItem;
	PaginatedGrid<TodoItem> todoGrid;
	Binder<TodoItem> binder;
	Environment env;
	
	public TaskDetailView(Dialog formDialog, TodoItem todoItem, PaginatedGrid<TodoItem> todoGrid, TodoServices todoServices, Environment env) {
		addClassName("task-form");
		this.env = env;
		this.todoServices = todoServices;
		this.formDialog = formDialog;
		this.todoItem = todoItem;
		this.todoGrid = todoGrid;
		
		configureTaskName();
		configureDescription();
		configureTobePerformed();
		configureCreated();
		configureCompleteCombo();
		addInputValidation();
		
		add(configureEditButton(), taskName, description, completeCombo, tobePerformedDateTime, createdDateTime, getFooterLayout());
	}
	
	private void configureCompleteCombo() {
		completeCombo = new ComboBox<>();
		completeCombo.setItems("Yes", "No");
		completeCombo.setValue(todoItem.isComplete() ? "Yes" : "No");
		completeCombo.setLabel("Complete");
		completeCombo.setReadOnly(true);
		completeCombo.addValueChangeListener(e -> {
			if (completeCombo.getValue().equals("Yes")) {
				tobePerformedDateTime.setReadOnly(true);
			} else {
				tobePerformedDateTime.setReadOnly(false);
			}
		});
	}
	
	private void configureTaskName() {
		taskName = new TextField("Task name");
		taskName.setWidth("375px");
		taskName.setValue(todoItem.getTaskName());
		taskName.setReadOnly(true);
        taskName.setRequiredIndicatorVisible(true);
	}
	
	private TinyButton configureEditButton() {
		editIcon = new Icon(VaadinIcon.EDIT);
		editIcon.setColor("green");
		
		readIcon = new Icon(VaadinIcon.FILE_TEXT);
		readIcon.setColor("green");
		
		edit = new TinyButton();
		edit.setIcon(editIcon);
		edit.setToolTip(env.getProperty("tooltip.edit"));
		edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		edit.addClickListener(e -> toggleEdit());
		edit.getElement().getStyle().set("margin-left", "auto");
		return edit;
	}
	
	private void configureDescription() {
		description = new TextArea("Description");
		description.setWidth("375px");;
		description.setValue(todoItem.getDescription());
        description.setRequiredIndicatorVisible(true);
        description.setReadOnly(true);
	}
	
	private void configureTobePerformed() {
		tobePerformedDateTime = new DateTimePicker("To be performed");
        tobePerformedDateTime.setDatePlaceholder("Date");
        tobePerformedDateTime.setTimePlaceholder("Time");
        tobePerformedDateTime.setValue(todoItem.getDateToBePerformed());
        tobePerformedDateTime.setRequiredIndicatorVisible(true);
        tobePerformedDateTime.setReadOnly(true);
	}
	
	private void configureCreated() {
		createdDateTime = new DateTimePicker("Created On");
		createdDateTime.setDatePlaceholder("Date");
		createdDateTime.setTimePlaceholder("Time");
		createdDateTime.setValue(todoItem.getDateCreated());
		createdDateTime.setReadOnly(true);
	}
	
	private void toggleEdit() {
		if (!edit.hasClassName("read")) {
			edit.setToolTip(env.getProperty("tooltip.read"));
			edit.setIcon(readIcon);
			edit.addClassName("read");
		} else {
			edit.setToolTip(env.getProperty("tooltip.edit"));
			edit.setIcon(editIcon);
			edit.removeClassName("read");
		}
		save.setEnabled(!save.isEnabled());
		taskName.setReadOnly(!taskName.isReadOnly());
		description.setReadOnly(!description.isReadOnly());
		tobePerformedDateTime.setReadOnly(!tobePerformedDateTime.isReadOnly() || todoItem.isComplete());
		completeCombo.setReadOnly(!completeCombo.isReadOnly());
	}
	
	private HorizontalLayout getFooterLayout() {
		save = new Button("Save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.setEnabled(false);
		save.addClickListener(e -> saveTodoTask());
	    
		close = new Button("Close");
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		close.getStyle().set("background", "red");
		close.addClickListener(e -> formDialog.close());
	    
	    return new HorizontalLayout(save, close); 
	  }
	
	private void addInputValidation() {
		binder = new Binder<>();
		binder.forField(taskName)
				.withValidator(new StringLengthValidator(env.getProperty("task.name.validation"), 1, 30))
				.bind(TodoItem::getTaskName, TodoItem::setTaskName);

		binder.forField(description)
        		.withValidator(new StringLengthValidator(env.getProperty("description.length.validation"), 0, 150))
        		.bind(TodoItem::getDescription, TodoItem::setDescription);

		binder.forField(tobePerformedDateTime)
				.asRequired(env.getProperty("perform.time.validation"))
				.bind(TodoItem::getDateToBePerformed,TodoItem::setDateToBePerformed);
	}
	
	private void saveTodoTask() {
		todoItem.setTaskName(taskName.getValue());
		todoItem.setDescription(description.getValue());
		todoItem.setDateToBePerformed(tobePerformedDateTime.getValue());
		todoItem.setComplete(completeCombo.getValue().equals("Yes"));

		if (binder.writeBeanIfValid(todoItem)) {
			todoServices.updateTodoItem(todoItem);
			todoGrid.getDataProvider().refreshItem(todoItem);
			formDialog.close();
		}
	}
}
