package com.todo.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.persistence.model.Todo;
import com.todo.service.TodoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TodoResourceTest {

	private final static String URI = "/api/v1/todos";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private TodoService todoService;
	
	
	@Test
	void createTodo_whenValidInput_thenReturns200() throws Exception {
		
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setTitle("Task one");
		todo.setDescription("Task one description");
		todo.setDueDate(LocalDate.of(2020, 05, 20));
		
		 this.mockMvc.perform(post(URI)
		            .content(objectMapper.writeValueAsString(todo))
		            .contentType(MediaType.APPLICATION_JSON_VALUE))
		            .andExpect(status().isOk());
	}
	
	@Test
	void createTodo_whenNullInput_thenReturns400() throws Exception {
		
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setTitle(null);
		todo.setDescription("Task one description");
		todo.setDueDate(LocalDate.of(2020, 05, 20));
	  
		 mockMvc.perform(post(URI)
		            .content(objectMapper.writeValueAsString(todo))
		            .contentType("application/json"))
		            .andExpect(status().isBadRequest());
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
