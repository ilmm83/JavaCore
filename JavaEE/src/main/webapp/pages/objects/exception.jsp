<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error page</title>
</head>
<body>

	<%
		var trace = exception.getStackTrace();
		var traceAsString = new StringBuilder();

		for (var e : trace) {
			traceAsString.append(e.toString()).append("\n");
		}
	%>
	
	<%=
		traceAsString.toString()
	%>

</body>
</html>