<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language='java' %>
<html>
    <head>
        <title>JSP</title>
    </head>

    <body>
         <%= new Date(); %>

        <form method="get" action="/logout">
            <input type="submit" value="Log out" />
        </form>
    </body>
</html>