package todo.proit.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.exception.BadRequestException;
import todo.proit.common.mapper.TaskDtoMapper;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.common.validation.group.UserAction;
import todo.proit.persistence.entity.Task;
import todo.proit.persistence.service.TaskEntityService;

import java.util.List;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskEntityService taskEntityService;
    private final TaskDtoMapper mapper;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated(UserAction.CREATE.class)
                                         @RequestBody TaskRequest request, BindingResult result){
        if(result.hasErrors())
            throw new BadRequestException(result);
        Task task = taskEntityService.create(request);
        TaskDetailDto taskDto = mapper.taskToTaskDetailDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody TaskUpdateRequest request, BindingResult result){
        if(result.hasErrors())
            throw new BadRequestException(result);
        Task task = taskEntityService.update(request);
        TaskDetailDto taskDto = mapper.taskToTaskDetailDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/mark-as-done/{id}")
    public ResponseEntity<?> markAsDone(@PathVariable long id){
        Task task = taskEntityService.updateStatus(id, TaskStatus.DONE);
        TaskDetailDto taskDto = mapper.taskToTaskDetailDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/mark-as-pending/{id}")
    public ResponseEntity<?> markAsPending(@PathVariable long id){
        Task task = taskEntityService.updateStatus(id, TaskStatus.PENDING);
        TaskDetailDto taskDto = mapper.taskToTaskDetailDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        Task task = taskEntityService.getById(id);
        TaskDetailDto taskDto = mapper.taskToTaskDetailDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<Task> taskPageModel = taskEntityService.getAll();
        List<TaskDto> taskDtos = mapper.taskToTaskDto(taskPageModel);
        return ResponseEntity.ok(taskDtos);
    }

    @GetMapping("/get-all/page")
    public ResponseEntity<?> getAllPaginated(){
        Page<Task> taskPageModel = taskEntityService.getAllPaginated();
        Page<TaskDto> taskDtos = taskPageModel.map(mapper::taskToTaskDto);
        return ResponseEntity.ok(taskDtos);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> searchPaginated(@Validated @RequestBody TaskSearchRequest request, BindingResult result){
        if(result.hasErrors())
            throw new BadRequestException(result);

        Page<Task> taskPageModel = taskEntityService.searchPaginated(request);
        Page<TaskDto> taskDtos = taskPageModel.map(mapper::taskToTaskDto);
        return ResponseEntity.ok(taskDtos);
    }
}
