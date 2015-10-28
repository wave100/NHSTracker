<%-- 
    Document   : listActivities
    Created on : Oct 22, 2015, 6:39:08 PM
    Author     : Rish
--%>

<%@page import="me.rishshadra.nhstracker.sql.Reader"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="me.rishshadra.nhstracker.models.Activity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="me.rishshadra.nhstracker.models.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%Student s = (Student) request.getAttribute("student");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activity List</title>
    </head>
    <body>
        <%//DecimalFormat df = new DecimalFormat("###.##");
            String checkboxFormat;%>
        <link href="css/bootstrap.min.css" rel="stylesheet"> <link href="css/bootstrap-theme.min.css" rel="stylesheet"> <link href="css/theme.css" rel="stylesheet">
        <style>body{padding-top:25px;} td{word-wrap:break-word;} a{color: #0000EE} a:visited{color: #0000EE}</style>
        <table class="table table-striped">
            <thead> <tr> <th>Hours</th> <th>Observer Email</th> <th>Observer Name</th> <th>Description</th> <th>Approval Status</th> <th>Actions</th>
                        <%if (!s.isEmpty()) {
                                for (Activity a : (ArrayList<Activity>) s.getActivities()) {
                                    if (a.isApproved()) {
                                        checkboxFormat = "checked=\"checked\"";
                                    } else {
                                        checkboxFormat = "";
                                    }
                                    out.println("<tr> <td>" + new DecimalFormat("###.##").format(a.getHours()) + "</td> <td>" + a.getObsemail() + "</td> <td>" + a.getObsname() + "</td> <td>" + a.getProjdesc() + "</td> <td>" + "<input type=\"checkbox\" disabled=\"disabled\" " + checkboxFormat + " />" + "</td>" /* <td>" + a.isGroupproj() + "</td>*/ + "<td><a href=\"RequestHandler?action=removeActivity&pin=" + s.getPIN() + "&studentid=" + s.getID() + "&activityid=" + a.getActivityID() + "&name=" + s.getName() + "\">Del</a> / <a href=\"updateActivity.jsp?pin=" + s.getPIN() + "&studentid=" + s.getID() + "&activityid=" + a.getActivityID() + "\">Edit</a></td>" + "</tr>");
                                }
                                out.println("</table>");

                                out.println("<h4>Total: " + s.getHours() + " hours. (" + s.getApprovedHours() + " approved) </h4>");
                            } else {
                                out.println(s.getError()); 
                            }
                        %>
                    </body>
                    </html>
