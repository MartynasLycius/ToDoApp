package com.martynaslycius.vaadin.ui;

import com.martynaslycius.vaadin.data.interfaces.TodoRepository;
import com.martynaslycius.vaadin.data.models.ToDo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@Route(value = "", layout = MainLayout.class)
@PWA(name = "Vaadin ToDo Application", shortName = "Vaadin ToDo App", description = "This is an example Vaadin application.", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("Todos List")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(MainView.class);

	private TodoRepository todoRepository;
	private MessageSource messageSource;
	private Locale locale;

	public MainView(@Autowired TodoRepository todoRepository, @Autowired UI ui, @Autowired MessageSource messageSource,
			@Autowired Locale locale) {

		this.todoRepository = todoRepository;
		this.locale = locale;
		this.messageSource = messageSource;

		VerticalLayout todosList = new VerticalLayout();
		List<ToDo> allTodos = todoRepository.findAll();
		logger.debug("Number of todos found " + allTodos.size());

		if (allTodos.isEmpty()) {
			todosList.add(new Span(messageSource.getMessage("com.vaadin.ui.todosempty", null, locale)));
		} else {
			HorizontalLayout todoHeader = new HorizontalLayout();
			todoHeader.add(new H2("Id"), new H2("Todo Date"), new H2("Item Name"), new H2("Description"),
					new H2("Actions"));
			todoHeader.addClassName("cell-border");
			todosList.add(todoHeader);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			for (ToDo todo : allTodos) {
				HorizontalLayout todoList = new HorizontalLayout();

				Button editAnchor = new Button("Edit Todo");
				editAnchor.addClickListener(event -> {
					ui.getPage().setLocation("/edit-todo/" + todo.getId());
				});
				Button deleteAnchor = new Button("Delete");
				deleteAnchor.addClickListener(event -> {
					showDeleteDialog(todo.getId(), todo.getItemName());
				});
				todoList.add(new Span(String.valueOf(todo.getId())), new Span(dateFormat.format(todo.getTodoDate())),
						new Span(todo.getItemName()), new Span(todo.getDescription()),
						new Span(editAnchor, new Text(" "), deleteAnchor));
				todoList.addClassName("cell-border");
				todosList.add(todoList);
			}
		}
		add(todosList);
	}

	private void showDeleteDialog(Long todoId, String name) {
		Dialog dialog = new Dialog();
		Label messageLabel = new Label(
				messageSource.getMessage("com.vaadin.ui.promptdelete", new Object[] { name }, locale));
		NativeButton confirmButton = new NativeButton("Confirm", event -> {
			todoRepository.deleteById(todoId);
			messageLabel.setText(messageSource.getMessage("com.vaadin.ui.deleted", new Object[] { name }, locale));
			UI.getCurrent().getPage().reload();
		});
		NativeButton cancelButton = new NativeButton("Cancel", event -> {
			dialog.close();
		});
		dialog.add(new VerticalLayout(messageLabel, new HorizontalLayout(confirmButton, cancelButton)));
		dialog.open();
	}
}
