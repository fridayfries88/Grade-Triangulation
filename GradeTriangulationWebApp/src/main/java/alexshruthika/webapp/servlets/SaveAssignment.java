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

import alexshruthika.webapp.PrivateServlet;
import alexshruthika.webapp.DatabaseConnection;

/**
 *
 * @author alexp
 */
public class SaveAssignment extends PrivateServlet {

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
        // send back to assignments if no assignmentID
        if (request.getSession().getAttribute("assignmentID") == null) {
            response.sendRedirect("/assignments");
            return;
        }
        // get assignment and class id
        int assignmentID = (int)request.getSession().getAttribute("assignmentID");
        int classID = (int)request.getSession().getAttribute("classID");
        
        try {
            String currentValue;
            // get all student ids
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select id from students where student_class_id=?");
            st.setInt(1, classID);
            ResultSet studentIDs = st.executeQuery();
            while (studentIDs.next()) {
                // go through each column
                for (int i = 0; i < 15; i++) {
                    System.err.println(request.getParameter(studentIDs.getInt(1) + "_" + i));
                    if ((currentValue = request.getParameter(studentIDs.getInt(1) + "_" + i)) != null) {
                        st = DatabaseConnection.init().prepareStatement(
                        "update assignment" + assignmentID + " set `"
                        + request.getParameter("header" + i) + "` = ? where assignment"
                        + assignmentID + "_student_id=?");
                        st.setString(1, currentValue);
                        st.setInt(2, studentIDs.getInt(1));
                        st.executeUpdate();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        if (request.getParameter("download") != null)
            response.sendRedirect("/download-assignment");
        else
            response.sendRedirect("/assignment");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
