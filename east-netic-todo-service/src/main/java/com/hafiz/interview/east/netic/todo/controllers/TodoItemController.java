package com.hafiz.interview.east.netic.todo.controllers;

import com.hafiz.interview.east.netic.todo.core.constants.EndPoint;
import com.hafiz.interview.east.netic.todo.core.crud.CrudController;
import com.hafiz.interview.east.netic.todo.dataclass.todo.TodoItemDTO;
import com.hafiz.interview.east.netic.todo.entities.TodoItem;
import com.hafiz.interview.east.netic.todo.services.TodoItemService;
import com.hafiz.interview.east.netic.todo.utility.TodoItemConversionUtility;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoint.TODO_ITEM_URL)
public class TodoItemController extends CrudController<TodoItem, TodoItemDTO> {

    private final TodoItemConversionUtility conversionUtility;
    private final TodoItemService service;
    private static final long MAXVALUE = 10000L;

    TodoItemController(TodoItemService service, TodoItemConversionUtility conversionUtility) {
        super(service, conversionUtility);
        this.conversionUtility = conversionUtility;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("by-user-id")
    @Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
    public Page<TodoItemDTO> getListByUserId(@RequestParam(value = "page", required = false, defaultValue = "0") Long page,
                                     @RequestParam(value = "size", required = false, defaultValue = MAXVALUE + "") Long size) {
        return conversionUtility.getDtoList(service.getListByUserId(page, size));
    }

}
