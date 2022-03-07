package com.example.application.views;

import com.example.application.model.Task;
import com.example.application.repositories.TaskRepository;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Route("/")
public class MainView extends VerticalLayout {
    private final TaskRepository repository;

    public MainView(TaskRepository repository) {
        this.repository = repository;
        setUp();
    }

    private void setUp() {
        VerticalLayout todosList = createTodoList();
        DatePicker datePicker = new DatePicker();
        TextField itemName = new TextField();
        TextField itemDescription = new TextField();

        datePicker.setValue(LocalDate.now());

        loadFromDatabase(todosList);

        itemDescription.setPlaceholder("Description");
        datePicker.setPlaceholder("Date");
        itemName.setPlaceholder("Name");

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> onClick(todosList, datePicker, itemName, itemDescription));
        addButton.addClickShortcut(Key.ENTER);


        add(
                new H1("ToDoApp"),
                todosList,
                new HorizontalLayout(
                        datePicker,
                        itemName,
                        itemDescription,
                        addButton
                )
        );
    }

    private VerticalLayout createTodoList() {
        VerticalLayout todosList = new VerticalLayout();
        todosList.setPadding(false);
        todosList.setSpacing(false);
        return todosList;
    }

    private void addListItem(Task task, VerticalLayout todosList) {
        HorizontalLayout note = new HorizontalLayout(
                new Label(task.getDate().toString()),
                new Label(task.getName()),
                new Label(task.getDescription()),
                new RouterLink("Edit", EditTaskView.class, task.getId()));
        todosList.add(note, new HtmlComponent("br"));
    }

    private void onClick(VerticalLayout todosList, DatePicker datePicker, TextField itemName, TextField itemDescription) {
        if (StringUtils.isNotEmpty(itemName.getValue()) && StringUtils.isNotEmpty(itemDescription.getValue()) && datePicker.getValue() != null) {
            Task task = new Task(datePicker.getValue(), itemName.getValue(), itemDescription.getValue());
            task = this.repository.save(task);
            addListItem(task, todosList);
        }
    }

    private void loadFromDatabase(VerticalLayout todosList) {
        for (Task task : repository.findAll()) {
            addListItem(task, todosList);
        }
    }
}
