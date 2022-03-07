package com.example.application.views;

import com.example.application.model.Task;
import com.example.application.repositories.TaskRepository;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.NoResultException;

@Route("editTask")
public class EditTaskView extends VerticalLayout implements HasUrlParameter<Long> {
    private final TaskRepository repository;
    private Long selectedId;
    private Task selectedTask;

    public EditTaskView(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.selectedId = parameter;
        this.selectedTask = repository.findById(parameter).orElseThrow(NoResultException::new);
        setUp();
    }

    private void setUp() {
        VerticalLayout todosList = createTodoList();

        DatePicker datePicker = new DatePicker();
        TextField itemName = new TextField();
        TextField itemDescription = new TextField();

        displayNote(todosList);

        itemDescription.setValue(selectedTask.getDescription());
        datePicker.setValue(selectedTask.getDate());
        itemName.setValue(selectedTask.getName());

        Button editButton = new Button("Edit");
        editButton.addClickListener(click -> onClick(datePicker, itemName, itemDescription));
        editButton.addClickShortcut(Key.ENTER);

        add(
                new H1("ToDoApp"),
                todosList,
                new HorizontalLayout(
                        datePicker,
                        itemName,
                        itemDescription,
                        editButton
                )
        );
    }

    private void onClick(DatePicker datePicker, TextField itemName, TextField itemDescription) {
        if (StringUtils.isNotEmpty(itemName.getValue()) && StringUtils.isNotEmpty(itemDescription.getValue()) && datePicker.getValue() != null) {
            selectedTask.setDate(datePicker.getValue());
            selectedTask.setName(itemName.getValue());
            selectedTask.setDescription(itemDescription.getValue());
            this.repository.save(selectedTask);
            UI.getCurrent().navigate("/");
        }
    }

    private VerticalLayout createTodoList() {
        VerticalLayout todosList = new VerticalLayout();
        todosList.setPadding(false);
        todosList.setSpacing(false);
        return todosList;
    }

    private void addListItem(Task task, VerticalLayout todosList) {
        todosList.add(
                new HorizontalLayout(
                        new Label(task.getDate().toString()),
                        new Label(task.getName()),
                        new Label(task.getDescription())),
                new HtmlComponent("br"));
    }

    private void displayNote(VerticalLayout todosList) {
        addListItem(selectedTask, todosList);
    }
}
