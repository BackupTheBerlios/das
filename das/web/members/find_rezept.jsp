<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
<jsp:useBean id="ctrl" class="das.ui.ctrl.FindRezeptCtrl">
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
	<title>DAS - Rezept suchen</title>	
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
		<h4>Rezepte suchen</h4>
		
		<%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
		<das:errorlist controller="${ctrl}"/>
		<p/>
		
		<p/>
		<form action="find_rezept.jsp?cmd=find" method="POST">

		<table width="400" border="0">
                <tr> 
                  <td>Max. Kalorien</td>
                  <td><input name="cal" type="text" width="300" value="" maxlength="300" size="25"></td>
                </tr>
                <tr> 
                  <td>Max. Fettgehalt</td>
                  <td><input name="fat" type="text" width="300" value="" maxlength="300" size="25"></td>
                </tr>
                <tr height="5"></tr>
                <tr> 
                  <td>Mind. Bewertung</td>
                  <td><select name="score" onChange="MM_jumpMenu('parent',this,0)">
                        <option>egal</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                      </select><br>
                  <td>
                </tr>
                <tr height="5"></tr>
                <tr> 
                  <td>Stichwort</td>
                  <td><input name="nameExpr" type="text" width="300" value="" maxlength="300" size="25"></td>
                </tr>
                <tr height="5"></tr>
                <tr> 
                  <td></td>
                  <td><input type="submit" value="Suchen"></td>
                </tr>
              </table>

		</form>
		<p />
		<!-- eine auflistung von allen Rezepten -->
		<h4>Rezept auswдhlen</h4>
		
                        ${ctrl.message}
			<c:if test="${not empty ctrl.results}">
			<table cellpadding="0" cellspacing="2">
			<c:forEach items="${ctrl.results}" var="item">
				<tr>
					<td><a href="view_rezept.jsp?&id=${item.id}">${item.name}</a></td>
				</tr>
			</c:forEach>
			</table>
			</c:if>

		
		
	</div>
	</td>
	</tr>
</tbody>
</table>
</div>
</body>
</html>    
