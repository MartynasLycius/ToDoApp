package com.eh.todo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eh.todo.MainApplication;
import com.eh.todo.model.TODOModel;
import com.eh.todo.service.TODOService;
import com.eh.todo.util.DateUtil;
import com.eh.todo.util.PropertyUtil;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Controller
public class TODOController {

	private static final Logger LOG = LoggerFactory.getLogger(MainApplication.class);

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
     * This method use for both save and update data to database,
     * also set some init data when record saved
     *
     * @return  : This method redirect / path
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @PostMapping("/saveTODO")
    public String updateTODO(@ModelAttribute("todo") TODOModel todo) {

        if (todo.getTodoTableId() == null) {
            //set system date as created date
            todo.setCreateDate(DateUtil.format(new Date(), DateUtil.YYYY_MM_DD));
            // set isDone by default 0
            todo.setIsDone(0);

            // save record to database
            service.saveTODO(todo);
            LOG.info(PropertyUtil.Record(PropertyUtil.SAVED));
        } else {
            TODOModel hasData = service.getTODOById(todo.getTodoTableId());

            //checking object has data or not
            if(!ObjectUtils.isEmpty(hasData)) {
                //update data
                todo.setCreateDate(hasData.getCreateDate());
                todo.setIsDone(hasData.getIsDone());

                // update todo to database
                service.saveTODO(todo);
                LOG.info(PropertyUtil.Record(PropertyUtil.UPDETED));
            } else {
                LOG.error(PropertyUtil.Record(PropertyUtil.NOT_UPDETED));
            }
        }
        return "redirect:/";
    }

    /**
     * This method save done data to database
     *
     * @return  : This method redirect / path
     * @since   : 1.0.00 EH
     * @author  : Md. Emran Hossain
     */
    @GetMapping("/doneTODO/{id}")
    public String doneTODO(@PathVariable(value = "id") long id) {
        TODOModel hasData = service.getTODOById(id);

        //checking object has data or not
        if(!ObjectUtils.isEmpty(hasData)) {
            //revert flag
            hasData.setIsDone(hasData.getIsDone() == 1 ? 0 : 1);

            // update todo to database
            service.saveTODO(hasData);
            LOG.info(PropertyUtil.Record(PropertyUtil.UPDETED));
        } else {
            LOG.error(PropertyUtil.Record(PropertyUtil.NOT_UPDETED));
        }
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
