package todo.proit.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import todo.proit.common.model.request.task.TaskRequest;
import todo.proit.ui.service.TaskViewService;

/**
 * @author dipanjal
 * @since 2/20/2021
 */
@Route("create-task")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class CreateTaskView extends BaseView {

    private final TaskViewService taskService;

    private TextField nameTextField = new TextField("Task Name");
    private TextArea descriptionTextArea = new TextArea("Description");
    private Button submitBtn = new Button();
    private Button homeNavBtn = new Button();

    private Binder<TaskRequest> binder = new BeanValidationBinder<>(TaskRequest.class);

    public CreateTaskView(TaskViewService taskService) {
        this.taskService = taskService;

        this.configureFormComponents();
        super.configureNavBtn(homeNavBtn);

        super.addClassName("list-view");
        super.setSizeFull();
        super.add(
                nameTextField,
                descriptionTextArea,
                createButtonLayout()
        );
    }

    private void configureFormComponents(){
        this.nameTextField.setPlaceholder("Name");
        super.configureTextField(nameTextField);
        this.descriptionTextArea.setPlaceholder("Description");
        super.configureTextArea(descriptionTextArea);

        this.bindFields();

        this.configureSubmitButton();
    }

    private void configureSubmitButton(){
        super.configureSubmitButton(submitBtn);
        submitBtn.addClickListener(event -> submitRequest());
    }

    private void submitRequest(){
        TaskRequest request = new TaskRequest();
        request.setName(this.nameTextField.getValue())
                .setDescription(this.descriptionTextArea.getValue());

        if(!binder.isValid()){
            binder.validate();
            return;
        }

        this.taskService.create(request);
        super.showNotification("Task created successfully!");
        UI.getCurrent().navigate("");
    }

    private Component createButtonLayout(){
        HorizontalLayout layout = new HorizontalLayout(submitBtn, homeNavBtn);
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }

    private void bindFields(){
        binder.forField(nameTextField)
                .asRequired(ERR_NAME_REQUIRED)
                .bind(TaskRequest::getName, TaskRequest::setName);

        binder.forField(descriptionTextArea).asRequired(ERR_DESC_REQUIRED)
                .bind(TaskRequest::getDescription, TaskRequest::setDescription);

        binder.setBean(new TaskRequest());
    }
}
