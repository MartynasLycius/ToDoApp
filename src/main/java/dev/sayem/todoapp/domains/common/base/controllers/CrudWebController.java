package dev.sayem.todoapp.domains.common.base.controllers;

import dev.sayem.todoapp.domains.common.base.models.dtos.BaseDto;
import org.springframework.ui.Model;

public interface CrudWebController<T extends BaseDto> {
    String search(String query, int page, int size, Model model);

    String find(Long id);

    String createPage(Model model);

    String create(T dto);

    String updatePage(Long id, Model model);

    String update(Long id, T dto);

    String delete(Long id);
}