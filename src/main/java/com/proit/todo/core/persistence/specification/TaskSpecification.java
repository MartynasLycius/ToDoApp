package com.proit.todo.core.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.persistence.entity.Task;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;



public class TaskSpecification {

    public static Specification<Task> getSearch(TaskSearchForm searchForm){
        return  (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            /**
             * Name
             * */
            if(StringUtils.isNoneBlank(searchForm.getName())){
                predicates.add( cb.like( root.get("name"),
                                        "%"+searchForm.getName().trim()+"%"
                                        )
                               );
            }

            /**
             * Description
             * */
            if(StringUtils.isNoneBlank(searchForm.getDescription())){
                predicates.add( cb.like( root.get("description"),
                                        "%"+searchForm.getDescription().trim()+"%"
                                    )
                                );
            }

            /**
             * State
             * */
            if(searchForm.getState()!=null){
                predicates.add( cb.equal( root.get("state"),
                                            searchForm.getState().ordinal()
                                        )
                            );
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
