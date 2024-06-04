/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.util.*;

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
            String[] criteria = new String[0];
            String[] criteriaTypes = new String[0];
            if (name == null || name.isEmpty()) {
                focus = "name";
                break checks;
            }
            
            message = checkCriteria(request, criteria, criteriaTypes);
            if (message != null) {
                focus = "criterium0";
                break checks;
            }
            System.err.println(Arrays.toString(criteria) + " " + Arrays.toString(criteriaTypes));
        }
        request.setAttribute("courseCode", request.getSession().getAttribute("courseCode"));
        request.setAttribute("focus", focus);
        request.setAttribute("message", message);
        request.setAttribute("types", getTypes());
        request.getRequestDispatcher("/WEB-INF/new-assignment.jsp").include(request, response);

    }
    
    private String checkCriteria(HttpServletRequest request, String[] criteria, String[] criteriaTypes) {
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
            return "No Criteria. Make sure rows are filled in order.";
        names.toArray(criteria);
        types.toArray(criteriaTypes);
        return null;
    }
    
    private void createAssignment() {
        
    }
    
    private String getTypes() {
        // eventually, this will access sql
        return "<a onclick=\"setType(this)\">Type1</a>" +
               "<a onclick=\"setType(this)\">Type2</a>";
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
