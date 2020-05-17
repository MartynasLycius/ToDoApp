package com.todo.services;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.todo.persistence.model.Todo;
import com.todo.service.TodoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class TodoServiceTest {
	
	private final static String URI = "/api/v1/todos";
	
	@MockBean
	private TodoService todoService;
	
	@Autowired
	private WebApplicationContext webAppCon;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppCon).build();
	}
	
	@Test
	public void testGetATodo() throws Exception {

		// Given
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setTitle("Task one");
		todo.setDescription("Task one description");
		todo.setDueDate(LocalDate.of(2020, 05, 20));
		
		Clock clock = Clock.systemDefaultZone();
		
		todo.setCreatedAt(Instant.now(clock));
		todo.setUpdatedAt(Instant.now(clock));

		given(todoService.findById(todo.getId())).willReturn(todo);

		// Get a Todo
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1/todos/{id}", 1L)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

	}
	
	@Test
	public void testGetAllTodos() throws Exception {

		// given
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setTitle("Task one");
		todo.setDescription("Task one description");
		todo.setDueDate(LocalDate.of(2020, 05, 25));

		List<Todo> todos = Arrays.asList(todo);
		given(todoService.getTodos()).willReturn(todos);

		// Get All Todos
		mockMvc.perform(get(URI)).andExpect(status().isOk())
				.andExpect(content().json("[{'id':1,'title':'Task one','description':'Task one description','dueDate':'2020-05-25'}]"));

	}

}
