package com.todo.shantanu.ui;

import com.todo.shantanu.entity.Todo;
import com.todo.shantanu.service.TodoService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;

import static com.todo.shantanu.entity.RoutePath.ROOT;
import static java.lang.Long.parseLong;

@Slf4j
@Route("edit")
public class TodoEditView extends VerticalLayout implements HasUrlParameter<String> {

    TodoService todoService = new TodoService();
    TextField title;
    TextArea desciption;
    DatePicker taskdate;
    Button updateBtn;
    Button cancelBtn;
    Todo todo = new Todo();

    public TodoEditView() {

        initializeBasicDesign();
        initialBasicComponents();

        updateBtnClickListener(updateBtn, ROOT.getRoutingPath());
        cancelBtnClickListener(cancelBtn, ROOT.getRoutingPath());

        add(title, desciption, taskdate, updateBtn);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String p) {

        String parameter = getParameter(beforeEvent);
        todo = todoService.findById(parseLong(parameter));
        setFieldValues(todo);
    }

    private String getParameter(BeforeEvent event) {
        Location location = event.getLocation();

        QueryParameters queryParameters = location.getQueryParameters();

        if (queryParameters.getQueryString().length() > 2) {
            return queryParameters.getQueryString().substring(3);
        }
        return null;
    }

    private void updateBtnClickListener(Button button, String route) {

        button.addClickListener(e -> {
            todo.setTitle(title.getValue());
            todo.setTaskdate(taskdate.getValue());
            todo.setDescription(desciption.getValue());
            todoService.update(todo);
            Notification.show("Update " + title.getValue() + "!");
            UI.getCurrent().navigate(route);
        });
    }

    private void cancelBtnClickListener(Button button, String route) {
        button.addClickListener(e -> UI.getCurrent().navigate(route));
    }

    private void setFieldValues(Todo t) {
        desciption.setValue(t.getDescription());
        taskdate.setValue(t.getTaskdate());
        title.setValue(t.getTitle());
    }

    private void initialBasicComponents() {
        title = new TextField("Title");
        desciption = new TextArea("Description");
        taskdate = new DatePicker("Date");
        updateBtn = new Button("Update");
        cancelBtn = new Button("Cancel");

        HorizontalLayout h = new HorizontalLayout();
        h.add(updateBtn, cancelBtn);
    }

    private void initializeBasicDesign() {
        setWidth("80%");
        setHeight("100%");
        setAlignItems(Alignment.CENTER);
    }
}