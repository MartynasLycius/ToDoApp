package com.vaadin.zahid.dto;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.zahid.entity.Task;
import com.vaadin.zahid.service.IChangeItemListenerService;

public class ItemLayout extends HorizontalLayout {

    private final DatePicker date;
    private final TextField name;
    private final TextField description;

    public ItemLayout(Task todo, IChangeItemListenerService changeListener) {
        setWidth("100%");
        setDefaultVerticalComponentAlignment(Alignment.CENTER);

        date = new DatePicker();
        date.setReadOnly(true);

        name = new TextField();
        name.setReadOnly(true);

        description = new TextField();
        description.setReadOnly(true);

        Binder<Task> binder = new Binder<>(Task.class);
        binder.bindInstanceFields(this);
        binder.setBean(todo);

        add(date);
        add(name);
        addAndExpand(description);

        binder.addValueChangeListener(event -> changeListener.itemChanged(todo));
    }
}
