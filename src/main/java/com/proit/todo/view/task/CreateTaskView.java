package com.proit.todo.view.task;

import com.proit.todo.model.Task;
import com.proit.todo.service.TaskService;
import com.proit.todo.view.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route(value = "tasks/new", layout = MainView.class)
public class CreateTaskView extends VerticalLayout {

    private final TaskService taskService;

    Binder<Task> binder;
    Task task;

    public CreateTaskView(TaskService taskService) {
        this.taskService = taskService;
        this.task = new Task();
        this.binder = new Binder<>();

        TextField nameField = new TextField();
        nameField.setPlaceholder("Name");
        TextField descriptionField = new TextField();
        descriptionField.setPlaceholder("Description");
        DatePicker datePicker = new DatePicker();
        datePicker.setPlaceholder("Date");

        binder.bind(nameField, Task::getName, Task::setName);
        binder.bind(descriptionField, Task::getDescription, Task::setDescription);
        binder.bind(datePicker, Task::getDate, Task::setDate);

        HorizontalLayout actions = new HorizontalLayout();
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setClassName("bg-red-200 text-gray-700");
        cancelBtn.addClickListener(e -> UI.getCurrent().navigate(ListTaskView.class));
        actions.add(cancelBtn);
        Button saveBtn = new Button("Save");
        saveBtn.setClassName("bg-green-500 text-white");
        saveBtn.addClickListener(e -> {
            try {
                binder.writeBean(task);
                taskService.save(task);
                Notification.show("Successfully saved the task.");
                UI.getCurrent().navigate(ListTaskView.class);
            } catch (ValidationException ex) {
                Notification.show("Validation Failed");
                ex.printStackTrace();
            }
        });
        actions.add(saveBtn);
        add(nameField, descriptionField, datePicker, actions);
    }


}
