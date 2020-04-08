package com.devsoftbd.com.ToDoApp.controllers;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devsoftbd.com.ToDoApp.dto.ReturnObject;
import com.devsoftbd.com.ToDoApp.enums.TodoItemStatus;
import com.devsoftbd.com.ToDoApp.models.TodoItemsModel;
import com.devsoftbd.com.ToDoApp.models.UsersModel;
import com.devsoftbd.com.ToDoApp.services.TodoItemsService;

@RequestMapping("/todo-items")
@Controller
public class TodoItemsController {
	@Autowired
	private TodoItemsService todoItemService;
	@Autowired
	private MessageSource msgSource;

	@GetMapping("")
	public String findAll(Model model, @ModelAttribute("status") String status,
			@ModelAttribute("message") String message) {
		if (!StringUtils.isEmpty(status)) {
			model.addAttribute("status", status);
			model.addAttribute("message", message);
		}
		model.addAttribute("statusList",
				Stream.of(TodoItemStatus.values()).map(TodoItemStatus::name).collect(Collectors.toList()));
		return "/todoitems/view-todo-items";
	}

	@GetMapping("/add")
	public String addTodoItem(Model model) {
		return "/todoitems/add-todo-item";
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<TodoItemsModel>> todoList(@RequestParam("status") Optional<TodoItemStatus> status,
			@RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Date> startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<Date> endDate) {
		List<TodoItemsModel> todoList = todoItemService.findTodoList(status.isPresent() ? status.get() : null,
				startDate.isPresent() ? startDate.get() : null, endDate.isPresent() ? endDate.get() : null);
		if (!StringUtils.isEmpty(todoList))
			return new ResponseEntity<>(todoList, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/add", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<?> addTodoItem(@RequestBody Optional<TodoItemsModel> todoItemModel, Locale locale) {
		if (todoItemModel.isPresent()) {
			UsersModel user = (UsersModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			todoItemModel.get().setUser(user);
			TodoItemsModel persistModel = todoItemService.save(todoItemModel.get());
			if (persistModel != null) {
				return new ResponseEntity<>(persistModel, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(msgSource.getMessage("system.message.invalidParam", null, locale),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/update")
	public String update(@Valid @ModelAttribute Optional<TodoItemsModel> todoItem, RedirectAttributes redirectAttr,
			Locale locale) {
		if (todoItem.isPresent()) {
			ReturnObject ro = todoItemService.update(todoItem.get(), locale);
			if (ro.isSuccessfull()) {
				redirectAttr.addFlashAttribute("status", "success");
				redirectAttr.addFlashAttribute("message", msgSource.getMessage("system.message.todoItemUpdated",
						new String[] { todoItem.get().getItemTitle() }, locale));
			} else {
				redirectAttr.addFlashAttribute("status", "error");
				redirectAttr.addFlashAttribute("message", ro.getMessage());
			}
		} else {
			redirectAttr.addFlashAttribute("status", "error");
			redirectAttr.addFlashAttribute("message",
					msgSource.getMessage("system.message.invalidParam", null, locale));
		}
		return "redirect:/todo-items";
	}

	@GetMapping("/{itemId}")
	public String getTodoById(@PathVariable("itemId") Optional<Integer> itemId, Model model, Locale locale) {
		if (itemId.isPresent()) {
			TodoItemsModel todoItem = todoItemService.getById(itemId.get());
			if (todoItem != null)
				model.addAttribute("todoItem", todoItem);
			model.addAttribute("statusList",
					Stream.of(TodoItemStatus.values()).map(TodoItemStatus::name).collect(Collectors.toList()));
		}
		return "/todoitems/edit-todo-item";
	}

	@GetMapping("/delete/{itemId}")
	@ResponseBody
	public ResponseEntity<?> deleteById(@PathVariable("itemId") Optional<Integer> itemId, Locale locale) {
		if (itemId.isPresent()) {
			if (todoItemService.deleteByItemId(itemId.get())) {
				return new ResponseEntity<>(msgSource.getMessage("system.message.todoItemDeleted", null, locale),
						HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(msgSource.getMessage("system.message.invalidParam", null, locale),
				HttpStatus.BAD_REQUEST);
	}
}
