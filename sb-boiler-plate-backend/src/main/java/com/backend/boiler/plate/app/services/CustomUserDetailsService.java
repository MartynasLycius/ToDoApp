package com.backend.boiler.plate.app.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.boiler.plate.Utils.Constants;
import com.backend.boiler.plate.app.models.User;
import com.backend.boiler.plate.app.repositories.UserRepository;

@Service
@Transactional(value = "masterTransactionManager", readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private Constants constants = Constants.getInstance();

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			Optional<User> user = userRepository.findOneByUsernameIgnoreCase(username);
			if (!user.isPresent()) {
				throw new UsernameNotFoundException("user not found");
			}
			return user.get();
		} catch (Exception e) {
			logger.error("ERROR During loading User ", e);
		}
		return null;
	}
}