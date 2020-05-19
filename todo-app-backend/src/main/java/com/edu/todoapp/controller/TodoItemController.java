package com.edu.todoapp.controller;

import com.edu.todoapp.entity.TodoItem;
import com.edu.todoapp.model.RestResponse;
import com.edu.todoapp.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping("/todo-item")
    public ResponseEntity<?> saveTodoItem(@RequestBody TodoItem todoItem) {
        try {
            todoItemService.create(todoItem);
            return ResponseEntity.ok(new RestResponse(true, "success"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create record !!!");
        }
    }

    @PutMapping("/todo-item")
    public ResponseEntity<?> updateTodoItem(@RequestBody TodoItem todoItem) {
        try {
            todoItemService.update(todoItem);
            return ResponseEntity.ok(new RestResponse(true, "success"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot update record !!!");
        }
    }

    @PutMapping("/todo-item/done")
    public ResponseEntity<?> doneTodoItem(@RequestParam Integer id) {
        try {
            todoItemService.updateTodoStatus(id, 'Y');
            return ResponseEntity.ok(new RestResponse(true, "success"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot update record !!!");
        }
    }

    @PutMapping("/todo-item/make-todo")
    public ResponseEntity<?> makeTodoItem(@RequestParam Integer id) {
        try {
            todoItemService.updateTodoStatus(id, 'N');
            return ResponseEntity.ok(new RestResponse(true, "success"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot update record !!!");
        }
    }

    @GetMapping("/todo-item")
    public ResponseEntity<?> getTodoItemById(@RequestParam Integer id) {
        return ResponseEntity.ok(todoItemService.findById(id)
                .orElse(new TodoItem()));
    }

    @DeleteMapping("/todo-item")
    public ResponseEntity<?> deleteTodoItemById(@RequestParam Integer id) {
        try {
            todoItemService.deleteById(id);
            return ResponseEntity.ok(new RestResponse(true, "success"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot update record !!!");
        }
    }


    @GetMapping("/todo-item-list")
    public ResponseEntity<?> getTodoItemList() {
        return ResponseEntity.ok(todoItemService.findAll().stream()
                .filter(o-> !o.getStatus().equals('Y'))
                .sorted(Comparator.comparing(TodoItem::getItemDate))
                .collect(Collectors.toList()));
    }


    @GetMapping("/todo-item-list/done")
    public ResponseEntity<?> getTodoItemDoneList() {
        return ResponseEntity.ok(todoItemService.findAll().stream()
                .filter(o->o.getStatus().equals('Y'))
                .sorted(Comparator.comparing(TodoItem::getItemDate))
                .collect(Collectors.toList()));
    }


}
