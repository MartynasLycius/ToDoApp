package com.vaadin.zahid.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.zahid.service.ItemListLayoutService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "",layout = Layout.class)
public class MainVIew extends Div {

    private VerticalLayout todosList;

    @Autowired
    private ItemListLayoutService todoLayout;

    @PostConstruct
    void init(){
        setupLayout();
        addTodoList();

    }

    private void setupLayout() {
        todosList = new VerticalLayout();
        todosList.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        add(todosList);
    }


    private void addTodoList() {
        todoLayout.setWidth("80%");
//        todoLayout.countCompleted();
        todosList.add(todoLayout);
    }


}
