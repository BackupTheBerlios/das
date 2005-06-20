<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
<jsp:useBean id="ctrl" class="das.ui.ctrl.EditUserCtrl">
	<jsp:setProperty name="ctrl" property="pageContext" value="${pageContext}"/>
</jsp:useBean>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/inc/nocache.jspf" %>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/dasneat.css"/>
	<title>DAS - Mein Profil</title>
</head>
<body>
<div id="das-top">DAS - Di&auml;tassistent</div>
<div id="das-mid">
<table border="0" cellpadding="0" cellspacing="0" id="das-midtbl">
<tbody>
	<tr>
	<td  id="leftcolumn" valign="top">
		<%@ include file="/inc/menu.jspf" %>
	</td>
	<td id="centercolumn" valign="top">
		<div id="das-center">
		<h4>Mein Profil</h4>
		<%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
		<das:errorlist controller="${ctrl}"/>
		<form action="userprofil.jsp?cmd=save" method="POST">
			<table cellpadding="0" cellspacing="2">
				<tr>
					<td>ID</td>
					<td>${ctrl.fields.id}
						<input name="id" type="hidden" value="${ctrl.fields.id}"/>
					</td>
				</tr><tr>
					<td>Name</td>
					<td><input name="name" value="${ctrl.fields.name}" maxlength="100"/></td>
				</tr><tr>
					<td>Login</td>
					<td><input name="login" value="${ctrl.fields.login}" maxlength="50"/></td>
				</tr><tr>
					<td>Passwort</td>
					<td><input name="passwort" value="${ctrl.fields.passwort}" maxlength="50"/></td>
				</tr><tr>
					<td>E-Mail</td>
					<td><input name="email" value="${ctrl.fields.email}" maxlength="50"/></td>
				</tr><tr>
					<td>Gruppe</td>
					<td><das:selectname name="gruId" options="${ctrl.gruppen}" 
						selected="${ctrl.fields.gruId}"/></td>					
				</tr><tr>
					<td>Allergien:</td>                                 
					<c:forEach items="${ctrl.allergien}" var="item">			
						<td>${item.name}</td>
						<td><input type="checkbox" name="selectedAllergien" value="${item.id}"
							${ctrl.selectedAllergien[item.id]} /></td>
						</tr><tr>
						<td></td>
					</c:forEach>
				</tr>			
				<hr/>
				<tr>
					<td><input type="submit" value="Speichern"/></td>
					</form>
					<td><form action="index.jsp" method="POST">
						<input type="submit" value="Abbrechen" />
					</form>
					</td>
				</tr>
			</table>
		</div>
	</td>
	</tr>
</tbody>
</table>
</div>
</body>
</html>    
