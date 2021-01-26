package com.ovi.todo.views.todo.edit;

import com.ovi.todo.data.dto.request.TodoRequest;
import com.ovi.todo.data.dto.response.TodoResponse;
import com.ovi.todo.data.entity.TodoStatus;
import com.ovi.todo.data.service.contact.TodoService;
import com.ovi.todo.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;

@Route(value = "update", layout = MainView.class)
@PageTitle("Create Todo")
@CssImport("./styles/views/createtodo/create-todo-view.css")
@Log4j2
public class EditTodoView extends Div implements HasUrlParameter<String> {

    private final TextField todoTitle = new TextField("Todo Title");
    private final TextArea todoDescription = new TextArea("Todo Description");
    Select<TodoStatus> status = new Select<>();


    private final Button save = new Button("Update");
    private final Button cancel = new Button("Cancel");
    private final Binder<TodoRequest> binder = new Binder<>(TodoRequest.class);
    private final TodoService todoService;
    private String todoId;


    public EditTodoView(TodoService todoService) {
        setId("create-todo-view");
        this.todoService = todoService;
        /*Layout Populate*/
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        formValidation();

        /*Event bind*/
        binder.bindInstanceFields(this);
        clearForm();
        cancel.addClickListener(e -> clearForm());

        /*
         *  Event Register
         * */

        save.addClickListener(e -> {
            try {
                binder.validate();
                if (binder.isValid()) {
                    todoService.update(todoId, binder.getBean());
                    Notification.show("Todo is successfully updated.");
                    UI.getCurrent().navigate("list");
                }
            } catch (Exception ex) {
                Notification.show("Exception occurred");
            }

        });
    }

    private void clearForm() {
        binder.setBean(new TodoRequest());
    }

    private Component createTitle() {
        return new H3("Todo Edit");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        status.setLabel("Status");
        status.setItems(TodoStatus.values());
        formLayout.add(todoTitle, new Div(), todoDescription, new Div(), status);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String todoId) {
        this.todoId = todoId;
        TodoResponse todoResponse = todoService.getTodoById(todoId);
        todoTitle.setValue(todoResponse.getTodoTitle());
        todoDescription.setValue(todoResponse.getTodoDescription());
        status.setValue(todoResponse.getTodoStatus());
        System.out.println("herere" + todoId);
    }

    void formValidation() {
        binder.forField(todoTitle)
                .asRequired("Title is required")
                .withValidator(todoTitle ->
                        todoTitle.length() >= 5, "Title must contain at least five characters")
                .bind(TodoRequest::getTodoTitle, TodoRequest::setTodoTitle);
    }
}
