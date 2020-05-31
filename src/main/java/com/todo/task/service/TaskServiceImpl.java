package com.todo.task.service;

import com.todo.task.entity.Task;
import com.todo.task.repo.CommentRepository;
import com.todo.task.repo.TaskRepository;
import com.todo.user.entity.User;
import com.todo.user.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private static final Logger logger = LogManager.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Task task){
        taskRepository.save(task);
    }

    @Override
    public void delete(Task task){
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findByDate(LocalDate date){
        return taskRepository.findByDate(date);
    }

    public List<Task> findAllByUser(User user){
        return taskRepository.findByTaskOwner(user);
    }
    public List<Task> findAllByUserAndDate( User user, LocalDate date){
        return taskRepository.findByDateAndTaskOwner(date, user);
    }
}
