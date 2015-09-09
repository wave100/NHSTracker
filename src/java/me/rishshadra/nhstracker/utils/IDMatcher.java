/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.utils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.rishshadra.nhstracker.Database;
import me.rishshadra.nhstracker.Reader;
import me.rishshadra.nhstracker.Student;

/**
 *
 * @author Rish
 */
public class IDMatcher {

    private Connection connect = null;
    private Reader r;

    public static void main(String[] args) {
        IDMatcher idm = new IDMatcher();
        // Whatever you want to do here.
    }
    
    public IDMatcher() {
        r = new Reader();
        Database db = new Database();
        try {
            connect = db.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IDMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void matchIDs(PrintWriter out) throws SQLException {

        try (PreparedStatement clearHourDB = connect.prepareStatement("TRUNCATE hrdb;")) {
            clearHourDB.executeUpdate();
        }

        PreparedStatement tempdbps = connect.prepareStatement("SELECT * FROM tempdb;");
        ResultSet tempdbrs = tempdbps.executeQuery();
        ArrayList<String> unindexedStudents = new ArrayList<>();
        int unmatched = 0;
        int counter = 0;
        int processedcounter = 0;
        int unmatchedstudents = 0;
        System.out.println("Unmatched and Duplicated Students: \n");
        while (tempdbrs.next()) {
            processedcounter++;
            String name = tempdbrs.getString("studentname");
            ArrayList<Student> namedstudents = r.getStudentsByName(name);
            if (r.getStudentsByName(name).size() != 1 && name.contains(" ")) {
                if (r.getStudentsByName(name.substring(name.indexOf(" "), name.length())).size() != 1) {
                    unmatched++;
                    System.out.println(name + " (Unmatched)");
                    if (r.getStudentsByName(name.substring(name.indexOf(" "), name.length())).isEmpty() && !unindexedStudents.contains(name)) {
                        unmatchedstudents++;
                        System.out.println(name + " (Unmatched)"); // + " ." + name.substring(name.indexOf(" "), name.length()).trim() + " " + r.getStudentsByName(name.substring(name.indexOf(" "), name.length())).size()
                        unindexedStudents.add(name);
                    }
                } else {
                    namedstudents = r.getStudentsByName(name.substring(name.indexOf(" "), name.length()));
                    r.addActivity(namedstudents.get(0).getID(), tempdbrs.getFloat("hrs"), tempdbrs.getString("description"), tempdbrs.getString("supervisor"), tempdbrs.getString("contact"), false, false);
                    counter++;
                }
                if (r.getStudentsByName(name).size() > 1) {
                    System.out.println(name + " (Duplicated)");
                }

            } else if (r.getStudentsByName(name).size() != 1 && !name.contains(" ")) {
                unmatched++;
                System.out.println(name + " (Unmatched)");
            } else {
                r.addActivity(namedstudents.get(0).getID(), tempdbrs.getFloat("hrs"), tempdbrs.getString("description"), tempdbrs.getString("supervisor"), tempdbrs.getString("contact"), false, false);
                counter++;
            }

        }

        ArrayList<Student> students = r.getStudentsByName("");
        for (Student s : students) {
            s.generateNewPIN();
            s.update();
        }

        System.out.println("\n" + unmatched + " unmatched entries from " + unmatchedstudents + " students.");
        System.out.println(counter + " rows successfully migrated.");
        System.out.println(processedcounter + " entries processed.");
    }

}
