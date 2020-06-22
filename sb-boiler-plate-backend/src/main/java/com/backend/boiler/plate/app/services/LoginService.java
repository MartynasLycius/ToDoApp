package com.backend.boiler.plate.app.services;

import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.boiler.plate.app.models.OauthClient;
import com.backend.boiler.plate.app.models.User;
import com.backend.boiler.plate.app.repositories.ClientEntityRepository;
import com.backend.boiler.plate.app.repositories.UserRepository;
import com.backend.boiler.plate.authentication.BCryptPasswordEncoder;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ClientEntityRepository clientEntityRepository;
	
	public String getToken(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameterMap().containsKey("username") && request.getParameterMap().containsKey("password") && !request.getParameter("username").equals("") && !request.getParameter("password").equals("")) {
			Optional<User> user = userRepository.findOneByUsernameIgnoreCase(request.getParameter("username"));
			if (user.isPresent()) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if(bCryptPasswordEncoder.matches(request.getParameter("password"), user.get().getPassword())) {
					OauthClient oauthClient = clientEntityRepository.findOneByAdditionalInformation("{\"client\": \"default\"}");
					response.setContentType("application/json");
					response.setStatus(HttpServletResponse.SC_OK);
					return "{ \"token\": \""+Base64.getEncoder().encodeToString((oauthClient.getClientId()+":"+oauthClient.getClientSecret()).getBytes())+"\"}";
				}
			}
		}
		return "";
	}
}
