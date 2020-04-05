package com.rali.servicce.impl;

import com.rali.entity.Todo;
import com.rali.exception.ApiException;
import com.rali.repository.TodoRepository;
import com.rali.servicce.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoItems(Integer page, Integer size) {

        log.info("fetching all todo entry");
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAllToDos(pageable);
    }

    public Todo updateTodoItem(Todo todo) throws InvocationTargetException, IllegalAccessException {

        Todo existingTodo = getTodoItemById(todo.getId());

        todo.setCreatedAt(existingTodo.getCreatedAt());
        todo.setModifiedAt(new Date());
        BeanUtils.copyProperties(existingTodo, todo);

        log.info("updating ToDo entry -- id: " + todo.getId());
        return todoRepository.save(todo);
    }

    public Todo getTodoItemById(String id) {
        log.info("fetching todo item with id: " + id);

        return todoRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NO_CONTENT));
    }

    public void deleteTodoItem(String id) {

        if (null != getTodoItemById(id)) {
            log.info("deleting todo item with id: " + id);
            todoRepository.deleteById(id);
        }
    }

    public Todo createTodoItem(Todo todo) {

        log.info("saving new ToDo entry");
        todo.setIsDone(false);
        todo.setCreatedAt(new Date());
        return todoRepository.save(todo);
    }
}
