package dev.sayem.todoapp.domains.common.base.controllers;

import dev.sayem.todoapp.domains.common.base.models.dtos.BaseDto;
import dev.sayem.todoapp.exceptions.NotFoundException;
import org.springframework.ui.Model;

public interface CrudWebController<T extends BaseDto> {
    String search(String query, int page, int size, Model model);

    String find(Long id) throws NotFoundException;

    String createPage(Model model);

    String create(T dto);

    String updatePage(Long id, Model model) throws NotFoundException;

    String update(Long id, T dto) throws NotFoundException;

    String delete(Long id) throws NotFoundException;
}