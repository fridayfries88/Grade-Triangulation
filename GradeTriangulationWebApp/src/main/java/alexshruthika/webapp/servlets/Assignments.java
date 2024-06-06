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
 * TODO: allow searching by unit and type
 * @author alexp
 */
public class Assignments extends PrivateServlet {

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
            response.sendRedirect("/assignments");
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
            request.setAttribute("classInfo", result.getString("course_code") + ","
                                            + result.getInt("year") + ","
                                            + "SEM" + result.getInt("semester") + ","
                                            + "P" + result.getInt("period")); // TODO
        
            // get assignments
            String assignments = "";
            st = DatabaseConnection.init().prepareStatement(
            "select * from assignments where assignment_class_id=?");
            st.setInt(1, classID);
            result = st.executeQuery();
            while (result.next()) {
                assignments += makeAssignment(result);
            }
            if (assignments.isEmpty()) {
                assignments = "You do not have any assignments. <button onclick='window.location = \"/new-assignment\"'>Create one now.</button>";
            } else {
                assignments += "<button onclick='window.location = \"/new-assignment\"'>Create new assignment</button>";
            }
            request.setAttribute("assignments", assignments);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        request.getRequestDispatcher("/WEB-INF/assignments.jsp").include(request, response);
    }

    private String makeAssignment(ResultSet result) throws SQLException {
        return "<button onclick='window.location = \"/assignment?assignmentID=" +
             result.getInt("id") + "\"'>" + result.getString("name") + "</button><br>\n";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Shows all assignments of a class";
    }// </editor-fold>

}
