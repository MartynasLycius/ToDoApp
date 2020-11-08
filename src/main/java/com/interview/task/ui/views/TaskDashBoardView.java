package com.interview.task.ui.views;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.vaadin.klaudeta.PaginatedGrid;

import com.interview.task.ToDoTrackerAppApplication;
import com.interview.task.models.TodoItem;
import com.interview.task.services.TodoServices;
import com.interview.task.services.UtilityServices;
import com.interview.task.ui.MainView;
import com.interview.task.ui.customizedComponents.TinyButton;
import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import dev.mett.vaadin.tooltip.Tooltips;

@Route(value = "taskDashBoard", layout = MainView.class)
@PageTitle("Task Dashboard")
@CssImport("./styles/views/taskdashboard/task-dashboard-view.css")
public class TaskDashBoardView extends VerticalLayout {
	
    Environment env;
	PaginatedGrid<TodoItem> todoGrid = new PaginatedGrid<>(TodoItem.class);
	
	static final Logger logger = LoggerFactory.getLogger(ToDoTrackerAppApplication.class);
	ListDataProvider<TodoItem> todoDataProvider;
	List<TodoItem> todoEntities;
    TodoServices todoServices;
    UtilityServices utilityServices;
    Label totalTasks, dataInfo;
    TextField searchField;
    Dialog formDialog;
    TinyButton dueFilter, completeFilter, reset, deleteSelected, finishSelected;
    ToggleButton paginationToggle;
    HorizontalLayout searchPanel, functionalityPanel, paginationContainerLayout;
    int itemsPerPage = 10;
    int visiblePageNumbers = 5;
    TaskDetailView taskDetail;
    DatePicker deadlinePicker;
    
    public TaskDashBoardView(TodoServices todoServices, UtilityServices utilityServices, Environment env) {
    	this.env = env;
        this.todoServices = todoServices;
        this.utilityServices = utilityServices;
        setId("task-dashboard-view");
        configureTopPanel();
        configureGrid();
        configureTotalTasks();
        add(functionalityPanel, searchPanel, dataInfo, todoGrid, paginationContainerLayout);
    }
	
    private Div getDetailFormContent(TodoItem todoItem) {
    	taskDetail = new TaskDetailView(formDialog, todoItem, todoGrid, todoServices, env);
    	Div content = new Div(taskDetail); 
        content.addClassName("form-content");
        content.setSizeFull();
        return content;
    }
    
	private void configureTopPanel() {
		functionalityPanel = new HorizontalLayout();
		searchPanel = new HorizontalLayout();
		dataInfo = new Label();
		configureSearch();
		configureMenubar();
	}

	private void configureMenubar() {
		dueFilter = new TinyButton(VaadinIcon.THUMBS_DOWN, "red");
		dueFilter.addClickListener(e -> filterOverdueTasks());
		dueFilter.setLeftMargin();
		dueFilter.setToolTip(env.getProperty("tooltip.due"));
		
		completeFilter = new TinyButton(VaadinIcon.THUMBS_UP, "green");
		completeFilter.addClickListener(e -> filterCompleteTasks());
		completeFilter.setToolTip(env.getProperty("tooltip.complete"));

		reset = new TinyButton(VaadinIcon.REFRESH);
		reset.addClickListener(e -> resetList());
		reset.setToolTip(env.getProperty("tooltip.reload"));
		
		deleteSelected = new TinyButton(VaadinIcon.FILE_REMOVE, "red");
		deleteSelected.addClickListener(e -> deleteSelectedTasks());
		deleteSelected.setToolTip(env.getProperty("tooltip.delete"));
		
		finishSelected = new TinyButton(VaadinIcon.CHECK_SQUARE_O, "green");
		finishSelected.addClickListener(e -> finishSelectedTasks());
		finishSelected.setToolTip(env.getProperty("tooltip.finish"));
		
		functionalityPanel.add(dueFilter, completeFilter, reset, deleteSelected, finishSelected);
	}

	private void selectTaskByDate() {
		if (!deadlinePicker.isEmpty()) {
			todoDataProvider.addFilter(todoItem ->
				todoItem.getDateToBePerformed().getYear() == deadlinePicker.getValue().getYear() 
					&& todoItem.getDateToBePerformed().getDayOfYear() == deadlinePicker.getValue().getDayOfYear());
			dataInfo.setText(MessageFormat.format(env.getProperty("tasks.on.date"), deadlinePicker.getValue()));
		} else {
			resetList();
		}
	}

