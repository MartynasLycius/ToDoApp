package com.kids.crm.todo.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ComponentFactory {


    public static HorizontalLayout createHeader(String title) {
        HorizontalLayout header = new HorizontalLayout();
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        header.setSizeFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        Label label = new Label(title);
        header.add(label);
        header.setClassName("heading-label");
        return header;
    }

    public static HorizontalLayout addHComponent(Component ...components) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        for(Component component : components){
            layout.add(component);
        }

        return layout;
    }


}
