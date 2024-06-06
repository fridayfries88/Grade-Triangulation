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
public class Assignment extends PrivateServlet {
    private static final int MAX_TYPES = 13;

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
        // if just getting sent here, set session data and reload to avoid id in url
        if (request.getParameter("assignmentID") != null) {
            request.getSession().setAttribute("assignmentID", Integer.valueOf(request.getParameter("assignmentID")));
            response.sendRedirect("/assignment");
            return;
        }
        // send back to assignments if no assignmentID
        if (request.getSession().getAttribute("assignmentID") == null) {
            response.sendRedirect("/assignments");
            return;
        }
        // get assignment id
        int assignmentID = (int)request.getSession().getAttribute("assignmentID");
        
        try {
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select name from assignments where id=?");
            st.setInt(1, assignmentID);
            ResultSet result = st.executeQuery();
            result.next();
            request.setAttribute("name", result.getString(1));
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        String[] criteriaAndRows = makeRows(assignmentID);
        request.setAttribute("criteria", criteriaAndRows[0]);
        request.setAttribute("rows", criteriaAndRows[1]);
        
        request.getRequestDispatcher("/WEB-INF/assignment.jsp").include(request, response);
    }
    
    private String[] makeRows(int assignmentID) {
        try {
            String[] out = {"", ""};
            HashMap<String, String[]> types = getTypes();
            String[] criteriaTypes;
            String[] values;
            // retrieve assignment data from database
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select * from assignment" + assignmentID);
            ResultSet result = st.executeQuery();
            criteriaTypes = getCriteriaTypes(result.getMetaData());
            // make header for rows
            out[0] = makeHeader(result.getMetaData());
            // make each row
            while (result.next()) {
                // put values in array
                values = new String[result.getMetaData().getColumnCount() - 1];
                for (int i = 2; i <= result.getMetaData().getColumnCount(); i++) {
                    values[i-2] = result.getString(i);
                }
                // add row to output
                out[1] += makeRow(result.getString(1), values, types, criteriaTypes);
            }
            return out;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return null;
    }
    
    private String makeHeader(ResultSetMetaData metaData) throws SQLException, ClassNotFoundException {
        String out = "";
        String columnName;
        for (int i = 2; i <= metaData.getColumnCount(); i++) {
            columnName = metaData.getColumnName(i);
            columnName = columnName.substring(0, columnName.lastIndexOf('_'));
            // make string for header cell
            out += "<th>" + columnName + "</th>\n";
        }
        return out;
    }
    
    /**
    * @param student: name of student lastname, firstname
    * @param values: already inputted criteria in order of columns
    * @param types: hashmap of all types, key=name of type, value=allowed strings
    * @param criteriaTypes: array of all criteria types in order of columns
    * @return String of html to make row of dropdowns for each criterium
    */
    private String makeRow(String student, String[] values, HashMap<String, String[]> types, String[] criteriaTypes) {
        String out =  "<tr>\n" +
                        "<td>" + student + "</td>\n";
        for (int i = 0; i < criteriaTypes.length; i++) {
            // if no options, make text input instead of dropdown
            if (types.get(criteriaTypes[i])[0] == null) {
                out += "<td><input name=\"value_" + student + "_" + i + "\" type=\"text\" class=\"value\"></td>\n";
                continue;
            }
            // if type is percent, make number from 0-100
            if (types.get(criteriaTypes[i])[0].equals("////percentage////")) {
                out += "<td><input name=\"value_" + student + "_" + i +
                       "\" type=\"number\" type=\"range\" min=\"0\" max=\"100\" "
                     + "step=\"0.5\" class=\"value\"></td>\n";
                continue;
            }
            out += "<td><div class=\"dropdown\">\n" +
                    "<button type=\"button\" class=\"dropbtn\">" + values[i] + "</button>\n" + 
                    "<input name=\"value_" + student + "_" + i + "\" type=\"hidden\" class=\"value\">\n" +
                    "<div class=\"dropdown-content\">\n";
            for (String j : types.get(criteriaTypes[i])) {
                if (j == null) break;
                out += "<a onclick=\"setValue(this)\">" + j + "</a>";
            }
            out += "</div>\n</div></td>\n";
        }
        return out + "</tr>";
    }
    
    private String[] getCriteriaTypes(ResultSetMetaData metaData) throws SQLException, ClassNotFoundException {
        String[] types = new String[metaData.getColumnCount() - 1];
        String columnName;
        for (int i = 2; i <= metaData.getColumnCount(); i++) {
            columnName = metaData.getColumnName(i);
            types[i - 2] = columnName.substring(columnName.lastIndexOf('_') + 1);
        }
        return types;
    }
    
    private HashMap<String, String[]> getTypes() throws SQLException, ClassNotFoundException {
        HashMap<String, String[]> out = new HashMap();
        String[] currentType;
        // get all types
        PreparedStatement st = DatabaseConnection.init().prepareStatement(
        "select * from types");
        ResultSet result = st.executeQuery();
        while (result.next()) {
            // fill array with all options
            currentType = new String[MAX_TYPES];
            for (int i = 1; 
                 i <= MAX_TYPES
                 && (currentType[i-1] = result.getString("" + i)) != null; 
                 i++) {}
            // put in hashmap
            out.put(result.getString("name"), currentType);
        }
        return out;
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
