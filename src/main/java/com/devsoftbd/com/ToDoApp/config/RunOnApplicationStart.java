package com.devsoftbd.com.ToDoApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.devsoftbd.com.ToDoApp.models.UsersModel;
import com.devsoftbd.com.ToDoApp.repositories.UsersRepository;

@Component
public class RunOnApplicationStart implements CommandLineRunner {
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Insert an user into users table when application start.
	@Override
	public void run(String... args) throws Exception {
		UsersModel user = new UsersModel();
		user.setEnabled(true);
		user.setFirstName("Palash Kumar");
		user.setLastName("Nath");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));

		userRepository.save(user);

	}
}
