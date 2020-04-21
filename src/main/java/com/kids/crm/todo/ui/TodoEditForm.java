package com.kids.crm.todo.ui;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Objects;

@Route(value = "edit")
public class TodoEditForm extends VerticalLayout implements HasUrlParameter<Long> {
    @Inject
    TodoService todoService;

    Todo edit;

    TextField titleField;
    TextArea descField;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long parameter) {
        System.out.println("\n\n\n\n parameterHere: \n\n\n" + parameter);
        if(Objects.nonNull(parameter)){
            todoService.findById(parameter)
            .map(todo -> {
                edit = todo;
                populateField(edit);
                return todo;
            })
            .orElseThrow(NotFoundException::new)
            ;
        } else {
            Notification.show("Id missing");
        }
    }


    void populateField(Todo todo){
        titleField.setValue(edit.getTitle());
        descField.setValue(edit.getDescription());
    }

    @PostConstruct
    public void init() {
        titleField = new TextField("Title");
        descField = new TextArea("Description");

        Button saveButton = new Button("Save",
                e -> {
                    edit.setTitle(titleField.getValue());
                    edit.setDescription(descField.getValue());
                    todoService.update(edit);
                    UI.getCurrent().navigate("list");
                });  UI.getCurrent().navigate("list");
        setWidth("590px");
        setClassName("auto-margin");

        add(ComponentFactory.createHeader("Edit Todo"),
                titleField,
                descField,
                ComponentFactory.addHComponent(saveButton, new RouterLink("List", TodoList.class)));
    }
}
