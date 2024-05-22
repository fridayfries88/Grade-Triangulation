/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;

/**
 * Initializes connection to database
 * Found on https://www.geeksforgeeks.org/java-servlet-and-jdbc-example-insert-data-in-mysql/
 * 
 * @author alexp
 */
public class DatabaseConnection {
    public static Connection initDatabase() throws SQLException
                                                    , ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3006/gradetriangulation"
              , "root"
              , "admin");
    }
}
