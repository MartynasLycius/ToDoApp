package dev.sayem.todoapp.domains.todoItem.controllers;

import dev.sayem.todoapp.domains.common.base.controllers.CrudWebController;
import dev.sayem.todoapp.domains.todoItem.models.dtos.TodoItemDto;
import dev.sayem.todoapp.domains.todoItem.models.entities.TodoItem;
import dev.sayem.todoapp.domains.todoItem.models.mappers.TodoItemMapper;
import dev.sayem.todoapp.domains.todoItem.services.TodoItemService;
import dev.sayem.todoapp.exceptions.NotFoundException;
import dev.sayem.todoapp.routing.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TodoItemController implements CrudWebController<TodoItemDto> {
    private final TodoItemService todoItemService;
    private final TodoItemMapper todoItemMapper;

    @Autowired
    public TodoItemController(TodoItemService todoItemService, TodoItemMapper todoItemMapper) {
        this.todoItemService = todoItemService;
        this.todoItemMapper = todoItemMapper;
    }

    @GetMapping(Route.SEARCH_TODO_ITEMS)
    @Override
    public String search(@RequestParam(value = "query", defaultValue = "") String query,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "0") int size,
                         Model model) {
        Page<TodoItem> items = this.todoItemService.search(query, page, size);

        model.addAttribute("query", query);
        model.addAttribute("items", items.map(this.todoItemMapper::map));
        return "fragments/todo/list";
    }

    @Override
    public String find(@PathVariable("id") Long id) throws NotFoundException {
        TodoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        return "fragments/details";
    }

    @GetMapping(Route.CREATE_TODO_ITEMS_PAGE)
    @Override
    public String createPage(Model model) {
        model.addAttribute("actionUrl", "/todos");
        return "fragments/todo/create_update";
    }

    @PostMapping(Route.CREATE_TODO_ITEMS)
    @Override
    public String create(@Valid @ModelAttribute TodoItemDto dto) {
        TodoItem item = this.todoItemMapper.map(dto, null);
        this.todoItemService.save(item);
        return "redirect:" + Route.SEARCH_TODO_ITEMS;
    }


    @GetMapping(Route.UPDATE_TODO_ITEM_PAGE)
    @Override
    public String updatePage(@PathVariable("id") Long id,
                             Model model) throws NotFoundException {
        TodoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        model.addAttribute("item", this.todoItemMapper.map(item));
        model.addAttribute("actionUrl", "/todos/" + item.getId());
        return "fragments/todo/create_update";
    }

    @PostMapping(Route.UPDATE_TODO_ITEM)
    @Override
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute TodoItemDto dto) throws NotFoundException {
        TodoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        item = this.todoItemMapper.map(dto, item);
        this.todoItemService.save(item);
        return "redirect:" + Route.SEARCH_TODO_ITEMS;
    }

    @PostMapping(Route.DELETE_TODO_ITEM)
    @Override
    public String delete(@PathVariable("id") Long id) throws NotFoundException {
        this.todoItemService.delete(id, true);
        return "redirect:" + Route.SEARCH_TODO_ITEMS;
    }
}
