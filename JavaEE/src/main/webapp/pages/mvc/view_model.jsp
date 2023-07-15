<%@ page contentType="text/html;charset=UTF-8" language='java' %>
<html>
    <head>
        <title>Student Bean</title>
    </head>

    <body>
        <jsp:useBean id="student" class="edu.mvc.Student" scope="request"/>
        This student name is <jsp:getProperty name="student" property="name" />
    </body>
</html>