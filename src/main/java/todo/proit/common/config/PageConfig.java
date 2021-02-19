package todo.proit.common.config;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import todo.proit.common.enums.DirectionEnum;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
public final class PageConfig {
    public final static int DEFAULT_MIN_PAGE_SIZE = 10;
    public final static int DEFAULT_MAX_PAGE_SIZE = 50;
    public final static int DEFAULT_PAGE_NO = 0;
    public final static DirectionEnum DEFAULT_SORT_DIRECTION = DirectionEnum.ASC;
    public final static String DEFAULT_SORT_BY = "id";

    public static PageRequest getDefaultPageRequest(){
        return PageRequest.of(
                DEFAULT_PAGE_NO,
                DEFAULT_MIN_PAGE_SIZE,
                getDefaultSortOrder()
        );
    }

    public static PageRequest getPageRequest(int pageNumber, int pageSize){
        return PageRequest.of(
                pageNumber,
                pageSize,
                getDefaultSortOrder()
        );
    }

    private static Sort getDefaultSortOrder(){
        return getSortOrder(DEFAULT_SORT_BY, DEFAULT_SORT_DIRECTION.name());
    }

    private static Sort getSortOrder(String field, String order){
        Sort.Order sortOrder = DirectionEnum.isAscending(order)
                        ?  Sort.Order.asc(field) : Sort.Order.desc(field);
        return Sort.by(sortOrder);
    }
}
