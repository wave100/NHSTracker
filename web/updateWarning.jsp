<%-- 
    Document   : updateWarning
    Created on : Oct 26, 2015, 11:19:42 PM
    Author     : Rish
--%>

<!DOCTYPE html>

<html>
    <head>
        <title>Update Warning Banner</title>
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

            <% if (request.getAttribute("error") == null) {%>
            <!--<frame id="notificationframe"><div class="alert alert-info" role="alert"> <strong>Warning!</strong> This site is still under construction. If something breaks, please email me at rshadra@gmail.com and I'll sort things out! </div></frame>-->
            <%} else {%>
            <frame id="notificationframe"><div class="alert alert-<%=request.getAttribute("error-type")%>" role="alert"> <%=request.getAttribute("error")%> </div></frame>
                <%}%>

            <form method="POST" action="RequestHandler">
                <input type="hidden" name="action" value="updatewarning" />
                <input id="adminpass" type="password" name="adminpass" placeholder="Admin Password" /> <br />
                <input id="content" type="text" name="content" placeholder="Warning Text" /> <input type="checkbox" name="enabled" /><br />
                Type: <select name="type">
                    <option value="0">Success</option>
                    <option value="1">Info</option>
                    <option value="2">Warning</option>
                    <option value="3">Danger</option>
                </select><br />
                <input type="submit" placeholder="Submit" required /> <br />
            </form>

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
