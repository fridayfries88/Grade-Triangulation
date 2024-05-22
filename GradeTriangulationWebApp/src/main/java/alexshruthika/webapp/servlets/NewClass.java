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

/**
 *
 * @author alexp
 */
public class NewClass extends HttpServlet {
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
            response.getWriter().println("Hello " + (String)request.getSession().getAttribute("uname"));
        }
        
        
    }
    
    private void createClass(String courseCode) {
        try {
            Connection con = DatabaseConnection.initDatabase();
            PreparedStatement st = con.prepareStatement("insert into ");
            st.setString(1, courseCode);
            
        } catch (Exception e) { 
            System.err.println("Error: " + e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
