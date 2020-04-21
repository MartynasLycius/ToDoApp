package com.kids.crm.todo.ui;

import com.kids.crm.todo.GreetService;
import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Route(value = "list")
public class TodoList extends VerticalLayout {
    @Inject
    private GreetService greetService;

    @Inject
    private TodoService todoService;

    @PostConstruct
    public void init() {

        setWidth("590px");
        setClassName("auto-margin");

        Grid<Todo> grid = new Grid<>(Todo.class);
        List<Todo> todos = todoService.fetch();
        grid.setItems(todos);
        grid.removeColumnByKey("id");

        grid.setColumns("id", "title", "description");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(new RouterLink("Add Todo", TodoForm.class));
        horizontalLayout.add(new Button("Edit Selected",
                e -> Notification.show("clicked")));
        horizontalLayout.add(new Button("Delete Selected",
                e -> {
                        grid.getSelectedItems().forEach(todo -> {
                            todos.remove(todo);
                            grid.getDataProvider().refreshAll();
                            todoService.delete(todo);
                        });
                        Notification.show("Item deleted");
                }
        ));


        add(ComponentFactory.createHeader("All Todo"),
                horizontalLayout,
                grid);
    }
}
