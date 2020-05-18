package com.vaadin.todo.views.todo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import com.vaadin.todo.entity.ToDoItem;
import com.vaadin.todo.service.ToDoItemService;
import com.vaadin.todo.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Route(value = "", layout = MainLayout.class)
@PageTitle("Todo List | ToDO App")
public class ListView extends VerticalLayout implements RouterLayout {

    Grid<ToDoItem> toDoItemGrid = new Grid<>(ToDoItem.class);
    @Autowired
    private ToDoItemService toDoItemService;

    private ListView() {
        addClassName("todo-item-list-view");
        setSizeFull();
        configureGrid();
        add(toDoItemGrid);
        updateToDOItemList();
    }


    private void configureGrid() {
        toDoItemGrid.addClassName("list-view");
        toDoItemGrid.setWidth("50%");
        toDoItemGrid.setColumns("itemName", "description", "createdDate");

    }


    private void updateToDOItemList() {
        toDoItemGrid.setItems(toDoItemService.findAll());
    }

}
