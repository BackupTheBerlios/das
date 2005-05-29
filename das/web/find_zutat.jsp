<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib tagdir="/WEB-INF/tags" prefix="das" %>

<jsp:useBean id="ctrl" class="das.ui.ctrl.FindZutatCtrl">
	<jsp:setProperty name="ctrl" property="pageContext" value="${pageContext}"/>
</jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<%@ include file="/inc/nocache.jspf" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dasutil.js">
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/styles.css"/>
	<title>DAS - Zutaten suchen</title>	
</head>
<body>
	<h3>Zutaten suchen</h3>
	<das:errorlist controller="${ctrl}"/>
	Kategorien:<p>
	<c:forEach items="${ctrl.kategorien}" var="kat">
		<a href="find_zutat.jsp?cmd=find&kat=${kat.id}">${kat.name}</a><br/>
	</c:forEach>
	<p/>
	<form action="find_zutat.jsp?cmd=find" method="POST">
		Zutat Name: 
		<input name="nameExpr" value="${ctrl.fields.nameExpr}" size="30" maxlength="100"/></br>
		<input type="submit" value="Suchen" />
		<p/>
		
		${ctrl.message}
		<c:if test="${not empty ctrl.results}">
		<table cellpadding="0" cellspacing="2">
		<c:forEach items="${ctrl.results}" var="item">
			<tr>
				<td><a href="edit_zutat.jsp?cmd=edit&id=${item.id}">${item.name}</a></td>
				<td><input type="checkbox" name="selected" value="${item.id}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
		
		<hr width="50%" align="left"/>
		<button type="button" onClick="submitForm('find_zutat.jsp?cmd=delete', 'Sicher?')">
			L&ouml;schen</button>
		<button type="button" onClick="submitForm('find_zutat.jsp?cmd=new')">Neu</button>
	</form>
</body>
</html>
