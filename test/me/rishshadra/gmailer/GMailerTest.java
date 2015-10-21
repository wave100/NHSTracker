/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.gmailer;

import com.google.api.services.gmail.model.Message;
import java.util.List;
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
public class GMailerTest {
    
    public GMailerTest() {
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
     * Test of checkAuth method, of class GMailer.
     */
    @Test
    public void testCheckAuth() {
        System.out.println("checkAuth");
        GMailer instance = new GMailer();
        instance.checkAuth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class GMailer.
     */
    @Test
    public void testSendMessage() throws Exception {
        System.out.println("sendMessage");
        String to = "";
        String subject = "";
        String content = "";
        GMailer instance = new GMailer();
        instance.sendMessage(to, subject, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessages method, of class GMailer.
     */
    @Test
    public void testGetMessages() throws Exception {
        System.out.println("getMessages");
        GMailer instance = new GMailer();
        List<Message> expResult = null;
        List<Message> result = instance.getMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class GMailer.
     */
    @Test
    public void testGetMessage() throws Exception {
        System.out.println("getMessage");
        String id = "";
        GMailer instance = new GMailer();
        Message expResult = null;
        Message result = instance.getMessage(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markMessageRead method, of class GMailer.
     */
    @Test
    public void testMarkMessageRead() throws Exception {
        System.out.println("markMessageRead");
        String id = "";
        GMailer instance = new GMailer();
        instance.markMessageRead(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of archiveMessage method, of class GMailer.
     */
    @Test
    public void testArchiveMessage() throws Exception {
        System.out.println("archiveMessage");
        String id = "";
        GMailer instance = new GMailer();
        instance.archiveMessage(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
