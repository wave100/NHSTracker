/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import me.rishshadra.nhstracker.models.Student;
import me.rishshadra.nhstracker.models.Activity;
import me.rishshadra.nhstracker.sql.Reader;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rish
 */
public class ReaderTest {
    
    public ReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addActivity method, of class Reader.
     */
    @Test
    public void testAddActivity_7args() throws Exception {
        System.out.println("addActivity");
        int i = 0;
        float h = 0.0F;
        String d = "";
        String on = "";
        String oe = "";
        boolean a = false;
        boolean g = false;
        Reader instance = new Reader();
        instance.addActivity(i, h, d, on, oe, a, g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addActivity method, of class Reader.
     */
    @Test
    public void testAddActivity_Activity() throws Exception {
        System.out.println("addActivity");
        Activity a = null;
        Reader instance = new Reader();
        instance.addActivity(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroupParticipant method, of class Reader.
     */
    @Test
    public void testAddGroupParticipant() throws Exception {
        System.out.println("addGroupParticipant");
        String fname = "";
        String lname = "";
        Reader instance = new Reader();
        instance.addGroupParticipant(fname, lname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStudent method, of class Reader.
     */
    @Test
    public void testAddStudent_4args() throws Exception {
        System.out.println("addStudent");
        String n = "";
        int g = 0;
        String e = "";
        int iy = 0;
        Reader instance = new Reader();
        instance.addStudent(n, g, e, iy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStudent method, of class Reader.
     */
    @Test
    public void testAddStudent_Student() throws Exception {
        System.out.println("addStudent");
        Student s = null;
        Reader instance = new Reader();
        instance.addStudent(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Reader.
     */
    @Test
    public void testClose() throws Exception {
        System.out.println("close");
        Reader instance = new Reader();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endOfYearMaintenance method, of class Reader.
     */
    @Test
    public void testEndOfYearMaintenance() {
        System.out.println("endOfYearMaintenance");
        Reader instance = new Reader();
        instance.endOfYearMaintenance();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generatePIN method, of class Reader.
     */
    @Test
    public void testGeneratePIN() {
        System.out.println("generatePIN");
        Reader instance = new Reader();
        int expResult = 0;
        int result = instance.generatePIN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivityByID method, of class Reader.
     */
    @Test
    public void testGetActivityByID() throws Exception {
        System.out.println("getActivityByID");
        int id = 0;
        Reader instance = new Reader();
        Activity expResult = null;
        Activity result = instance.getActivityByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultSetLength method, of class Reader.
     */
    @Test
    public void testGetResultSetLength() throws Exception {
        System.out.println("getResultSetLength");
        ResultSet rs = null;
        Reader instance = new Reader();
        int expResult = 0;
        int result = instance.getResultSetLength(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentActivities method, of class Reader.
     */
    @Test
    public void testGetStudentActivities() throws Exception {
        System.out.println("getStudentActivities");
        int id = 0;
        Reader instance = new Reader();
        ArrayList<Activity> expResult = null;
        ArrayList<Activity> result = instance.getStudentActivities(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentByID method, of class Reader.
     */
    @Test
    public void testGetStudentByID() throws Exception {
        System.out.println("getStudentByID");
        int id = 0;
        Reader instance = new Reader();
        Student expResult = null;
        Student result = instance.getStudentByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentsByInductionYear method, of class Reader.
     */
    @Test
    public void testGetStudentsByInductionYear() throws Exception {
        System.out.println("getStudentsByInductionYear");
        int year = 0;
        Reader instance = new Reader();
        ArrayList<Student> expResult = null;
        ArrayList<Student> result = instance.getStudentsByInductionYear(year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentsByName method, of class Reader.
     */
    @Test
    public void testGetStudentsByName() throws Exception {
        System.out.println("getStudentsByName");
        String name = "";
        Reader instance = new Reader();
        ArrayList<Student> expResult = null;
        ArrayList<Student> result = instance.getStudentsByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of groupParticipantSignOut method, of class Reader.
     */
    @Test
    public void testGroupParticipantSignOut() throws Exception {
        System.out.println("groupParticipantSignOut");
        String fname = "";
        String lname = "";
        Reader instance = new Reader();
        instance.groupParticipantSignOut(fname, lname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStudent method, of class Reader.
     */
    @Test
    public void testRemoveStudent_int() throws Exception {
        System.out.println("removeStudent");
        int id = 0;
        Reader instance = new Reader();
        instance.removeStudent(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStudent method, of class Reader.
     */
    @Test
    public void testRemoveStudent_Student() throws Exception {
        System.out.println("removeStudent");
        Student s = null;
        Reader instance = new Reader();
        instance.removeStudent(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testConnection method, of class Reader.
     */
//    @Test
//    public void testTestConnection() throws Exception {
//        System.out.println("testConnection");
//        Reader instance = new Reader();
//        instance.testConnection();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of toggleApproval method, of class Reader.
     */
    @Test
    public void testToggleApproval() throws Exception {
        System.out.println("toggleApproval");
        int id = 0;
        Reader instance = new Reader();
        instance.toggleApproval(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateActivity method, of class Reader.
     */
    @Test
    public void testUpdateActivity() throws Exception {
        System.out.println("updateActivity");
        Activity a = null;
        Reader instance = new Reader();
        instance.updateActivity(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStudent method, of class Reader.
     */
    @Test
    public void testUpdateStudent() throws Exception {
        System.out.println("updateStudent");
        Student s = null;
        Reader instance = new Reader();
        instance.updateStudent(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
