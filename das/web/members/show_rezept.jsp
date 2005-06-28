<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="das" %>
<%@ page import='java.util.*' %>
<%@ page import='das.bl.model.Zutat' %>
<%@ page import='das.bl.service.ZutatenService' %>


<%-- @autor: Kirill --%><%-- @autor: Arash Zargamy --%>

<jsp:useBean id="ctrl" class="das.ui.ctrl.ShowRezeptCtrl">
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
                    
            <table>
                <tr>
                    <td align=left>
                        
                    
                        <h4>${ctrl.fields.name}</h4>
		
                        <%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
                        <das:errorlist controller="${ctrl}"/>
                      
                        <table width="400" border="0">
                            <tr valign="top"> 
                                <td>Zutaten:</td>
                                <td></td>   
                            </tr>                       
                            <%
                            Map zutaten = ctrl.getZutaten();
                            Set set = zutaten.keySet();
                            Iterator iter = set.iterator();
                            ZutatenService zs = new ZutatenService(request.getRemoteUser());
                            Zutat z;
                            
                            while(iter.hasNext()){
                                Long id = (Long)iter.next();
                                Long wert = (Long)zutaten.get(id);
                                
                                z = zs.loadZutat(id);
                                String name = z.getName();
                                String einheit = z.getEinheit();
                            %><tr> 
                                <td></td>
                                <td><%=wert%> <%=einheit%> <%=name%></td>   
                            </tr>     <%}%>
                        
                            <tr height="20"></tr>
                            <tr valign="top"> 
                                <td>Anleitung:</td>
                                <td><textarea name="" cols="" rows="25" readonly="readonly" >${ctrl.fields.anleitung}</textarea></td>   
                            </tr>
                            <tr height="20"></tr>
                            <tr> 
                                <td>Kalorien:</td>
                                <td><%= ctrl.sumKalorien() %></td>   
                            </tr>
                            <tr> 
                                <td>Fett:</td>
                                <td><%= ctrl.sumFett() %> g</td>   
                            </tr>
                            <tr> 
                                <td>Zucker:</td>
                                <td><%= ctrl.sumZucker() %> g</td>   
                            </tr>
                            <tr height="20"></tr>
                            <tr> 
                                <form action="show_rezept.jsp?cmd=bewerten" method="POST">
                                <input name="id" type="hidden" value="${ctrl.fields.id}"/>
                                <td valign="middle"></td>
                                <td valign="middle"><img src="../img/thumbdown.gif" border="0">   <input name="bew" type="radio" value="1"> 1  <input name="bew" type="radio" value="2"> 2  <input name="bew" type="radio" CHECKED  value="3"> 3  
                                <input name="bew" type="radio"  value="4"> 4  <input name="bew" type="radio" value="5"> 5   <img src="../img/thumbup.gif" border="0"></td>   
                            </tr>
                                <tr> 
                                    <td>&empty; Bewertung:</td>
                                    <td><%= ctrl.rezept.getAvgRating() %> </td>  
                                </tr>
                                <tr> 
                                    <td></td>
                                    <td><input name="save" type="submit" value="Bewerten"></td>  
                                </tr>
                            </form>
                        </table>            
                      
                            
                    </td>
                    <td align="left">
                        <%
                        if(ctrl.isAllergic()){
                        %>
                        <div align="center">
                            <img src="../img/achtung.gif" alt="Allergisch!"><p />
                          <h3>Rezept enth&auml;lt Zutaten,<br>
                            auf die Sie m&ouml;gichlerweise allergisch sind.</h3>
                        </div>
                        <% }  %>
                    </td>
                </tr>
            </table><p />
                    <a href="find_rezept.jsp">Zur&uuml;ck zur Auswahl</a>
        </div>
    </body>
</html>    
