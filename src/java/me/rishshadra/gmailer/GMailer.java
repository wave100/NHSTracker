/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.gmailer;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class GMailer {

    Gmail gmail;

    public GMailer() {
        try {
            gmail = TokenHolder.getGmail(); //TokenHolder is a class that authenticates with Google and returns a gmail object. It is not included in the repository because it's got my API keys in it.
        } catch (IOException ex) {
            Logger.getLogger(GMailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkAuth() {
        try {
            gmail = TokenHolder.getGmail();
        } catch (IOException ex) {
            Logger.getLogger(GMailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String to, String subject, String content) throws MessagingException, IOException {
        checkAuth();

        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));

        email.setFrom(new InternetAddress("nhsit@hillers.org"));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(content);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        email.writeTo(baos);
        Message toSend = new Message();
        toSend.setRaw(Base64.encodeBase64URLSafeString(baos.toByteArray()));

        gmail.users().messages().send("me", toSend).execute();
    }

    public List<Message> getMessages() throws IOException {
        checkAuth();

        return gmail.users().messages().list("me").execute().getMessages();
    }

    public Message getMessage(String id) throws IOException {
        checkAuth();

        for (Message m : gmail.users().messages().list("me").execute().getMessages()) {
            if (m.getId().equals(id)) {
                return m;
            }
        }

        return new Message();
    }

    public void markMessageRead(String id) throws IOException {
        checkAuth();

        ArrayList<String> al = new ArrayList<>();
        al.add("UNREAD");
        ModifyMessageRequest mmr = new ModifyMessageRequest().setRemoveLabelIds(al);
        gmail.users().messages().modify("me", id, mmr).execute();
    }

    public void archiveMessage(String id) throws IOException {
        checkAuth();

        ArrayList<String> al = new ArrayList<>();
        al.add("INBOX");
        ModifyMessageRequest mmr = new ModifyMessageRequest().setRemoveLabelIds(al);
        gmail.users().messages().modify("me", id, mmr).execute();
    }
}
