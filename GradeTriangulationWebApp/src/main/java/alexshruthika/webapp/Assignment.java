/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author alexp
 */
public class Assignment {
    private String type, name;
    private int id, classId, userId;
    private HashMap<Integer, MarkSet> markSets;
    
    public Assignment(ResultSet result) {
        try {
            type = result.getString("type");
            name = result.getString("name");
            id = result.getInt("id");
            classId = result.getInt("classId");
            userId = result.getInt("userId");
            
            markSets = new HashMap();
            PreparedStatement st = DatabaseConnection.init().prepareStatement(
            "select * from ?");
            st.setString(1, "assignment" + id);
            ResultSet markResult = st.executeQuery();
            while (markResult.next()) {
                markSets.put((Integer)result.getInt("student_id"), new MarkSet(result));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
    }
    
    public HashMap<Integer, MarkSet> getMarkSets() {
        return markSets;
    }
    
    public MarkSet getMarkSet(int student_id) {
        return markSets.get(student_id);
    }
    
    public int getId() {
        return id;
    }
}
