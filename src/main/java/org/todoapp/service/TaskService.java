package org.todoapp.service;

import org.todoapp.dao.ITaskDao;
import org.todoapp.dao.TaskDao;
import org.todoapp.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskService implements ITaskService {

    private ITaskDao taskDao;

    public TaskService() {
        taskDao = new TaskDao();
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
        dispatcher.forward(request, response);
    }

    public void insertTask(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ParseException {
        String name = request.getParameter("taskName");
        String description = request.getParameter("description");
        String taskDate = request.getParameter("task_date");
        System.out.println("---------" + taskDate);
        Date parsedDate = null;
        if (!taskDate.isEmpty()) {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDate);
        }
        Task newTask = new Task(name, description, parsedDate);
        taskDao.saveTask(newTask);
        response.sendRedirect("list");
    }

    public void listTask(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        List<Task> listTask = taskDao.getAllTask();
        request.setAttribute("listTask", listTask);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
        dispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskDao.getTask(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);

    }

    public void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("taskName");
        String description = request.getParameter("description");
        String taskDate = request.getParameter("task_date");

        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(taskDate);

        Task task = new Task(id, name, description, parsedDate);
        taskDao.updateTask(task);
        response.sendRedirect("list");
    }

    public void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskDao.deleteUser(id);
        response.sendRedirect("list");
    }
}
