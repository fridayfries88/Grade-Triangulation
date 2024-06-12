<%-- 
    Document   : assignments
    Created on : May 29, 2024, 1:43:04 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/styles/generic.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - Assignments</title>
    </head>
    <body>
    <center>
        <h1><%=request.getAttribute("classInfo")%></h1>
        <%=request.getAttribute("assignments")%>
    </center>
    </body>
</html>
