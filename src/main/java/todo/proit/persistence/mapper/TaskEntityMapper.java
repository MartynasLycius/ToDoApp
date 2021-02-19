package todo.proit.persistence.mapper;

import org.springframework.stereotype.Component;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.enums.YesNoEnum;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.persistence.entity.Task;

import java.util.Date;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Component
public class TaskEntityMapper {

    public Task mapToNewTask(TaskRequest request) {
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING.getCode());
        task.setCreatedDate(new Date());
        task.setIsActive(YesNoEnum.YES.getCode());
        return task;
    }

    public Task mapToUpdatedTask(Task task, TaskRequest request) {
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        return task;
    }

    public Task mapToUpdatedTask(Task task, TaskStatus status) {
        task.setStatus(status.getCode());
        return task;
    }
}
