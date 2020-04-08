package com.murad.todoapp.controller;


import com.murad.todoapp.Mapper.ToDoMapper;
import com.murad.todoapp.domain.ToDo;
import com.murad.todoapp.service.ToDoService;
import com.murad.todoapp.utility.Maneger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */


@Controller
@RequestMapping(value = "/todo")
public class ToDoController {


    @Autowired
    ToDoService toDoService;

    @Autowired
    ToDoMapper toDoMapper;

    /**
     * This method will show todo form
     *
     * @param model
     * @return this will return todo form
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showTodoForm(Model model) {
        model.addAttribute("todo", new ToDo());
        return "todo/form";
    }


    /**
     * This method will Save or edit todo
     *
     * @param todo               object to save or edit
     * @param bindingResult      will check error
     * @param redirectAttributes will show Flash Attribute for add or edit .
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTodo(@Valid @ModelAttribute(value = "todo") ToDo todo, BindingResult bindingResult,
                           final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "todo/form";
        }
        /**
         * if todo.getId() is null. Then Save As a New todo .
         * Else edit and save It.
         */
        if (todo.getId() == null) {
            toDoMapper.mapTodoForAdd(todo);
            toDoService.save(todo);
            redirectAttributes.addFlashAttribute("save", todo.getItemName() + " ToDo Added Successfully.");
        } else {
            ToDo todoForEdit = toDoService.findOneById(todo.getId());
            toDoMapper.mapToDoForEdit(todo, todoForEdit);
            toDoService.save(todoForEdit);
            redirectAttributes.addFlashAttribute("edit", todo.getItemName() + " ToDo Updated Successfully.");
        }
        return "redirect:alltodolist";
    }


    /**
     * This method will show all todo list
     *
     * @param model    will have todo list
     * @param pageable use for pagination
     * @return todo list view page
     */
    @RequestMapping(value = "/alltodolist")
    public String showAllTodoList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        model.addAttribute("allTodo", toDoService.findAll(pageable));
        return "todo/list";
    }

    /**
     * search in the todo list
     *
     * @param searchArgument search argument
     * @param model          model will have todo list
     * @param pageable       use for pagination
     * @return todo list get by search argument view page
     */
    @RequestMapping(value = "/search")
    public String search(@RequestParam("ser") String searchArgument, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        if (searchArgument.isEmpty())
            return "redirect:alltodolist";
        model.addAttribute("allTodo", toDoService.search(searchArgument, pageable));
        return "todo/list";
    }

    /**
     * Edit a todo
     *
     * @param todoId primary key for a todo object
     * @param model  will have todo object with given todoId
     * @return todo edit form
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer todoId, Model model) {
        ToDo todo = toDoService.findOneById(todoId);
        todo.setEndDateString(todo.getEndDate() == null ? "" : todo.getEndDate().format(Maneger.formatter));
        todo.setStartDateString(todo.getStartDate() == null ? "" : todo.getStartDate().format(Maneger.formatter));
        model.addAttribute("todo", todo);
        return "todo/form";
    }


    /**
     * delete a todo
     *
     * @param todoId        primary key for a todo object
     * @param redirectAttrs will show Flash Attribute for delete
     * @return todo list after delete
     */
    @RequestMapping(value = "/delete/{id}")
    String delete(@PathVariable("id") Integer todoId, RedirectAttributes redirectAttrs) {
        ToDo todo = toDoService.findOneById(todoId);
        toDoService.deleteById(todoId);
        redirectAttrs.addFlashAttribute("delete", todo.getItemName() + " ToDo Deleted Successfully.");
        return "redirect:/todo/alltodolist";
    }


}