	private void finishSelectedTasks() {
		Set<TodoItem> selectedTodoList = todoGrid.getSelectionModel().getSelectedItems();
		if (selectedTodoList.size()==0) {
			dataInfo.setText(env.getProperty("finish.selection.text"));
		} else {
			List<Long> tobeCompletedTodoList = new ArrayList<>();
			selectedTodoList.forEach(todoItem -> {
				if (!todoItem.isComplete()) {
					tobeCompletedTodoList.add(todoItem.getTaskId());
					todoItem.setComplete(true);
				}	
			});
			if (tobeCompletedTodoList.size()>0)
				todoServices.updateCompleteState(tobeCompletedTodoList);

			todoGrid.getSelectionModel().deselectAll();

			selectedTodoList.forEach(todoItem -> {
				if (tobeCompletedTodoList.contains(todoItem.getTaskId())) {
					todoGrid.getDataProvider().refreshItem(todoItem);
				}
			});
			dataInfo.setText(MessageFormat.format(env.getProperty("complete.task.confirmation"), tobeCompletedTodoList.size()));
		}
	}

	private void filterCompleteTasks() {
		dataInfo.setText(env.getProperty("complete.result.text"));
		todoDataProvider.clearFilters();
		todoDataProvider.addFilter(todoItem -> todoItem.isComplete());
	}

	private void filterOverdueTasks() {
		dataInfo.setText(env.getProperty("overdue.result.text"));
		todoDataProvider.clearFilters();
		todoDataProvider.addFilter(todoItem -> todoItem.getDateToBePerformed().isBefore(LocalDateTime.now()));
	}

	private void configureSearch() {
		searchField = new TextField();
		searchField.setPlaceholder(env.getProperty("search.instruction.text"));
		searchField.setClearButtonVisible(true);
		searchField.setWidth("300px");
		searchField.setValueChangeMode(ValueChangeMode.LAZY);
		searchField.addValueChangeListener(e -> filterByNameOrDescription());
		
		deadlinePicker = new DatePicker();
		deadlinePicker.setPlaceholder(env.getProperty("filter.task.by.date"));
		deadlinePicker.setClearButtonVisible(true);
		deadlinePicker.addValueChangeListener(e -> selectTaskByDate());
		
		searchPanel.add(searchField, deadlinePicker);
	}

	private void configureTotalTasks() {
		totalTasks = new Label();
		totalTasks.setText("Total: " + todoDataProvider.getItems().size());
		totalTasks.getElement().getStyle().set("margin-left", "auto");
		searchPanel.add(totalTasks);
	}

	private void configureGrid() {
		todoGrid.addClassName("todo-grid");
		todoGrid.setId("todo-grid");
		configureGridColumns();
		updateList();
		configurePagination();
		todoGrid.setSelectionMode(SelectionMode.MULTI);
	}
	
	private void configurePagination() {
		paginationToggle = new ToggleButton();
		paginationToggle.getElement().getStyle().set("margin-left", "auto");
		Tooltips.getCurrent().setTooltip(paginationToggle, env.getProperty("tooltip.pagination"));
		paginationToggle.setValue(true);
		paginationToggle.addValueChangeListener(e -> togglePaginationAction());

		if (VaadinSession.getCurrent().getAttribute("paginationState") == null) {
			VaadinSession.getCurrent().setAttribute("paginationState", true);
		}
		
		if (Boolean.parseBoolean(VaadinSession.getCurrent().getAttribute("paginationState").toString())) {
			todoGrid.setPageSize(itemsPerPage);
			todoGrid.setPaginatorSize(visiblePageNumbers);
			paginationToggle.setValue(true);
		} else {
			todoGrid.setPaginationVisibility(false);
			todoGrid.setPageSize(todoDataProvider.getItems().size() == 0 ? 1 : todoDataProvider.getItems().size());
			VaadinSession.getCurrent().setAttribute("paginationState", false);
			paginationToggle.setValue(false);
		}
		logger.info("Vaadinsession pagination value: " + VaadinSession.getCurrent().getAttribute("paginationState"));
		paginationContainerLayout = new HorizontalLayout();
		paginationContainerLayout.add(paginationToggle);
		todoGrid.setPaginationContainer(paginationContainerLayout);
	}
	
