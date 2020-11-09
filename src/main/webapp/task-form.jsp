<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
 <title>TODO</title>
</head>
<body>
 <center>
  <h1>TO-DO</h1>
        <h2>
         <a href="new">New Task</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Task</a>

        </h2>
 </center>
    <div align="center">
  <c:if test="${task != null}">
   <form action="update" method="post">
        </c:if>
        <c:if test="${task == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${task != null}">
               Edit User
              </c:if>
              <c:if test="${task == null}">
               Add New Task
              </c:if>
             </h2>
            </caption>
          <c:if test="${task != null}">
           <input type="hidden" name="id" value="<c:out value='${task.id}' />" />
          </c:if>
            <tr>
                <th>Task Name: </th>
                <td>
                 <input type="text" name="taskName" size="45"
                   value="<c:out value='${task.taskName}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                 <input type="text" name="description" size="45"
                   value="<c:out value='${task.description}' />"
                 />
                </td>
            </tr>

             <tr>
                 <th>Date: </th>
                 <td>
                   <input type="date" name="task_date" size="45"
                     value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${task.taskDate}" />"
                    />
                  </td>
             </tr>

            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>