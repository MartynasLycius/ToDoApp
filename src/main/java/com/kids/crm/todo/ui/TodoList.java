package com.kids.crm.todo.ui;

import com.kids.crm.todo.GreetService;
import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Route(value = "list")
public class TodoList extends VerticalLayout {
    @Inject
    private GreetService greetService;

    @Inject
    private TodoService todoService;

    @PostConstruct
    public void init() {

        Grid<Todo> grid = new Grid<>(Todo.class);

        List<Todo> todos = todoService.fetch();


        grid.setItems(todos);

        grid.removeColumnByKey("id");


        grid.setColumns("id", "title", "description");

        Button button = new Button("Say hello",
                e -> Notification.show("clicked"));

        add(grid, button);
    }
}
