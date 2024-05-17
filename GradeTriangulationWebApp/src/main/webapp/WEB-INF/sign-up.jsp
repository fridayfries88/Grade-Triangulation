<%-- 
    Document   : sign-up
    Created on : May 16, 2024, 12:17:59 p.m.
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
        <form method="post" action="home">
            Username: <input name="username" type="text" required> 
            <br>
            Password: <input id="password" name="password" type="password" required>
            <input type="checkbox" onclick="togglePassword('password')">
            <br>
            Confirm: <input id="confirmPassword" name="confirmPassword" type="password" required>
            <input type="checkbox" onclick="togglePassword('confirmPassword')">
            <br>
            <button type="submit">Sign Up</button>
            <br> <br>
            <button type="button" onclick="window.location='/home/sign-up'">No Account? Sign Up Here</button>
        </form>
    </center> 
    </body>
    <script>
        function togglePassword(var id) {
            var password = document.getElementById(id);
            if (password.type === "password") {
                password.type = "text";
            } else {
                password.type = "password";
            }
        }
    </script>
</html>
