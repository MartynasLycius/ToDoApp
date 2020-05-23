package com.santam.todolycias.backend.service;

import com.santam.todolycias.backend.entity.Todo;
import com.santam.todolycias.backend.repository.TodoRepository;
import com.santam.todolycias.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TodoService {

    private static final Logger LOGGER = Logger.getLogger(TodoService.class.getName());
    private UserRepository userRepository;
    private TodoRepository todoRepository;

    public TodoService(UserRepository userRepository, TodoRepository todoRepository){
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    //Find All
    public List<Todo> findAll(){
        return todoRepository.findAll();
    }
    //Find By ID
    public Optional<Todo> findByID(int id){
        return todoRepository.findById(id);
    }

    //Count todoo total
    public long count(long ownBy){
        return todoRepository.count();
    }

    //Delete todoo item
    public void deleteTodo(Todo todo){
        todoRepository.delete(todo);
    }
    //Save todoo item
    public void saveTodo(Todo todo){
        if(todo == null){
            LOGGER.log(Level.SEVERE,
                    "Todo is null, Please try again");
            return;
        }
       todoRepository.save(todo);
    }
    //Save minimal todoo item
    public void minimalSaveTodo(String title, String desc, int status, String deadline, int ownBy){
        todoRepository.minimalSave(title, desc, status, deadline, ownBy);
    }


}
