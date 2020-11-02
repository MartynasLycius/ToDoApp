package com.seal.todoapp.com.seal.todoapp.controller;

import com.seal.todoapp.com.seal.todoapp.service.TodoAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class TodoAppViewController {

    private final TodoAppService todoAppService;

    @GetMapping(value = {"/", "/list"})
    public String list() {
        return "list";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoAppService.getOne(id));
        return "edit";
    }

}