<%-- TODO
 Auslagern der html elemente: linkes menue, rechtes menue --%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>DAS</title>
        <link type="text/css" href="${pageContext.request.contextPath}/css/dasneat.css" rel="stylesheet">
    </head>
    <body>
        <div id="das-main">
            <div id="das-top">DAS - GUI Prototyp v 0.3
                <div id="das-mid">
                    <table border="0" cellpadding="0" cellspacing="0" id="das-midtbl">
                        <tbody>
                            <tr>
                                <td  id="leftcolumn" valign="top">
                                    <%@include file="/inc/menu.jspf" %>
                                </td>
                                <td id="centercolumn" valign="top"><div id="das-center">
                                    <h4>Mein Profil</h4>
                                    <br>
                                    <form action="" method="get">
                                        <table width="400" border="0">
                                            <tr> 
                                                <td valign="top">Allergien:</td>
                                                <td><input name="" type="checkbox">Zucker<br>
                                                    <input name="" type="checkbox">
                                                    Alkohol<br>
                                                    <input name="" type="checkbox">...<br>
                                                </td>
                                            </tr>
                                            <tr height="20"></tr>
                                            <tr>
                                                <td>E-Mail:</td>
                                                <td><input name="email" type="text" id="email" <%--value="Alte email des users aus der DB"--%> size="40" width="100"></td>
                                            </tr>  
                                            <tr height="20"></tr>
                                            <tr>
                                                <td colspan="2">Passwort &auml;ndern</td>
                                            </tr>  
                                            <tr>
                                                <td>Alt:</td>
                                                <td><input name="passold" type="text" id="passold" value="" size="20" width="100"></td>
                                            </tr>  
                                            <tr>
                                                <td>Neu:</td>
                                                <td><input name="passnew" type="text" id="passnew" value="" size="20" width="100"></td>
                                            </tr>  
                                            <tr>
                                                <td>Best&auml;tigen:</td>
                                                <td><input name="passnew" type="text" id="passnew" value="" size="20" width="100"></td>
                                            </tr>  
                                            <tr height="20"></tr>  
                                            <tr>
                                                <td ><a href="Main.htm"><input name="cancel" type="submit" value="Abbrechen"></a></td>
                                                <td><input name="save" type="submit" value="Speichern"></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div></td>
                                <td id="rightcolumn" valign="top"><div class="box logout_box">
                                    <!-- commented out temporary
                                    <div class="box-title">Benutzer: "User"</div>
                                    <br><center><a href="Index.htm">Logout</a><br>
                                    <br><img src="${pageContext.request.contextPath}img/logo_kl.jpg"></center>
                                    -->
                                </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="das-bot">
                    <div id="das-power">DAS - Di&auml;t Assistent Software </div>
                </div>
            </div>
        </div>
        <%-- DEBUG --%>
        <c:choose>
            <c:when test="${empty cookie}" >
                <h2>We did not find any cookies in the request</h2>
            </c:when>
            <c:otherwise>

                <h2>The name and value of each found cookie</h2>

                <c:forEach var="cookieVal" items="${cookie}">
                    <strong>Cookie name:</strong> <c:out value="${cookieVal.key}" /><br>
                    <strong>Cookie value:</strong> <c:out value=
                    "${cookieVal.value.value}" /><br><br>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <%-- DEBUG --%>
    </body>
</html>
