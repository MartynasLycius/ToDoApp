package com.todo.web.service;

import com.todo.web.entity.TodoItem;
import com.todo.web.exception.RecordNotFoundException;
import com.todo.web.form.TodoItemForm;
import com.todo.web.repository.TodoItemRepository;
import com.todo.web.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for todoItem
 *
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 2020-20-05
 */
@Service
public class TodoItemService {

    @Autowired
    TodoItemRepository todoItemRepository;

    /**
     * This method used to initialized the TodoItemForm
     *
     * @param model        this is the model object
     * @param todoItemForm TodoItemForm
     */
    public void viewInit(Model model, TodoItemForm todoItemForm) {
        if (todoItemForm == null) {
            todoItemForm = new TodoItemForm();
        }
        model.addAttribute(todoItemForm);
    }

    /**
     * This method used to get all the todoItems
     *
     * @return List todoItems
     */
    public List<TodoItem> getAllTodoItems() {
        List<TodoItem> result = todoItemRepository.findByOrderByTargetDateAsc();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * This method used to get the todoItem by it's id
     *
     * @param id long value
     * @return todoItem TodoItem
     * @throws RecordNotFoundException exception
     */
    public TodoItem getTodoItemById(Long id) throws RecordNotFoundException {
        Optional<TodoItem> todoItem = todoItemRepository.findById(id);

        if (todoItem.isPresent()) {
            return todoItem.get();
        } else {
            throw new RecordNotFoundException("No TodoItem record exist for given id");
        }
    }

    /**
     * This method used to registry the todoItem
     *
     * @param todoItemForm TodoItemForm
     * @throws RecordNotFoundException exception
     */
    public void registry(TodoItemForm todoItemForm) throws RecordNotFoundException {
        TodoItem todoItem = new TodoItem();
        if (todoItemForm.getId() != null) {
            todoItem = getTodoItemById(todoItemForm.getId());
        }
        todoItem.setItemName(todoItemForm.getItemName());
        todoItem.setDescription(todoItemForm.getDescription());
        todoItem.setTargetDate(Util.strToDt(todoItemForm.getTargetDate()));

        todoItemRepository.save(todoItem);
    }

    /**
     * This method used to delete the todoItem by it's id
     *
     * @param id    long value
     * @param model this is the model object
     * @throws RecordNotFoundException exception
     */
    public void deleteTodoById(@PathVariable("id") long id, Model model) throws RecordNotFoundException {
        TodoItem todoItem = getTodoItemById(id);
        todoItemRepository.delete(todoItem);
        model.addAttribute("todoItems", todoItemRepository.findAll());
    }

    /**
     * This method used to get the todoItemForm by it's id
     *
     * @param id long value
     * @return todoItemForm TodoItemForm
     * @throws RecordNotFoundException exception
     */
    public TodoItemForm getTodoItemForm(Long id) throws RecordNotFoundException {
        TodoItemForm todoItemForm = new TodoItemForm();
        TodoItem todoItem;
        if (id != null) {
            todoItem = getTodoItemById(id);
            todoItemForm.setId(todoItem.getId());
            todoItemForm.setItemName(todoItem.getItemName());
            todoItemForm.setDescription(todoItem.getDescription());
            todoItemForm.setTargetDate(Util.dtToStr(todoItem.getTargetDate()));
        }
        return todoItemForm;
    }

}