<%-- 
    Document   : updateActivity
    Created on : Oct 27, 2015, 10:30:48 PM
    Author     : Rish
--%>

<%@page import="me.rishshadra.nhstracker.models.Activity"%>
<%@page import="me.rishshadra.nhstracker.models.Student"%>
<%@page import="me.rishshadra.nhstracker.sql.Reader"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Activity</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="img/favicon.ico">
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap theme -->
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/theme.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type='text/javascript' src="js/hover.js"></script>

        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-71586223-1', 'auto');
            ga('send', 'pageview');

        </script>

    </head>
    <body role="document">

        <div class="container theme-showcase" role="main">

            <form method="POST" action="RequestHandler" id="activityform">
                <%  Reader r = new Reader();
                    Student s = r.getStudentByID(Integer.valueOf(request.getParameter("studentid")));
                    Activity a = r.getActivityByID(Integer.valueOf(request.getParameter("activityid")));
                    r.close();
                    if (Integer.valueOf(request.getParameter("pin")) == s.getPIN()) {

                %>
                <input type="hidden" name="action" value="updateactivity" />
                <input type="hidden" name="studentid" value="<%=s.getID()%>"/>
                <input type="hidden" name="activityid" value="<%=a.getActivityID()%>"/>
                <input type="hidden" name="pin" value="<%=s.getPIN()%>"/>
                <table>
                    <tr> <td>Observer Name:&nbsp;&nbsp;</td> <td> <input type="text" name="obsname" value="<%=a.getObsname()%>" required /> </td> </tr>
                    <tr> <td>Observer Email:</td> <td> <input type="text" name="obsemail" value="<%=a.getObsemail()%>" required /> </td> </tr>
                    <tr> <td>Hours:</td> <td> <input type="number" step="any" min="0" name="hours" value="<%=a.getHours()%>" required /> </td> </tr>
                </table><br />

                Description: <br />

                <textarea name="description" style="resize: none; width: 254px; height: 100px;" required><%=a.getProjdesc()%></textarea><br /><br />
                <div id="groupdiv">Was this project a group project? &nbsp;&nbsp; <input type="checkbox" name="groupproj" id="group" /> <br /> <br />

                    <input type="submit" value="Update" /> &nbsp;&nbsp; By submitting this form, I swear that all of the information in this form is truthful.</div>
                    <%} else {%>
                Incorrect PIN entered.
                <%}%>
            </form>

        </div> <!-- /container -->

        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
