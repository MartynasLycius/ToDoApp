package com.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ToDoApplication {

	private static final Logger log = LoggerFactory.getLogger(ToDoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class);
	}
}
