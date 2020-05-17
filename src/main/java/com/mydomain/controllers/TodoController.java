package com.mydomain.controllers;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.mydomain.models.TodoItem;
import com.mydomain.services.TodoService;
import com.mydomain.services.PagedTodoItems;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping(path="/{id}", produces="application/json")
	@ResponseBody
	public TodoItem viewJson(@PathVariable(name="id") Long id, Model model) {
		return todoService.getTodoItemById(id);
	}
	
	@PostMapping(value = "/")
	public RedirectView save(
			@ModelAttribute("todo") TodoItem todo) {
		todo.setDate(new Date());
		todoService.saveTodoItem(todo);
		return new RedirectView("/todo/list");
	}

	@DeleteMapping("/{id}")
	public RedirectView delete(@PathVariable(name="id") Long id, Model model) {
		todoService.deleteTodoItemById(id);
		return new RedirectView("/todo/list");
	}
	
	@GetMapping(path="/{id}")
	public String view(@PathVariable(name="id") Long id, Model model) {
		model.addAttribute("todo", todoService.getTodoItemById(id));
		return "todoView";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name="id") Long id, Model model) {
		TodoItem todo = todoService.getTodoItemById(id);
		model.addAttribute("todo", todo);
		return "todoForm";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("todo", new TodoItem());
		return "todoForm";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		return "todoList";
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public List<TodoItem> listTodo(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize, @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
		return  todoService.getTodoItems(page, pageSize, sortBy);
	}
	
	
	@GetMapping(value = "/listdemo")
	@ResponseBody
	public PagedTodoItems listTodoDemo(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "3") Integer pageSize, @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
		
		
		
		return todoService.getTodosPage(page-1, pageSize, sortBy);
	}
	
}