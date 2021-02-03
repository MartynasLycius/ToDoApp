package com.todo;

import com.todo.controller.TaskRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.*;

@SpringBootTest(classes = ToDoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RepoTests {

	@Autowired
	private TaskRepository repository;

	@Test
	public void shouldFillOutComponentsWithDataWhenTheApplicationIsStarted() {
		then(this.repository.count()).isEqualTo(2);
	}

	@Test
	public void shouldFindOneTaskWithTask3() {
		then(this.repository.findBytaskNameStartsWithIgnoreCase("task3")).hasSize(2);
	}
}
