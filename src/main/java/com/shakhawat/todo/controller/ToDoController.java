package com.shakhawat.todo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shakhawat.todo.validator.ToDoValidator;
import com.shakhawat.todo.model.ToDo;
import com.shakhawat.todo.service.ToDoService;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;
	
	@Autowired
    private ToDoValidator toDoValidator;
	
	// Display List of ToDo
	@GetMapping("/")
	public ModelAndView allToDos(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
		ModelAndView mav = new ModelAndView();
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<ToDo> allToDos = toDoService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        
        mav.addObject("toDoList", allToDos);

        int totalPages = allToDos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }
        mav.setViewName("todo/todo_list");
        
		return mav;
	}
	
	// Create ToDo
	@GetMapping("/createToDo")
	public ModelAndView createToDoForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("todo", new ToDo());
		mav.setViewName("todo/todo_add");
		return mav;
	}
	
	// Save ToDo
	@Transactional
	@PostMapping("/saveToDo")
	public String saveToDo(@ModelAttribute("todo") @Validated ToDo todo, BindingResult bindingResult, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Model model) {
		
		toDoValidator.validate(todo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("todo", todo);
			return "todo/todo_add";
		}
		
		toDoService.saveToDo(todo);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("message", "Data saved successfully!");
		
		return "redirect:/";
	}
	
	// Edit ToDo
	@GetMapping("/editToDo")
	public ModelAndView editToDo(@ModelAttribute("id") String id, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		ToDo todo = new ToDo();
		try {
			todo = toDoService.findToDoById(id);
		} catch (Exception e) {
			mav.addObject("error", true);
			mav.addObject("message", e.getMessage());
			mav.addObject("todo", todo);
			mav.setViewName("todo/todo_edit");
			return mav;
		}		
		mav.addObject("todo", todo);
		mav.setViewName("todo/todo_edit");
		return mav;
	}
	
	// Update ToDo
	@Transactional
	@PostMapping("/updateToDo")
	public String updateToDo(@ModelAttribute("todo") @Validated ToDo todo, BindingResult bindingResult, SessionStatus sessionStatus, RedirectAttributes redirectAttributes, Model model) {
		
		toDoValidator.validate(todo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("todo", todo);
			return "todo/todo_edit";
		}
		
		try {
			toDoService.updateToDo(todo);
		} catch (Exception e) {
			model.addAttribute("error", true);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("todo", todo);
			return "todo/todo_edit";
		}
		
		toDoService.updateToDo(todo);
		sessionStatus.setComplete();
		
		redirectAttributes.addFlashAttribute("message", "Data updated successfully!");
		return "redirect:/";
	}

}
