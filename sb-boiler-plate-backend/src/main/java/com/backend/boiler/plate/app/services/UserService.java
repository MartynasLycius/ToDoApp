package com.backend.boiler.plate.app.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.boiler.plate.Utils.Constants;
import com.backend.boiler.plate.app.models.Role;
import com.backend.boiler.plate.app.models.User;
import com.backend.boiler.plate.app.models.UserInfo;
import com.backend.boiler.plate.app.repositories.UserRepository;
import com.backend.boiler.plate.authentication.BCryptPasswordEncoder;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAccessMenuGenerator userAccessMenuGenerator;
	
	public UserInfo getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = new UserInfo();
		Optional<User> useOptional = userRepository.findOneByUsername(request.getUserPrincipal().getName());
		if(useOptional.isPresent()) {
			userInfo.setUser(useOptional.get());
			userInfo.setAccessMenu(userAccessMenuGenerator.generateMenu(useOptional));
		}
		return userInfo;
	}
	
	public User addUser(User user) {
		try {
			Set<Role> roles = new HashSet<Role>();
			Role role = new Role();
			role.setId(2L);
			role.setName("ROLE_"+Constants.ROLE_USER);
			roles.add(role);
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRoles(roles);
			return userRepository.save(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
