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
        request.setAttribute("error", checkForm(request, response));
        request.getRequestDispatcher("/WEB-INF/sign-up.jsp").include(request, response);
    }
    
    private String checkForm(HttpServletRequest request, HttpServletResponse response) {
        String message;
        // retrieve inputs from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        // erase inputs and error from form
        request.setAttribute("username", "");
        request.setAttribute("password", "");
        request.setAttribute("confirmPassword", "");
        request.setAttribute("error", "");
        request.setAttribute("focused", "username");
        // check if username and password are valid
        if ((message = checkValidUsername(username, request)) != null
         || (message = checkValidPassword(password, confirmPassword, request)) != null)
            return message;
        // if no error, create user and return empty error message
        createUser(username, password, request.getSession());
        return "";
    }
    
    private String checkValidUsername(String username, HttpServletRequest request) {
        // if no username, don't send error but don't create user either
        if (username == null || username.isEmpty())
            return "";
        if (username.length() > 49)
            return "Username must be less than 50 characters.";
        try {
            // check if there's already a user with that name
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
                "select id from users where username=?");
            st.setString(1, username);
            ResultSet result = st.executeQuery();
            result.next();
            result.getString("id");
            
            // username is already in database
            return "Username is in use.";
        } catch (SQLException | ClassNotFoundException e) { // username does not exist in database
            // focus password
            request.setAttribute("focused", "password");
            // keep username
            request.setAttribute("username", username);
            return null;
        }
    }
    
    private String checkValidPassword(String password, String confirmPassword, HttpServletRequest request) {
        if (password == null || password.isEmpty())
            return "";
        int pLength = password.length();
        if (pLength < 5 || pLength > 49)
            return "Password must be between 5 and 49 characters.";
        // keep password
        request.setAttribute("password", password);
        if (!password.equals(confirmPassword)) {
            // focus confirmPassword
            request.setAttribute("focused", "confirmPassword");
            return "Passwords do not match.";
        }
        return null;
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
            st.executeUpdate();
            
            // put user if in http session data
            st = con.prepareStatement(
            "select id from users where username=?");
            st.setString(1, username);
            ResultSet result = st.executeQuery();
            result.next();
            session.setAttribute("user_id", (String)result.getString("id"));
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Unable to create user. Error: " + e);
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
