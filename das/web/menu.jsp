<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<%@ include file="/inc/nocache.jspf" %>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/styles.css"/>
	<title>JSP Page</title>
</head>
<body>
	<h4>Menü</h4>

	<a href="find_zutat.jsp" target="Hauptframe">Nahrungsmittel</a>
	<p>
	<a href="Rezepte.htm" target="Hauptframe">Rezepte anzeigen</a>
	<p>
	<a href="RezeptErstellen.htm" target="Hauptframe">Rezept erstellen</a>
	<p>
	<a href="MeinProfil.htm" target="Hauptframe">Mein Profil</a>
	<p>
	<a href="NahrungsmittelBearbeiten.htm" target="Hauptframe">Nahrungsmittel bearbeiten</a>
	<p>
	<a href="RezepteBearbeiten.htm" target="Hauptframe">Rezepte bearbeiten</a>
	<p>
	<a href="AllergienBearbeiten.htm" target="Hauptframe">Allergien bearbeiten</a>
	<p>
</body>
</html>
