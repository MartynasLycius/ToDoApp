package todo.proit.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.klaudeta.PaginatedGrid;
import todo.proit.common.config.PageConfig;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.ui.service.TaskViewService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dipanjal
 * @since 2/20/2021
 */

@Route("")
public class MainView extends BaseView {

    private final TaskViewService taskService;

    private PaginatedGrid<TaskDetailDto> taskGrid = new PaginatedGrid<>(TaskDetailDto.class);
    private TextField searchByNameField = new TextField();
    private RadioButtonGroup<String> searchByStateBtn = new RadioButtonGroup<>();
    private TaskSearchRequest taskSearchForm = new TaskSearchRequest();
    private Button createNewTaskNavBtn = new Button();
    private List<TaskDetailDto> tasks = new ArrayList<>();

    public MainView(@Autowired TaskViewService taskService) {
        this.taskService = taskService;

        this.configureSearchFields();
        this.configureGrid();
        this.configureNavBtn();

        this.fetchTaskByState(TaskStatus.ALL.getCode());

        super.addClassName("list-view");
        super.setSizeFull();
        super.add(this.createNewTaskNavBtn,
                this.searchByNameField,
                this.searchByStateBtn,
                this.taskGrid);
    }

    private void configureSearchFields(){

        this.searchByStateBtn.setItems(TaskStatus.getAllValueAsArray());
        this.searchByStateBtn.setValue(TaskStatus.ALL.getValue());

        this.searchByNameField.setPlaceholder("Search by Name... ");
        this.searchByNameField.setClearButtonVisible(true);
        this.searchByNameField.setValueChangeMode(ValueChangeMode.LAZY);

        this.searchByStateBtn.addValueChangeListener(cl-> this.fetchTaskByState(TaskStatus.getCodeByValue(cl.getValue())));
        this.searchByNameField.addValueChangeListener(e-> this.fetchTaskByName(e.getValue()));

    }

    private void configureGrid(){
        this.setClassName("task-grid");
        this.taskGrid.setColumns("name","status","createdAt");
        this.taskGrid.setPageSize(PageConfig.DEFAULT_MIN_PAGE_SIZE);
        taskGrid.setPaginatorSize(3);

        this.taskGrid.addComponentColumn(task->{
            Button editBtn =  new Button("Edit");
            editBtn.addClickListener(e-> UI.getCurrent()
                    .navigate("edit-task/"+task.getId())
            );

            return editBtn;
        }).setHeader("Action");

        this.taskGrid.addComponentColumn(task -> {
            Checkbox changeStateBtn =  new Checkbox();
            changeStateBtn.setValue(TaskStatus.isDone(task.getStatus()));

            changeStateBtn.addClickListener(e -> {
                        boolean isChecked = changeStateBtn.getValue();
                        TaskStatus newStatus = isChecked ?  TaskStatus.DONE : TaskStatus.PENDING;

                        TaskDetailDto updatedTask = this.taskService.updateStatus(task.getId(), newStatus);
                        this.refreshGrid(updatedTask);
                    }
            );
            return changeStateBtn;

        }).setHeader("Check");

    }

    private void fetchTaskByState(int status){
        this.taskSearchForm.setStatus(status);
        this.tasks = this.taskService.search(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskByName(String name){
        this.taskSearchForm.setName(name);
        this.tasks = this.taskService.search(taskSearchForm);
        this.taskGrid.setItems(tasks);
    }

    private void refreshGrid(TaskDetailDto task){
        this.tasks = this.tasks
                .stream()
                .map(t -> (t.getId() == task.getId()) ? task : t)
                .collect(Collectors.toList());
        this.taskGrid.setItems(this.tasks);
    }

    private void configureNavBtn(){
        this.createNewTaskNavBtn.setText("Create task");

        this.createNewTaskNavBtn.addClickListener(e-> UI.getCurrent()
                .navigate("create-task")
        );
    }
}
