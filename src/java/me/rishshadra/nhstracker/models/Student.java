/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.models;

import me.rishshadra.nhstracker.sql.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish
 */
public class Student {

    private ArrayList<Activity> activities;
    private boolean empty;

    private int gradyear, inductionyear, id, pin;
    private String name, email, error;

    public Student(int i, String n, int g, int p, String e, int iy) {
        id = i;
        name = n;
        gradyear = g;
        pin = p;
        email = e;
        inductionyear = iy;
    }

    public Student(boolean b, String e) {
        empty = b;
        error = e;
    }

    public int generateNewPIN() {
        setPin(new Reader().generatePIN()); //Why is GeneratePIN() a method of reader?!?
        return pin;
    }

    public ArrayList getActivities() {
        //System.out.println("Getting Activities");
        Reader r = new Reader();
        try {
            activities = r.getStudentActivities(id);
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Activities Gotten");
        return activities;
    }
    
    public float getApprovedHours() {
        getActivities();
        
        float sum = 0;
        
        for (Activity activity : activities) {
            if (activity.isApproved()) sum += activity.getHours();
        }
        
        return sum;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getError() {
        if (empty) {
            return error;
        } else {
            return "No error.";
        }
    }

    public int getGradYear() {
        return gradyear;
    }

    public float getGroupHours() {
        getActivities();
        
        float sum = 0;
        
        for (Activity activity : activities) {
            if (activity.isGroupproj()) sum += activity.getHours();
        }
        
        return sum;
    }
    
    public float getHours() {
        //System.out.println("Getting Hours");
        getActivities();
        //System.out.println("Activities Gotten");
        float sum = 0;
        for (Activity activity : activities) {
            //System.out.println("Summing Hours");
            sum += activity.getHours();
        }
        //System.out.println("Hours Gotten");
        return sum;
    }

    public int getID() {
        return id;
    }
    
    public int getInductionYear() {
        return inductionyear;
    }

    public String getName() {
        return name;
    }

    public int getPIN() {
        return pin;
    }

    public boolean isEmpty() {
        return empty;
    }
    
    public void setEmail(String e) {
        email = e;
    }
    
    public void setGradYear(int y) {
        gradyear = y;
    }

    public void setInductionYear(int iy) {
        inductionyear = iy;
    }
    
    public boolean setPin(int i) {
        if (String.valueOf(i).length() == 4) {
            pin = i;
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        try {
            Reader r = new Reader();
            r.updateStudent(this);
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateActivities() {
        try {
            Reader r = new Reader();
            activities = r.getStudentActivities(id);
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validatePIN(int p) {
        return pin == p;
    }

}
