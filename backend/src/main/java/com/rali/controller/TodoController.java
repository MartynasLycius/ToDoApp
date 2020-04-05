package com.rali.controller;

import com.rali.constant.PropertyNamespace;
import com.rali.dto.TodoDto;
import com.rali.dto.transformer.TodoDtoTransformer;
import com.rali.exception.ApiException;
import com.rali.servicce.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/v1/todos")
@Api(tags = "todo", value = "todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    private static final TodoDtoTransformer TODO_DTO_TRANSFORMER = new TodoDtoTransformer();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Todo", notes = "Create Todo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Todo create success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity creteToDo(@RequestBody TodoDto todoDto) {

        try {

            log.info("initiating create-todo-api");

            JSONObject response = new JSONObject();
            response.put(PropertyNamespace.STATUS, HttpStatus.CREATED.value());
            response.put(PropertyNamespace.MESSAGE, HttpStatus.CREATED.getReasonPhrase());
            response.put(PropertyNamespace.DATA, TODO_DTO_TRANSFORMER.getToDoDto(todoService.createTodoItem(TODO_DTO_TRANSFORMER.getToDo(todoDto))));

            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(PropertyNamespace.ERROR,  ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Todo(s) ", notes = "Get list of Todo(s)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<TodoDto> getToDos(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {

        try {

            log.info("initiating get-all-todo-api");

            JSONObject response = new JSONObject();
            response.put(PropertyNamespace.STATUS, HttpStatus.OK.value());
            response.put(PropertyNamespace.MESSAGE, HttpStatus.OK.getReasonPhrase());
            response.put(PropertyNamespace.DATA, TODO_DTO_TRANSFORMER.getToDoDos(todoService.getTodoItems(page, size)));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(PropertyNamespace.ERROR,  ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update Todo", notes = "Update a Todo item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> updateToDo(@PathVariable String id, @RequestBody TodoDto todoDto) {

        try {
            log.info("initiating update-todo-api");
            todoDto.setId(id);

            JSONObject response = new JSONObject();
            response.put(PropertyNamespace.STATUS, HttpStatus.OK.value());
            response.put(PropertyNamespace.MESSAGE, HttpStatus.OK.getReasonPhrase());
            response.put(PropertyNamespace.DATA, TODO_DTO_TRANSFORMER.getToDoDto(todoService.updateTodoItem(TODO_DTO_TRANSFORMER.getToDo(todoDto))));

            return new ResponseEntity(response, HttpStatus.OK);

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(PropertyNamespace.ERROR,  ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get Todo", notes = "Get a single Todo item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> getToDo(@PathVariable String id) {

        try {

            log.info("initiating get-todo-api");
            JSONObject response = new JSONObject();
            response.put(PropertyNamespace.STATUS, HttpStatus.OK.value());
            response.put(PropertyNamespace.MESSAGE, HttpStatus.OK.getReasonPhrase());
            response.put(PropertyNamespace.DATA, TODO_DTO_TRANSFORMER.getToDoDto(todoService.getTodoItemById(id)));

            return new ResponseEntity(response, HttpStatus.OK);

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(PropertyNamespace.ERROR,  ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete Todo", notes = "Delete a single Todo item")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> deleteToDo(@PathVariable String id) {

        try {

            log.info("initiating delete todo api");
            todoService.deleteTodoItem(id);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(PropertyNamespace.ERROR,  ex);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
