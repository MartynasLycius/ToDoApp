package com.hafiz.interview.east.netic.todo.services;

import com.hafiz.interview.east.netic.todo.core.crud.CrudService;
import com.hafiz.interview.east.netic.todo.dataclass.common.HeaderDTO;
import com.hafiz.interview.east.netic.todo.entities.TodoItem;
import com.hafiz.interview.east.netic.todo.repositories.TodoItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService extends CrudService<TodoItem> {

    private final TodoItemRepository repository;
    private final HeaderDTO headerDTO;

    public TodoItemService(TodoItemRepository repository, HeaderDTO headerDTO) {
        super(repository);
        this.repository = repository;
        this.headerDTO = headerDTO;
    }

    public Page<TodoItem> getListByUserId(Long page, Long size) {
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        return repository.findAllByUserId(headerDTO.getUserId(), pageable);
    }
}
