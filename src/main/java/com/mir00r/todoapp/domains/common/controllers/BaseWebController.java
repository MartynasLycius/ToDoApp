package com.mir00r.todoapp.domains.common.controllers;

import com.mir00r.todoapp.domains.common.models.dtos.BaseDto;
import com.mir00r.todoapp.exceptions.notfound.NotFoundException;
import org.springframework.ui.Model;

/**
 * @author mir00r on 29/1/21
 * @project IntelliJ IDEA
 */
public interface BaseWebController<T extends BaseDto> {

    String search(String query, int page, int size, Model model);

    String find(Long id) throws NotFoundException;

    String createPage(Model model);

    String create(T dto);

    String updatePage(Long id, Model model) throws NotFoundException;

    String update(Long id, T dto) throws NotFoundException;

    String delete(Long id) throws NotFoundException;
}
