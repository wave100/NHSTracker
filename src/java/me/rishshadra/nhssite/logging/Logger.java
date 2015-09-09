/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite.logging;

import java.util.Date;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class Logger {
    public static void logText(String t) {
        Date d = new Date();
        System.out.println(d.toString() + ": " + t);
    }
}
