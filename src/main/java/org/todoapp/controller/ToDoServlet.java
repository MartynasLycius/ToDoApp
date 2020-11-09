package org.todoapp.controller;

import org.todoapp.service.ITaskService;
import org.todoapp.service.TaskService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class ToDoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ITaskService taskService;

    public void init() {
        taskService = new TaskService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    taskService.showNewForm(request, response);
                    break;
                case "/insert":
                    taskService.insertTask(request, response);
                    break;
                case "/delete":
                    taskService.deleteTask(request, response);
                    break;
                case "/edit":
                    taskService.showEditForm(request, response);
                    break;
                case "/update":
                    taskService.updateTask(request, response);
                    break;
                default:
                    taskService.listTask(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }


}
