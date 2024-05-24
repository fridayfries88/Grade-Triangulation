<%-- 
    Document   : home
    Created on : May 16, 2024, 12:31:59 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <center>
        <h1>Welcome to Grade Triangulation</h1>
        <p>Log in or sign up</p>
        <form method="post" action="login">
            Username: <input name="username" type="text" value='<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>' required> 
            <br>
            Password: <input id="password" name="password" type="password" required>
            <input type="checkbox" onclick="togglePassword()"> <br>
            <button type="submit">Log In</button>
            <p><%=request.getAttribute("error")%></p> <!-- TODO: Make Red -->
            <button type="button" onclick="window.location='/sign-up'">No Account? Sign Up Here</button>
        </form>
    </center> 
    </body>
    <script>
        function togglePassword() {
            var password = document.getElementById("password");
            if (password.type === "password") {
                password.type = "text";
            } else {
                password.type = "password";
            }
        }
    </script>
</html>
