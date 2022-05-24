package com.todo.app.rest;

import com.todo.app.core.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserRegistrationResource {
    @GetMapping("/user/registration")
    public String showRegistration(WebRequest request, Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }
}
