/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.util.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.PrivateServlet;

/**
 *
 * @author alexp
 */
public class NewStudent extends PrivateServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("firstName") == null) {
            request.setAttribute("courseCode", request.getSession().getAttribute("courseCode"));
            request.getRequestDispatcher("/WEB-INF/new-student.jsp").include(request, response);
            return;
        }
        try (Connection con = DatabaseConnection.init()) {
            ResultSet assignmentIDs;
            ResultSet result;
            int studentID;
            PreparedStatement st = con.prepareStatement(
            "insert into students (student_class_id, first_name, last_name) values (?, ?, ?)");
            st.setInt(1, (int)request.getSession().getAttribute("classID"));
            st.setString(2, request.getParameter("firstName"));
            st.setString(3, request.getParameter("lastName"));
            st.executeUpdate();
            st = con.prepareStatement(
            "select id from assignments where assignment_class_id=?");
            st.setInt(1, (int)request.getSession().getAttribute("classID"));
            assignmentIDs = st.executeQuery();
            st = con.prepareStatement(
            "select max(id) from students");
            result = st.executeQuery();
            result.next();
            studentID = result.getInt(1);
            while (assignmentIDs.next()) {
                st = con.prepareStatement(
                "insert into assignment" + assignmentIDs.getInt(1) + " (assignment"
              + assignmentIDs.getInt(1) + "_student_id) values (?)");
                st.setInt(1, studentID);
                st.executeUpdate();
            }
            response.sendRedirect("/students");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Page to create a student";
    }// </editor-fold>

}
