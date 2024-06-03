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
        <p>Sign up here</p>
        <form method="post" action="sign-up">
            Username: <input id="username" name="username" type="text" value='<%=request.getAttribute("username")%>'> 
            <br>
            Password: <input id="password" name="password" type="password" value='<%=request.getAttribute("password")%>'>
            <input name="showPassword" type="checkbox" onclick='togglePassword("password")' value='<%=request.getParameter("showPassword")%>'>
            <br>
            Confirm: <input id="confirmPassword" name="confirmPassword" type="password" value='<%=request.getAttribute("confirmPassword")%>'>
            <input name="showConfirm" type="checkbox" onclick='togglePassword("confirmPassword")' value='<%=request.getParameter("showConfirm")%>'>
            <p><%=request.getAttribute("error")%></p> <!-- TODO: Make red -->
            <button type="submit">Create Account</button>
            <br> <br>
            <button type="button" onclick="window.location='/login'">Already have an account? Login here</button>
        </form>
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
