package com.mir00r.todoapp.domains.todo.services.beans;

import com.mir00r.todoapp.commons.PageAttr;
import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import com.mir00r.todoapp.domains.todo.repositories.ToDoItemRepository;
import com.mir00r.todoapp.domains.todo.services.ToDoItemService;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@Service
public class ToDoItemServiceBean implements ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoItemServiceBean(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @Override
    public Page<ToDoItem> search(String query, int page, int size) {
        return this.toDoItemRepository.search(query, PageAttr.getPageRequest(page, size));
    }

    @Override
    public ToDoItem save(ToDoItem entity) {
        return this.toDoItemRepository.save(entity);
    }

    @Override
    public Optional<ToDoItem> find(Long id) {
        return this.toDoItemRepository.find(id);
    }

    @Override
    public void delete(Long id, Boolean softDelete) throws NotFoundException {
        if (softDelete) {
            ToDoItem item = this.toDoItemRepository.find(id).orElseThrow(NotFoundException::new);
            item.setDeleted(true);
            this.toDoItemRepository.save(item);
            return;
        }
        this.toDoItemRepository.deleteById(id);
    }
}
