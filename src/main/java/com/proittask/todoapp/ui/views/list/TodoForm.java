package com.proittask.todoapp.ui.views.list;

import com.proittask.todoapp.backend.entity.Item;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class TodoForm extends FormLayout {
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    DatePicker targetDate = new DatePicker("Target Date");
    ComboBox<Item.Status> status = new ComboBox<>("Status");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Item> binder = new BeanValidationBinder<>(Item.class);

    public TodoForm(List<Item> items) {
        addClassName("todo-form");

        binder.bindInstanceFields(this);
        status.setItems(Item.Status.values());
        add(
                name,
                description,
                targetDate,
                status,
                createButtonsLayout()
        );
    }

    public void setItem(Item item) {
        binder.setBean(item);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        delete.addClickShortcut(Key.DELETE);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class TodoFormEvent extends ComponentEvent<TodoForm> {
        private Item item;

        protected TodoFormEvent(TodoForm source, Item item) {
            super(source, false);
            this.item = item;
        }

        public Item getItem() {
            return item;
        }
    }

    public static class SaveEvent extends TodoFormEvent {
        SaveEvent(TodoForm source, Item item) {
            super(source, item);
        }
    }

    public static class DeleteEvent extends TodoFormEvent {
        DeleteEvent(TodoForm source, Item item) {
            super(source, item);
        }

    }

    public static class CloseEvent extends TodoFormEvent {
        CloseEvent(TodoForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
