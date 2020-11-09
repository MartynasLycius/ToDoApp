<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
 <title>ToDo List</title>
</head>
<body>
 <center>
  <h1>ToDo List</h1>
        <h2>
         <a href="new">New Task</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Task</a>

        </h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Task</h2></caption>
            <tr>
                <th>ID</th>
                <th>Action Name</th>
                <th>Description</th>
                <th>Task Date</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="task" items="${listTask}">
                <tr>
                    <td><c:out value="${task.id}" /></td>
                    <td><c:out value="${task.taskName}" /></td>
                    <td><c:out value="${task.description}" /></td>
                    <td><fmt:formatDate type="date" value="${task.taskDate}" /></td>
                    <td>
                     <a href="edit?id=<c:out value='${task.id}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${task.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
