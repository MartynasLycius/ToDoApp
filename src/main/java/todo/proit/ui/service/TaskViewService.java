package todo.proit.ui.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.exception.RecordNotFoundException;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.persistence.entity.Task;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
public interface TaskViewService {
    TaskDetailDto getById(long id) throws RecordNotFoundException;
    Page<TaskDetailDto> getAllPaginated() throws RecordNotFoundException;
    List<TaskDetailDto> getAll() throws RecordNotFoundException;
    Page<TaskDetailDto> searchPaginated(TaskSearchRequest request) throws RecordNotFoundException;
    List<TaskDetailDto> search(TaskSearchRequest request) throws RecordNotFoundException;

    TaskDetailDto create(TaskRequest request);
    TaskDetailDto update(TaskUpdateRequest request) throws RecordNotFoundException;
    TaskDetailDto updateStatus(long id, TaskStatus status) throws RecordNotFoundException;
}
