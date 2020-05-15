package com.proit.todo.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SpecificationUtil {

    public static  <T> Specification<T> getEqual(final String fieldName,final Object filterValue) {
        return  (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(filterValue instanceof String && StringUtils.isBlank((String)filterValue)){
                return null;
            }else if(filterValue==null){
                return null;
            }

            predicates.add(cb.equal(root.get(fieldName), filterValue));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
    public static  <T> Specification<T> getLikeKeyWord(final String fieldName,final String filterValue) {
        return  (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(filterValue==null || StringUtils.isBlank(filterValue.trim())){
                return null;
            }


            predicates.add(cb.like(root.get(fieldName), "%"+filterValue.trim()+"%"));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}