/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo;

import com.nazmul.todo.constant.ViewIdentifires;
import com.nazmul.todo.service.NavigationEvent;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 *
 * @author nazmul
 */
@UIScoped
public class ErrorView  extends CustomComponent implements View {
    
    @Inject
    private javax.enterprise.event.Event<NavigationEvent> navigationEvent;

    @Override
    public void enter(ViewChangeEvent event) {
        MVerticalLayout layout = new MVerticalLayout();
        layout.setSizeFull();
        Label l = new Label("Unfortunately, something goes wrong.");
        Button button = createButton();
        layout.add(l, Alignment.BOTTOM_CENTER);
        layout.add(button, Alignment.TOP_CENTER);
        setCompositionRoot(layout);
    }

    private Button createButton() {
        Button button = new Button("Back to todos");
        button.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigationEvent.fire(new NavigationEvent(ViewIdentifires.TODOS_VIEW));
            }
        });
        return button;
    }
    
}
