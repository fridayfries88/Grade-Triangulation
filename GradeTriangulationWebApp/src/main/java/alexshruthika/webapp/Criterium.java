/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alexp
 * @param <T>
 */
public class Criterium {
    private ArrayList<String> options;
    
    private String name;
    private String value;
    
    public Criterium(String name) {
        this.name = name;
    }
    
    public Criterium(String name, String value) {
        this(name);
        this.value = value;
    }
    
    public Criterium(String name, ArrayList<String> options) {
       this(name);
       this.options = options;
    }
    
    public Criterium (String name, ArrayList<String> options, String value) {
        this(name, options);
        setValue(value);
    }
    
    public final void addOption(String option) {
        options.add(option);
    }
    
    public void removeOption(String option) {
        options.remove(option);
    }
    
    public final void setValue(String value) {
        this.value = value;
        if (!options.contains(value)) {
            addOption(value);
        }
    }
    
    public static Criterium[] generate(ResultSet result) {
        try {
            Criterium[] out = new Criterium[result.getMetaData().getColumnCount()];
            String colName;
            int typeIndex, j;
            for (int i = 3; i <= out.length; i++) {
                colName = result.getMetaData().getColumnClassName(i);
                // go through name of column until not number
                for (j = 0; "0123456789".indexOf(colName.charAt(j)) != -1; j++);
                typeIndex = Integer.parseInt(colName.substring(0, j));
                colName = colName.substring(j);
                out[i - 3] = new Criterium(
                                colName
                              , getOptions(typeIndex)
                              , result.getString(i)
                            );
            }
            return out;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
            return null;
        }
    }
    
    private static ArrayList getOptions(int typeIndex) throws SQLException, ClassNotFoundException {
        ArrayList<String> out = new ArrayList();
        PreparedStatement st = DatabaseConnection.init().prepareStatement(
        "select * from types where id=?");
        st.setInt(1, typeIndex);
        ResultSet result = st.executeQuery();
        result.next();
        for (int i = 0; result.getString(i) != null; i++) {
            out.add(result.getString(i));
        }
        return out;
    }
}
