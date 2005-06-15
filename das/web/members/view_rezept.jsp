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
        <title>DAS - Rezept: ${ctrl.fields.name}</title>
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
                    <h4>${ctrl.fields.name}</h4>
		
                    <%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
                    <das:errorlist controller="${ctrl}"/>
                      
                    <table width="600" border="0">
                        <tr valign="top"> 
                            <td>Zutaten:</td>
                            <td>4 Stk Eier</td>   
                        </tr>
                        <tr> 
                            <td></td>
                            <td>300 g Mehl</td>   
                        </tr>
                        <tr> 
                            <td></td>
                            <td>100 ml Milch</td>   
                        </tr>
                        <tr> 
                            <td></td>
                            <td>100 g Zucker</td>   
                        </tr>
                        <tr height="20"></tr>
                        <tr valign="top"> 
                            <td>Anleitung:</td>
                            <td>Alle Zutaten mischen und 40 min backen bei 230 Grad.</td>   
                        </tr>
                        <tr height="20"></tr>
                        <tr> 
                            <td>Kalorien:</td>
                            <td>777</td>   
                        </tr>
                        <tr> 
                            <td>Fettgehalt:</td>
                            <td>500</td>   
                        </tr>
                        <tr> 
                            <td>Allergien:</td>
                            <td>Zucker</td>   
                        </tr>
                        <tr height="20"></tr>
                        <tr> 
                            <td>Eigene Bewertung:</td>
                            <td>2</td>   
                        </tr>
                        <tr> 
                            <td>Durchschn. Bewertung:</td>
                            <td>3</td>   
                        </tr>
                        <tr height="20"></tr>
                        <tr> 
                            <td>Bewerten:</td>
                            <td><input name="1" type="radio"> 1  <input name="2" type="radio"> 2  <input name="3" type="radio"> 3  
                            <input name="4" type="radio"> 4  <input name="5" type="radio"> 5</td>   
                        </tr>
                        <tr> 
                            <td></td>
                            <td><input name="save" type="submit" value="Speichern"></td>   
                        </tr>
                    </table>      
                                        
                              
                                        
                                        
                </td>
            </tr>
        </tbody>
            </table>
        </div>
    </body>
</html>    
