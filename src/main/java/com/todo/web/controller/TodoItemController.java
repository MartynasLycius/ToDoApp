package com.todo.web.controller;

import com.todo.web.exception.RecordNotFoundException;
import com.todo.web.form.TodoItemForm;
import com.todo.web.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class for todoItem(s)
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 2020-20-05
 */
@Controller
@RequestMapping("/")
public class TodoItemController {

    @Autowired
    TodoItemService service;

    /**
     * This method is used to show index page with all the todoItems
     *
     * @param model this is the model object
     * @return String view name.
     */
    @GetMapping(value = {"/", "/todo/list"})
    public String showAllTodoItem(Model model) {
        model.addAttribute("todoItems", service.getAllTodoItems());
        return "index";
    }

    /**
     * This method is used to show todoItem add form
     *
     * @param model this is the model object
     * @return String view name.
     */
    @GetMapping("/todo/add")
    public String showAddForm(Model model) {
        service.viewInit(model, null);
        return "add-todo";
    }

    /**
     * This method is used to save todoItem
     *
     * @param todoItemForm TodoItemForm
     * @param result       BindingResult
     * @param model        this is the model object
     * @return String view name.
     * @throws RecordNotFoundException exception
     */
    @PostMapping("/todo/save")
    public String addTodoItem(@Valid @ModelAttribute("todoItemForm") TodoItemForm todoItemForm,
                              BindingResult result, Model model) throws RecordNotFoundException {
        if (result.hasErrors()) {
            service.viewInit(model, todoItemForm);
            return "add-todo";
        }
        service.registry(todoItemForm);
        return "redirect:list";
    }

    /**
     * This method is used to show edit form
     *
     * @param id    long value for todoItemId
     * @param model this is the model object
     * @return String view name.
     * @throws RecordNotFoundException exception
     */
    @GetMapping("/todo/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) throws RecordNotFoundException {
        TodoItemForm todoItemForm = service.getTodoItemForm(id);
        service.viewInit(model, todoItemForm);
        model.addAttribute("todoItemForm", todoItemForm);
        return "update-todo";
    }

    /**
     * This method is used to show edit form
     *
     * @param todoItemForm TodoItemForm
     * @param result       BindingResult
     * @param model        this is the model object
     * @return String view name.
     * @throws RecordNotFoundException exception
     */
    @PostMapping("/todo/update")
    public String updateTodoItem(@Valid @ModelAttribute("todoItemForm") TodoItemForm todoItemForm,
                                 BindingResult result, Model model) throws RecordNotFoundException {
        if (result.hasErrors()) {
            service.viewInit(model, todoItemForm);
            return "update-todo";
        }
        service.registry(todoItemForm);
        return "redirect:list";
    }

    /**
     * This method is used to show edit form
     *
     * @param id    long value for todoItemId
     * @param model this is the model object
     * @return String view name.
     * @throws RecordNotFoundException exception
     */
    @GetMapping("todo/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) throws RecordNotFoundException {
        service.deleteTodoById(id, model);
        return "index";
    }

}
