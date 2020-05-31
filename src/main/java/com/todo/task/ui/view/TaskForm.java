package com.todo.task.ui.view;

import com.todo.task.entity.Task;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.shared.Registration;

public class TaskForm extends FormLayout {

    @PropertyId("time")
    private final TimePicker time = new TimePicker("Time");

    @PropertyId("date")
    private final DatePicker date = new DatePicker("Date");

    @PropertyId("taskName")
    private final TextField taskName = new TextField("Task Title");

    @PropertyId("description")
    private final TextArea desc = new TextArea("Description");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Cancel");

    private final Binder<Task> binder = new BeanValidationBinder<>(Task.class);
    private Task task;

    public TaskForm(){
        this.task=new Task();
        addClassName("task-form");
        binder.bindInstanceFields(this);
        binder.setBean(task);
        //binder.forField(time).bind(Task::getTime, Task::setTime);

        add(
                time,
                date,
                taskName,
                desc,
                createButtonsLayout()
        );
    }

    public void setTask(Task task) {
        binder.setBean(task);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class TaskFormEvent extends ComponentEvent<TaskForm> {
        private Task task;

        protected TaskFormEvent(TaskForm source, Task task) {
            super(source, false);
            this.task = task;
        }

        public Task getTask() {
            return task;
        }
    }

    public static class SaveEvent extends TaskFormEvent {
        SaveEvent(TaskForm source, Task contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends TaskFormEvent {
        DeleteEvent(TaskForm source, Task contact) {
            super(source, contact);
        }

    }

    public static class CancelEvent extends TaskFormEvent {
        CancelEvent(TaskForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
