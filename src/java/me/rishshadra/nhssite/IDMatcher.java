/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish
 */
public class IDMatcher {

    private Reader r;

    private Connection connect = null;

    public IDMatcher() {
        r = new Reader();
        Database db = new Database();
        try {
            connect = db.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IDMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void matchIDs() throws SQLException {
        
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
            if (r.getStudentsByName(name).size() != 1) {
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
                if(r.getStudentsByName(name).size() > 1) {
                    System.out.println(name + " (Duplicated)");
                }
                
            } else {
                r.addActivity(namedstudents.get(0).getID(), tempdbrs.getFloat("hrs"), tempdbrs.getString("description"), tempdbrs.getString("supervisor"), tempdbrs.getString("contact"), false, false);
                counter++;
            }

        }
        
        NHSDB.randomizeStudentPINs();
        
        System.out.println("\n" + unmatched + " unmatched entries from " + unmatchedstudents + " students.");
        System.out.println(counter + " rows successfully migrated.");
        System.out.println(processedcounter + " entries processed.");
    }

}
