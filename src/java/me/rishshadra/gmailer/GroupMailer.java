/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.gmailer;

import java.io.IOException;
import javax.mail.MessagingException;
import me.rishshadra.nhstracker.Student;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class GroupMailer {

    private final String message;
    private final String subject;
    private String preparedMessage;
    private Student student;
    private final GMailer mailer = new GMailer();

    public GroupMailer(String m, String s) {
        message = m;
        subject = s;
    }

    public void prepareMessage(Student s, String link) {
        student = s;
        preparedMessage = message;
        String fullName = s.getName();
        String pin = String.valueOf(s.getPIN());

        if (preparedMessage.contains("$NAME") && preparedMessage.contains("$FULL_NAME") && preparedMessage.contains("$PIN") && preparedMessage.contains("$LINK")) {
            preparedMessage = preparedMessage.replace("$NAME", fullName.substring(0, fullName.indexOf(" ")));
            preparedMessage = preparedMessage.replace("$FULL_NAME", fullName);
            preparedMessage = preparedMessage.replace("$PIN", pin);
            preparedMessage = preparedMessage.replace("$LINK", link);
        } else {
            System.out.println("Malformed Input: " + preparedMessage.contains("$NAME") + preparedMessage.contains("$FULL_NAME") + preparedMessage.contains("$PIN") + preparedMessage.contains("$LINK"));
        }
        me.rishshadra.nhstracker.logging.Logger.logText("Prepared message for " + s.getName());
    }
    
    public void printPreparedMessage() {
        System.out.println(preparedMessage);
    }
    
    public String getPreparedMessage() {
        return preparedMessage;
    }
    
    public void sendPreparedMessage() throws MessagingException, IOException {
        me.rishshadra.nhstracker.logging.Logger.logText("Sent message to " + student.getName() + " (" + student.getEmail() + ")");
        mailer.sendMessage(student.getEmail(), subject, preparedMessage);
    }
    
}