	private void togglePaginationAction() {
		if (paginationToggle.getValue()) {
			todoGrid.setPaginationVisibility(true);
			todoGrid.setPageSize(itemsPerPage);
			todoGrid.setPaginatorSize(visiblePageNumbers);
			VaadinSession.getCurrent().setAttribute("paginationState", true);
		} else {
			todoGrid.setPaginationVisibility(false);
			todoGrid.setPageSize(todoDataProvider.getItems().size() == 0 ? 1 : todoDataProvider.getItems().size());
			VaadinSession.getCurrent().setAttribute("paginationState", false);
		}
	}

	private void launchDetail(TodoItem todoItem, Button detail) {
		formDialog = new Dialog();
		formDialog.add(getDetailFormContent(todoItem));
		formDialog.open();
	}

	private void configureGridColumns() {
		todoGrid.setColumns();
		todoGrid.addColumn(todoItem -> utilityServices.abbreviateText(todoItem.getTaskName(), 20)).setHeader("Task Name");
		todoGrid.addColumn(todoItem -> utilityServices.abbreviateText(todoItem.getDescription(), 40)).setHeader("Description");
		todoGrid.addComponentColumn(todoItem -> utilityServices.getFormatToBePerformed(todoItem.getDateToBePerformed())).setHeader("To Be Performed");
		
		todoGrid.addComponentColumn(todoItem -> {
			HorizontalLayout rowActionLayout = new HorizontalLayout();
			Button finishTask = new Button("Finish");
			finishTask.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			if (todoItem.isComplete()) {
				finishTask.setText("DONE!");
				finishTask.setEnabled(false);
				finishTask.getStyle().set("color","green");
			}
			finishTask.addClickListener(e -> completeTask(todoItem, finishTask));

			Button taskDetail = new Button("Detail");
			taskDetail.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			taskDetail.addClickListener(e -> launchDetail(todoItem, taskDetail));

			Button removeTask = new Button("Remove");
			removeTask.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
			removeTask.addClickListener(e -> removeTask(todoItem, removeTask));

			rowActionLayout.add(taskDetail, removeTask, finishTask);
			return rowActionLayout;
		}).setHeader("").setKey("actionCol");

		todoGrid.getColumns().forEach(col -> col.setAutoWidth(true));
		todoGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
	}

	private void completeTask(TodoItem todoItem, Button finishButton) {
		todoItem.setComplete(true);
		TodoItem updatedTask = todoServices.updateTodoItem(todoItem);
		if (updatedTask.isComplete()) {
			todoGrid.getDataProvider().refreshItem(todoItem);
		}
	}
	
	private void removeTask(TodoItem todoItem, Button removeButton) {
		todoServices.deleteTodoItem(todoItem.getTaskId());
		todoEntities.remove(todoItem);
		todoDataProvider = new ListDataProvider<>(todoEntities);
		todoGrid.setDataProvider(todoDataProvider);
		totalTasks.setText("Total: " + todoDataProvider.getItems().size());
	}
	
	private void updateList() {
		dataInfo.setText("");
		todoEntities = todoServices.findAllTasks();
		todoDataProvider = new ListDataProvider<>(todoEntities);
		todoGrid.setDataProvider(todoDataProvider);
	}
    
	private void resetList() {
		todoDataProvider.clearFilters();
		dataInfo.setText("");
	}
	
	private void filterByNameOrDescription() {
		String searchText = searchField.getValue();
 		if (utilityServices.isNullOrEmpty(searchText)) {
			resetList();
		} else {
			dataInfo.setText(env.getProperty("search.result.text"));
			todoDataProvider.addFilter(todoItem -> todoItem.getTaskName().contains(searchText) || todoItem.getDescription().contains(searchText));
		}
	}
	
	private void deleteSelectedTasks() {
		Set<TodoItem> selectedTodoList = todoGrid.getSelectionModel().getSelectedItems();
		if (selectedTodoList.size()==0) {
			dataInfo.setText(env.getProperty("delete.selection.text"));
		} else {
			List<Long> selectedTaskIds = new ArrayList<>();
			List<TodoItem> tobeRemoved = new ArrayList<>();
			selectedTodoList.forEach(todoItem -> {
				selectedTaskIds.add(todoItem.getTaskId());
				tobeRemoved.add(todoItem);
			});
			todoServices.deleteListedTodoItems(selectedTaskIds);
			dataInfo.setText(env.getProperty("delete.task.confirmation"));
			todoEntities.removeAll(tobeRemoved);
			todoDataProvider = new ListDataProvider<>(todoEntities);
			todoGrid.setDataProvider(todoDataProvider);
		}
		totalTasks.setText("Total: " + todoDataProvider.getItems().size());
	}
}
