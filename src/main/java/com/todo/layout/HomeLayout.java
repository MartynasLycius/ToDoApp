package com.todo.layout;


import com.todo.utils.LayoutUtils;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CssImport(value = "./styles/shared-styles.css", themeFor = "vaadin-app-layout")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class HomeLayout extends AppLayout{
    private static final Logger logger= LogManager.getLogger(HomeLayout.class);

    public HomeLayout() {
        createHeader();
    }

    private void createHeader() {
        addToNavbar(LayoutUtils.getHeader());
    }

}
