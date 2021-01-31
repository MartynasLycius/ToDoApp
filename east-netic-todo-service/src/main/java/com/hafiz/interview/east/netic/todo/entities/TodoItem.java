package com.hafiz.interview.east.netic.todo.entities;

import com.hafiz.interview.east.netic.todo.core.crud.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "todo")
public class TodoItem extends BaseEntity {
    private UUID userId;
    private String title;
    private String description;
    private LocalDateTime date;
}
