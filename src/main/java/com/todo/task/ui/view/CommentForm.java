package com.todo.task.ui.view;

import com.todo.task.entity.Comment;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;


public class CommentForm extends FormLayout {

    @PropertyId("comment")
    private final TextArea comment = new TextArea("Comment");

    Binder<Comment> binder = new BeanValidationBinder<>(Comment.class);
    public CommentForm(){
        addClassName("comment-form");

        binder.bindInstanceFields(this);


        add(comment);

    }
}
