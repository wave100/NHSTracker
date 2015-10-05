package me.rishshadra.nhstracker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rish
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.rishshadra.nhstracker.consts.Consts;

/**
 *
 * @author Rish
 */
public class Reader {

    private Connection connect;
    private Database db;

    public Reader() {

        db = new Database();

        //System.out.println("Init Reader");
        try {
            //System.out.println("Getting Conn");
            connect = db.getConnection();
            //System.out.println("Gotten Conn");
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Reader: Reconnecting.");
            reconnect();
        }
    }

    public void addActivity(int i, float h, String d, String on, String oe, boolean a, boolean g) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO hrdb VALUES(?,?,?,?,?,?,?,0);")) {
            ps.setInt(1, i);
            ps.setString(2, d);
            ps.setFloat(3, h);
            ps.setString(4, on);
            ps.setString(5, oe);
            ps.setBoolean(6, a);
            ps.setBoolean(7, g); //getStudentActivities must be run again to update the activity list.
            ps.executeUpdate();
        }
    }

    public void addActivity(Activity a) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO hrdb VALUES(?,?,?,?,?,?,?,0);")) {
            ps.setInt(1, a.getStudentID());
            ps.setString(2, a.getProjdesc());
            ps.setFloat(3, a.getHours());
            ps.setString(4, a.getObsname());
            ps.setString(5, a.getObsemail());
            ps.setBoolean(6, a.isApproved());
            ps.setBoolean(7, a.isGroupproj()); //getStudentActivities must be run again to update the activity list.
            ps.executeUpdate();
        }
    }

    public void addGroupParticipant(String fname, String lname) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO groupdb VALUES(?,?,?);")) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            ps.executeUpdate();
        }
    }

    public void addStudent(String n, int g, String e, int iy) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO studb VALUES(0,?,?,?,?,?);")) {
            ps.setString(1, n);
            ps.setInt(2, g);
            ps.setInt(3, generatePIN());
            ps.setString(4, e);
            ps.setInt(5, iy);

            ps.executeUpdate();
        }
    }

    public void addStudent(Student s) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO studb VALUES(0,?,?,?,?,?);")) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getGradYear());
            ps.setInt(3, generatePIN());
            ps.setString(4, s.getEmail());
            ps.setInt(5, s.getInductionYear());

            ps.executeUpdate();
        }
    }

    public void close() throws SQLException {
        connect.close();
    }

    //TODO: ADD REMOVAL METHODS FOR STUDENTS AND ACTIVITIES.
    public void endOfYearMaintenance() {
        // Back up information, write to CSV or similar and generate HTML summary page with a method that is to be written. Then truncate and restore students who will be returning next year to studb. Hrdb stays empty.
    }

    public int generatePIN() {
        Random r = new Random();
        return r.nextInt(9000) + 1000;
    }

    public Activity getActivityByID(int id) throws SQLException {
        ResultSet rs;
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM hrdb WHERE ActivityID=?;")) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (getResultSetLength(rs) != 1) {
                System.out.println("Multiple activities found with identical ID: " + id);
            }
            rs.next();
            return new Activity(rs.getInt("StudentID"), rs.getFloat("Hours"), rs.getString("ProjDesc"), rs.getString("ObsName"), rs.getString("ObsEmail"), rs.getBoolean("approved"), rs.getBoolean("groupproj"), rs.getInt("ActivityID"));

        }
    }

    public int getResultSetLength(ResultSet rs) throws SQLException {
        rs.last();
        int length = rs.getRow();
        rs.beforeFirst(); //So getStudentActivity will work. DO NOT CLOSE THE RESULTSET HERE! THINGS WILL BREAK HORRENDOUSLY. YOU HAVE BEEN FOREWARNED.
        return length;
    }

    public ArrayList<Activity> getStudentActivities(int id) throws SQLException {
        //System.out.println("Reader: Getting Student Activities.");
        ResultSet rs;
        ArrayList<Activity> activities;
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM hrdb WHERE StudentID=?;")) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            activities = new ArrayList<>();
            while (rs.next()) {
                activities.add(new Activity(rs.getInt("StudentID"), rs.getInt("Hours"), rs.getString("ProjDesc"), rs.getString("ObsName"), rs.getString("ObsEmail"), rs.getBoolean("Approved"), rs.getBoolean("GroupProj"), rs.getInt("ActivityID")));
            }
        }
        rs.close();
        //System.out.println("Reader: Student Activities Returned");
        return activities;
    }

    public Student getStudentByID(int id) throws SQLException {
        ResultSet rs;
        Student s;
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM studb WHERE StudentID = " + id + ";")) {
            rs = ps.executeQuery();
            if (getResultSetLength(rs) == 0) {
                System.out.println("No student found with ID " + id);
                s = new Student(true, "No student found with ID " + id);
            } else if (getResultSetLength(rs) == 1) {
                rs.next();
                s = new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getInt("GraduationYear"), rs.getInt("PIN"), rs.getString("Email"), rs.getInt("InductionYear"));
            } else {
                System.out.println("Multiple matches found with ID " + id);
                s = new Student(true, "Multiple matches found with ID " + id);
            }
        }
        rs.close();
        return s;
    }

    public ArrayList<Student> getStudentsByInductionYear(int year) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement ps = connect.prepareStatement("SELECT StudentID FROM studb WHERE InductionYear = ?;")) {
            ps.setInt(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                students.add(getStudentByID(rs.getInt("StudentID")));
            }
        }
        rs.close();
        return students;
    }
    
    public ArrayList<Student> getStudentsByName(String name) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM studb WHERE Name LIKE ?;")) {
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getInt("GraduationYear"), rs.getInt("PIN"), rs.getString("Email"), rs.getInt("InductionYear")));
            }
        }
        rs.close();
        return students;
    }

    public void groupParticipantSignOut(String fname, String lname) throws SQLException {
        ResultSet rs;
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM groupdb WHERE LastName=?")) {
            ps.setString(1, lname);
            rs = ps.executeQuery();
            if (getResultSetLength(rs) == 1) {
                addActivity(((Student) getStudentsByName(lname).get(0)).getID(), (new java.util.Date().getTime() - rs.getDate("StartTime").getTime()) / 3600000, "GROUP PROJECT", Consts.GROUP_SUPERVISOR_NAME, Consts.GROUP_SUPERVISOR_EMAIL, false, true);
                //System.out.println(getStudentsByName(lname).size());
            } else {
                System.out.println("Name collision detected while signing out group project participant: " + getResultSetLength(rs) + " matches for last name " + lname);
            }
        }
        rs.close();
    }

    public void removeStudent(int id) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("DELETE FROM studb WHERE StudentID=?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        try (PreparedStatement ps = connect.prepareStatement("DELETE FROM hrdb WHERE StudentID=?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void removeStudent(Student s) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("DELETE FROM studb WHERE StudentID=?;")) {
            ps.setInt(1, s.getID());
            ps.executeUpdate();
        }

        try (PreparedStatement ps = connect.prepareStatement("DELETE FROM hrdb WHERE StudentID=?;")) {
            ps.setInt(1, s.getID());
            ps.executeUpdate();
        }
    }

    public void testConnection() throws SQLException {
        connect.prepareStatement("show tables;").execute();
        while (connect.isClosed()) {
            reconnect();
            connect.prepareStatement("show tables;").execute();
        }
    }

    public void toggleApproval(int id) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("UPDATE hrdb SET approved = !approved WHERE activityID=?;")) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public void updateActivity(Activity a) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("UPDATE hrdb SET ProjDesc=?, Hours=?, ObsName=?, ObsEmail=?, Approved=?, GroupProj=? WHERE ActivityID=?;")) {
            ps.setString(1, a.getProjdesc());
            ps.setFloat(2, a.getHours());
            ps.setString(3, a.getObsname());
            ps.setString(4, a.getObsemail());
            ps.setBoolean(5, a.isApproved());
            ps.setBoolean(6, a.isGroupproj()); //getStudentActivities must be run again to update the activity list.
            ps.setInt(7, a.getActivityID());
            ps.executeUpdate();
        }
    }

    public void updateStudent(Student s) throws SQLException {
        try (PreparedStatement ps = connect.prepareStatement("UPDATE studb SET Name=?, GraduationYear=?, PIN=?, Email=? WHERE StudentID=?;")) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getGradYear());
            ps.setInt(3, s.getPIN());
            ps.setString(4, s.getEmail());
            ps.setInt(5, s.getID());
            ps.executeUpdate();
        }
    }

    private void reconnect() {
        try {
            connect = db.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            //    reconnect();
        }
    }

}

//TODO:
//  Email addresses in the student DB
//
//IDEAS:
//  Leaderboard based on volunteer hours?
//
