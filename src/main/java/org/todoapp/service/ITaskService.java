package org.todoapp.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface ITaskService {
     void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
     void insertTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException;
     void listTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException;
     void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
     void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException;
     void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
