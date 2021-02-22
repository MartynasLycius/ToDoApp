package todo.proit.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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
    private TextArea descriptionTextField = new TextArea("Description");
    private Button submitBtn = new Button();
    private Button homeNavBtn = new Button();

    public CreateTaskView(TaskViewService taskService) {
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

    private void configureFormComponents(){
        this.nameTextField.setPlaceholder("Name");
        super.configureTextField(nameTextField, "Name field is required");
        this.descriptionTextField.setPlaceholder("Description");
        super.configureTextArea(descriptionTextField);
        this.configureSubmitButton();
    }

    private void configureSubmitButton(){
        submitBtn.setText("Create");
        submitBtn.setWidthFull();

        this.submitBtn.addClickListener(e->{
            TaskRequest request = new TaskRequest();
            request.setName(this.nameTextField.getValue())
                    .setDescription(this.descriptionTextField.getValue());

            this.taskService.create(request);

            super.showNotification("Task created successfully!");

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
