<%@page language="java" contentType="text/html"%>

<!DOCTYPE html>
<html>
<head>
<title>Concurrency</title>
</head>
<body>
	<%!
	int k = 0;
	Object syncK = new Object();
	%>

	<%
	synchronized (syncK) {
		out.print(k);
		int j = k + 1;
		Thread.sleep(5000);
		k = j;
		out.println(" -> " + k);
	}
	%>
</body>
</html>