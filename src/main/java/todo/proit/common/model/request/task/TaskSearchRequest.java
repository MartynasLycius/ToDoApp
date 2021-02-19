package todo.proit.common.model.request.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import todo.proit.common.model.request.pagination.PaginationRequest;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@AllArgsConstructor
@Getter
public class TaskSearchRequest extends TaskRequest {
    private int status;
    private String date;
    private PaginationRequest paginationRequest;

    public TaskSearchRequest() {
        this.paginationRequest = new PaginationRequest();
    }

    public TaskSearchRequest setStatus(int status) {
        this.status = status;
        return this;
    }

    public TaskSearchRequest setDate(String date) {
        this.date = date;
        return this;
    }

    public TaskSearchRequest setPaginationRequest(PaginationRequest paginationRequest) {
        this.paginationRequest = paginationRequest;
        return this;
    }
}
