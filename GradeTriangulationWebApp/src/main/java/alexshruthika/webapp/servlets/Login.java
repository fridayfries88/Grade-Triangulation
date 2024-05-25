/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
/**
 *
 * @author alexp
 */
public class Login extends HttpServlet {

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
        String message = "";
        response.setContentType("text/html;charset=UTF-8");
        
        checkUsername: try {
            if (request.getParameter("username") == null)
                break checkUsername;
            // connect to database and prepare statement to retrieve password given username
            Connection con = DatabaseConnection.init();
            PreparedStatement st = con.prepareStatement("select password, id from users where username =?");
            // insert inputted username into statement
            st.setString(1, request.getParameter("username"));
            ResultSet result = st.executeQuery();
            result.next();
            // check if inputted password equals password in users table
            if (result.getString("password").equals(request.getParameter("password"))) {
                // add user id to http session data
                request.getSession().setAttribute("user_id", result.getInt("id"));
                
                // send user to next page
                response.sendRedirect("/classes");
                return;
            }
            message = "Password is Incorrect";
            request.setAttribute("username", request.getParameter("username"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            if (e instanceof SQLException)
                message = "Invalid Username";
            else
                System.err.println("Error: " + e);
        }
        request.setAttribute("error", message);
        request.getRequestDispatcher("/WEB-INF/login.jsp").include(request, response);
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
