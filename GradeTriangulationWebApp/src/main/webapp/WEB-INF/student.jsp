<%-- 
    Document   : student
    Created on : Jun 7, 2024, 1:19:51 p.m.
    Author     : alexp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="/styles/dropdown.css" rel="stylesheet" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("name")%></title>
    </head>
    <body>
    <center>
        <h1><%=request.getAttribute("name")%></h1>
        <form action="/save-student" method="post" style="display:inline-block">
            <%=request.getAttribute("rows")%>
        <button type="submit">Save</button>
        <button type="button" onclick="backToClasses()">Back to classes</button>
        <button type="button" onclick="downloadTable()">Download this table</button>
        </form>
    </center>   
    <script>
        var isSaved = true;
        
        function setValue(val) {
            var cell = val.parentElement.parentElement;
            cell.getElementsByClassName("dropbtn")[0].textContent = val.textContent;
            cell.getElementsByClassName("value")[0].value = val.textContent;
            isSaved = false;
        }
        
        function backToClasses() {
            if (isSaved || window.confirm('Are you sure you want to exit without saving?'))
                window.location = '/classes';
        }
        
        function resizeInput(input) {
            if (input.value.length < 10)
                input.size = 10;
            else
                input.size = input.value.length;
            isSaved = false;
        }
    </script>
    </body>
</html>
