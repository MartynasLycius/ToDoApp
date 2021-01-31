package com.hafiz.interview.east.netic.todo.core.crud;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICrudService<E extends BaseEntity> {
    Page<E> getList(Long page, Long size);
    Optional<E> getById(UUID id);
    E create(Optional<E> e);
    List<E> createAll(List<E> e);
    E update(Optional<E> e) throws Exception;
    void deleteById(Optional<UUID> id);
}
