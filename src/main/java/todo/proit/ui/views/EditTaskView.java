package todo.proit.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import todo.proit.common.model.dto.TaskDetailDto;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.common.model.request.task.TaskUpdateRequest;
import todo.proit.ui.service.TaskViewService;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
@Route("edit-task")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class EditTaskView extends BaseView implements HasUrlParameter<Long> {

    private final TaskViewService taskService;

    private TextField nameTextField = new TextField("Task Name");
    private TextArea descriptionTextArea = new TextArea("Description");
    private Button submitButton = new Button();
    private Button homeButton = new Button();
    private long taskId;

    private Binder<TaskUpdateRequest> binder = new BeanValidationBinder<>(TaskUpdateRequest.class);

    public EditTaskView(TaskViewService taskService) {
        this.taskService = taskService;

        this.configureFormComponents();
        super.configureNavBtn(homeButton);

        super.addClassName("list-view");
        super.setSizeFull();
        super.add(
                nameTextField,
                descriptionTextArea,
                createButtonLayout()
        );
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        this.setTaskData(aLong);
    }

    private void setTaskData(Long id){
        this.taskId = id;

        TaskDetailDto task = this.taskService.getById(id);
        this.nameTextField.setValue(task.getName());
        this.descriptionTextArea.setValue(task.getDescription());
    }

    private void configureFormComponents(){
        this.nameTextField.setPlaceholder("Name");
        super.configureTextField(nameTextField);
        this.descriptionTextArea.setPlaceholder("Description");
        super.configureTextArea(descriptionTextArea);

        this.bindFields();

        this.configureSubmitButton();
    }

    private void bindFields(){
        binder.forField(nameTextField)
                .asRequired(ERR_NAME_REQUIRED)
                .bind(TaskRequest::getName, TaskRequest::setName);

        binder.forField(descriptionTextArea).asRequired(ERR_DESC_REQUIRED)
                .bind(TaskRequest::getDescription, TaskRequest::setDescription);

        binder.setBean(new TaskUpdateRequest());
    }

    private void configureSubmitButton(){
        super.configureSubmitButton(submitButton);
        this.submitButton.addClickListener(e-> submitRequest());
    }

    private void submitRequest(){
        TaskUpdateRequest request = new TaskUpdateRequest();
        request.setId(this.taskId)
                .setName(this.nameTextField.getValue())
                .setDescription(this.descriptionTextArea.getValue());

        if(!binder.isValid()){
            binder.validate();
            return;
        }

        this.taskService.update(request);
        super.showNotification("Task updated successfully!");
        UI.getCurrent().navigate("");
    }

    private Component createButtonLayout(){
        HorizontalLayout layout = new HorizontalLayout(submitButton, homeButton);
        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }
}
