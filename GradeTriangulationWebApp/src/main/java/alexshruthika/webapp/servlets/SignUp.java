/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.sql.*;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;

/**
 *
 * @author alexp
 */
public class SignUp extends HttpServlet {

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
        String message = "";
        String username = request.getParameter("username");
        if ((message = checkValidUsername(username, message)).isEmpty()) {
            // continue
        }
        
        String password = request.getParameter("password");
        if (!password.equals(request.getParameter("confirmPassword"))) {
            message = "Passwords do not match";
        }
        
        request.setAttribute("error", message);
        request.getRequestDispatcher("/WEB-INF/sign-up.jsp").include(request, response);
    }
    
    private String checkValidUsername(String username, String message) {
        if (username.length() >= 50)
            return "Username is too long.";
        try {
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
                "select username from users where username=?");
            st.setString(1, username);
            st.executeQuery();
            // username is already in database
            return "Username is in use.";
        } catch (SQLException | ClassNotFoundException e) {
            // username does not exist in database
            return message;
        }
    }
    
    private void createUser(String username, String password, HttpSession session) {
        try {
            // connect to database
            Connection con = DatabaseConnection.init();
            
            // add user to table of users
            PreparedStatement st = con.prepareStatement(
                "insert into users (username, password) values (?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.executeQuery();
            
            // put user if in http session data
            st = con.prepareStatement(
            "select id from users where username=?");
            st.setString(1, username);
            ResultSet result = st.executeQuery();
            result.next();
            session.setAttribute("user_id", (String)result.getString("id"));
        } catch (SQLException | ClassNotFoundException e) {
            
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
        return "Short description";
    }// </editor-fold>

}
