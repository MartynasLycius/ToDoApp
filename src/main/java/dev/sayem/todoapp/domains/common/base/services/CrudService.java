package dev.sayem.todoapp.domains.common.base.services;

import dev.sayem.todoapp.domains.common.base.models.entities.BaseEntity;
import dev.sayem.todoapp.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CrudService<T extends BaseEntity> {
    Page<T> search(String query, int page, int size);

    T save(T entity);

    Optional<T> find(Long id);

    void delete(Long id, Boolean softDelete) throws NotFoundException;
}