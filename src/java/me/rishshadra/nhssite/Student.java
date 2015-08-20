/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

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

    private int gradyear, id, pin;
    private String name;

    public Student(int i, String n, int g, int p) {
        id = i;
        name = n;
        gradyear = g;
        pin = p;
    }

    public Student(boolean b) {
        empty = b;
    }

    public int generateNewPIN() {
        setPin(new Reader().generatePIN());
        return pin;
    }

    public ArrayList getActivities() {
        try {
            activities = new Reader().getStudentActivities(id);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activities;
    }

    public int getGradYear() {
        return gradyear;
    }

    public int getHours() {
        getActivities();
        int sum = 0;
        for (Activity activity : activities) {
            sum += activity.getHours();
        }
        return sum;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPIN() {
        return pin;
    }

    public void setGradYear(int y) {
        gradyear = y;
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
            new Reader().updateStudent(this);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateActivities() {
        try {
            activities = new Reader().getStudentActivities(id);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validatePIN(int p) {
        return pin == p;
    }

}
