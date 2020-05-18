package com.nazmul.todo.view;

import com.nazmul.todo.constant.ViewIdentifires;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.viritin.layouts.MVerticalLayout;


@CDIView(ViewIdentifires.TODOS_VIEW)
@Title("Todos")
//@ViewMenuItem(order = ViewMenuItem.BEGINNING)
public class TodosView extends MVerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private TodosComponentImpl todosView;

    @PostConstruct
    public void initComponent() {
        this.setMargin(false);
        addComponents(todosView);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
