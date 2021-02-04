package com.mir00r.todoapp.domains.todo.models.mappers;

import com.mir00r.todoapp.domains.common.models.mappers.BaseMapper;
import com.mir00r.todoapp.domains.todo.models.dtos.ToDoItemDto;
import com.mir00r.todoapp.domains.todo.models.entities.ToDoItem;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
@Component
public class ToDoItemMapper implements BaseMapper<ToDoItem, ToDoItemDto> {

    @Override
    public ToDoItemDto map(ToDoItem entity) {
        Objects.requireNonNull(entity);
        ToDoItemDto dto = new ToDoItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    @Override
    public ToDoItem map(ToDoItemDto dto, ToDoItem exEntity) {
        ToDoItem entity = exEntity;
        if (entity == null) entity = new ToDoItem();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate() == null ? new Date() : dto.getDate());
        return entity;
    }
}
