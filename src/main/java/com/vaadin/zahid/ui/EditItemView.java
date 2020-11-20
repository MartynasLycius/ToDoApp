package com.vaadin.zahid.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.zahid.entity.Task;
import com.vaadin.zahid.service.ItemListLayoutService;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "editItem",layout =Layout.class)
public class EditItemView extends Div {

    public EditItemView(ItemListLayoutService todoLayout){
        GridCrud<Task> crud = new GridCrud<>(Task.class, todoLayout);
        add(crud);
        setSizeFull();
    }


}
