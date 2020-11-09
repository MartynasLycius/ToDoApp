package org.todoapp.dao;

import org.todoapp.model.Task;

import java.util.List;

public interface ITaskDao {
     void saveTask(Task task);
     List< Task > getAllTask();
     Task getTask(int id);
     void updateTask(Task task);
     void deleteUser(int id);
}
