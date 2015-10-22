/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.gmailer;

import java.io.IOException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import me.rishshadra.nhstracker.Student;
import me.rishshadra.nhstracker.consts.Consts;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class Mailer {

    private final String message;
    private final String subject;
    private String preparedMessage;
    private final GmailInterface mailer = new GmailInterface();
    private final ArrayList<Student> studentList;

    public Mailer(String m, String s, ArrayList<Student> sl) {
        message = m;
        subject = s;
        studentList = sl;
    }

    public void prepareMessage(Student s, String link) {
        preparedMessage = message;
        String fullName = s.getName();
        String pin = String.valueOf(s.getPIN());

        if (preparedMessage.contains("$NAME") || preparedMessage.contains("$FULL_NAME") || preparedMessage.contains("$PIN") || preparedMessage.contains("$LINK")) {
            preparedMessage = preparedMessage.replace("$NAME", fullName.substring(0, fullName.indexOf(" ")));
            preparedMessage = preparedMessage.replace("$FULL_NAME", fullName);
            preparedMessage = preparedMessage.replace("$PIN", pin);
            preparedMessage = preparedMessage.replace("$LINK", link);
        } else {
            System.out.println("Malformed Input: " + preparedMessage.contains("$NAME") + preparedMessage.contains("$FULL_NAME") + preparedMessage.contains("$PIN") + preparedMessage.contains("$LINK"));
        }
        me.rishshadra.nhstracker.logging.Logger.logText("Prepared message for " + s.getName());
    }

    public String getPreparedMessage() {
        return preparedMessage;
    }

    public void sendMessages() throws MessagingException, IOException {
        for (Student s : studentList) {
            prepareMessage(s, Consts.SITE_URL);
            me.rishshadra.nhstracker.logging.Logger.logText("Sent message to " + s.getName() + " (" + s.getEmail() + ")");
            mailer.sendMessage(s.getEmail(), subject, preparedMessage);
        }
    }

}
