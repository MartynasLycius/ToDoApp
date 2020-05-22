package com.vaadin.todo.views.todo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.service.ToDoItemService;
import com.vaadin.todo.utils.DateUtils;
import com.vaadin.todo.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


@Route(value = "add-todo", layout = MainLayout.class)
@PageTitle("Add Todo")
public class AddTodoForm extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    TextField itemName = new TextField();
    TextArea description = new TextArea();
    DatePicker createdDate = new DatePicker();
    Button save = new Button("Save");
    Button close = new Button("Cancel");

    Binder<TodoItem> binder = new BeanValidationBinder<>(TodoItem.class);

    @Autowired
    private ToDoItemService toDoItemService;

    public AddTodoForm() {
        addClassName("contact-form");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        manualBinder();
        add(itemName, description, createdDate);
        createButtonsLayout();
    }

    private void createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        save.addClickListener(click -> validateAndSave());
        close.addClickListener(click -> {
        });
        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));
        HorizontalLayout Add = new HorizontalLayout(save, close);
        add(Add);
    }

    private void manualBinder() {
        binder.forField(createdDate)
                .withConverter(
                        new LocalDateToDateConverter())
                .bind(TodoItem::getCreatedDate, TodoItem::setCreatedDate);
        binder.bindInstanceFields(this);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            saveToDoItem();
        }
    }

    private void saveToDoItem() {
        try {
            TodoItem toDoItem = new TodoItem();
            toDoItem.setItemName(itemName.getValue());
            toDoItem.setDescription(description.getValue());
            Date date = DateUtils.localDateToDate(createdDate.getValue());
            toDoItem.setCreatedDate(date);
            toDoItemService.save(toDoItem);
            UI.getCurrent().navigate(TodoListView.class);
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }

    }

}
