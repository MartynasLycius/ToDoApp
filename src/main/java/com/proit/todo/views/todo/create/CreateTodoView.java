package com.proit.todo.views.todo.create;

import com.proit.todo.data.dto.request.TodoRequest;
import com.proit.todo.data.service.contact.TodoService;
import com.proit.todo.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "todo", layout = MainView.class)
@PageTitle("Create Todo")
@CssImport("./styles/views/createtodo/create-todo-view.css")
public class CreateTodoView extends Div {

    private final TextField todoTitle = new TextField("Todo Title");
    private final TextArea todoDescription = new TextArea("Todo Description");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final Binder<TodoRequest> binder = new Binder<>(TodoRequest.class);

    public CreateTodoView(TodoService todoService) {
        setId("create-todo-view");

        /*Layout Populate*/
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        /*Form validation message*/
        formValidation();

        /*Auto bind to binder*/
        binder.bindInstanceFields(this);
        clearForm();
        cancel.addClickListener(e -> clearForm());

        /*
         * Save Event Register
         * */
        save.addClickListener(e -> {
            try {
                binder.validate();
                if (binder.isValid()) {
                    todoService.save(binder.getBean());
                    Notification.show("A Todo is created successfully.").setPosition(Notification.Position.BOTTOM_CENTER);
//                    UI.getCurrent().navigate("task-list");
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
        return new H3("Todo Create");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(todoTitle, new Div(), todoDescription);
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

    void formValidation() {
        binder.forField(todoTitle)
                .asRequired("Title is required")
                .withValidator(todoTitle ->
                        todoTitle.length() >= 5, "Title must contain at least five characters")
                .bind(TodoRequest::getTodoTitle, TodoRequest::setTodoTitle);
    }
}
