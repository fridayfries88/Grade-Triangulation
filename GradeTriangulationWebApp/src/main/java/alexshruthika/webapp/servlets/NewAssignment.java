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
public class NewAssignment extends PrivateServlet {

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
        String focus = "type";
        checks: {
            String type = request.getParameter("type");
            String name = request.getParameter("name");
            String[][] criteria; // array 0 is names, array 1 is types
            if (name == null || name.isEmpty()) {
                focus = "name";
                break checks;
            }
            
            criteria = checkCriteria(request);
            if (criteria.length == 1) {
                focus = "criterium0";
                message = criteria[0][0];
                break checks;
            }
            createAssignment(type, name, criteria, request.getSession());
        }
        request.setAttribute("courseCode", request.getSession().getAttribute("courseCode"));
        request.setAttribute("focus", focus);
        request.setAttribute("message", message);
        request.setAttribute("types", getTypes());
        request.getRequestDispatcher("/WEB-INF/new-assignment.jsp").include(request, response);

    }
    
    private String[][] checkCriteria(HttpServletRequest request) {
        String[][] out;
        ArrayList<String> names = new ArrayList();
        ArrayList<String> types = new ArrayList();
        names.add(request.getParameter("criterium0"));
        types.add(request.getParameter("type0"));
        int i;
        for (i = 1; names.getLast() != null && types.getLast() != null; i++) {
            names.add(request.getParameter("criterium" + i));
            types.add(request.getParameter("type" + i));
        }
        if (i == 1)
            return new String[][]{{"No Criteria. Make sure rows are filled in order."}};
        out = new String[2][names.size()];
        out[0] = names.toArray(out[0]);
        out[1] = types.toArray(out[1]);
        return out;
    }
    
    private void createAssignment(String type, String name, String[][] criteria, HttpSession session) {
        try (Connection con = DatabaseConnection.init()) {
            int id;
            String tableStatement;
            
            // add assignment to assignments table
            PreparedStatement st = con.prepareStatement(
            "insert into assignments (assignment_class_id, type, name) values (?, ?, ?)");
            st.setInt(1, (Integer)session.getAttribute("classID"));
            st.setString(2, type);
            st.setString(3, name);
            st.executeUpdate();
            
            // get id of new assignment
            st = con.prepareStatement(
            "select max(id) from assignments");
            ResultSet result = st.executeQuery();
            result.next();
            id = result.getInt(1);
            
            tableStatement = 
            "create table assignment" + id + "(\n" +
                "`student_id` int unsigned not null,\n";
            for (int i = 0; i < criteria[0].length - 1; i++) {
                tableStatement += "`" + criteria[0][i] + "_" + criteria[1][i] + "` varchar(100),\n";
            }
            tableStatement+=
                "index student_id(student_id ASC),\n" +
                "constraint student_id foreign key(student_id) references students(id) on delete restrict on update cascade);";
            st = con.prepareStatement(tableStatement);
            st.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }
    
    private String getTypes() {
        // eventually, this will access sql
        return "<a onclick=\"setType(this)\">Yes/No</a>" +
               "<a onclick=\"setType(this)\">GradeLevels</a>" +
               "<a onclick=\"setType(this)\">Text</a>";
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
