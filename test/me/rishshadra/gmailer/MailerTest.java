/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.gmailer;

import me.rishshadra.nhstracker.gmailer.Mailer;
import me.rishshadra.nhstracker.Student;
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
public class MailerTest {
    
    public MailerTest() {
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
     * Test of prepareMessage method, of class GroupMailer.
     */
    @Test
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        Student s = null;
        String link = "";
        Mailer instance = null;
        instance.prepareMessage(s, link);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreparedMessage method, of class GroupMailer.
     */
    @Test
    public void testGetPreparedMessage() {
        System.out.println("getPreparedMessage");
        Mailer instance = null;
        String expResult = "";
        String result = instance.getPreparedMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendPreparedMessage method, of class GroupMailer.
     */
    @Test
    public void testSendPreparedMessage() throws Exception {
        System.out.println("sendPreparedMessage");
        Mailer instance = null;
        instance.sendMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
