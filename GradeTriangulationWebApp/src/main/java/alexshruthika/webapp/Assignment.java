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
public class Assignment {
    private String type, name;
    private int id, classId, userId;
    private HashMap<Integer, ArrayList<Criterium>> criteria;
    
    public Assignment(ResultSet result) {
        try {
            type = result.getString("type");
            name = result.getString("name");
            id = result.getInt("id");
            classId = result.getInt("classId");
            userId = result.getInt("userId");
            
            criteria = new HashMap();
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select * from ?");
            st.setString(1, "assignment" + id);
            ResultSet markResult = st.executeQuery();
            while (markResult.next()) {
                criteria.put((Integer)result.getInt("student_id"), Criterium.generate(result));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }
    
    public HashMap<Integer, ArrayList<Criterium>> getAllCriteria() {
        return criteria;
    }
    
    public ArrayList<Criterium> getStudentCriteria(int student_id) {
        return criteria.get(student_id);
    }
    
    public int getId() {
        return id;
    }
}
