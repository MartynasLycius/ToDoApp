package com.mir00r.todoapp.domains.common.services;

import com.mir00r.todoapp.domains.common.models.entities.base.BaseEntity;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public interface BaseService<T extends BaseEntity> {
    Page<T> search(String query, int page, int size);

    T save(T entity);

    Optional<T> find(Long id);

    void delete(Long id, Boolean softDelete) throws NotFoundException;
}
