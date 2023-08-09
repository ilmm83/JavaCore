<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/wow.tld" prefix="wow"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom bodyless tag test</title>
</head>
<body>
	<%
	String date = request.getParameter("date");
	%>

	<span>weekday today: <wow:weekday /></span>
	<br />
	<span>weekday <%=date%>: <wow:weekday date="<%=date%>" /></span>
</body>
</html>