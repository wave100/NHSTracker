/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker;

import me.rishshadra.nhstracker.sql.C3P0Database;
import java.sql.Connection;
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
public class C3P0DatabaseTest {
    
    public C3P0DatabaseTest() {
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
     * Test of getConnection method, of class C3P0Database.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection expResult = null;
        Connection result = C3P0Database.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
