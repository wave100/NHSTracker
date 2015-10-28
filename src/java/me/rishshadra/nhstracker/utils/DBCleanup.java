/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.rishshadra.nhstracker.sql.Reader;
import me.rishshadra.nhstracker.models.Student;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class DBCleanup {

    private static final int YEAR_TO_REMOVE = 2015;

    public static void main(String[] args) {
        System.out.println("Starting DB Clean job...");

        Reader r = new Reader();
        ArrayList<Student> students = new ArrayList<>();
        try {
            students = r.getStudentsByName("");
        } catch (SQLException ex) {
            Logger.getLogger(DBCleanup.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Student s : students) {
            if (s.getGradYear() == YEAR_TO_REMOVE) {
                try {
                    System.out.println("Removed student: " + s.getName());
                    r.removeStudent(s);
                } catch (SQLException ex) {
                    Logger.getLogger(DBCleanup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println("Cleanup complete!");
    }
}
