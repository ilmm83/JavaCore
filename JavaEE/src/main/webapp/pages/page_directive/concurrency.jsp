<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isThreadSafe="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Concurrency in JSP</title>
</head>
<body>

	<%!int i = 0;%>
	<%
	out.print(i);
	int j = i + 1;

	Thread.sleep(5000);
	i = j;

	out.println(" -> " + i);
	%>
</body>
</html>