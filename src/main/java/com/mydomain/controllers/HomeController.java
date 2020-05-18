package com.mydomain.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/todo/");
	}
}
