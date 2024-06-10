<%-- 
    Document   : new-student
    Created on : Jun 10, 2024, 11:48:26 a.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - New Student</title>
    </head>
    <body>
    <center>
        <h1>Add a new student</h1>
        <form action="new-student" method="post">
            Student Name (First then last): <br>
            <input type="text" name="firstName" required><input type="text" name="lastName" required><br>
            <input type="submit" value="Add">
        </form>
    </center>
    </body>
</html>
