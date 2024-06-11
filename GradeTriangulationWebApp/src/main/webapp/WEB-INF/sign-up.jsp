<%-- 
    Document   : sign-up
    Created on : May 16, 2024, 12:17:59 p.m.
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
        <p>Sign up here</p>
        <form method="post" action="sign-up">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" type="text" value='<%=request.getAttribute("username")%>'> 
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" value='<%=request.getAttribute("password")%>'>
                <input name="showPassword" type="checkbox" onclick='togglePassword("password")' value='<%=request.getParameter("showPassword")%>'>
                show password
            </div>
            <div class="form-group">
                <label for="password">Confirm</label>
                <input id="confirmPassword" name="confirmPassword" type="password" value='<%=request.getAttribute("confirmPassword")%>'>
                <input name="showConfirm" type="checkbox" onclick='togglePassword("confirmPassword")' value='<%=request.getParameter("showConfirm")%>'>
                show password
            </div>
            <button type="submit">Create Account</button>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        </form>
        <button class="redirect-button" type="button" onclick="window.location='/login'">Already have an account? Login here</button>
    </div>
    </center> 
    </body>
    <script>
        // focus input told by servlet
        document.getElementById("<%=request.getAttribute("focused")%>").focus();
        
        function togglePassword(id) {
            var password = document.getElementById(id);
            if (password.type === "password") {
                password.type = "text";
            } else {
                password.type = "password";
            }
        }
    </script>
</html>
