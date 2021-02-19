package todo.proit.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.exception.RecordNotFoundException;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.persistence.entity.Task;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
public interface TaskEntityService {
    @Transactional(readOnly = true)
    Task getById(long id) throws RecordNotFoundException;
    @Transactional(readOnly = true)
    Page<Task> getAllPaginated() throws RecordNotFoundException;
    @Transactional(readOnly = true)
    List<Task> getAll() throws RecordNotFoundException;
    @Transactional(readOnly = true)
    Page<Task> searchPaginated(TaskSearchRequest request) throws RecordNotFoundException;
    @Transactional(readOnly = true)
    List<Task> search(TaskSearchRequest request) throws RecordNotFoundException;

    @Transactional
    Task create(TaskRequest request);
    @Transactional
    Task update(TaskUpdateRequest request) throws RecordNotFoundException;
    @Transactional
    Task updateStatus(long id, TaskStatus status) throws RecordNotFoundException;
}
