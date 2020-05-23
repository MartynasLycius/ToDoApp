package com.vaadin.todo.views.todo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.*;
import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.execption.notFoundException;
import com.vaadin.todo.service.ToDoItemService;
import com.vaadin.todo.utils.DateUtils;
import com.vaadin.todo.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "edit-todo", layout = MainLayout.class)
@PageTitle("Edit Todo")
public class EditTodoForm extends TodoForm implements HasUrlParameter<String>  {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ToDoItemService toDoItemService;
    TodoItem todoItem;

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        try {
            todoItem = toDoItemService.findById(Long.parseLong(parameter)).orElse(null);
            itemName.setValue(todoItem.getItemName());
            description.setValue(todoItem.getDescription());
            createdDate.setValue(DateUtils.convertToLocalDate(todoItem.getCreatedDate()));
        } catch (notFoundException ex) {
            Notification.show(ex.getMessage());
        }
    }

    @Override
    protected void createButtonsLayout() {
        super.createButtonsLayout();
        save.addClickListener(click -> validateAndSave());


    }

    private void validateAndSave() {
        if (binder.isValid()) {
            saveToDoItem();
        }
    }

    private void saveToDoItem() {
        try {
            todoItem.setItemName(itemName.getValue());
            todoItem.setDescription(description.getValue());
            todoItem.setCreatedDate(DateUtils.localDateToDate(createdDate.getValue()));
            toDoItemService.save(todoItem);
            UI.getCurrent().navigate(TodoListView.class);
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }

    }


}
