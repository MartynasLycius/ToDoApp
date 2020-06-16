package com.mydomain.testingweb;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.mydomain.controllers.TodoController;
import com.mydomain.models.TodoItem;
import com.mydomain.services.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoService;

	@Test
	public void view() throws Exception {
		TodoItem newTodoItem = new TodoItem();
		newTodoItem.setId(1l);
		newTodoItem.setDate(new Date());
		newTodoItem.setName("Test TODO");
		newTodoItem.setDescription("Test Desc");

		Mockito.when(todoService.getTodoItemById(1l)).thenReturn(newTodoItem);
		Mockito.when(todoService.getTodoItemById(2l)).thenThrow(RuntimeException.class);

		mockMvc.perform(get("/todo/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/todo/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	public void edit() throws Exception {
		TodoItem newTodoItem = new TodoItem();
		newTodoItem.setId(1l);
		newTodoItem.setDate(new Date());
		newTodoItem.setName("Test TODO");
		newTodoItem.setDescription("Test Desc");

		Mockito.when(todoService.getTodoItemById(1l)).thenReturn(newTodoItem);
		Mockito.when(todoService.getTodoItemById(2l)).thenThrow(RuntimeException.class);

		mockMvc.perform(get("/todo/edit/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/todo/edit/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	public void add() throws Exception {

		mockMvc.perform(get("/todo/add").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/todo/add/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isNotFound());

	}

	@Test
	public void list() throws Exception {

		mockMvc.perform(get("/todo/list").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

	}

}