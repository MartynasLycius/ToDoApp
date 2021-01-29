package com.mir00r.todoapp.domains.common.controllers;

import com.mir00r.todoapp.domains.common.models.dtos.BaseDto;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public interface BaseController<T extends BaseDto> {

    ResponseEntity<Page<T>> search(String query, int page, int size);

    ResponseEntity<T> findItem(Long id) throws NotFoundException;

    ResponseEntity<T> createItem(T dto);

    ResponseEntity<T> updateItem(Long id, T dto) throws NotFoundException;

    ResponseEntity<Object> deleteItem(Long id) throws NotFoundException;
}
