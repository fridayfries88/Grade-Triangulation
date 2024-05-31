<%-- 
    Document   : new-class
    Created on : May 14, 2024, 12:28:44 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Class</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <div>
            <h1>Create a new class</h1>
            <form action="new-class" method="post">
                Course Code:
                <input type="text" name="courseCode" id="courseCode" size="10" required> <br>
                Year:
                <input type="number" name="year" id="year" min="2024" max="2100"> <br>
                Semester:  
                1<input type="radio" name="semester" value="1"> 
                2<input type="radio" name="semester" value="2"> <br>
                Period:
                1<input type="radio" name="period" value="1"> 
                2<input type="radio" name="period" value="2">
                3<input type="radio" name="period" value="3"> 
                4<input type="radio" name="period" value="4"> <br>
                Students: <br>
                <textarea name="Students" 
                          onkeyup="textAreaAdjust(this)"
                          style="overflow:hidden"
                          placeholder="Firstname Lastname
Firstname Lastname..."
                          cols="40" rows="4"></textarea>
                          
                <p><%=request.getAttribute("message")%></p>
                <button type="submit" name="create">Create</button>
            </form>
        </div>
    </center>
    <script>
        // put current year in form
        if (document.getElementById("year").value === "")
            document.getElementById("year").value = new Date().getFullYear();
        
        // focus input told by servlet
        document.getElementById("<%=request.getAttribute("focused")%>").focus();
        
        function textAreaAdjust(textarea) {
            textarea.style.height = "1px";   
            textarea.style.height = (25 + textarea.scrollHeight)+"px"; 
        }
    </script>
    </body>
</html>
