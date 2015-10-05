/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.utils;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NoInitialContextException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.rishshadra.nhstracker.Database;

/**
 *
 * @author rshad
 */
public class DatabaseStatus extends HttpServlet {

    Database db;
    
    /**
     * Initializes the servlet.
     */
    @Override
    public void init() {
        db = new Database();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Displays status of the program's database connection.";
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Database Status</title>");
//            out.println("</head>");
//            out.println("<body>");
//
//            Connection c = db.getConnection();
//
//            try {
//                out.println("Closed: " + c.isClosed());
//                out.println("ReadOnly: " + c.isReadOnly());
//                out.println("Valid: " + c.isValid(100));
//                out.println("Object: " + c);
//            } catch (CommunicationsException e) {
//                out.println("bork.");
//            }
//
//            if (c.isClosed()) {
//                out.println("booork.");
//                c.close();
//                c = db.getConnection();
//                out.println("Closed: " + c.isClosed());
//                out.println("ReadOnly: " + c.isReadOnly());
//                out.println("Valid: " + c.isValid(100));
//                out.println("Object: " + c);
//            }
//            c.close();
//            out.println("</body>");
//            out.println("</html>");
        }
    }
}
