/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.handlers;

import me.rishshadra.nhstracker.models.Activity;
import me.rishshadra.nhstracker.models.Student;
import me.rishshadra.nhstracker.sql.Reader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.rishshadra.nhstracker.matchers.StudentMatcher;
import me.rishshadra.nhstracker.gmailer.GmailInterface;
import me.rishshadra.nhstracker.consts.Consts;
import me.rishshadra.nhstracker.consts.Credentials;
import me.rishshadra.nhstracker.gmailer.Mailer;
import me.rishshadra.nhstracker.warnings.WarningTypes;

/**
 *
 * @author Rish
 */
public class RequestHandler extends HttpServlet {

    //Reader r;
    public void addActivity(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        GmailInterface mail = new GmailInterface();
        if (request.getParameterMap().size() == 8 || request.getParameterMap().size() == 7) {
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
                Student s; //= new Student(true, "Could not create student with ID: " + id);

                try {
                    s = r.getStudentByID(id);
                    if (s.isEmpty()) {
                        me.rishshadra.nhstracker.logging.Logger.logText(s.getError());
                        try {
                            request.setAttribute("error", "<strong>Error!</strong> " + s.getError() + ".");
                            request.setAttribute("error-type", "danger");
                            request.getRequestDispatcher("submit.jsp").forward(request, response);
                        } catch (ServletException | IOException ex) {
                            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        me.rishshadra.nhstracker.logging.Logger.logText("Student matched. Email: " + s.getEmail());

                        if (Integer.parseInt(request.getParameter("pin")) == s.getPIN()) {

                            r.addActivity(id, Float.parseFloat(request.getParameter("hours")), request.getParameter("description"), request.getParameter("obsname"), request.getParameter("obsemail"), false, group);

                            String hourPlural1, hourPlural2;

                            if (Float.valueOf(request.getParameter("hours")).intValue() == 1) {
                                hourPlural1 = "hour";
                            } else {
                                hourPlural1 = "hours";
                            }

                            if ((s.getHours() + Float.valueOf(request.getParameter("hours")).intValue()) == 1) {
                                hourPlural2 = "hour";
                            } else {
                                hourPlural2 = "hours";
                            }

                            mail.sendMessage(s.getEmail(), "Hour Submission Confirmation", "You have successfully submitted " + request.getParameter("hours") + " volunteer " + hourPlural1 + ". You have submitted a total of " + Float.toString(s.getHours()) + " " + hourPlural2 + " this year. Click <a href=\" " + Consts.SITE_URL + "  /submit.jsp\">here</a> to submit more hours, or click <a href=\"" + Consts.SITE_URL + "/RequestHandler?action=gethours&studentname=" + s.getName() + "&pin=" + s.getPIN() + "\">here</a> to view a breakdown of the hours that you have submitted so far. <br /> <br /> <h6>This is an automatically generated message. Replies to this email will be forwarded to the NHS officers. Not " + s.getName() + "? Send a message to " + Consts.SUPPORT_EMAIL + " and I'll sort it out.</h6> <br /> <br /> <h6>--</h6>"); //Format that float. Yes, that one.

                            try {
                                request.setAttribute("error", "<strong>Success!</strong> Hours successfully added to " + s.getName() + "'s total.");
                                request.setAttribute("error-type", "success");
                                request.getRequestDispatcher("submit.jsp").forward(request, response);
                            } catch (ServletException | IOException ex) {
                                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            request.setAttribute("error", "<strong>Error!</strong> An incorrect PIN was entered.");
                            request.setAttribute("error-type", "danger");
                            request.getRequestDispatcher("submit.jsp").forward(request, response);
                        }
                    }
                } catch (SQLException | IOException | ServletException ex) { //On messagingexception, set message type to warning and tell user that email was unable to be sent.
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    request.setAttribute("error", "<strong>Error!</strong> PINs must be numeric.");
                    request.setAttribute("error-type", "danger");
                    try {
                        request.getRequestDispatcher("submit.jsp").forward(request, response);
                    } catch (ServletException | IOException ex1) {
                        Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (MessagingException ex) {
                    request.setAttribute("error", "<strong>Warning!</strong> Your hours were submitted, but a confirmation email could not be sent.");
                    request.setAttribute("error-type", "warning");
                    try {
                        request.getRequestDispatcher("submit.jsp").forward(request, response);
                    } catch (ServletException | IOException ex1) {
                        Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            } else {
                try {
                    mail.sendMessage(Consts.SUPPORT_EMAIL, "[NHSTracker-ERROR]", "Student could not be matched to ID, activity not added. Activity information to follow: <br /> " + "\tName:\t\t\t\t" + request.getParameter("name") + "<br />ID: " + id + "<br />Hours: " + request.getParameter("hours") + "<br />Description: " + request.getParameter("description") + "<br />Obs. Name: " + request.getParameter("obsname") + "<br />Obs. Email: " + request.getParameter("obsemail") + "<br />Approval: " + "false" + "<br />Group Status: " + group + "<br /> <br /> --");
                } catch (MessagingException | IOException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                me.rishshadra.nhstracker.logging.Logger.logText("Processing Error (Student could not be matched to ID, activity not added. Activity information to follow:)");
                me.rishshadra.nhstracker.logging.Logger.logText("\tName:\t\t\t\t" + request.getParameter("name"));
                me.rishshadra.nhstracker.logging.Logger.logText("\tID:\t\t\t\t" + id);
                me.rishshadra.nhstracker.logging.Logger.logText("\tHours:\t\t\t\t" + Float.parseFloat(request.getParameter("hours")));
                me.rishshadra.nhstracker.logging.Logger.logText("\tDescription:\t\t\t" + request.getParameter("description"));
                me.rishshadra.nhstracker.logging.Logger.logText("\tObs. Name:\t\t\t" + request.getParameter("obsname"));
                me.rishshadra.nhstracker.logging.Logger.logText("\tObs. Email:\t\t\t" + request.getParameter("obsemail"));
                me.rishshadra.nhstracker.logging.Logger.logText("\tApproval:\t\t\tfalse");
                me.rishshadra.nhstracker.logging.Logger.logText("\tGroup Status:\t\t\t" + group);
                try {
                    request.setAttribute("error", "<strong>Error!</strong> No such user found. Please check the spelling of your name and ensure that you are a member of the NHS. If the issue continues, contact " + Consts.SUPPORT_EMAIL + ".");
                    request.setAttribute("error-type", "danger");
                    request.getRequestDispatcher("submit.jsp").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (5+1 or 6+1) expected.");
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
        //GMailer email = new GMailer();
        Map<String, String[]> map = request.getParameterMap();
        if (request.getParameterMap().size() == 5 && map.containsKey("password") && map.get("password")[0].hashCode() == Credentials.ADMIN_PASSWORD_HASH) {
            if (map.containsKey("name") && map.containsKey("graduationyear") && map.containsKey("email")) {
                try {
                    r.addStudent(map.get("name")[0], Integer.parseInt(map.get("graduationyear")[0]), map.get(("email"))[0], Consts.CURRENT_INDUCTION_SECTION);
                    //email.sendMessage(map.get("email")[0], "Welcome to the National Honor Society!", "Dear ");
                } catch (SQLException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                me.rishshadra.nhstracker.logging.Logger.logText("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
                out.println("Student " + map.get("name")[0] + " added with graduation year of " + map.get("graduationyear")[0] + ".");
            }
        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected."); //Add a function that returns a bool, true if lengths match false if lengths don't. If lengths don't, it does a println. Code = clean, problem = solved!
            out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (2+1) expected.");
        }
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void emailPIN(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        Mailer m;
        Student s = new Student(true, "If you are reading this, emailPIN() goofed.");

        try {

            if (request.getParameter("input").contains("@")) {
                s = r.getStudentByEmail(request.getParameter("input"));
            } else {
                int id = StudentMatcher.attemptMatch(request.getParameter("input"));
                if (id < 0) {
                    s = new Student(true, "Student with requested name or email not found.");
                } else {
                    s = r.getStudentByID(id);
                }
            }

            if (s.isEmpty()) {
                me.rishshadra.nhstracker.logging.Logger.logText(s.getError());
                out.println(s.getError());

                request.setAttribute("error", "<strong>Error!</strong> No student found with name/email " + request.getParameter("input"));
                request.setAttribute("error-type", "danger");
                try {
                    request.getRequestDispatcher("ForgotPIN.jsp").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                ArrayList<Student> sl = new ArrayList<>();
                sl.add(s);
                String msg = "Dear $NAME, <br /> <br />Your PIN for the hour tracker is $PIN. <br /> <br />";
                m = new Mailer(msg, "Your Hour Tracker PIN", sl);
                m.sendMessages();
            }
            r.close();
        } catch (SQLException | MessagingException | IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("error", "<strong>Success!</strong> Your PIN was emailed to you at " + s.getEmail());
        request.setAttribute("error-type", "success");

        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getHours(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        if (request.getParameterMap().containsKey("id")) {
            try {
                request.setAttribute("name", r.getStudentByID(Integer.parseInt(request.getParameter("id"))).getName());
                r.close();
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException | IOException | SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                int id = StudentMatcher.attemptMatch(request.getParameter("studentname"));
                if (id > 0) {
                    try {
                        Student s = r.getStudentByID(id);
                        if (Integer.parseInt(request.getParameter("pin")) == s.getPIN()) {
                            if (!s.isEmpty()) {
                                request.setAttribute("student", s);
                                request.getRequestDispatcher("listActivities.jsp").forward(request, response);

                            } else {
                                out.println(s.getError());
                            }
                        } else {
                            out.println("Incorrect PIN entered.");
                        }

                        me.rishshadra.nhstracker.logging.Logger.logText("Got Hours For: " + s.getName());

                    } catch (SQLException | ServletException | IOException ex) {
                        Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NumberFormatException ex) {
                        out.println("Incorrect PIN entered.");
                    } finally {
                        r.close();
                    }

                } else {
                    out.println("Incorrect name entered.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getHoursAdmin(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter("adminpass").hashCode() == Credentials.ADMIN_PASSWORD_HASH || Integer.parseInt(request.getParameter("adminpass")) == Credentials.ADMIN_PASSWORD_HASH) {
                me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication successful. Displaying page.");

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
                            out.println("<tr> <td>" + df.format(a.getHours()) + "</td> <td>" + a.getObsemail() + "</td> <td>" + a.getObsname() + "</td> <td>" + a.getProjdesc() + "</td> <td>" + "<form method=\"POST\" action=\"RequestHandler\" id=\"approve" + a.getActivityID() + "\"><input type=\"hidden\" name=\"action\" value=\"approveactivity\"/><input type=\"hidden\" name=\"id\" value=\"" + a.getStudentID() + "\"/><input type=\"hidden\" name=\"activityid\" value=\"" + a.getActivityID() + "\"/><input type=\"hidden\" name=\"adminpass\" value=\"" + Credentials.ADMIN_PASSWORD_HASH + "\"/><input type=\"checkbox\" onClick=\"document.getElementById('approve" + a.getActivityID() + "').submit();\"" + checkboxFormat + " /></form>" + "</td>" /* <td>" + a.isGroupproj() + "</td>*/ + "</tr>");
                        }
                        out.println("</table>");
                        out.println("<h4>Total: " + s.getHours() + " hours.</h4>");
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
            } else {
                me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication failed.");
                out.println("<h1>The password that you entered was incorrect.</h1>");
            }
        } catch (NullPointerException ex) {
            me.rishshadra.nhstracker.logging.Logger.logText("Failed to get hours as admin. Insufficient or incorrect parameters.");
            out.println("Incorrect or insufficient parameters. If you are seeing this message, contact " + Consts.SUPPORT_EMAIL + " with a description of what you were doing before this error occurred.");
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
        Reader r = new Reader();
        Student s = new Student(true, "removeActivity init error");
        try {
            s = r.getStudentByID(Integer.valueOf(request.getParameter("studentid")));
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Integer.valueOf(request.getParameter("pin")).equals(s.getPIN())) {
            try {
                r.removeActivity(Integer.valueOf(request.getParameter("activityid")));
                r.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                request.setAttribute("student", s);
                request.getRequestDispatcher("listActivities.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("error-type", "danger");
            request.setAttribute("error", "Incorrect PIN entered.");
            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void removeStudent(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        try {
            r.removeStudent(Integer.valueOf(request.getParameter("studentid")));
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchStudents(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("adminpass").hashCode() == Credentials.ADMIN_PASSWORD_HASH) {
            Reader r = new Reader();
            ArrayList<Student> results = new ArrayList<>();
            try {
                results = r.getStudentsByName(request.getParameter("studentname"));
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (request.getParameterMap().containsKey("belowQuota")) {
                out.println("<style>.notEnoughSubmitted{color: #0000EE !important;} a{color: #B2B2FA;} a:visited{color: #B2B2FA;}</style>");
                for (Student s : results) {
                    if (s.getApprovedHours() < 10) {
                        if (s.getHours() >= 10) {
                            out.println("<a href=\"#\" onclick='parent.viewHours(" + s.getID() + ")'>" + s.getName() + "</a><br />");
                        } else {
                            out.println("<a href=\"#\" class=\"notEnoughSubmitted\" onclick='parent.viewHours(" + s.getID() + ")'>" + s.getName() + "</a><br />");
                        }
                    }
                }
            } else {
                out.println("<style>a{color: #0000EE;} a:visited{color: #0000EE;}</style>");
                for (Student s : results) {
                    out.println("<a href=\"#\" onclick='parent.viewHours(" + s.getID() + ")'>" + s.getName() + "</a><br />");
                }
            }
            try {
                r.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication failed.");
            out.println("<h1>The password that you entered was incorrect.</h1>");
        }
    }

    public void toggleApproval(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("adminpass").hashCode() == Credentials.ADMIN_PASSWORD_HASH || Integer.parseInt(request.getParameter("adminpass")) == Credentials.ADMIN_PASSWORD_HASH) {
            me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication successful.");
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

        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication failed.");
            out.println("<h1>The password that you entered was incorrect.</h1>");
        }

    }

    public void toggleWarning(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("adminpass").hashCode() == Credentials.ADMIN_PASSWORD_HASH || Integer.parseInt(request.getParameter("adminpass")) == Credentials.ADMIN_PASSWORD_HASH) {
            me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication successful.");
            Reader r = new Reader();
            try {
                r.toggleWarning();
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            updateWarning(out, request, response); //Is this necessary?
            try {
                r.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Admin authentication failed.");
            out.println("<h1>The password that you entered was incorrect.</h1>");
        }

    }

    public void updateActivity(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        Reader r = new Reader();
        try {
            if (r.getStudentByID(Integer.valueOf(request.getParameter("studentid"))).getPIN() == Integer.valueOf(request.getParameter("pin"))) {
                boolean groupproj = false, approved = false;
                if (request.getParameterMap().containsKey("groupproj")) {
                    groupproj = true;
                }
                if (request.getParameterMap().containsKey("approved")) {
                    approved = true;
                }
                new Activity(Integer.parseInt(request.getParameter("studentid")), Float.parseFloat(request.getParameter("hours")), request.getParameter("description"), request.getParameter("obsname"), request.getParameter("obsemail"), false, groupproj, Integer.parseInt(request.getParameter("activityid"))).update();
            }
            request.setAttribute("student", r.getStudentByID(Integer.valueOf(request.getParameter("studentid"))));
            r.close();
            request.getRequestDispatcher("listActivities.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterMap().size() == 5) {
            Student s = new Student(true, "This feature has not yet been implemented.");
        } else {
            me.rishshadra.nhstracker.logging.Logger.logText("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
            out.println("Malformed URL! " + request.getParameterMap().size() + " parameters recieved, (4+1) expected.");
        }
    }

    public void updateWarning(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("adminpass").hashCode() == Credentials.ADMIN_PASSWORD_HASH) {
            Reader r = new Reader();
            try {
                r.updateWarning(Integer.parseInt(request.getParameter("type")), request.getParameter("content"), request.getParameterMap().containsKey("enabled"));
                r.close();
            } catch (SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("error-type", WarningTypes.ALERT_NAMES[WarningTypes.SUCCESS]);
            request.setAttribute("error", "<strong>Success!</strong> The global warning was successfully updated.");
        } else {
            request.setAttribute("error-type", WarningTypes.ALERT_NAMES[WarningTypes.DANGER]);
            request.setAttribute("error", "<strong>Error!</strong> You did not enter the correct admin password.");
        }

        try {
            request.getRequestDispatcher("updateWarning.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
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

        try (PrintWriter out = response.getWriter()) { //Print HTML style and head over here to clean up code in methods called below.

            if (map.containsKey("action")) {
                if (request.getParameter("action").equalsIgnoreCase("addstudent")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Adding Student.");
                    addStudent(out, request);
                } else if (request.getParameter("action").equalsIgnoreCase("removestudent")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Removing Student.");
                    removeStudent(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("updatestudent")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Updating Student.");
                    updateStudent(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("addactivity")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Adding Activity");
                    addActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("removeactivity")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Removing Activity");
                    removeActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("updateactivity")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Updating Activity");
                    updateActivity(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("searchstudents")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Searching Students");
                    searchStudents(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("gethours")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Getting Hours");
                    getHours(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("gethoursadmin")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Getting Hours (Admin)");
                    getHoursAdmin(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("approveactivity")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Approving Activity");
                    toggleApproval(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("emailpin")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Emailing PIN");
                    emailPIN(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("updatewarning")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Updating Warning");
                    updateWarning(out, request, response);
                } else if (request.getParameter("action").equalsIgnoreCase("blank")) {
                    me.rishshadra.nhstracker.logging.Logger.logText("Generating Blank Page");
                    out.println("");
                }
            } else {
                me.rishshadra.nhstracker.logging.Logger.logText("No action specified.");
                out.println("No action specified.");
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
