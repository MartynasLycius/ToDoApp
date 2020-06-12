package dev.sayem.todoapp.domains.todoItem.models.mappers;

import dev.sayem.todoapp.domains.common.base.models.mappers.BaseMapper;
import dev.sayem.todoapp.domains.todoItem.models.dtos.TodoItemDto;
import dev.sayem.todoapp.domains.todoItem.models.entities.TodoItem;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class TodoItemMapper implements BaseMapper<TodoItem, TodoItemDto> {

    @Override
    public TodoItemDto map(TodoItem entity) {
        Objects.requireNonNull(entity);
        TodoItemDto dto = new TodoItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    @Override
    public TodoItem map(TodoItemDto dto, TodoItem exEntity) {
        TodoItem entity = exEntity;
        if (entity == null) entity = new TodoItem();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate() == null ? new Date() : dto.getDate());
        return entity;
    }
}
