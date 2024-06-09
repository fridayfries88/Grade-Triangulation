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
public class Student extends PrivateServlet {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // if just getting sent here, set session data and reload to avoid id in url
        if (request.getParameter("studentID") != null) {
            request.getSession().setAttribute("studentID", Integer.valueOf(request.getParameter("studentID")));
            response.sendRedirect("/student");
            return;
        }
        // send back to assignments if no studentID
        if (request.getSession().getAttribute("studentID") == null) {
            response.sendRedirect("/students");
            return;
        }
        // get student id
        int studentID = (int)request.getSession().getAttribute("studentID");
        
        try {
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select first_name, last_name from students where id=?");
            st.setInt(1, studentID);
            ResultSet result = st.executeQuery();
            result.next();
            request.setAttribute("name", result.getString(1) + " " + result.getString(2));
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        request.setAttribute("rows", makeRows(studentID, (Integer)request.getSession().getAttribute("classID")));
        
        request.getRequestDispatcher("/WEB-INF/assignment.jsp").include(request, response);
    }
    
    private String makeRows(int studentID, int classID) {
        try (Connection con = DatabaseConnection.init()) {
            String out = "";
            HashMap<String, String[]> types = getTypes();
            
            // go through all assignments of class
            PreparedStatement st = con.prepareStatement(
            "select * from assignments where assignment_class_id=?");
            st.setInt(1, classID); // class id is wrong
            ResultSet assignments = st.executeQuery();
            ResultSet row;
            while (assignments.next()) {
                st = con.prepareStatement(
                "select * from assignment" + assignments.getInt("id")+ "where "
                + "assignment" + assignments.getInt("id") + "_student_id=?");
                st.setInt(1, studentID);
                row = st.executeQuery();
                row.next();
                out += makeRow(assignments.getString("name"), assignments.getInt("id"), row, types);
            }
            return out;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return null;
    }
    
    private String makeRow(String assignmentName, int assignmentID, ResultSet row, HashMap<String, String[]> types) throws SQLException {
        String out = "<tr><td></td>\n"; // maybe add date/type in this box
        // get criteria names and types
        String[] columnNames = new String[row.getMetaData().getColumnCount() - 1];
        String[] criteria = columnNames;
        String[] criteriaTypes = columnNames;
        int typeDivider;
        String value;
        
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = row.getMetaData().getColumnName(i+2);
            typeDivider = columnNames[i].lastIndexOf('_');
            criteria[i] = columnNames[i].substring(0, typeDivider);
            criteriaTypes[i] = columnNames[i].substring(typeDivider + 1);
            // make header row
            out += "<td style=\"max-width:100%;white-space:nowrap\">"
                + criteria[i] + "<input name=\"header" + i + "\" type=\"hidden\""
                + " value=\"" + columnNames[i] + "\"></td>";
        }
        // make value row
        out +=  "</tr>\n<tr>\n<td style=\"max-width:100%;white-space:nowrap\">"
            + assignmentName + "</td>\n";
        for (int i = 0; i < criteria.length; i++) {
            value = row.getString(i+2);
            // if no options, make text input instead of dropdown
            if (types.get(criteriaTypes[i])[0] == null) {
                out += "<td style=\"max-width:100%;white-space:nowrap\">"
                    + "<input name=\"" + assignmentID + "_" + i + "\" type=\"text\""
                    + " class=\"value\" style=\"resize:horizontal\" value=\""
                    + (value != null ? value : "") + "\"></td>\n";
                continue;
            }
            // if type is percent, make number from 0-100
            if (types.get(criteriaTypes[i])[0].equals("////percentage////")) {
                out += "<td style=\"max-width:100%;white-space:nowrap\">"
                    + "<input name=\"" + assignmentID + "_" + i + "\" type=\"number\""
                    + " type=\"range\" min=\"0\" max=\"100\" step=\"0.5\""
                    + " class=\"value\" value=\"" + (value != null ? value : "") + "\"></td>\n";
                continue;
            }
            out += "<td style=\"max-width:100%;white-space:nowrap\">"
                + "<div class=\"dropdown\">\n"
                + "<button type=\"button\" class=\"dropbtn\""
                + " style=\"resize:horizontal\">" + (value != null ? value
                : "[Dropdown]") + "</button>\n<input name=\"" + assignmentID
                + "_" + i + "\" type=\"hidden\" value=\"" + value
                + "\" class=\"value\">\n<div class=\"dropdown-content\">\n";
            for (String j : types.get(criteriaTypes[i])) {
                if (j == null) break;
                out += "<a onclick=\"setValue(this)\">" + j + "</a>";
            }
            out += "</div>\n</div></td>\n";
        }
        return out + "</tr>";
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
        return "Table of all assignments and criteria for one student";
    }// </editor-fold>

}
