<%-- 
    Document   : new-assignment
    Created on : May 31, 2024, 2:07:16 p.m.
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - New Assignment</title>
    </head>
    <body>
    <center>
        <h1>Create a new assignment</h1>
        <form action="new-assignment" method="post">
            Type of assignment:
            <input type="text" name="type" required> <br>
            Name:
            <input type="text" name="name"> <br>
            Criteria:
            <table name="criteria"
                   id="criteria">
            <tr>
            <th>Name</th>
            <th>Type</th>
            </tr>
            <tr>
                <td><input type="text" name="name1"></td>
            <td>
            <div class="dropdown">
                <button type="button" class="dropbtn">[Type]</button>
                <div class="dropdown-content">
                  <%=request.getAttribute("types")%>
                  <a href="/new-type">New</a>
                </div>
            </div>
            </td>
            </tr>
            </table>
            <input type="button" onclick="addRow()" value="Add Criterium">
            <br><br>
            <input type="submit" value="Create Assignment">
        </form>
    </center>
    <script>
        function addRow() {
            var criteria = document.getElementById("criteria");
            var row = criteria.insertRow();
            row.insertCell(0).innerHTML = '<input type="text" name="name1">';
            row.insertCell(1).innerHTML = 
                '<div class="dropdown">'
                + '<button type="button" class="dropbtn">[Type]</button>\n'
                + '<div class="dropdown-content">\n'
                  + '<%=request.getAttribute("types")%>'
                  + '<a href="/new-type">New</a>'
                + '</div>'
              + '</div>';
        }
        
        function setType(type) {
            var dropdown = type.parentElement.parentElement.getElementsByClassName("dropbtn");
            dropdown[0].textContent = type.textContent;
        }
    </script>
    </body>
</html>
