package com.todo.shantanu.ui;

import com.todo.shantanu.entity.Todo;
import com.todo.shantanu.service.TodoService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.todo.shantanu.entity.RoutePath.TODO_VIEW;

@Route("")
@PageTitle("Home")
@PWA(name = "Todo App with Vaadin and Java EE", shortName = "Project Base", enableInstallPrompt = false)
@Theme(value = Lumo.class)
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Slf4j
public class MainView extends VerticalLayout {

    TodoService todoService = new TodoService();

    public MainView() {

        Button addBtn = new Button("Add");
        Label label = new Label("Todo Application");
        label.setHeight("70%");
        label.setWidth("70%");

        List<Todo> todos = todoService.findAllTodos();

        add(addBtn, label);

        todos.forEach(todo ->
                add(new TodoRowView(todo))
        );

        addBtn.addClickListener(e -> UI.getCurrent().navigate(TODO_VIEW.getRoutingPath()));
    }
}
