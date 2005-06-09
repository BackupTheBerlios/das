<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>    
	<%@ include file="/inc/nocache.jspf" %>
	<link rel="stylesheet" type="text/css" 
		href="${pageContext.request.contextPath}/css/dasneat.css">
	<title>Login</title>
</head>
<body>
	<form action="j_security_check" method="post">
		<table>
			<tr><td>Username:</td><td><input name="j_username"></td></tr>
			<tr><td>Passwort:</td><td><input type="password" name="j_password"></td></tr>
		</table>
		<input type="submit" value="Login">
	</form>
</body>
</html>
