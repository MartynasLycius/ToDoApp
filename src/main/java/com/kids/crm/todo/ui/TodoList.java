package com.kids.crm.todo.ui;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Route(value = "list")
public class TodoList extends VerticalLayout {

    @Inject
    private TodoService todoService;

    @PostConstruct
    public void init() {

        setWidth("590px");
        setClassName("auto-margin");

        List<Todo> todos = todoService.fetch();
        Grid<Todo> grid = buildTable(todos);

        HorizontalLayout buttonHolder = buildActionBar(todos, grid);

        add(ComponentFactory.createHeader("All Todo"),
                buttonHolder,
                grid);
    }

    private HorizontalLayout buildActionBar(List<Todo> todos, Grid<Todo> grid){
        HorizontalLayout buttonHolder = new HorizontalLayout();
        buttonHolder.add(new RouterLink("Add Todo", TodoAddForm.class));
        buttonHolder.add(new Button("Edit Selected",
                e -> {
                    Set<Todo> selectedItems = grid.getSelectedItems();
                    if(selectedItems.isEmpty()){
                        Notification.show("Please select todo Item first");
                    } else {
                        UI.getCurrent().navigate(TodoEditForm.class, selectedItems.stream().findFirst().get().getId());
                    }

                }));
        buttonHolder.add(new Button("Delete Selected",
                e -> {
                    grid.getSelectedItems().forEach(todo -> {
                        todos.remove(todo);
                        grid.getDataProvider().refreshAll();
                        todoService.delete(todo);
                    });
                    Notification.show("Item deleted");
                }
        ));

        return buttonHolder;
    }

    private Grid<Todo> buildTable(List<Todo> todos){
        Grid<Todo> grid = new Grid<>(Todo.class);
        grid.setItems(todos);
        grid.removeColumnByKey("id");
        grid.setColumns("id", "title", "description");
        return grid;
    }
}
