package com.martynaslycius.vaadin.ui;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.martynaslycius.vaadin.data.interfaces.TodoRepository;
import com.martynaslycius.vaadin.data.interfaces.TodoService;
import com.martynaslycius.vaadin.data.models.ToDo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "edit-todo", layout = MainLayout.class)
@PageTitle("Edit Todo")
public class EditTodoForm extends VerticalLayout implements HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(EditTodoForm.class);

	private DatePicker datePicker = new DatePicker("To do date");
	private TimePicker timePicker = new TimePicker("Set a time");
	private TextField itemNameField = new TextField("Item name");
	private TextArea descriptionTextArea = new TextArea("Description");

	private TodoService todoService;
	private TodoRepository todoRepository;
	private MessageSource messageSource;
	private Locale locale;

	public EditTodoForm(@Autowired TodoService todoService, @Autowired TodoRepository todoRepository,
			@Autowired MessageSource messageSource, @Autowired Locale locale) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
		this.messageSource = messageSource;
		this.locale = locale;
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		logger.debug("url parameter : " + parameter);
		try {
			Long id = Long.parseLong(parameter);
			ToDo todo = todoRepository.findById(id).orElse(null);
			if (todo != null) {
				VerticalLayout todoForm = new VerticalLayout();

				LocalDateTime todoTime = todo.getTodoDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				timePicker.setValue(todoTime.toLocalTime());
				datePicker.setValue(todoTime.toLocalDate());

				itemNameField.setValue(todo.getItemName());
				descriptionTextArea.setValue(todo.getDescription());
				todoForm.add(new HorizontalLayout(datePicker, timePicker));
				todoForm.add(itemNameField);
				todoForm.add(descriptionTextArea);

				Button addButton = new Button("Update Todo");
				addButton.addClickShortcut(Key.ENTER);
				addButton.addClickListener(clickEvent -> {
					addButton.setEnabled(false);
					if (datePicker.isEmpty()) {
						Notification notification = Notification
								.show(messageSource.getMessage("com.vaadin.ui.dateempty", null, locale));
						notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
					} else if (itemNameField.getValue().isEmpty()) {
						Notification notification = Notification
								.show(messageSource.getMessage("com.vaadin.ui.itemnameempty", null, locale));
						notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
					} else {
						Date todoDate = todoService.getTodoDate(datePicker, timePicker);

						ToDo updatedTodo = todoService.saveTodo(todo, todoDate, itemNameField.getValue(),
								descriptionTextArea.getValue());
						Notification notification = Notification.show(messageSource.getMessage(
								"com.vaadin.ui.todoupdated", new Object[] { updatedTodo.getItemName() }, locale));
						notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
					}
					addButton.setEnabled(true);
				});
				todoForm.add(addButton);
				add(todoForm);
			}
		} catch (NumberFormatException e) {
			logger.error("Parsing error ", e);
		}

		// }
	}

}
