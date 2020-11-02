package com.seal.todoapp.com.seal.todoapp.controller;

import com.seal.todoapp.com.seal.todoapp.model.TodoRequest;
import com.seal.todoapp.com.seal.todoapp.service.TodoAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class TodoAppRestController {

    private final TodoAppService todoAppService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(todoAppService.list());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TodoRequest request) {
        log.info("create POST /rest/create with request `{}`", request);
        return ResponseEntity.ok(todoAppService.create(request));
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody TodoRequest request) {
        log.info("edit POST /rest/edit with request `{}`", request);
        return ResponseEntity.ok(todoAppService.edit(request));
    }

    @PostMapping("/mark-complete")
    public ResponseEntity<?> markComplete(@RequestBody TodoRequest request) {
        log.info("markComplete Post /rest/mark-complete request `{}`", request);
        return ResponseEntity.ok(todoAppService.markComplete(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("delete DELETE /rest/{id} for id `{}`", id);
        todoAppService.delete(id);
        return ResponseEntity.ok("");
    }

}
