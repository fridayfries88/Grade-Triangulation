/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import alexshruthika.webapp.PrivateServlet;
import alexshruthika.webapp.DatabaseConnection;

/**
 *
 * @author alexp
 */
public class Students extends PrivateServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // if just getting sent here, set session data and reload to avoid id in url
        if (request.getParameter("classID") != null) {
            request.getSession().setAttribute("classID", Integer.valueOf(request.getParameter("classID")));
            response.sendRedirect("/students");
            return;
        }
        // send back to classes if no classID
        if (request.getSession().getAttribute("classID") == null) {
            response.sendRedirect("/classes");
            return;
        }
        // get class id
        int classID = (int)request.getSession().getAttribute("classID");
        
        try {
            // get other class information
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select * from classes where id=?");
            st.setInt(1, classID);
            ResultSet result = st.executeQuery();
            result.next();
            request.getSession().setAttribute("courseCode", result.getString("course_code"));
            request.setAttribute("courseCode", result.getString("course_code"));
            request.setAttribute("classInfo", result.getString("course_code") + ", "
                                            + result.getInt("year") + ", "
                                            + "SEM" + result.getInt("semester") + ", "
                                            + "P" + result.getInt("period")); // TODO
        
            // get students
            String students = "<table style='border-collapse:separate;table-layout:auto;border-spacing:10px'>";
            st = DatabaseConnection.init().prepareStatement(
            "select * from students where student_class_id=?");
            st.setInt(1, classID);
            result = st.executeQuery();
            while (result.next()) {
                students += makeStudent(result);
            }
            if (students.isEmpty()) {
                students = "</table>You do not have any students. <button onclick='window.location = \"/new-student\"'>Add some now.</button>";
            } else {
                students += "</table><button onclick='window.location = \"/new-student\"'>Add a student</button>";
            }
            request.setAttribute("students", students);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        request.getRequestDispatcher("/WEB-INF/students.jsp").include(request, response);
    }
    
    private String makeStudent(ResultSet result) throws SQLException {
        return "<tr><td><button onclick='window.location = \"/student?studentID="
             + result.getInt("id") + "\"'>" + result.getString("first_name")
             + " " + result.getString("last_name") + "</button></td>" 
             + "<td><button style='background-color:#FF6060' onclick='deleteStudent(\"" + result.getString("first_name")
             + " " + result.getString("last_name") + "\", " + result.getInt("id") + ")'>x</button></td></tr>\n";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Lists all students with buttons to redirct to student page";
    }

}
