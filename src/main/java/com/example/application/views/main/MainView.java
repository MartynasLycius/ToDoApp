package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        VerticalLayout todosList = new VerticalLayout();

        DatePicker datePicker = new DatePicker();
        TextField itemName = new TextField();
        TextField itemDescription = new TextField();

        itemDescription.setPlaceholder("Description");
        datePicker.setPlaceholder("Date");
        itemName.setPlaceholder("Name");

        Button addButton = new Button("Add");
        addButton.addClickListener(click -> todosList.add(itemName.getValue()));
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("ToDoApp"),
                todosList,
                new HorizontalLayout(
                        datePicker,
                        itemName,
                        itemDescription,
                        addButton
                )
        );
    }
}
