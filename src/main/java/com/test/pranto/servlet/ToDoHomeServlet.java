package com.test.pranto.servlet;

import javax.servlet.annotation.WebServlet;

import com.test.pranto.client.page.ToDoHomePage;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;


@WebServlet(urlPatterns = "/*", name = "MyHomeServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = ToDoHomePage.class, productionMode = false)
public class ToDoHomeServlet extends VaadinServlet {
}