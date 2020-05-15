package com.martynaslycius.vaadin.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.martynaslycius.vaadin.data.interfaces.TodoService;
import com.martynaslycius.vaadin.data.models.ToDo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "add-todo", layout = MainLayout.class)
@PageTitle("Add Todo")
public class AddTodoForm extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private DatePicker datePicker = new DatePicker("To do date");
	private TimePicker timePicker = new TimePicker("Set a time");
	private TextField itemNameField = new TextField("Item name");
	private TextArea descriptionTextArea = new TextArea("Description");

	public AddTodoForm(@Autowired TodoService todoService, @Autowired UI ui,
			@Autowired MessageSource messageSource, @Autowired Locale locale) {
		VerticalLayout todoForm = new VerticalLayout();

		timePicker.setValue(LocalTime.MIDNIGHT);
		datePicker.setValue(LocalDate.now());

        todoForm.add(new HorizontalLayout(datePicker, timePicker));
        todoForm.add(itemNameField);
        todoForm.add(descriptionTextArea);
        
        Button addButton = new Button("Add Todo");
		addButton.addClickShortcut(Key.ENTER);
		addButton.addClickListener(event -> {
			addButton.setEnabled(false);
			if (datePicker.isEmpty()) {
				Notification notification = Notification.show(messageSource.getMessage("com.vaadin.ui.dateempty", null, locale));
		        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}
			else if (itemNameField.getValue().isEmpty()) {
				Notification notification = Notification.show(messageSource.getMessage("com.vaadin.ui.itemnameempty", null, locale));
		        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				addButton.setEnabled(true);
			}
			else {
				System.out.println("todo date " + datePicker.getValue() + " and time " + timePicker.getValue() +" item name " + itemNameField.getValue() +" and description " + descriptionTextArea.getValue());
				ToDo todo = new ToDo();
				Date todoDate = todoService.getTodoDate(datePicker, timePicker);
	
				todo = todoService.saveTodo(todo, todoDate, itemNameField.getValue(), descriptionTextArea.getValue());
				if (todo.getId() != null) {
					Notification notification = Notification.show(messageSource.getMessage("com.vaadin.ui.todosaved", new Object[]{todo.getItemName()}, locale));
			        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

					ui.getPage().setLocation("/edit-todo/" + todo.getId());
				}
				else {
					Notification notification = Notification.show(messageSource.getMessage("com.vaadin.ui.saveerror", null, locale));
					notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				}
			}
		});

		todoForm.add(addButton);
		add(todoForm);
	}
}
