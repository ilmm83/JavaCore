<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/wow.tld" prefix="wow"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom tag with body test</title>
</head>
<body>
	<%
	String date = request.getParameter("date");
	%>

	<span>weekday today: <wow:weekdayBody></wow:weekdayBody></span>
	<br />
	<span>weekday ${param.d}: <wow:weekdayBody>${param.d}</wow:weekdayBody></span>
</body>
</html>