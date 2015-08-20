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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rish
 */
public class RequestHandler extends HttpServlet {

    Reader r;

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "General request handler for getting info out of database";
    }// </editor-fold>

    @Override
    public void init() {
        //r = new Reader();
    }

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
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String[]> map = request.getParameterMap();

        try (PrintWriter out = response.getWriter()) { //Print some HTML stuff before to clean up code below.
            r = new Reader();
            if (map.containsKey("action")) {
                if (request.getParameter("action").equalsIgnoreCase("addstudent")) {
                    if (request.getParameterMap().size() == 3) {
                        if (map.containsKey("name") && map.containsKey("graduationyear")) {
                            r.addStudent(map.get("name")[0], Integer.parseInt(map.get("graduationyear")[0]));
                            System.out.println("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
                            out.println("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
                        }
                    } else {
                        System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected."); //Add a function that returns a bool, true if lengths match false if lengths don't. If lengths don't, it does a println. Code = clean, problem = solved!
                        out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected.");
                    }
                } else if (request.getParameter("action").equalsIgnoreCase("removestudent")) {
                    //TODO: ADD REMOVE STUDENT METHOD!
                    System.out.println("Unimplemented.");
                    out.println("Unimplemented.");
                } else if (request.getParameter("action").equalsIgnoreCase("updatestudent")) {
                    if (request.getParameterMap().size() == 5) {
                        //Figure out how to handle this without sending the PIN to any clients.
                    } else {
                        System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
                        out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
                    }
                } else if (request.getParameter("action").equalsIgnoreCase("addactivity")) {
                    if (request.getParameterMap().size() == 8) {

                    } else {
                        System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (7+1) expected.");
                        out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (7+1) expected.");
                    }
                } else if (request.getParameter("action").equalsIgnoreCase("removeactivity")) {
                    //TODO: ADD REMOVE ACTIVITY METHOD!
                    System.out.println("Unimplemented.");
                    out.println("Unimplemented.");
                } else if (request.getParameter("action").equalsIgnoreCase("updateactivity")) {

                } else if (request.getParameter("action").equalsIgnoreCase("searchstudents")) {
                    ArrayList<Student> results = r.getStudentsByName(request.getParameter("studentname"));

                    for (Student s : results) {
                        out.println("<a href=\"#\" onclick='parent.viewHours(" + s.getID() + ")'>" + "ID: " + s.getID() + ", Name: " + s.getName() + "</a><br />");
                        //System.out.println("ID: " + s.getID() + ", Name: " + s.getName() + "<br />");
                    }
                } else if (request.getParameter("action").equalsIgnoreCase("gethours")) {
                    out.println(r.getStudentByID(Integer.parseInt(request.getParameter("id"))).getHours());
                } else if (request.getParameter("action").equalsIgnoreCase("blank")) {
                    out.println("");
                }
            } else {
                System.out.println("No action specified.");
                out.println("No action specified.");
            }
            r.close();
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
