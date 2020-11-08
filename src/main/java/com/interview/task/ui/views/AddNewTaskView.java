package com.interview.task.ui.views;

import org.springframework.core.env.Environment;

import com.interview.task.models.TodoItem;
import com.interview.task.services.TodoServices;
import com.interview.task.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "addTask", layout = MainView.class)
@PageTitle("Add New Task")
@CssImport("./styles/views/addnewtask/add-new-task-view.css")
public class AddNewTaskView extends VerticalLayout {

	TodoServices todoServices;
	Environment env;
	
	TodoItem todoItem;
	Binder<TodoItem> binder;
	FormLayout layoutWithBinder;
	HorizontalLayout formWrapper, actionButtonLayout;
	TextField taskName;
	TextArea description;
	DateTimePicker tobePerformedDateTime;
	Button add, reset;

	public AddNewTaskView(TodoServices todoServices, Environment env) {
		setId("add-new-task-view");
		this.todoServices = todoServices;
		this.env = env;
		
		initializeAll();
		addInputValidation();
		addInputElementsToForm();
		adjustFormInputElements();
		defineButtonActions();

		add(formWrapper, actionButtonLayout);
		setJustifyContentMode(JustifyContentMode.CENTER);
	}

	private void initializeAll() {
		binder = new Binder<>();
		todoItem = new TodoItem();

		taskName = new TextField();
		taskName.setWidth("375px");
		taskName.setRequiredIndicatorVisible(true);
		
		description = new TextArea();
		description.setWidth("375px");
		description.setRequiredIndicatorVisible(true);

		tobePerformedDateTime = new DateTimePicker();
		tobePerformedDateTime.setDatePlaceholder("Date");
		tobePerformedDateTime.setTimePlaceholder("Time");
		tobePerformedDateTime.setRequiredIndicatorVisible(true);
		
		layoutWithBinder = new FormLayout();
		layoutWithBinder.setWidth("500px");

		formWrapper = new HorizontalLayout();

		add = new Button("Add", new Icon(VaadinIcon.INSERT));
		add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		add.getStyle().set("marginRight", "10px");

		reset = new Button("Reset", new Icon(VaadinIcon.REFRESH));
		reset.addThemeVariants(ButtonVariant.LUMO_ERROR);

		actionButtonLayout = new HorizontalLayout();
		actionButtonLayout.add(add, reset);
		actionButtonLayout.setJustifyContentMode(JustifyContentMode.CENTER);

	}

	private void addInputElementsToForm() {
		layoutWithBinder.addFormItem(taskName, "Task name").getElement().setAttribute("colspan", "2");
		layoutWithBinder.addFormItem(description, "Description").getElement().setAttribute("colspan", "2");
		layoutWithBinder.addFormItem(tobePerformedDateTime, "To Be Performed");
	}

	private void adjustFormInputElements() {
		formWrapper.getStyle().set("marginTop", "10%");
		formWrapper.add(layoutWithBinder);
		formWrapper.setJustifyContentMode(JustifyContentMode.CENTER);
	}

	private void addInputValidation() {
		binder.forField(taskName)
				.withValidator(
						new StringLengthValidator(env.getProperty("task.name.validation"), 1, 30))
				.bind(TodoItem::getTaskName, TodoItem::setTaskName);

		binder.forField(description)
				.withValidator(
						new StringLengthValidator(env.getProperty("description.length.validation"), 0, 150))
				.bind(TodoItem::getDescription, TodoItem::setDescription);

		binder.forField(tobePerformedDateTime).asRequired(env.getProperty("perform.time.validation"))
				.bind(TodoItem::getDateToBePerformed, TodoItem::setDateToBePerformed);
	}

	private void defineButtonActions() {
		add.addClickListener(event -> {
			if (binder.writeBeanIfValid(todoItem)) {
				TodoItem addedItem = todoServices.addTodoItem(todoItem);
				Notification.show("New task: " + addedItem.getTaskName() + " added successfully!");
				binder.readBean(null);
				todoItem = new TodoItem();
			}
		});

		reset.addClickListener(event -> {
			binder.readBean(null);
		});
	}
}
