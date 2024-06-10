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
public class DownloadAssignment extends PrivateServlet {

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
        if (request.getSession().getAttribute("assignmentID") == null) {
            response.sendRedirect("/assignments");
            return;
        }
        // get assignment and class id
        int assignmentID = (int)request.getSession().getAttribute("assignmentID");
        
        try (Connection con = DatabaseConnection.init();
             PrintWriter out = response.getWriter()) {
            String value;
            PreparedStatement st = con.prepareStatement(
            "select * from assignment" + assignmentID);
            ResultSet assignment = st.executeQuery();
            ResultSet studentName;
            st = con.prepareStatement(
            "select first_name, last_name from students where id=?");
            out.print("Student,");
            for (int i = 2; i <= assignment.getMetaData().getColumnCount(); i++) {
                out.print("," + assignment.getMetaData().getColumnName(i));
            }
            while (assignment.next()) {
                st.setInt(1, assignment.getInt("assignment" + assignmentID + "_student_id"));
                studentName = st.executeQuery();
                studentName.next();
                out.print(studentName.getString("first_name") + " " + studentName.getString("last_name"));
                for (int i = 2; i <= assignment.getMetaData().getColumnCount(); i++) {
                    value = assignment.getString(i);
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
        return "Downloads Assignment to csv";
    }// </editor-fold>

}