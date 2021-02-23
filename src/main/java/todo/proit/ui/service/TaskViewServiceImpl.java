package todo.proit.ui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.exception.RecordNotFoundException;
import todo.proit.common.mapper.TaskDtoMapper;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.persistence.entity.Task;
import todo.proit.persistence.service.TaskEntityService;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
@Service
@RequiredArgsConstructor
public class TaskViewServiceImpl implements TaskViewService {

    private final TaskEntityService taskEntityService;
    private final TaskDtoMapper mapper;

    @Override
    public TaskDetailDto getById(long id) throws RecordNotFoundException {
        Task task = taskEntityService.getById(id);
        return mapper.taskToTaskDetailDto(task);
    }

    @Override
    public Page<TaskDetailDto> getAllPaginated() throws RecordNotFoundException {
        Page<Task> taskPage = taskEntityService.getAllPaginated();
        return taskPage.map(mapper::taskToTaskDetailDto);
    }

    @Override
    public List<TaskDetailDto> getAll() throws RecordNotFoundException {
        List<Task> taskList = taskEntityService.getAll();
        return mapper.taskToTaskDetailDto(taskList);
    }

    @Override
    public Page<TaskDetailDto> searchPaginated(TaskSearchRequest request) throws RecordNotFoundException {
        Page<Task> taskPage = taskEntityService.searchPaginated(request);
        return taskPage.map(mapper::taskToTaskDetailDto);
    }

    @Override
    public List<TaskDetailDto> search(TaskSearchRequest request) throws RecordNotFoundException {
        List<Task> taskList = taskEntityService.search(request);
        return mapper.taskToTaskDetailDto(taskList);
    }

    @Override
    public TaskDetailDto create(TaskRequest request) {
        Task newTask = taskEntityService.create(request);
        return mapper.taskToTaskDetailDto(newTask);
    }

    @Override
    public TaskDetailDto update(TaskUpdateRequest request) throws RecordNotFoundException {
        Task taskUpdated = taskEntityService.update(request);
        return mapper.taskToTaskDetailDto(taskUpdated);
    }

    @Override
    public TaskDetailDto updateStatus(long id, TaskStatus status) throws RecordNotFoundException {
        Task taskUpdated= taskEntityService.updateStatus(id, status);
        return mapper.taskToTaskDetailDto(taskUpdated);
    }
}
