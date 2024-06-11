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
public class DownloadStudent extends PrivateServlet {

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
        response.setContentType("text/csv");
        // send back to assignments if no assignmentID
        if (request.getSession().getAttribute("studentID") == null) {
            response.sendRedirect("/students");
            return;
        }
        // get student and class id
        int studentID = (int)request.getSession().getAttribute("studentID");
        
        try (Connection con = DatabaseConnection.init();
             PrintWriter out = response.getWriter()) {
            int assignmentID;
            String value;
            ResultSet result;
            PreparedStatement st = con.prepareStatement(
            "select id, name from assignments where assignment_class_id=?");
            st.setInt(1, (int)request.getSession().getAttribute("classID"));
            ResultSet assignments = st.executeQuery();
            while (assignments.next()) {
                assignmentID = assignments.getInt("id");
                // get assignment table value
                st = con.prepareStatement("select * from assignment"
                   + assignmentID + " where assignment" + assignmentID
                   + "_student_id=?");
                st.setInt(1, studentID);
                result = st.executeQuery();
                result.next();
                // print criteria
                for (int i = 2; i <= result.getMetaData().getColumnCount(); i++) {
                    value = result.getMetaData().getColumnName(i);
                    value = value.substring(0, value.lastIndexOf('_'));
                    out.print("," + value);
                }
                out.print("\n" + assignments.getString("name"));
                for (int i = 2; i <= result.getMetaData().getColumnCount(); i++) {
                    value = result.getString(i);
                    if (value == null) value = "";
                    out.print("," + value);
                }
                out.println();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        response.sendRedirect("/assignment");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Downloads Student to csv";
    }// </editor-fold>

}