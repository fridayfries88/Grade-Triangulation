/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alexp
 */
public class Class_ {
    private String courseCode;
    private int id;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    
    public Class_(ResultSet result) {
        try {
            courseCode = result.getString("courseCode");
            id = result.getInt("id");
            
            // retrieve students from database
            students = new ArrayList();
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select * from students where classId=?");
            ResultSet studentResult = st.executeQuery();
            while (studentResult.next()) {
                students.add(new Student(studentResult));
            }
            
            // retrieve assignments from database
            assignments = new ArrayList();
            st = DatabaseConnection.init().prepareStatement(
            "select * from students where classId=?");
            ResultSet assignmentResult = st.executeQuery();
            while (assignmentResult.next()) {
                students.add(new Student(studentResult));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }
    
    public HashMap<Integer, Criterium[]> getStudentMarks(int student) {
        HashMap<Integer, Criterium[]> out = new HashMap();
        for (Assignment i : assignments) {
            out.put(i.getId(), i.getStudentCriteria(student));
        }
        return out;
    }
    
    public HashMap<Integer, Criterium[]> getAssignmentMarks(int assignment) {
        return assignments.get(assignment).getAllCriteria();
    }
}
