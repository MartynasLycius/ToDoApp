package dev.sayem.todoapp.domains.todoItem.controllers;

import dev.sayem.todoapp.domains.common.base.controllers.CrudWebController;
import dev.sayem.todoapp.domains.todoItem.models.dtos.TodoItemDto;
import dev.sayem.todoapp.domains.todoItem.models.entities.TodoItem;
import dev.sayem.todoapp.domains.todoItem.services.TodoItemService;
import dev.sayem.todoapp.routing.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoItemController implements CrudWebController<TodoItemDto> {
    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping(Route.SEARCH_TODO_ITEMS)
    @Override
    public String search(String query, int page, int size, Model model) {
        Page<TodoItem> items = this.todoItemService.search(query,page,size);

        model.addAttribute("items",items);
        return null;
    }

    @Override
    public String find(Long id) {
        return null;
    }

    @Override
    public String createPage(Model model) {
        return null;
    }

    @Override
    public String create(TodoItemDto dto) {
        return null;
    }

    @Override
    public String updatePage(Long id, Model model) {
        return null;
    }

    @Override
    public String update(Long id, TodoItemDto dto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
