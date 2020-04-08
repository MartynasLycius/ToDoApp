package com.devsoftbd.com.ToDoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devsoftbd.com.ToDoApp.services.DashboardService;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {
	@Autowired
	private DashboardService dashboardService;;

	@GetMapping({ "", "/" })
	public String dashboard(Model model) {
		model.addAttribute("statusWiseCountList", dashboardService.getTodoCountGroupByStatus());
		return "/dashboard";
	}
}
