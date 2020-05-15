package com.proit.todo.api.mapper;

import com.proit.todo.api.dto.task.TaskDetailedDto;
import com.proit.todo.api.dto.task.TaskSummaryDto;
import com.proit.todo.core.persistence.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class TaskMapper {
    public abstract TaskDetailedDto toDetailsDto(Task task);

    public abstract TaskSummaryDto toSummeryDto(Task task);

    public List<TaskSummaryDto> toSummeryDto(List<Task> tasks){
        return tasks.stream()
                    .map(this::toSummeryDto)
                    .collect(Collectors.toList());
    }
}
