<%-- 
    Document   : home
    Created on : May 16, 2024, 12:31:59 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <center>
        <h1>Welcome to Grade Triangulation</h1>
        <p>Log in or sign up</p>
        <form name ="makeClassForm" method="post" action="home">
            Username: <input id="username" type="text" value='<%=request.getParameter("username") == null ? "" : request.getParameter("username")%>'> 
            Password: <input id="password" type="password">
            <input id="showPassword" type="checkbox" onclick="togglePassword()">
        </form>
        <button type="button" onclick="window.location='/new-class'">Create new class</button>
    </center> 
    </body>
    <script>
        function togglePassword() {
            var x = document.getElementById("password");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password"
            }
        }
    </script>
</html>
