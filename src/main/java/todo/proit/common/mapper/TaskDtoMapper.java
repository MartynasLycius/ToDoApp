package todo.proit.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import todo.proit.common.config.DateFormats;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.persistence.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dipanjal
 * @since 2/18/2021
 */

@Mapper(componentModel = "spring", imports = {TaskStatus.class})
public interface TaskDtoMapper {

    @Mapping(target = "status", expression = "java(TaskStatus.getValueByCode(task.getStatus()))")
    TaskDto taskToTaskDto(Task task);
    List<TaskDto> taskToTaskDto(List<Task> taskList);


    @Mappings({
        @Mapping(target = "status", expression = "java(TaskStatus.getValueByCode(task.getStatus()))"),
        @Mapping(target = "createdAt", source = "createdDate", dateFormat = DateFormats.APP_DATE_FORMAT)
    })
    @Named("task_detail_dto_mapper")
    TaskDetailDto taskToTaskDetailDto(Task task);
    default List<TaskDetailDto> taskToTaskDetailDto(List<Task> taskList){
        return taskList
                .stream()
                .map(this::taskToTaskDetailDto)
                .collect(Collectors.toList());
    }
}
