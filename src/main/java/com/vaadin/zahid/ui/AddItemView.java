package com.vaadin.zahid.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.zahid.entity.Task;
import com.vaadin.zahid.service.ItemListLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Route(value = "addItem",layout = Layout.class)
public class AddItemView  extends Div {

    @Autowired
    private ItemListLayoutService todoLayout;

    @PostConstruct
    void init(){
        addForm();
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("100%");

        DatePicker date =new DatePicker();
        date.setPlaceholder("Select event date");
        TextField name = new TextField();
        name.setPlaceholder("Enter task name");
        TextField description = new TextField();
        description.setPlaceholder("Enter description");

        Button addButton = new Button("Add");

        formLayout.add(date);
        formLayout.add(name);
        formLayout.addAndExpand(description);
        formLayout.add(addButton);

        addButton.addClickListener(click-> {
            if(!StringUtils.isEmpty(name.getValue()) && !StringUtils.isEmpty(description.getValue()) ){
                todoLayout.add(new Task(name.getValue(),date.getValue(),description.getValue()));
                date.clear();
                name.clear();
                description.clear();
                Notification.show("Item is added successfully.");
            }else{
                Notification.show("Empty Todo item cannot be added.");
            }

        });

        name.focus();
        addButton.addClickShortcut(Key.ENTER);

        add(formLayout);
    }


}
