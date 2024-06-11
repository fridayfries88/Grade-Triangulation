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
            response.sendRedirect("/assignment?assignmentID=" + createAssignment(type, name, criteria, request.getSession()));
        }
        request.setAttribute("courseCode", request.getSession().getAttribute("courseCode"));
        request.setAttribute("focus", focus);
        request.setAttribute("message", message);
        request.setAttribute("types", getTypes());
        request.getRequestDispatcher("/WEB-INF/new-assignment.jsp").include(request, response);

    }
    /**
     * 
     * @param request
     * @return 2 element array: 0 = names, 1 = types
     */
    private String[][] checkCriteria(HttpServletRequest request) {
        String[][] out;
        ArrayList<String> names = new ArrayList();
        ArrayList<String> types = new ArrayList();
        int i;
        for (i = 0; request.getParameter("criterium" + i) != null
                 && !request.getParameter("criterium" + i).isEmpty()
                 && request.getParameter("type" + i) != null
                 && !request.getParameter("type" + i).isEmpty(); i++) {
            System.err.println(request.getParameter("type" + i));
            if (names.contains(request.getParameter("criterium" + i).toLowerCase())) {
                continue;
            }
            names.add(request.getParameter("criterium" + i).toLowerCase());
            types.add(request.getParameter("type" + i));
        }
        if (i == 0)
            return new String[][]{{"No Criteria. Make sure rows are filled in order."}};
        out = new String[][]{new String[names.size()], new String[names.size()]};
        out[0] = names.toArray(out[0]);
        out[1] = types.toArray(out[1]);
        return out;
    }
    
    private int createAssignment(String type, String name, String[][] criteria, HttpSession session) {
        try (Connection con = DatabaseConnection.init()) {
            int id;
            String tableStatement;
            String[] columnNames = new String[15];
            
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
                "`assignment" + id + "_student_id` int unsigned not null,\n";
            int i;
            for (i = 0; i < criteria[0].length; i++) {
                columnNames[i] = "`" + criteria[0][i] + "_" + criteria[1][i] + "`";
                tableStatement += columnNames[i] + " varchar(500),\n";
            }
            // add extra row at the end
            columnNames[i] = "`Additional notes_Text`";
            tableStatement += columnNames[i] + " varchar(500),\n";
            tableStatement+=
                "index assignment" + id + "_student_id(assignment" + id + "_student_id ASC),\n" +
                "constraint assignment" + id + "_student_id foreign key(assignment" + id + "_student_id) references students(id) on delete cascade on update cascade);";
            st = con.prepareStatement(tableStatement);
            st.executeUpdate();
            createStudents((Integer)session.getAttribute("classID"), id);
            return id;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        
        return -1;
    }
    
    private void createStudents(int classID, int assignmentID) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> studentIDs = new ArrayList();
        Connection con = DatabaseConnection.init();
        // get all students
        PreparedStatement st = con.prepareStatement(
        "select id from students where student_class_id=?");
        st.setInt(1, classID);
        ResultSet result = st.executeQuery();
        while (result.next()) {
            studentIDs.add(result.getInt(1));
        }
        
        // add each student too assignment
        for (Integer studentID : studentIDs) {
            st = con.prepareStatement("insert into assignment" + assignmentID
               + " (assignment" + assignmentID + "_student_id) values (?);");
            st.setInt(1, studentID);
            st.executeUpdate();
        }
    }
    
    private String getTypes() {
        String out = "";
        try {
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select name from types");
            ResultSet result = st.executeQuery();
            while (result.next()) {
                out += "<a onclick=\"setType(this)\">" + result.getString(1) + "</a>";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
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
        return "Page to create an assignment";
    }// </editor-fold>

}
