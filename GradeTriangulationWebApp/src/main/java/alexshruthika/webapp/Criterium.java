/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author alexp
 * @param <T>
 */
public class Criterium<T> {
    private ArrayList<T> options;
    
    private String name;
    private T value;
    
    public Criterium(String name) {
        this.name = name;
    }
    
    public Criterium(String name, T value) {
        this(name);
        this.value = value;
    }
    
    public Criterium(String name, T[] options) {
       this(name);
       this.options = new ArrayList(Arrays.asList(options));
    }
    
    public Criterium (String name, T[] options, T value) {
        this(name, options);
        setValue(value);
    }
    
    public final void addOption(T option) {
        options.add(option);
    }
    
    public void removeOption(T option) {
        options.remove(option);
    }
    
    public final void setValue(T value) {
        this.value = value;
        if (!options.contains(value)) {
            addOption(value);
        }
    }
    
    public static ArrayList<Criterium> generate(ResultSet result) {
        return null;
    }
}
