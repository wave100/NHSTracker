/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.logging;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class ErrorHandler {

    public static void processError(String destination, String level, String description) {
        Logger.logText("[" + level + "] " + description + "(occurred while trying to access " + destination + ")");
    }

    public static void processError(String destination, String level, String description, PrintWriter out) {
        Logger.logText("");
    }

    public static void processError(String destination, String level, String description, PrintWriter out, HttpServletRequest request, HttpServletResponse response) {

    }
}
