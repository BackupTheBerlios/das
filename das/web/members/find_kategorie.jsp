<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
<jsp:useBean id="ctrl" class="das.ui.ctrl.FindKategorieCtrl">
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
		href="${pageContext.request.contextPath}/css/dasneat.css"/>
	<title>DAS - Kategorien suchen</title>	
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
		<h4>Kategorien bearbeiten</h4>
		
		<%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
		<das:errorlist controller="${ctrl}"/>
		<p/>

		<form action="find_kategorie.jsp?cmd=find" method="POST">

			${ctrl.message}
			<c:if test="${not empty ctrl.kategorien}">
		
			<table cellpadding="0" cellspacing="2">
			<c:forEach items="${ctrl.kategorien}" var="item">
				<tr>
					<td><a href="edit_kategorie.jsp?cmd=edit&id=${item.id}">${item.name}</a></td>
					<td><input type="checkbox" name="selected" value="${item.id}"/></td>
				</tr>
			</c:forEach>
			</table>
			</c:if>

			<hr width="50%" align="left"/>
			<button type="submit" onClick="submitForm('find_kategorie.jsp?cmd=delete', 'Sicher?')">
				L&ouml;schen</button>
			<button type="submit" onClick="submitForm('edit_kategorie.jsp?cmd=new')">Neu</button>
		</form>
		</div>
	</td>
	</tr>
</tbody>
</table>
</div>
</body>
</html>    
