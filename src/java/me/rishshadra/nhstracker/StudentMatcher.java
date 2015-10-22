/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import me.rishshadra.nhstracker.sql.Reader;
import java.sql.SQLException;

/**
 *
 * @author Rish
 */
public class StudentMatcher {

    private static Reader r;

    private static int matchByFirstName(String fname) throws SQLException {
        if (r.getStudentsByName(fname).size() == 1) {
            return ((Student) r.getStudentsByName(fname).get(0)).getID();
        } else {
            return -1;
        }
    }

    private static int matchByLastName(String lname) throws SQLException {
        if (r.getStudentsByName(lname).size() == 1) {
            return ((Student) r.getStudentsByName(lname).get(0)).getID();
        } else {
            return -1;
        }
    }

    private static int matchByFullName(String fullname) throws SQLException {
        if (r.getStudentsByName(fullname).size() == 1) {
            return ((Student) r.getStudentsByName(fullname).get(0)).getID();
        } else {
            return -1;
        }
    }

    public static String getLastName(String s) {
        if (s.contains(" ")) {
            return s.substring(s.indexOf(" ") + 1, s.length());
        } else {
            return s;
        }
    }

    public static String getFirstName(String s) {
        if (s.contains(" ")) {
            return s.substring(0, s.indexOf(" "));
        } else {
            return s;
        }
    }

    public void truncateMiddleName(String fullname) {
        if (fullname.indexOf(" ") != fullname.lastIndexOf(" ")) {
            //TO BE IMPLEMENTED
        }
    }

    public static int attemptMatch(String fullname) throws SQLException {
        r = new Reader();
        
        int ret = -1;
        
        int fullmatch = matchByFullName(fullname);
        if (fullmatch > -1) {
            ret = fullmatch;
        } else {
            int firstmatch = matchByFirstName(getFirstName(fullname));
            if (firstmatch > -1) {
                ret = firstmatch;
            } else {
                int lastmatch = matchByLastName(getLastName(fullname));
                if (lastmatch > -1) {
                    ret = lastmatch;
                }
            }
        }
        
        r.close();
        
        return ret;
    }
}
