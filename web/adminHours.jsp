<%@page import="me.rishshadra.nhstracker.sql.Reader"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>View All Hours (Admin)</title>
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

        <script language="javascript">
            var hoursURL = "RequestHandler?action=getallhours";

            function setHourList() {
                if (document.getElementById("unapproved").checked) {
                    document.getElementById("resultFrame").setAttribute("src", hoursURL + "&adminpass=" + document.getElementById("adminpass").value + "&unapproved");
                } else {
                    document.getElementById("resultFrame").setAttribute("src", hoursURL + "&adminpass=" + document.getElementById("adminpass").value);
                }
            }

        </script>    

        <style>

            #resultFrame {
                border: none;
                height: 80%;
                width: 100%;
                overflow: scroll;
            }

        </style>

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
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#" style="cursor:default;"><img style="max-width:40px; margin-top: -7px;" src="img/logo.png"></img> </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">View</a></li>
                        <li><a href="submit.jsp">Submit</a></li>
                        <li><a href="about.jsp">About</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container theme-showcase" role="main" id="maindiv">

            <% if (request.getAttribute("error") == null) {
                    Reader r = new Reader();
                    out.println(r.getWarning().getHTML());
                    r.close();
                } else {%>
            <frame id="notificationframe"><div class="alert alert-<%=request.getAttribute("error-type")%>" role="alert"> <%=request.getAttribute("error")%> </div></frame>
                <%}%>

            <form>
                <input id="adminpass" type="password" name="adminpass" placeholder="Admin Password" /> <input type="button" value="Submit" onClick="setHourList();"/> &nbsp;&nbsp;&nbsp;
                Show approved hours: <input id="unapproved" name="unapproved" type="checkbox" onClick="setHourList();" />
            </form>

            <iframe id="resultFrame" src=""></iframe>

            <div id="creditholder"><h6 id="credittext">@</h6></div>

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
