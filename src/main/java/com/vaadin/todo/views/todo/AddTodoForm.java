package com.vaadin.todo.views.todo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.service.ToDoItemService;
import com.vaadin.todo.utils.DateUtils;
import com.vaadin.todo.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "add-todo", layout = MainLayout.class)
@PageTitle("Add Todo")
public class AddTodoForm extends TodoForm {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ToDoItemService toDoItemService;

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
            TodoItem toDoItem = new TodoItem();
            toDoItem.setItemName(itemName.getValue());
            toDoItem.setDescription(description.getValue());
            toDoItem.setCreatedDate(DateUtils.localDateToDate(createdDate.getValue()));
            toDoItemService.save(toDoItem);
            UI.getCurrent().navigate(TodoListView.class);
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }

    }


}
