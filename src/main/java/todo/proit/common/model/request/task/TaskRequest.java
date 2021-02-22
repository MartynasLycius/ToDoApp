package todo.proit.common.model.request.task;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Getter
public class TaskRequest {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "description cannot be empty")
    private String description;

    public TaskRequest setName(String name) {
        this.name = name;
        return this;
    }

    public TaskRequest setDescription(String description) {
        this.description = description;
        return this;
    }
}
