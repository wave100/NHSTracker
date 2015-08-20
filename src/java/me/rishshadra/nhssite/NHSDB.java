package me.rishshadra.nhssite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rish
 */
public class NHSDB {

    private static Reader reader;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            reader = new Reader();
        
        /*try {
            new IDMatcher().matchIDs();
            } catch (SQLException ex) {
            Logger.getLogger(NHSDB.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        
    }

    private static void testPinGen() {
        System.out.println(reader.generatePIN());
    }

    private static void addNamesToDB(int count) {
        String[] firstnames = {"John", "Jane", "Jack", "Mike", "Frank", "Mark", "Eric"};
        String[] lastnames = {"Doe", "Johnson", "Peter", "Alexander", "Robertson"};
        int[] gradyears = {2015, 2016};
        ArrayList<String> fullnames = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            fullnames.add(firstnames[r.nextInt(firstnames.length)] + " " + lastnames[r.nextInt(lastnames.length)]);
            System.out.println(fullnames.get(fullnames.size() - 1));
            try {
                reader.addStudent(fullnames.get(fullnames.size() - 1), gradyears[r.nextInt(2)]);
            } catch (SQLException ex) {
                Logger.getLogger(NHSDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void randomizeStudentPINs() throws SQLException {
        ArrayList<Student> students = reader.getStudentsByName("");
        for (Student s : students) {
            s.generateNewPIN();
            s.update();
        }
    }

}
