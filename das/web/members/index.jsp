<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<%@ include file="/inc/nocache.jspf" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/dasneat.css"/>
	<title>DAS - Home</title>	
</head>
<body>
<div id="das-top"><%@ include file="/inc/dascaption.jspf" %></div>
<div id="das-mid">
<table border="0" cellpadding="0" cellspacing="0" id="das-midtbl">
<tbody>
	<tr>
	<td  id="leftcolumn" valign="top">
		<%@ include file="/inc/menu.jspf" %>
	</td>
	<td id="centercolumn" valign="top">
		<div id="das-center">
		<h4>DAS Di&auml;tassistent</h4>		
		</div>
	</td>
	</tr>
</tbody>
</table>
</div>
</body>
</html>    
