/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import me.rishshadra.nhstracker.handlers.RequestHandler;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class RequestHandlerTest {
    
    public RequestHandlerTest() {
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
     * Test of addActivity method, of class RequestHandler.
     */
    @Test
    public void testAddActivity() {
        System.out.println("addActivity");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.addActivity(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStudent method, of class RequestHandler.
     */
    @Test
    public void testAddStudent() {
        System.out.println("addStudent");
        PrintWriter out = null;
        HttpServletRequest request = null;
        RequestHandler instance = new RequestHandler();
        instance.addStudent(out, request);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHours method, of class RequestHandler.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.getHours(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHoursAdmin method, of class RequestHandler.
     */
    @Test
    public void testGetHoursAdmin() {
        System.out.println("getHoursAdmin");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.getHoursAdmin(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServletInfo method, of class RequestHandler.
     */
    @Test
    public void testGetServletInfo() {
        System.out.println("getServletInfo");
        RequestHandler instance = new RequestHandler();
        String expResult = "";
        String result = instance.getServletInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class RequestHandler.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        RequestHandler instance = new RequestHandler();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeActivity method, of class RequestHandler.
     */
    @Test
    public void testRemoveActivity() {
        System.out.println("removeActivity");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.removeActivity(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStudent method, of class RequestHandler.
     */
    @Test
    public void testRemoveStudent() {
        System.out.println("removeStudent");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.removeStudent(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchStudents method, of class RequestHandler.
     */
    @Test
    public void testSearchStudents() {
        System.out.println("searchStudents");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.searchStudents(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toggleApproval method, of class RequestHandler.
     */
    @Test
    public void testToggleApproval() {
        System.out.println("toggleApproval");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.toggleApproval(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateActivity method, of class RequestHandler.
     */
    @Test
    public void testUpdateActivity() {
        System.out.println("updateActivity");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.updateActivity(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStudent method, of class RequestHandler.
     */
    @Test
    public void testUpdateStudent() {
        System.out.println("updateStudent");
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        RequestHandler instance = new RequestHandler();
        instance.updateStudent(out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of doGet method, of class RequestHandler.
//     */
//    @Test
//    public void testDoGet() throws Exception {
//        System.out.println("doGet");
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        RequestHandler instance = new RequestHandler();
//        instance.doGet(request, response);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of doPost method, of class RequestHandler.
//     */
//    @Test
//    public void testDoPost() throws Exception {
//        System.out.println("doPost");
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        RequestHandler instance = new RequestHandler();
//        instance.doPost(request, response);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of processRequest method, of class RequestHandler.
//     */
//    @Test
//    public void testProcessRequest() throws Exception {
//        System.out.println("processRequest");
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        RequestHandler instance = new RequestHandler();
//        instance.processRequest(request, response);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
