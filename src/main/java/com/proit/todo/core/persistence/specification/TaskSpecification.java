package com.proit.todo.core.persistence.specification;

import com.proit.todo.core.Form.task.TaskSearchForm;
import com.proit.todo.core.persistence.entity.Task;
import com.proit.todo.core.util.SpecificationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class TaskSpecification {
    public static Specification<Task> getName(final String filterValue) {
        return  SpecificationUtil.getLikeKeyWord("name",filterValue);
    }
    public static Specification<Task> getDescription(final String filterValue) {
        return  SpecificationUtil.getLikeKeyWord("description",filterValue);
    }

    public static Specification<Task> getSearch(TaskSearchForm searchForm){
        return  (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.isNoneBlank(searchForm.getName())){
                predicates.add(cb.like(root.get("name"), "%"+searchForm.getName().trim()+"%"));
            }

            if(StringUtils.isNoneBlank(searchForm.getDescription())){
                predicates.add(cb.like(root.get("description"), "%"+searchForm.getDescription().trim()+"%"));
            }

            if(searchForm.getState()!=null){
                predicates.add(cb.equal(root.get("state"), +searchForm.getState().ordinal()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
