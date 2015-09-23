/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.gmailer;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class GroupMailer {
    public GroupMailer(String s) {
        if (s.contains("$NAME") && s.contains("$FULL_NAME") && s.contains("$PIN") && s.contains("$LINK")) {
            s.replaceAll("$NAME", "nametest");
            s.replaceAll("$FULL_NAME", "fullnametest");
            s.replaceAll("$PIN", "pintest");
            s.replaceAll("$LINK", "linktest");
        }
        
        System.out.println(s);
        
    }
}
