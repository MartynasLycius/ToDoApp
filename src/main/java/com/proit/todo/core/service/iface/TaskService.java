package com.proit.todo.core.service.iface;

import com.proit.todo.core.Form.task.TaskCreateForm;
import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.Form.task.TaskUpdateForm;
import com.proit.todo.core.constant.Enums;
import com.proit.todo.core.persistence.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Task getById(int id,boolean throwExceptionIfNotFound);
    Task getById(int id);
    Task create(TaskCreateForm form);
    Task update(TaskUpdateForm form);
    Task updateState(int id, Enums.TASK_STATE state);
    Page<Task> getAllBySearchCriteria(TaskSearchForm taskSearchForm);
}
