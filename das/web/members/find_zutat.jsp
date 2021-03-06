<%-- @author k --%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
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
		href="${pageContext.request.contextPath}/css/dasneat.css"/>
	<title>DAS - Nahrungsmittel suchen</title>	
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
		<h4>Nahrungsmittel</h4>
		
		<%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
		<das:errorlist controller="${ctrl}"/>
		<p/>
		<c:forEach items="${ctrl.kategorien}" var="kat">
			<a href="find_zutat.jsp?cmd=find&kat=${kat.id}">${kat.name}</a><br/>
		</c:forEach>
		<p/>
		<form action="find_zutat.jsp?cmd=find" method="POST">
			Name der Zutat:<br/>
			(Grosskleinschreibung egal. * steht f&uuml;r jede beliebige Buchstabenfolge)<p />
			<input name="nameExpr" value="${ctrl.fields.nameExpr}" size="30" maxlength="100"/>
			<input type="submit" value="Suchen" />
			<p/>

			${ctrl.message}
			<c:if test="${not empty ctrl.results}">
			<table cellpadding="0" cellspacing="2">
			<c:forEach items="${ctrl.results}" var="item">
				<tr>
					<td><a href="edit_zutat.jsp?cmd=edit&id=${item.id}">${item.name}</a></td>
					<td><input type="checkbox" name="selected" value="${item.id}"
						onclick="deleteButton.disabled = false"/></td>
				</tr>
			</c:forEach>
			</table>
			</c:if>

			<hr width="50%" align="left"/>
			<button id="deleteButton" type="button" disabled
				onClick="submitForm('find_zutat.jsp?cmd=delete', 'Wollen Sie wirklich L&ouml;schen?')">
				L&ouml;schen</button>
			<button type="button" onClick="submitForm('edit_zutat.jsp?cmd=new')">Neu</button>
		</form>
		</div>
	</td>
	</tr>
</tbody>
</table>
</div>
</body>
</html>    
