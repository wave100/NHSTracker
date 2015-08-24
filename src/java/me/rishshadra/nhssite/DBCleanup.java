/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rshad
 */
public class DBCleanup extends HttpServlet {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Will be deprecated and consolidated into an admin panel servlet in the future.";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("TEST");
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            out.println("Starting DB Clean job...");
//        }
//        Reader r = new Reader();
//        ArrayList<Student> students = new ArrayList<>();
//        ArrayList<Integer> todelete = new ArrayList<>();
//        try {
//            students = r.getStudentsByName("");
//        } catch (SQLException ex) {
//            Logger.getLogger(DBCleanup.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        for (Student s : students) {
//            if (s.getGradYear() == 2015) {
//                try {
//                    r.removeStudent(s);
//                } catch (SQLException ex) {
//                    Logger.getLogger(DBCleanup.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//
//        System.out.println("Cleanup complete!");

    }
}
