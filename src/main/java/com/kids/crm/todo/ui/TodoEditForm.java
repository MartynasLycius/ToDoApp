package com.kids.crm.todo.ui;

import com.kids.crm.todo.model.Todo;
import com.kids.crm.todo.service.TodoService;
import com.kids.crm.todo.ui.component.ComponentFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Objects;

@Route(value = "edit")
public class TodoEditForm extends TodoForm implements HasUrlParameter<Long> {
    @Inject
    TodoService todoService;

    private Todo edit;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long parameter) {
        if (Objects.nonNull(parameter)) {
            todoService.findById(parameter)
                    .map(todo -> {
                        edit = todo;
                        populateField(todo);
                        return todo;
                    })
                    .orElseThrow(NotFoundException::new);
        } else {
            Notification.show("Id missing");
        }
    }


    private void populateField(Todo todo) {
        titleField.setValue(todo.getTitle());
        descField.setValue(todo.getDescription());
        completionDate.setValue(todo.getCompletionDate());
    }

    @PostConstruct
    public void init() {
        designLayout();
        Button saveButton = new Button("Save",
                e -> {
                    edit.setTitle(titleField.getValue());
                    edit.setDescription(descField.getValue());
                    edit.setCompletionDate(completionDate.getValue());
                    if(validateForm().validate().isOk()) {
                        System.out.println("\n\n\n\n Form valid \n\n\n");
                        todoService.update(edit);
                        UI.getCurrent().navigate("list");
                    }

                });

        add(ComponentFactory.createHeader("Edit Todo"),
                titleField,
                descField,
                completionDate,
                ComponentFactory.addHComponent(saveButton, new RouterLink("List", TodoList.class)));
    }
}
