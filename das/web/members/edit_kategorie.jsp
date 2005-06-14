<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
<jsp:useBean id="ctrl" class="das.ui.ctrl.EditKategorieCtrl">
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
	<title>DAS - Kategorien bearbeiten</title>
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
		<h4>Kategorien</h4>
		<%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
		<das:errorlist controller="${ctrl}"/>
		<form action="edit_kategorie.jsp?cmd=save" method="POST">
			<table cellpadding="0" cellspacing="2">
				<tr>
					<td>ID</td>
					<td>${ctrl.fields.id}
						<input name="id" type="hidden" value="${ctrl.fields.id}"/>
					</td>
				</tr><tr>
					<td>Name</td>
					<td><input name="name" value="${ctrl.fields.name}"/></td>
				</tr>
                                <hr/>
                                <tr>
                                        <td><input type="submit" value="Speichern"/></td>
                                        </form>
                                        <td><form action="find_kategorie.jsp" method="POST">
                                                    <input type="submit" value="Zurück" />
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
