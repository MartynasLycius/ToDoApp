package todo.proit.persistence.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import todo.proit.common.config.PageConfig;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.exception.RecordNotFoundException;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.persistence.entity.Task;
import todo.proit.persistence.mapper.TaskEntityMapper;
import todo.proit.persistence.repository.TaskRepository;
import todo.proit.persistence.spec.TaskSpecification;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Service
@RequiredArgsConstructor
public class TaskEntityServiceImpl implements TaskEntityService {

    private final TaskEntityMapper mapper;
    private final TaskRepository taskRepository;

    @Override
    public Task getById(long id) throws RecordNotFoundException {
        Task task = taskRepository.getDistinctById(id);
        if(task == null)
            throw new RecordNotFoundException("No task found for id "+ id);
        return task;
    }

    @Override
    public Page<Task> getAllPaginated() throws RecordNotFoundException {
        return taskRepository.findAll(
                TaskSpecification.getAllActiveSpecification(),
                PageConfig.getDefaultPageRequest()
        );
    }

    @Override
    public Page<Task> searchPaginated(TaskSearchRequest request) throws RecordNotFoundException {
        Specification<Task> taskSpecification = TaskSpecification.getSearchSpecification(request);
        PageRequest pageRequest = PageConfig.getPageRequest(
                request.getPaginationRequest().getPageNumber(),
                request.getPaginationRequest().getPageSize()
        );
        return taskRepository.findAll(taskSpecification, pageRequest);
    }

    @Override
    public List<Task> search(TaskSearchRequest request) throws RecordNotFoundException {
        Specification<Task> taskSpecification = TaskSpecification.getSearchSpecification(request);
        return taskRepository.findAll(taskSpecification);
    }

    @Override
    public List<Task> getAll() throws RecordNotFoundException {
        return taskRepository.findAll(TaskSpecification.getAllActiveSpecification());
    }


    @Override
    public Task create(TaskRequest request) {
        Task task = mapper.mapToNewTask(request);
        return taskRepository.save(task);
    }

    @Override
    public Task update(TaskUpdateRequest request) throws RecordNotFoundException {
        Task task = this.getById(request.getId());
        Task taskToUpdate = mapper.mapToUpdatedTask(task, request);
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Task updateStatus(long id, TaskStatus status) throws RecordNotFoundException {
        Task task = this.getById(id);
        Task taskToUpdate = mapper.mapToUpdatedTask(task, status);
        return taskRepository.save(taskToUpdate);
    }
}
