package alexshruthika.webapp.servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import java.util.Arrays;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.PrivateServlet;

/**
 *
 * @author alexp
 */
public class NewClass extends PrivateServlet {

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
        String message = "";
        String focus = "courseCode";
        checks: {
            String courseCode;
            int year, semester, period, classID;
            String[] studentNames;
            
            courseCode = request.getParameter("courseCode");
            if (courseCode == null || courseCode.isEmpty()) {
                break checks;
            }
            
            year = getYear(request.getParameter("year"));
            if (year == -1) {
                focus = "year";
                break checks;
            }
            
            if (request.getParameter("semester") == null) {
                message = "Choose semester.";
                break checks;
            }
            semester = Integer.parseInt(request.getParameter("semester"));
            
            if (request.getParameter("period") == null) {
                message = "Choose period.";
                break checks;
            }
            period = Integer.parseInt(request.getParameter("period"));
            
            studentNames = checkStudents(request.getParameter("students"));
            if (studentNames[0].equals("////Error////")) {
                focus = "students";
                message = studentNames[1];
                break checks;
            }
            System.err.println(Arrays.toString(studentNames));
            
            classID = createClass(courseCode, year, semester, period, studentNames, request.getSession());
            if (classID == -1)
                response.sendRedirect("/classes");
            else
                response.sendRedirect("/assignments?classID=" + classID);
            return;
        }
        request.setAttribute("message", message);
        request.setAttribute("focused", focus);
        request.getRequestDispatcher("/WEB-INF/new-class.jsp").include(request, response);
    }
    
    private int getYear(String yearString) {
        if (yearString == null || yearString.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // checks if students parameter is valid
    // fills string array with names of students
    // returns error message or null if no error
    private String[] checkStudents(String students) {
        boolean isEmpty = true;
        String[] studentNames = students.split("\n");
        for (int i  = 0; i < studentNames.length; i++) {
            studentNames[i] = studentNames[i].strip();
            if (studentNames[i].isEmpty())
                continue;
            if (!studentNames[i].contains(" "))
                return new String[]{"////Error////", "All Students must have first and last name."};
            isEmpty = false;
            
        }
        if (isEmpty)
            return new String[]{"////Error////", "No students found."};
        return studentNames;
    }
    
    private int createClass(String courseCode, int year, int semester, int period, String[] studentNames, HttpSession session) {
        int id = -1;
        String[] splitName;
        
        try (Connection con = DatabaseConnection.init()) {
            // add class to table
            PreparedStatement st = con.prepareStatement(
                    "insert into classes (user_id, course_code, year, semester, period) values (?, ?, ?, ?, ?)");
            st.setInt(1, (Integer)session.getAttribute("userID"));
            st.setString(2, courseCode);
            st.setInt(3, year);
            st.setInt(4, semester);
            st.setInt(5, period);
            st.executeUpdate();

            // get id of created class by getting highest id since auto_increment
            st = con.prepareStatement("select max(id) from classes");
            ResultSet result = st.executeQuery();
            result.next();
            id = result.getInt(1);

            // add all students to table
            st = con.prepareStatement(
                    "insert into students (student_class_id, first_name, last_name) values (?, ?, ?)");
            for (String i : studentNames) {
                if (i.isEmpty())
                    continue;
                splitName = i.split(" ");
                st.setInt(1, id);
                st.setString(2, splitName[0]);
                st.setString(3, splitName[1]);
                st.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) { 
            System.err.println("Error: " + e);
        }
        return id;
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
