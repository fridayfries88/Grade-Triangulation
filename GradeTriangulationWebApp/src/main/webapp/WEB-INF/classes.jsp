<%-- 
    Document   : classes
    Created on : May 23, 2024, 1:09:55 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/styles/generic.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Classes</title>
    </head>
    <body>
    <center>
        <h1>Your Classes</h1>
        <%=request.getAttribute("classes")%>
    
    </center>
    <script>
        function goToClass(id, location) {
            window.location = location + "?classID=" + id;
        }
    </script>
    </body>
    
</html>
