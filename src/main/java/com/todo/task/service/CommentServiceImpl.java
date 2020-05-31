package com.todo.task.service;

import com.todo.task.repo.CommentRepository;
import com.todo.task.repo.TaskRepository;
import com.todo.user.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


}
