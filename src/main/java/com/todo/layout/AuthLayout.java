package com.todo.layout;

import com.todo.utils.LayoutUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport(value = "./styles/shared-styles.css", themeFor = "vaadin-app-layout")
public class AuthLayout extends AppLayout{

    public AuthLayout() {
        createHeader();
    }

    private void createHeader() {
        addToNavbar(LayoutUtils.getHeader());
    }
}
