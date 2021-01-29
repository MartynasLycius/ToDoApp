package com.mir00r.todoapp.domains.todo.controllers;

import com.mir00r.todoapp.Routing.Router;
import com.mir00r.todoapp.commons.Constants;
import com.mir00r.todoapp.domains.common.controllers.BaseWebController;
import com.mir00r.todoapp.domains.todo.models.dtos.ToDoItemDto;
import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import com.mir00r.todoapp.domains.todo.models.mappers.ToDoItemMapper;
import com.mir00r.todoapp.domains.todo.services.ToDoItemService;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@Controller
public class ToDoWebController implements BaseWebController<ToDoItemDto> {
    private final ToDoItemService todoItemService;
    private final ToDoItemMapper todoItemMapper;

    @Autowired
    public ToDoWebController(ToDoItemService todoItemService, ToDoItemMapper todoItemMapper) {
        this.todoItemService = todoItemService;
        this.todoItemMapper = todoItemMapper;
    }

    @Override
    @GetMapping(Router.SEARCH_TODO_ITEMS)
    public String search(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "0") int size,
            Model model
    ) {

        Page<ToDoItem> items = this.todoItemService.search(query, page, size);

        model.addAttribute("query", query);
        model.addAttribute("items", items.map(this.todoItemMapper::map));
        return Constants.GET_ALL_TODO_ITEMS_PAGE_URL;
    }

    @Override
//    @GetMapping(Router.UPDATE_TODO_ITEM)
    public String find(@PathVariable("id") Long id) throws NotFoundException {
        ToDoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        return Constants.GET_SINGLE_TODO_ITEMS_PAGE_URL;
    }

    @Override
    @GetMapping(Router.CREATE_TODO_ITEMS_PAGE)
    public String createPage(Model model) {
        model.addAttribute("actionUrl", "/todos");
        return Constants.GET_CREATE_UPDATE_TODO_ITEMS_PAGE_URL;
    }

    @Override
    @PostMapping(Router.CREATE_TODO_ITEMS)
    public String create(@Valid @ModelAttribute ToDoItemDto dto) {
        ToDoItem item = this.todoItemMapper.map(dto, null);
        this.todoItemService.save(item);
        return "redirect:" + Router.SEARCH_TODO_ITEMS;
    }

    @Override
    @PostMapping(Router.UPDATE_TODO_ITEM_PAGE)
    public String updatePage(@PathVariable("id") Long id, Model model) throws NotFoundException {
        ToDoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        model.addAttribute("item", this.todoItemMapper.map(item));
        model.addAttribute("actionUrl", "/todos/" + item.getId());
        return Constants.GET_CREATE_UPDATE_TODO_ITEMS_PAGE_URL;
    }

    @Override
    @PostMapping(Router.UPDATE_TODO_ITEM)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute ToDoItemDto dto) throws NotFoundException {
        ToDoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        item = this.todoItemMapper.map(dto, item);
        this.todoItemService.save(item);
        return "redirect:" + Router.SEARCH_TODO_ITEMS;
    }

    @Override
    @PostMapping(Router.DELETE_TODO_ITEM)
    public String delete(@PathVariable("id") Long id) throws NotFoundException {
        this.todoItemService.delete(id, true);
        return "redirect:" + Router.SEARCH_TODO_ITEMS;
    }
}
