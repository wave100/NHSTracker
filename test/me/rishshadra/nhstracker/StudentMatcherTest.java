/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

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
public class StudentMatcherTest {
    
    public StudentMatcherTest() {
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
     * Test of matchByFirstName method, of class StudentMatcher.
     */
//    @Test
//    public void testMatchByFirstName() throws Exception {
//        System.out.println("matchByFirstName");
//        String fname = "";
//        int expResult = 0;
//        int result = StudentMatcher.matchByFirstName(fname);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of matchByLastName method, of class StudentMatcher.
//     */
//    @Test
//    public void testMatchByLastName() throws Exception {
//        System.out.println("matchByLastName");
//        String lname = "";
//        int expResult = 0;
//        int result = StudentMatcher.matchByLastName(lname);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of matchByFullName method, of class StudentMatcher.
//     */
//    @Test
//    public void testMatchByFullName() throws Exception {
//        System.out.println("matchByFullName");
//        String fullname = "";
//        int expResult = 0;
//        int result = StudentMatcher.matchByFullName(fullname);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getLastName method, of class StudentMatcher.
     */
//    @Test
//    public void testGetLastName() {
//        System.out.println("getLastName");
//        String s = "";
//        String expResult = "";
//        String result = StudentMatcher.getLastName(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirstName method, of class StudentMatcher.
//     */
//    @Test
//    public void testGetFirstName() {
//        System.out.println("getFirstName");
//        String s = "";
//        String expResult = "";
//        String result = StudentMatcher.getFirstName(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of truncateMiddleName method, of class StudentMatcher.
     */
    @Test
    public void testTruncateMiddleName() {
        System.out.println("truncateMiddleName");
        String fullname = "";
        StudentMatcher instance = new StudentMatcher();
        instance.truncateMiddleName(fullname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attemptMatch method, of class StudentMatcher.
     */
    @Test
    public void testAttemptMatch() throws Exception {
        System.out.println("attemptMatch");
        String fullname = "";
        int expResult = 0;
        int result = StudentMatcher.attemptMatch(fullname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
