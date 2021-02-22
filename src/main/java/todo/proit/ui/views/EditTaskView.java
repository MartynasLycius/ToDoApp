package todo.proit.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import todo.proit.common.model.dto.TaskDetailDto;
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
    private TextArea descriptionTextField = new TextArea("Description");
    private Button submitBtn = new Button();
    private Button homeNavBtn = new Button();
    private long taskId;

    public EditTaskView(TaskViewService taskService) {
        this.taskService = taskService;

        this.configureFormComponents();
        this.configureNavBtn();

        super.addClassName("list-view");
        super.setSizeFull();
        super.add(
                nameTextField,
                descriptionTextField,
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
        this.descriptionTextField.setValue(task.getDescription());
    }

    private void configureFormComponents(){
        this.nameTextField.setPlaceholder("Name");
        super.configureTextField(nameTextField, "Name field is required");
        this.descriptionTextField.setPlaceholder("Description");
        super.configureTextArea(descriptionTextField);
        this.configureSubmitButton();
    }

    private void configureSubmitButton(){
        submitBtn.setText("Update");
        submitBtn.setWidthFull();
//        submitBtn.addClickShortcut(Key.ENTER, KeyModifier.CONTROL);

        this.submitBtn.addClickListener(e->{
            TaskUpdateRequest request = new TaskUpdateRequest();
            request.setId(this.taskId)
                    .setName(this.nameTextField.getValue())
                    .setDescription(this.descriptionTextField.getValue());

            this.taskService.update(request);
            super.showNotification("Task updated successfully!");
            UI.getCurrent().navigate("");
        });
    }

    private void configureNavBtn(){
        this.homeNavBtn.setText("Go Home");
        homeNavBtn.setWidthFull();
        homeNavBtn.addClickShortcut(Key.ESCAPE);
        this.homeNavBtn.addClickListener(e-> UI.getCurrent().navigate("")
        );
    }

    private Component createButtonLayout(){
        HorizontalLayout layout = new HorizontalLayout(submitBtn, homeNavBtn);
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }
}
