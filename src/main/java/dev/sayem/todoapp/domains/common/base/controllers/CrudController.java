package dev.sayem.todoapp.domains.common.base.controllers;


import dev.sayem.todoapp.domains.common.base.models.dtos.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

interface CrudController<T extends BaseDto> {
    ResponseEntity<Page<T>> search(String query, int page, int size);

    ResponseEntity<T> find(Long id);

    ResponseEntity<T> create(T dto);

    ResponseEntity<T> update(Long id, T dto);

    ResponseEntity<Object> delete(Long id);
}