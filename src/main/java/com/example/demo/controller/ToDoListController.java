package com.example.demo.controller;


import com.example.demo.model.TodoItem;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/todos")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ToDoListController {

    private final ItemService itemService;

    @GetMapping
    public Page<TodoItem> list(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TodoItem> pageResult = itemService.findAll(pageRequest);
        return pageResult;
    }

    @PostMapping
    public ResponseEntity<TodoItem> create(@RequestBody @Valid  TodoItem todoRequest) {
        TodoItem saved = itemService.save(todoRequest);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoItem> update(@RequestBody @Valid  TodoItem todoRequest, @PathVariable("id") Long id) {
        TodoItem saved = itemService.update(todoRequest, id);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<TodoItem> fetch( @PathVariable("id") Long id) {
        TodoItem item = itemService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<TodoItem> delete( @PathVariable("id") Long id) {
        TodoItem item = itemService.deleteById(id);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

}
