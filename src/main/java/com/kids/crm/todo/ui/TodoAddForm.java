package com.kids.crm.todo.ui;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Route(value = "add")
public class TodoAddForm extends TodoForm {

    @Inject
    private TodoService todoService;

    @PostConstruct
    public void init() {
        designLayout();
        Button saveButton = new Button("Save",
                e -> {
                    if(validateForm().validate().isOk()){
                        todoService.create(new Todo(titleField.getValue(), descField.getValue(), completionDate.getValue()));
                        UI.getCurrent().navigate("list");
                    }

                });

        setWidth("590px");
        setClassName("auto-margin");
        add(ComponentFactory.createHeader("Add Todo"),
                titleField,
                descField,
                completionDate,
                ComponentFactory.addHComponent(saveButton, new RouterLink("List", TodoList.class)));
    }
}
