package com.todo.app.rest;

import com.todo.app.core.entity.TodoItem;
import com.todo.app.core.service.TodoItemService;
import com.todo.app.exception.ErrorHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
class TodoItemResources {
    private final TodoItemService service;
    private final ErrorHandler errorHandler;

    TodoItemResources(TodoItemService service, ErrorHandler errorHandler) {
        this.service = service;
        this.errorHandler = errorHandler;
    }

    @GetMapping("/task/all")
    String showAll(Model model) {
        model.addAttribute("task", service.getAll());
        return "todolist";
    }

    @GetMapping("/task/new")
    String showNewTaskForm(TodoItem todoItem, Model model) {
        model.addAttribute(todoItem);
        return "newtask";
    }

    @PostMapping("/addtask")
    String addTask(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newtask";
        }
        service.add(todoItem);
        return "redirect:/task/all";
    }


    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") long id, Model model) {
        TodoItem task = service.findById(id);
        model.addAttribute("task", task);
        return "update-task";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, @Valid TodoItem todoItem,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-task";
        }
        service.update(todoItem);
        return "redirect:/task/all";
    }

}
