package com.backend.boiler.plate.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.boiler.plate.Utils.Mappings;
import com.backend.boiler.plate.app.services.LoginService;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping(Mappings.LOGIN)
	public String browserLoginController(HttpServletRequest request, HttpServletResponse response) {
		String login = loginService.getToken(request, response);
		if(!login.equals("")) {
			return login;
		} else {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return "{ \"error\": \"Unauthorized user\"}";
		}
	}
}
