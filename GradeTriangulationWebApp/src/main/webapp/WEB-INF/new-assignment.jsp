<%-- 
    Document   : new-assignment
    Created on : May 31, 2024, 2:07:16 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - New Assignment</title>
    </head>
    <body>
        <h1>Create a new assignment</h1>
        <form action="new-assignment" method="post">
            Type of assignment:
            <input type="text" name="type" required> <br>
            
        </form>
    </body>
</html>
