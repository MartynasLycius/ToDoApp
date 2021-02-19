package todo.proit.common.model.request.task;

import lombok.Getter;

import javax.validation.constraints.Min;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Getter
public class TaskUpdateRequest extends TaskRequest {
    @Min(value = 1, message = "invalid task id must be grater than 0")
    private long id;

    public TaskUpdateRequest setId(long id) {
        this.id = id;
        return this;
    }
}
