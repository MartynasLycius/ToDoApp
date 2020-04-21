package com.kids.crm.todo.ui;

import com.kids.crm.todo.GreetService;
import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Route(value = "add")
public class TodoForm extends VerticalLayout {
    @Inject
    private GreetService greetService;

    @Inject
    private TodoService todoService;

    @PostConstruct
    public void init() {
        TextField titleField = new TextField("Title");
        TextArea descField = new TextArea("Description");

        Button saveButton = new Button("Save",
                e -> {
                    todoService.create(new Todo(titleField.getValue(), descField.getValue()));
                    UI.getCurrent().navigate("list");
                });  UI.getCurrent().navigate("list");
        setWidth("590px");
        setClassName("auto-margin");
        add(ComponentFactory.createHeader("Add Todo"),
                titleField,
                descField,
                ComponentFactory.addHComponent(saveButton, new RouterLink("List", TodoList.class)));
    }
}
