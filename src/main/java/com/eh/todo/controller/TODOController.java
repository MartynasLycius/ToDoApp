package com.eh.todo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eh.todo.model.TODOModel;
import com.eh.todo.service.TODOService;
import com.eh.todo.util.DateUtil;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Controller
public class TODOController {

    @Autowired
    private TODOService service;

    /**
     * This method shows all the data in / path with view
     *
     * @return  : This method return Model with page name
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("todoList", service.getAllTODO());
        return "index";
    }

    /**
     * This method display New todo Form
     *
     * @return  : This method return Model with page name
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @GetMapping("/newTODOForm")
    public String NewTODOForm(Model model) {
        // create model attribute to bind form data
        TODOModel todo = new TODOModel();
        model.addAttribute("todo", todo);
        return "new_todo";
    }

    /**
     * This method save data from view form
     *
     * @return  : This method redirect / path
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @PostMapping("/saveTODO")
    public String saveTODO(@ModelAttribute("todo") TODOModel todo) {

        todo.setCreateDate(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD));
        todo.setHasDone(0);
        // save todo to database
        service.saveTODO(todo);
        return "redirect:/";
    }

    /**
     * This method save done data to database
     *
     * @return  : This method redirect / path
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @PostMapping("/doneTODO/{id}")
    public String doneTODO(@PathVariable(value = "id") long id, @ModelAttribute("todo") TODOModel todo) {
        todo.setHasDone(1);
        // save done todo to database
        service.saveTODO(todo);
        return "redirect:/";
    }

    /**
     * This method update data from view form
     *
     * @param   : id, use it for update data in database by id
     * @return  : This method return update page name
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get todo from the service
        TODOModel todo = service.getTODOById(id);

        // set todo as a model attribute to pre-populate the form
        model.addAttribute("todo", todo);
        return "update_todo";
    }

    /**
     * This method delete data in database
     *
     * @param   : id, use it for delete data in database by id
     * @return  : This method return / path
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @GetMapping("/deleteTODO/{id}")
    public String deleteTODO(@PathVariable(value = "id") long id) {

        // call delete todo 
        this.service.deleteTODOById(id);
        return "redirect:/";
    }
}
