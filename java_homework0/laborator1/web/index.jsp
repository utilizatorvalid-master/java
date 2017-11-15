<%--
  Created by IntelliJ IDEA.
  User: groza
  Date: 10/3/2017
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

</head>
<body>
<form class="col s12" method="POST" action="/pairs">
    <div class="row">
        <div class="input-field col s6">

            <input id="key" class="validate" type="text" name="key" size="20" value=""/>
            <label for="key">Key</label>
        </div>
        <div class="input-field col s6">

            <input id="value" class="validate" type="text" name="value" size="20" value=""/>
            <label for="value">Value</label>
        </div>

    </div>
    <button class="btn waves-effect waves-light" type="submit" name="action">Subbmit
        <i class="material-icons right">send</i>
    </button>
    <%--<input type="submit" name="submit" value="Add Pair">--%>
</form>

<%
    String message = "Hello";
    for (int i = 1; i < 7; i++) {
        out.println("<H" + i + ">" + message + "</H" + i + ">");
    }
%>
<%= request.getParameter("nume") %>
Get the IP address of the client and the type of his browser
<%= request.getRemoteAddr() %>
<%= request.getHeader("user-agent") %>

</body>
</html>
