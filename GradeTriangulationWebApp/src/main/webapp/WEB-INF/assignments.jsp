<%-- 
    Document   : assignments
    Created on : May 29, 2024, 1:43:04 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - Assignments</title>
    </head>
    <body>
        <h1><%=request.getAttribute("classInfo")%></h1>
        <%=request.getAttribute("assignments")%>
    </body>
</html>
