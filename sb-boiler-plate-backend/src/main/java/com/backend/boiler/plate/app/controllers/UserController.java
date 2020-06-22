package com.backend.boiler.plate.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.boiler.plate.Utils.Constants;
import com.backend.boiler.plate.Utils.Mappings;
import com.backend.boiler.plate.app.models.User;
import com.backend.boiler.plate.app.models.UserInfo;
import com.backend.boiler.plate.app.services.UserService;

@RestController
@RequestMapping(Mappings.API)
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(Mappings.API_USER_INFO)
	public UserInfo browserLoginController(HttpServletRequest request, HttpServletResponse response) {
		return userService.getUserInfo(request, response);
	}
	
	@Secured({ "ROLE_"+Constants.ROLE_ADMIN })
	@PostMapping(Mappings.API_USER_ADD)
	public Object addUser(@RequestBody User user, HttpServletResponse response) {
		User createdUser = userService.addUser(user);
		if(createdUser!=null) {
			return createdUser;
		}
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return "{}";
	}
	
	@Secured({ "ROLE_"+Constants.ROLE_ADMIN })
	@GetMapping(Mappings.API_USERS)
	public List<User> addUser() {
		return userService.getAllUsers();
	}
	
}
