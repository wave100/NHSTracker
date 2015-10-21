/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.logging;

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
public class ErrorHandlerTest {
    
    public ErrorHandlerTest() {
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
     * Test of processError method, of class ErrorHandler.
     */
    @Test
    public void testProcessError_3args() {
        System.out.println("processError");
        String destination = "";
        String level = "";
        String description = "";
        ErrorHandler.processError(destination, level, description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processError method, of class ErrorHandler.
     */
    @Test
    public void testProcessError_4args() {
        System.out.println("processError");
        String destination = "";
        String level = "";
        String description = "";
        PrintWriter out = null;
        ErrorHandler.processError(destination, level, description, out);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processError method, of class ErrorHandler.
     */
    @Test
    public void testProcessError_6args() {
        System.out.println("processError");
        String destination = "";
        String level = "";
        String description = "";
        PrintWriter out = null;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ErrorHandler.processError(destination, level, description, out, request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
