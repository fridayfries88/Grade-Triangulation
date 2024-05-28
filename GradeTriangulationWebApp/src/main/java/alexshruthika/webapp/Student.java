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
public class Student {
    private String firstName, lastName;
    private int id;
    
    public Student(ResultSet result) {
        try {
            firstName = result.getString("firstName");
            lastName = result.getString("lastName");
            id = result.getInt("id");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }
}
