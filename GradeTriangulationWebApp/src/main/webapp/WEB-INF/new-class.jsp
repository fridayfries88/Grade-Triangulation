<%-- 
    Document   : new-class
    Created on : May 14, 2024, 12:28:44 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Class</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <h1>Create a new class</h1>
            <form action="new-class" method="post">
                Course Code: <br>
                <input type="text" name="courseCode" value='<%=
                    request.getParameter("courseCode") == null? ""
                  : request.getParameter("courseCode")%>' required>
                <br>
                Number of Students: <br>
                <input type="number" name="numStudents" value='<%=
                    (request.getParameter("numStudents") == null
                  || request.getParameter("numStudents").isEmpty() ? ""
                  : (Integer.parseInt(request.getParameter("numStudents")) <= 0 ? 1
                  : (Integer.parseInt(request.getParameter("numStudents")) > 50 ? 50
                  : request.getParameter("numStudents")))) %>' required>
                <br><br>
                <button type="submit" name="create">Create</button>
            </form>
        </div>
    </body>
</html>
