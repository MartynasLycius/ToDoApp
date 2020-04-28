package com.kids.crm.todo.ui;

import com.kids.crm.todo.model.Todo;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;

abstract class TodoForm extends VerticalLayout {
    TextField titleField = new TextField("Title");
    TextArea descField = new TextArea("Description");
    DatePicker completionDate = new DatePicker("Date");

    Binder<Todo> validateForm() {
        Binder<Todo> binder = new Binder<>(Todo.class);

        binder.forField(titleField)
                .asRequired("Field cannot be empty")
                .bind(Todo::getTitle, Todo::setTitle);

        binder.forField(completionDate)
                .withValidator(localDate -> localDate.isAfter(LocalDate.now()), "Completion date has to be after today's date")
                .bind(Todo::getCompletionDate, Todo::setCompletionDate)
        ;

        return binder;
    }

    void designLayout(){
        setWidth("590px");
        setClassName("auto-margin");
    }
}
