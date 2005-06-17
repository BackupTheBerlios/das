<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%-- Controller instantiieren und pageContext setzen --%>
<jsp:useBean id="ctrl" class="das.ui.ctrl.EditRezeptCtrl">
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
        <title>DAS - Rezept bearbeiten</title>
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
                                <h4>Rezept bearbeiten</h4>
		
                                <%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
                                <das:errorlist controller="${ctrl}"/>
                                <form action="edit_rezept.jsp?cmd=save" method="POST">
                                    
                                    <table width="500" border="0">
                                        <tr>
                                            <td>ID</td>
                                            <td>${ctrl.fields.id}
                                                <input name="id" type="hidden" value="${ctrl.fields.id}"/>
                                            </td>
                                        </tr>
                                        <tr> 
                                            <td>Name:</td>
                                            <td><input name="name" type="text" width="100" value="${ctrl.fields.name}" size="40">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top">
                                                Anleitung:
                                            </td>
                                            <td>
                                                <textarea name="anleitung" cols="40" rows="5">${ctrl.fields.anleitung}</textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td>
                                                <hr>
                                            </td>
                                        </tr>
                                               
                                        
                                        <tr>
                                            <td>
                                                <table>
                                                    <c:forEach items="${ctrl.zutaten}" var="z">
                                                        <tr>
                                                            <td>
                                                                <b>${z.name}</b>
                                                            </td>
                                                            <td>
                                                                <input name="${z.id}" type="text" width="10" value="0" maxlength="10">
                                                            </td>
                                                            <td>
                                                            <c:out value="${einheit[z.id]}" />

                                                            </td>
                                                           
                                                        </tr>
                                                    </c:forEach>
                                        
                                                </table>
                                            </td>
                                        </tr>
                                        
                                        
                                        
                                        

                                
                                        <tr>
                                            <td>
                                            </td>
                                            <td><hr>
                                                <input name="ok" type="submit" value="Rezept speichern">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>    
