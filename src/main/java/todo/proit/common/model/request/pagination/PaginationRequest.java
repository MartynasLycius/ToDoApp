package todo.proit.common.model.request.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import todo.proit.common.config.PageConfig;

import javax.validation.constraints.Min;

/**
 * @author dipanjal
 * @since 2/18/2021
 */

@Getter
@Setter
@AllArgsConstructor
public class PaginationRequest {
    @Min(value = PageConfig.DEFAULT_PAGE_NO, message = "Page number should be minimum "+PageConfig.DEFAULT_PAGE_NO)
    private int pageNumber;
    @Range(min = 0, max = PageConfig.DEFAULT_MAX_PAGE_SIZE, message = "Page size must be in between 0 to "+ PageConfig.DEFAULT_MAX_PAGE_SIZE)
    private int pageSize;
    private String sort;

    public PaginationRequest(){
        this.pageNumber = PageConfig.DEFAULT_PAGE_NO;
        this.pageSize = PageConfig.DEFAULT_MIN_PAGE_SIZE;
        this.sort = PageConfig.DEFAULT_SORT_DIRECTION.name();
    }
}
