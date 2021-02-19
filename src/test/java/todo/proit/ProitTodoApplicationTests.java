package todo.proit;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import todo.proit.common.mapper.TaskDtoMapper;
import todo.proit.common.model.dto.TaskDto;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.persistence.entity.Task;
import todo.proit.persistence.service.TaskEntityService;

import java.util.List;

@SpringBootTest
class ProitTodoApplicationTests {

	@Autowired
	TaskEntityService taskEntityService;
	@Autowired
	TaskDtoMapper mapper;

	@Test
	public void testPagination(){
		Page<Task> taskPage = taskEntityService.getAllPaginated();
		Page<TaskDto> taskDtoPage = taskPage.map(mapper::mapToTaskDetailDto);
		List<TaskDto> taskDtoList = taskDtoPage.getContent();
		Assertions.assertTrue(CollectionUtils.isNotEmpty(taskDtoList));
	}

	@Test
	public void testPaginationSearch(){
		TaskSearchRequest request = (TaskSearchRequest) new TaskSearchRequest()
				.setName("UB")
				.setDescription("Call");
		Page<Task> taskPage = taskEntityService.searchAllPaginated(request);
		Page<TaskDto> taskDtoPage = taskPage.map(mapper::mapToTaskDetailDto);
		List<TaskDto> taskDtoList = taskDtoPage.getContent();
		Assertions.assertTrue(CollectionUtils.isNotEmpty(taskDtoList));
	}

}
