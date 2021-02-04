package com.mir00r.todoapp.domains.common.controllers;

import com.mir00r.todoapp.Routing.Router;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
//@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "redirect:" + Router.SEARCH_TODO_ITEMS;
    }
}
