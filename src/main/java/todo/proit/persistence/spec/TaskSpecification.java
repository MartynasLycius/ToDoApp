package todo.proit.persistence.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import todo.proit.common.enums.TaskStatus;
import todo.proit.common.enums.YesNoEnum;
import todo.proit.common.model.request.task.TaskSearchRequest;
import todo.proit.persistence.entity.Task;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
public abstract class TaskSpecification {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESC = "description";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_IS_ACTIVE = "isActive";

    public static Specification<Task> getSearchSpecification(TaskSearchRequest request){
        return Specification.where(generateSearchSpecification(request));
    }

    public static Specification<Task> getAllActiveSpecification(){
        Specification<Task> taskSpecification = ( (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(
                                root.get(FIELD_IS_ACTIVE), YesNoEnum.YES.getCode()
                        )
                )
        );
        return Specification.where(taskSpecification);
    }

    private static Specification<Task> generateSearchSpecification(TaskSearchRequest request){
        return ( (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.isNotBlank(request.getName()))
                predicates.add(criteriaBuilder.like(root.get(FIELD_NAME), "%"+request.getName()+"%"));

            if(StringUtils.isNotBlank(request.getDescription()))
                predicates.add(criteriaBuilder.like(root.get(FIELD_DESC), "%"+request.getName()+"%"));

            if(TaskStatus.isValid(request.getStatus()) && TaskStatus.isNoAll(request.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get(FIELD_STATUS), request.getStatus()));

            predicates.add(criteriaBuilder.equal(root.get(FIELD_IS_ACTIVE), YesNoEnum.YES.getCode()));


            return criteriaBuilder.and(mapToArray(predicates));
        });
    }

    private static Predicate[] mapToArray(List<Predicate> predicates){
        return predicates.stream().toArray(Predicate[]::new);
    }
}
