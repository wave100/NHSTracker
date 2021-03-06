/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.matchers;

import me.rishshadra.nhstracker.models.Student;
import me.rishshadra.nhstracker.sql.Reader;
import java.sql.SQLException;

/**
 *
 * @author Rish
 */
public class StudentMatcher {

    private static Reader r;
    @Deprecated
    private static int matchByFirstName(String fname) throws SQLException {
        if (r.getStudentsByName(fname).size() == 1) {
            return ((Student) r.getStudentsByName(fname).get(0)).getID();
        } else {
            return -1;
        }
    }
    @Deprecated
    private static int matchByLastName(String lname) throws SQLException {
        if (r.getStudentsByName(lname).size() == 1) {
            return ((Student) r.getStudentsByName(lname).get(0)).getID();
        } else {
            return -1;
        }
    }
    @Deprecated
    private static int matchByFullName(String fullname) throws SQLException {
        if (r.getStudentsByName(fullname).size() == 1) {
            return (r.getStudentsByName(fullname).get(0)).getID();
        } else {
            return -1;
        }
    }

    private static int match(String name) throws SQLException {
        if (r.getStudentsByName(name).size() == 1) {
            return (r.getStudentsByName(name).get(0)).getID();
        } else {
            return -1;
        }
    }
    
    private static String getLastName(String s) {
        if (s.contains(" ")) {
            return s.substring(s.indexOf(" ") + 1, s.length());
        } else {
            return s;
        }
    }

    private static String getFirstName(String s) {
        if (s.contains(" ")) {
            return s.substring(0, s.indexOf(" "));
        } else {
            return s;
        }
    }

    public String truncateMiddleName(String fullname) {
        if (fullname.indexOf(" ") != fullname.lastIndexOf(" ")) {
            fullname = fullname.substring(0, fullname.indexOf(" ")) + fullname.substring(fullname.lastIndexOf(" "), fullname.length());
        }

        return fullname;

    }

    public static int attemptMatch(String fullname) throws SQLException {
        r = new Reader();

        int ret = -1;

        fullname = fullname.trim();
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
