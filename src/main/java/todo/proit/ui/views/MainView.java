package todo.proit.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
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
@PWA(name = "Todo App",
        shortName = "proIT Todo App",
        description = "Todo Application for proIT",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends BaseView {
    private final TaskViewService taskService;

    private PaginatedGrid<TaskDetailDto> taskGrid = new PaginatedGrid<>(TaskDetailDto.class);
    private TextField searchByNameField = new TextField();
    private RadioButtonGroup<String> taskStatusRadioBtn = new RadioButtonGroup<>();
    private TaskSearchRequest searchRequest = new TaskSearchRequest();
    private Button createNewTaskNavBtn = new Button();
    private List<TaskDetailDto> tasks = new ArrayList<>();
    private int selectedStatusCode = TaskStatus.ALL.getCode();

    public MainView(TaskViewService taskService) {
        this.taskService = taskService;

        this.configureSearchFields();
        this.configureGrid();
        this.configureNavBtn();

        this.fetchTaskByState(TaskStatus.ALL.getCode());

        super.addClassName("list-view");
        super.setSizeFull();
        super.add(this.createNewTaskNavBtn,
                this.searchByNameField,
                this.taskStatusRadioBtn,
                this.taskGrid);
    }

    private void configureSearchFields(){

        this.taskStatusRadioBtn.setItems(TaskStatus.getAllValueAsArray());
        this.taskStatusRadioBtn.setValue(TaskStatus.ALL.getValue());

        this.searchByNameField.setPlaceholder("Search by Name... ");
        this.searchByNameField.setClearButtonVisible(true);
        this.searchByNameField.setValueChangeMode(ValueChangeMode.LAZY);
        this.searchByNameField.setWidthFull();

        this.taskStatusRadioBtn.addValueChangeListener(changeEvent -> {
            selectedStatusCode = TaskStatus.getCodeByValue(changeEvent.getValue());
            fetchTaskByState(selectedStatusCode);
        });
        this.searchByNameField.addValueChangeListener(e-> this.fetchTaskByName(e.getValue()));

    }

    private void configureGrid(){
        super.setClassName("task-grid");
        taskGrid.setColumns("name","status","createdAt");
        taskGrid.setPageSize(PageConfig.DEFAULT_MIN_PAGE_SIZE);
        taskGrid.setPaginatorSize(3);

        taskGrid.addComponentColumn(task->{
            Button editBtn =  new Button("Edit", VaadinIcon.EDIT.create());
            editBtn.addClickListener(event ->
                    UI.getCurrent()
                            .navigate("edit-task/"+task.getId())
            );
            return editBtn;
        }).setHeader("Action");

        taskGrid.addComponentColumn(task -> {
            Checkbox changeStateBtn =  new Checkbox();
            changeStateBtn.setValue(TaskStatus.isDone(task.getStatus()));
            changeStateBtn.addClickListener(
                    event -> handleStatusChangeClickEvent(changeStateBtn.getValue(), task)
            );
            return changeStateBtn;

        }).setHeader("Check");

        taskGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void handleStatusChangeClickEvent(boolean isChecked, TaskDetailDto taskDto){
        TaskStatus newStatus = isChecked ?  TaskStatus.DONE : TaskStatus.PENDING;
        TaskDetailDto updatedTask = taskService.updateStatus(taskDto.getId(), newStatus);

        showNotification(String.format("Task marked as %s", updatedTask.getStatus()));
        /** Refresh Data Grid */
        this.refreshGrid(updatedTask);
    }

    private void fetchTaskByState(int status){
        searchRequest.setStatus(status);
        this.tasks = this.taskService.search(searchRequest);
        this.taskGrid.setItems(tasks);
    }
    private void fetchTaskByName(String name){
        this.searchRequest.setName(name);
        this.tasks = this.taskService.search(searchRequest);
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
        this.createNewTaskNavBtn.setText("Add Task");
        this.createNewTaskNavBtn.setWidthFull();
        this.createNewTaskNavBtn.setIcon(VaadinIcon.PLUS.create());
        this.createNewTaskNavBtn.addClickListener(e-> UI.getCurrent()
                .navigate("create-task")
        );
    }
}
