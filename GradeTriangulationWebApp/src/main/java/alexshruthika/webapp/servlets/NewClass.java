package alexshruthika.webapp.servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.PrivateServlet;

/**
 *
 * @author alexp
 */
public class NewClass extends PrivateServlet {
    private static final long serialVersionUID = 1L;

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
        String courseCode = request.getParameter("courseCode");
        String numStudents = request.getParameter("numStudents");
        
        try {
            if (courseCode == null || courseCode.isEmpty()
             || Integer.parseInt(numStudents) < 1 || Integer.parseInt(numStudents) > 50) {
                request.getRequestDispatcher("/WEB-INF/new-class.jsp").forward(request, response);
            } else {
                //createClass(courseCode);
                response.sendRedirect("/add-students");
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("/WEB-INF/new-class.jsp").forward(request, response);
            response.getWriter().println("<html>Hello " + (String)request.getSession().getAttribute("uname") + "<html>");
        }
        
        
    }
    
    private void createClass(String courseCode, HttpSession session) {
        try {
            Connection con = DatabaseConnection.init();
            PreparedStatement st = con.prepareStatement(
                "insert into classes (user_id, course_code) values (?, ?)");
            st.setInt(1, (Integer)session.getAttribute("user_id"));
            
        } catch (Exception e) { 
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
        return "Web page for creating a new class of students";
    }// </editor-fold>

}
