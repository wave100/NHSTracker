/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhssite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

    //Reader r;
    public void addActivity(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        if (request.getParameterMap().size() == 7 || request.getParameterMap().size() == 6) {
            int id = -1;
            try {
                id = StudentMatcher.attemptMatch(request.getParameter("name"));
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean group = false;
            if (request.getParameterMap().containsKey("groupproj")) {
                group = true; //TODO: MAKE SURE THIS WORKS.
            }
            if (id > -1) {
                try {
                    System.out.println("Student matched.");
                    r.addActivity(id, Float.parseFloat(request.getParameter("hours")), request.getParameter("description"), request.getParameter("obsname"), request.getParameter("obsemail"), false, group);
                } catch (SQLException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    request.setAttribute("error", "<strong>Success!</strong> Hours successfully added to " + r.getStudentByID(id).getName() + "'s total.");
                    request.setAttribute("error-type", "success");
                    request.getRequestDispatcher("submit.jsp").forward(request, response);
                } catch (ServletException | IOException | SQLException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Processing Error (Student could not be matched to ID, activity not added. Activity information to follow:)");
                System.out.println("\tID:\t\t\t" + id);
                System.out.println("\tHours:\t\t\t" + Float.parseFloat(request.getParameter("hours")));
                System.out.println("\tDescription:\t\t\t" + request.getParameter("description"));
                System.out.println("\tObs. Name:\t\t\t" + request.getParameter("obsname"));
                System.out.println("\tObs. Email:\t\t\t" + request.getParameter("obsemail"));
                System.out.println("\tApproval:\t\t\tfalse");
                System.out.println("\tGroup Status:\t\t\t" + group);
                try {
                    request.setAttribute("error", "<strong>Error!</strong> No such user found. Please check the spelling of your name and ensure that you are a member of the NHS. If the issue continues, contact rshadra@gmail.com.");
                    request.setAttribute("error-type", "danger");
                    request.getRequestDispatcher("submit.jsp").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (5+1 or 6+1) expected.");
            out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (5+1 or 6+1) expected.");
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStudent(PrintWriter out, HttpServletRequest request) {
        Reader r = new Reader();
        Map<String, String[]> map = request.getParameterMap();
        if (request.getParameterMap().size() == 4) {
            if (map.containsKey("name") && map.containsKey("graduationyear") && map.containsKey("email")) {
                try {
                    r.addStudent(map.get("name")[0], Integer.parseInt(map.get("graduationyear")[0]), map.get(("email"))[0]);
                } catch (SQLException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
                out.println("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
            }
        } else {
            System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected."); //Add a function that returns a bool, true if lengths match false if lengths don't. If lengths don't, it does a println. Code = clean, problem = solved!
            out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected.");
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getHours(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        try {
            Student s = r.getStudentByID(Integer.parseInt(request.getParameter("id")));
            if (!s.isEmpty()) {
                DecimalFormat df = new DecimalFormat("###.##");
                String checkboxFormat;
                out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\"> <link href=\"css/bootstrap-theme.min.css\" rel=\"stylesheet\"> <link href=\"css/theme.css\" rel=\"stylesheet\">");
                out.println("<style>body{padding-top:25px;} td{word-wrap:break-word;} a{color: #0000EE} a:visited{color: #0000EE}</style>");
                out.println("<table class=\"table table-striped\">");
                out.println("<thead> <tr> <th>Hours</th> <th>Observer Email</th> <th>Observer Name</th> <th>Description</th> <th>Approval Status</th>");
                for (Activity a : (ArrayList<Activity>) s.getActivities()) {
                    if (a.isApproved()) {
                        checkboxFormat = "checked=\"checked\"";
                    } else {
                        checkboxFormat = "";
                    }
                    out.println("<tr> <td>" + df.format(a.getHours()) + "</td> <td>" + a.getObsemail() + "</td> <td>" + a.getObsname() + "</td> <td>" + a.getProjdesc() + "</td> <td>" + "<input type=\"checkbox\" disabled=\"disabled\" " + checkboxFormat + " />" + "</td>" /* <td>" + a.isGroupproj() + "</td>*/ + "</tr>");
                }
                out.println("</table>");

                out.println("<h4>Total: " + s.getHours() + " hours. (" + s.getApprovedHours() + " approved) </h4>");

            } else {
                out.println(s.getError());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getHoursAdmin(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        try {
            Student s = r.getStudentByID(Integer.parseInt(request.getParameter("id")));
            //System.out.println("1");
            if (!s.isEmpty()) {
                //System.out.println("2");
                DecimalFormat df = new DecimalFormat("###.##");
                //System.out.println("2.1");
                String checkboxFormat;
                //System.out.println("2.2");
                out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\"> <link href=\"css/bootstrap-theme.min.css\" rel=\"stylesheet\"> <link href=\"css/theme.css\" rel=\"stylesheet\">");
                //System.out.println("2.3");
                out.println("<style>body{padding-top:25px;} td{word-wrap:break-word;} a{color: #0000EE} a:visited{color: #0000EE}</style>");
                //System.out.println("2.4");
                out.println("<table class=\"table table-striped\">");
                //System.out.println("2.5");
                out.println("<thead> <tr> <th>Hours</th> <th>Observer Email</th> <th>Observer Name</th> <th>Description</th> <th>Approval Status</th>");
                //System.out.println("2.6");
                for (Activity a : (ArrayList<Activity>) s.getActivities()) {
                    //System.out.println("3");
                    if (a.isApproved()) {
                        checkboxFormat = "checked=\"checked\"";
                        //System.out.println("4");
                    } else {
                        //System.out.println("5");
                        checkboxFormat = "";
                    }
                    out.println("<tr> <td>" + df.format(a.getHours()) + "</td> <td>" + a.getObsemail() + "</td> <td>" + a.getObsname() + "</td> <td>" + a.getProjdesc() + "</td> <td>" + "<form method=\"POST\" action=\"RequestHandler\" id=\"approve" + a.getActivityID() + "\"><input type=\"hidden\" name=\"action\" value=\"approveactivity\"/><input type=\"hidden\" name=\"id\" value=\"" + a.getStudentID() + "\"/><input type=\"hidden\" name=\"activityid\" value=\"" + a.getActivityID() + "\"/><input type=\"checkbox\" onClick=\"document.getElementById('approve" + a.getActivityID() + "').submit();\"" + checkboxFormat + " /></form>" + "</td>" /* <td>" + a.isGroupproj() + "</td>*/ + "</tr>");
                    //System.out.println("6");
                }
                out.println("</table>");
                //System.out.println("7");
                out.println("<h4>Total: " + s.getHours() + " hours.</h4>");
                //System.out.println("8");
            } else {
                //System.out.println("9");
                out.println(s.getError());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("10");
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "General request handler for getting info out of the database";
    }// </editor-fold>

    @Override
    public void init() {
        //r = new Reader();
    }

    public void removeActivity(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("removeactivity is unimplemented.");
        out.println("removeactivity is unimplemented.");
    }

    public void removeStudent(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("removestudent is unimplemented.");
        out.println("removestudent is unimplemented.");
    }

    public void searchStudents(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        ArrayList<Student> results = new ArrayList<>();
        try {
            results = r.getStudentsByName(request.getParameter("studentname"));
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("<style>.notEnoughSubmitted{color: #0000EE !important;} a{color: #B2B2FA;} a:visited{color: #B2B2FA;}</style>");
        if (request.getParameterMap().containsKey("belowQuota")) {
            for (Student s : results) {
                if (s.getApprovedHours() < 10) {
                    if (s.getHours() >= 10) {
                        out.println("<a href=\"#\" onclick='parent.viewHours(" + s.getID() + ")'>" /*+ "ID: " + s.getID() + ",*Name: " +*/ + s.getName() + "</a><br />");
                    } else {
                        out.println("<a href=\"#\" class=\"notEnoughSubmitted\" onclick='parent.viewHours(" + s.getID() + ")'>" /* + "ID: " + s.getID() + ", Name: " +*/ + s.getName() + "</a><br />");
                    }
                }
            }
        } else {
            for (Student s : results) {
                out.println("<a href=\"#\" onclick='parent.viewHours(" + s.getID() + ")'>" /*+ "ID: " + s.getID() + ", Name: " */ + s.getName() + "</a><br />");
            }
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toggleApproval(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        try {
            r.toggleApproval(Integer.parseInt(request.getParameter("activityid")));
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        getHoursAdmin(out, request, response);
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateActivity(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        if (request.getParameterMap().size() == 7 || request.getParameterMap().size() == 8 || request.getParameterMap().size() == 9) {
            boolean groupproj = false, approved = false;
            if (request.getParameterMap().containsKey("groupproj")) {
                groupproj = true;
            }
            if (request.getParameterMap().containsKey("approved")) {
                approved = true;
            }
            new Activity(Integer.parseInt(request.getParameter("studentid")), Float.parseFloat(request.getParameter("hours")), request.getParameter("description"), request.getParameter("obsname"), request.getParameter("obsemail"), approved, groupproj, Integer.parseInt(request.getParameter("activityid"))).update();
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().size() == 5) {
            Student s = new Student(true, "This feature has not yet been implemented.");
        } else {
            System.out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
            out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            if (map.containsKey("action")) {
                if (request.getParameter("action").equalsIgnoreCase("addstudent")) {
                    System.out.println("Adding Student.");
                    addStudent(out, request);
                } else if (request.getParameter("action").equalsIgnoreCase("removestudent")) {
                    System.out.println("Removing Student.");
                    removeStudent(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("updatestudent")) {
                    System.out.println("Updating Student.");
                    updateStudent(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("addactivity")) {
                    System.out.println("Adding Activity");
                    addActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("removeactivity")) {
                    System.out.println("Removing Activity");
                    removeActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("updateactivity")) {
                    System.out.println("Updating Activity");
                    updateActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("searchstudents")) {
                    System.out.println("Searching Students");
                    searchStudents(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("gethours")) {
                    System.out.println("Getting Hours");
                    getHours(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("gethoursadmin")) {
                    System.out.println("Getting Hours (Admin)");
                    getHoursAdmin(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("approveactivity")) {
                    System.out.println("Approving Activity");
                    toggleApproval(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("blank")) {
                    System.out.println("Generating Blank Page");
                    out.println("");
                }
            } else {
                System.out.println("No action specified.");
                out.println("No action specified.");
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
