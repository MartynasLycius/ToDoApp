package com.proit.todo.view.task;

import com.proit.todo.model.Task;
import com.proit.todo.service.TaskService;
import com.proit.todo.view.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.*;

import java.time.LocalDate;


@Route("tasks")
public class EditTaskView extends MainView implements HasUrlParameter<String>, BeforeEnterObserver {

    private final TaskService taskService;

    String parameter;
    Binder<Task> binder;
    Task task;


    public EditTaskView(TaskService taskService) {
        this.taskService = taskService;
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
        Button updateBtn = new Button("Update");
        updateBtn.setClassName("bg-green-500 text-white");
        updateBtn.addClickListener(e -> {
            try {
                binder.writeBean(task);
                taskService.update(task.getId(), task);
                Notification.show("Successfully updated task.");
                UI.getCurrent().navigate(ListTaskView.class);
            } catch (ValidationException ex) {
                Notification.show("Validation Failed");
                ex.printStackTrace();
            }
        });
        actions.add(updateBtn);
        add(nameField, descriptionField, datePicker, actions);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        this.parameter = parameter;
        this.task = taskService.find(Long.parseLong(parameter));
        this.binder.readBean(task);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (this.task == null) {
            event.rerouteTo(MainView.class);    // TODO: Redirect to NonFountView
        }
    }
}
