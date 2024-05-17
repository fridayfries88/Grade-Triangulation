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
                <input type="text" name="courseCode" value='<%=request.getParameter("courseCode") == null ? "" : request.getParameter("courseCode")%>'>
                <br>
                Number of Students: <br>
                <input type="number" name="numStudents" value='<%=request.getParameter("numStudents")%>' width="50">
                <br><br>
                <button type="submit" name="create">Create</button>
            </form>
        </div>
    </body>
</html>
