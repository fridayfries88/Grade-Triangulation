<%-- 
    Document   : assignment
    Created on : Jun 2, 2024, 12:52:30 p.m.
    Author     : alexp
--%>
<style>
/* Dropdown Button */
.dropbtn {
  background-color: #F0F0F0;
  color: black;
  padding: 5px;
  font-size: 12px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #E0E0E0;}
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("name")%></title>
    </head>
    <body>
    <center>
        <h1><%=request.getAttribute("name")%></h1>
        <form action="/save-assignment" method="post">
        <table>
            <tr>
                <th>Student</th>
                <%=request.getAttribute("criteria")%>
            </tr>
            <%=request.getAttribute("rows")%>
        </table>
        <button type="submit">Save</button>
        <button type="button" onclick="if (window.confirm('Make sure you have saved. Cancel to go back and save')) window.location = '/classes'">Back to classes</button>
        <button type="button" onclick="downloadTable()">Download this table</button>
        </form>
    </center>
    <script>
        function setValue(val) {
            var cell = val.parentElement.parentElement;
            cell.getElementsByClassName("dropbtn")[0].textContent = val.textContent;
            cell.getElementsByClassName("value")[0].value = val.textContent;
        }
    </script>
    </body>
</html>
