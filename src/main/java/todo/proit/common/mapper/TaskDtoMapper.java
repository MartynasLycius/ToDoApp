package todo.proit.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import todo.proit.common.config.DateFormats;
import todo.proit.common.enums.StatusEnum;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.persistence.entity.Task;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/18/2021
 */

@Mapper(componentModel = "spring", imports = {StatusEnum.class})
public interface TaskDtoMapper {

    @Mapping(target = "status", expression = "java(StatusEnum.getValueByCode(task.getStatus()))")
    TaskDto mapToTaskDto(Task task);
    List<TaskDto> mapToTaskDtoList(List<Task> taskList);


    @Mappings({
        @Mapping(target = "status", expression = "java(StatusEnum.getValueByCode(task.getStatus()))"),
        @Mapping(target = "createdAt", source = "createdDate", dateFormat = DateFormats.APP_DATE_FORMAT)
    })
    @Named("task_detail_dto_mapper")
    TaskDetailDto mapToTaskDetailDto(Task task);
}
