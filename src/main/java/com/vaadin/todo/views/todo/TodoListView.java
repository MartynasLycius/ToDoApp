package com.vaadin.todo.views.todo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.todo.entity.TodoItem;
import com.vaadin.todo.service.ToDoItemService;
import com.vaadin.todo.views.MainLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



@Component
@Scope("prototype")
@Route(value = "", layout = MainLayout.class)
@PageTitle("Todo List | ToDO App")
public class TodoListView extends VerticalLayout implements RouterLayout {

    Grid<TodoItem> toDoItemGrid = new Grid<>(TodoItem.class);
    private ToDoItemService toDoItemService;
    private TodoListView(ToDoItemService toDoItemService) {
        Button reset = new Button("Todo");
        reset.addClickListener(e -> {
            UI.getCurrent().navigate(AddTodoForm.class);
        });
        this.toDoItemService = toDoItemService;
        HorizontalLayout Add = new HorizontalLayout(reset);
        addClassName("todo-item-list-view");
        setSizeFull();
        configureGrid();
        add(Add, toDoItemGrid);
        updateTodoItemList();
    }


    private void configureGrid() {
        toDoItemGrid.addClassName("list-view");
        toDoItemGrid.setWidth("50%");
        toDoItemGrid.setColumns("itemName", "description", "createdDate");

    }

    private void updateTodoItemList() {
        toDoItemGrid.setItems(toDoItemService.findAll());
    }

}
