/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

import java.sql.SQLException;

/**
 *
 * @author Rish
 */
public class StudentMatcher {
    
    private static final Reader r = new Reader();

    public static int matchByFirstName(String fname) throws SQLException {
        if (r.getStudentsByName(fname).size() == 1) {
            return ((Student) r.getStudentsByName(fname).get(0)).getID();
        } else {
            return -1;
        }
    }

    public static int matchByLastName(String lname) throws SQLException {
        if (r.getStudentsByName(lname).size() == 1) {
            return ((Student) r.getStudentsByName(lname).get(0)).getID();
        } else {
            return -1;
        }
    }

    public static int matchByFullName(String fullname) throws SQLException {
        if (r.getStudentsByName(fullname).size() == 1) {
            return ((Student) r.getStudentsByName(fullname).get(0)).getID();
        } else {
            return -1;
        }
    }

    public static String getLastName(String s) {
        return s.substring(s.indexOf(" ") + 1, s.length());
    }

    public static String getFirstName(String s) {
        return s.substring(0, s.indexOf(" "));
    }

    public void truncateMiddleName(String fullname) {
        if (fullname.indexOf(" ") != fullname.lastIndexOf(" ")) {

        }
    }

    public static int attemptMatch(String fullname) throws SQLException {
        int fullmatch = matchByFullName(fullname);
        if (fullmatch > -1) {
            return fullmatch;
        } else {
            int firstmatch = matchByFirstName(getFirstName(fullname));
            if (firstmatch > -1) {
                return firstmatch;
            } else {
                int lastmatch = matchByLastName(getLastName(fullname));
                if (lastmatch > -1) {
                    return lastmatch;
                }
            }
        }
        return -1;
    }
}
