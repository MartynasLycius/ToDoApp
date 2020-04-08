package com.murad.todoapp.controller;
import com.murad.todoapp.utility.Maneger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("checkExpiration", Maneger.checkExpiration());
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToHomePage() {
        return "home/home";
    }


}


