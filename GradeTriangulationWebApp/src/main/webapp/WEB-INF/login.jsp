<%-- 
    Document   : login
    Created on : May 16, 2024, 12:31:59 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/styles/login.css" type="text/css">
<head>
    <title>Log In</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<center>
    <div class="login-container">
        <h1>Welcome to Grade Triangulation</h1>
        <p>Please log in or sign up</p>
        <form method="post" action="login">
            <div class="form-group">
                <label for="username">Username</label>
                <input name="username" type="text" id="username" value='<%= request.getAttribute("username") == null ? "" : request.getAttribute("username") %>' required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required>
                <input type="checkbox" onclick="togglePassword()"> show password
            </div>
            <button type="submit">Log In</button>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        </form>
        <button class="redirect-button" type="button" onclick="window.location='/sign-up'">No Account? Sign Up Here</button>
    </div>
</center>
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
</body>
</html>

