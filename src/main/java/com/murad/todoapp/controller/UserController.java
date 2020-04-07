package com.murad.todoapp.controller;

import com.murad.todoapp.Mapper.UserMapper;
import com.murad.todoapp.domain.User;
import com.murad.todoapp.formvalidator.MyUserFormValidator;


import com.murad.todoapp.service.UserService;
import com.murad.todoapp.utility.RoleTodo;
import com.murad.todoapp.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    MyUserFormValidator myUserFormValidator;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    /**
     * This will provide available role for a user .
     *
     * @param model
     */
    @ModelAttribute
    public void initializeAllRole(Model model) {
        model.addAttribute("roleComboBox", RoleTodo.values());
    }

    /**
     * This method will show user form
     *
     * @param model
     * @return this will return user form
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showUserForm(Model model) {
        model.addAttribute("myUser", new User());
        return "user/form";
    }

    /**
     * This method will Save or edit user
     *
     * @param user               object to save or edit
     * @param bindingResult      will check error
     * @param redirectAttributes will show Flash Attribute for add or edit .
     * @param onoffswitch        Select user is active or inactive
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, BindingResult bindingResult, @RequestParam(value = "onoffswitch", required = false, defaultValue = "false") boolean onoffswitch,
                           final RedirectAttributes redirectAttributes) {

        if (user.getId() != null) {
            MyUserFormValidator.setEditEmail(userService.findOneById(user.getId()).getEmail());
        }
        myUserFormValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/form";
        }
        user.setActive(onoffswitch);
        /**
         * if user.getId() is null. Then Save As a New User .
         * Else edit and save It.
         */
        if (user.getId() == null) {
            userService.save(user);
            redirectAttributes.addFlashAttribute("save", user.getEmail() + " User Added Successfully.");
        } else {
            userMapper.mapUserObjectForEdit(user, onoffswitch);
            redirectAttributes.addFlashAttribute("edit", user.getEmail() + " User Updated Successfully.");
        }
        return "redirect:allUser";
    }

    /**
     * This method will show all user list
     *
     * @param model    will have user list
     * @param pageable use for pagination
     * @return user list view page
     */
    @RequestMapping(value = "/allUser")
    public String getAllUser(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        model.addAttribute("allUser", userService.findAll(pageable));
        return "user/list";
    }


    /**
     * search in the user list
     *
     * @param searchArgument search argument
     * @param model          model will have user list
     * @param pageable       use for pagination
     * @return user list get by search argument view page
     */
    @RequestMapping(value = "/search")
    public String search(@RequestParam("ser") String searchArgument, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        if (searchArgument.isEmpty())
            return "redirect:allUser";
        model.addAttribute("allUser", userService.search(searchArgument, pageable));
        return "user/list";
    }

    /**
     * Edit a user
     *
     * @param userId primary key for a user object
     * @param model  will have user object with given todoId
     * @return user edit form
     */
    @RequestMapping(value = "/edit/{id}")
    public String editUser(@PathVariable("id") Integer userId, Model model) {
        User one = userService.findOneById(userId);
        one.setPasswordHash(null);
        model.addAttribute("myUser", one);
        return "user/form";
    }

    /**
     * delete a user
     *
     * @param userId        primary key for a user object
     * @param redirectAttrs will show Flash Attribute for delete
     * @return user list after delete
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer userId, RedirectAttributes redirectAttrs) {
        User user = userService.findOneById(userId);
        userService.save(user);
        userService.deleteById(userId);
        redirectAttrs.addFlashAttribute("delete", user.getEmail() + " User Deleted Successfully.");
        return "redirect:/user/allUser";
    }

    /**
     * @param userId   primary key for a user object
     * @param isActive set user active or inactive
     * @return update status
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    int setUserActiveStatus(@RequestParam("uid") Integer userId, @RequestParam("flag") boolean isActive) {
        User one = userService.findOneById(userId);
        one.setActive(isActive);
        userService.editAndSave(one);
        return Status.UPADTE_SUCCESS;
    }

}
