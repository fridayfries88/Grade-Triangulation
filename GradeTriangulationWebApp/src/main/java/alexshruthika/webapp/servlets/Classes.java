/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.PrivateServlet;
import alexshruthika.webapp.Class_;

/**
 *
 * @author alexp
 */
public class Classes extends PrivateServlet {

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
        String classes = "";
        response.setContentType("text/html;charset=UTF-8");
        try {
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
                    "select * from classes where user_id=?");
            st.setInt(1, (Integer)request.getSession().getAttribute("userID"));
            ResultSet result = st.executeQuery();
            while (result.next()) {
                classes += makeClass(result);
            }
            if (classes.isEmpty()) {
                classes = "You do not have any classes. <button onclick='window.location = \"/new-class\"'>Create one now.</button>";
            } else {
                classes += "<br><button onclick='window.location = \"/new-class\"'>Create new class</button>";
            }
            request.setAttribute("classes", classes);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        request.getRequestDispatcher("/WEB-INF/classes.jsp").include(request, response);
    }
    
    private String makeClass(ResultSet result) throws SQLException {
        return result.getString("course_code") + ","
                + result.getInt("year") + ","
                + "S" + result.getInt("semester") + ","
                + "P" + result.getInt("period") + "<br>"
              + "<button onclick=\"goToClass(\'" + result.getInt("id")
                  + "', '/assignments')\">assignments</button>"
              + "<button onclick=\"goToClass('" + result.getInt("id")
                  + "', '/students')\">students</button><br><br>";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Shows all classes of user";
    }// </editor-fold>

}
