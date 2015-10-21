/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import me.rishshadra.gmailer.GMailer;
import me.rishshadra.nhstracker.sql.Reader;
import me.rishshadra.nhstracker.Student;
import me.rishshadra.nhstracker.consts.Consts;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class LegacyGroupMailer {

    public static void main(String[] args) throws SQLException, MessagingException, IOException {
        GMailer mail = new GMailer();
        Reader r = new Reader();

        ArrayList<Student> students = r.getStudentsByName("");

        //for (Student s : students) {
            //mail.sendMessage(s.getEmail(), "Your NHSTracker PIN", "Dear " + s.getName().substring(0, s.getName().indexOf(" ")) + ", <br /> <br /> Your PIN for the <a href=\"http://nhs.icarusnet.me\">NHS hour tracking system</a> is " + s.getPIN() + ". In the coming days, the current searchable list of students will be replaced with a login system. As soon as the update rolls out, you will need this PIN to access your hours. If you have any questions, please send me an email at " + Consts.SUPPORT_EMAIL + ". <br /> <br /> Thanks! <br /> <br /> Rish Shadra <br /> Technology Officer <br /> <br /> <h6> This email was automatically generated. Responses to this email will be monitored by NHS officers.</h6> <br /> --");
        //

        //Student s = r.getStudentsByName("Shadra").get(0);
        //mail.sendMessage(s.getEmail(), "Your NHSTracker PIN", "Dear " + s.getName().substring(0, s.getName().indexOf(" ")) + ", <br /> <br /> Your PIN for the <a href=\"http://nhs.icarusnet.me\">NHS hour tracking system</a> is " + s.getPIN() + ". In the coming days, the current searchable list of students will be replaced with a login system. As soon as the update rolls out, you will need this PIN to access your hours. If you have any questions, please send me an email at " + Consts.SUPPORT_EMAIL + ". <br /> <br /> Thanks! <br /> <br /> Rish Shadra <br /> Technology Officer <br /> <br /> <h6> This email was automatically generated. Responses to this email will be monitored by NHS officers.</h6> <br /> --");
    }
}
