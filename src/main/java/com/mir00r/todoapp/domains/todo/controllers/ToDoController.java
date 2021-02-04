package com.mir00r.todoapp.domains.todo.controllers;

import com.mir00r.todoapp.Routing.Router;
import com.mir00r.todoapp.domains.common.controllers.BaseController;
import com.mir00r.todoapp.domains.todo.models.dtos.ToDoItemDto;
import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import com.mir00r.todoapp.domains.todo.models.mappers.ToDoItemMapper;
import com.mir00r.todoapp.domains.todo.services.ToDoItemService;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@RestController
public class ToDoController implements BaseController<ToDoItemDto> {

    private final ToDoItemService todoItemService;
    private final ToDoItemMapper todoItemMapper;

    @Autowired
    public ToDoController(ToDoItemService todoItemService, ToDoItemMapper todoItemMapper) {
        this.todoItemService = todoItemService;
        this.todoItemMapper = todoItemMapper;
    }

    @Override
    @GetMapping(Router.GET_TODO_ITEMS)
    public ResponseEntity<Page<ToDoItemDto>> search(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "0") int size
    ) {
        Page<ToDoItem> entities = this.todoItemService.search(query, page, size);
        return ResponseEntity.ok(entities.map(this.todoItemMapper::map));
    }

    @Override
    @GetMapping(Router.FIND_TODO_ITEMS)
    public ResponseEntity<ToDoItemDto> findItem(@PathVariable Long id) throws NotFoundException {
        ToDoItem entity = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(this.todoItemMapper.map(entity));
    }

    @Override
    @PostMapping(Router.CREATE_NEW_TODO_ITEMS)
    public ResponseEntity<ToDoItemDto> createItem(@Valid @RequestBody ToDoItemDto dto) {
        ToDoItem entity = this.todoItemService.save(this.todoItemMapper.map(dto, null));
        return ResponseEntity.ok(this.todoItemMapper.map(entity));
    }

    @Override
    @PatchMapping(Router.UPDATE_TODO_ITEMS)
    public ResponseEntity<ToDoItemDto> updateItem(@PathVariable Long id, @Valid @RequestBody ToDoItemDto dto) throws NotFoundException {
        ToDoItem entity = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        entity = this.todoItemMapper.map(dto, entity);
        return ResponseEntity.ok(this.todoItemMapper.map(entity));
    }

    @Override
    @DeleteMapping(Router.DELETE_TODO_ITEMS)
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) throws NotFoundException {
        this.todoItemService.delete(id, true);
        return ResponseEntity.ok().build();
    }
}
