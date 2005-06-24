<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="das" %>

<%@ page import='das.bl.model.Rezept' %>
<%@ page import='java.util.List' %>
<%@ page import='java.util.Iterator' %>

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
                        <h4>Rezepte suchen</h4>

                        <%-- Fehlerliste anzeigen falls fehler aufgetreten sind --%>
                        <das:errorlist controller="${ctrl}"/>
                        <p/>&nbsp;
                        <form action="find_rezept.jsp?cmd=find" method="POST">

                            <table width="400" border="0">
                              <!--  <tr> 
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
                                <tr height="5"></tr> -->
                                <tr> 
                                    <td>Stichwort</td>
                                    <td><input name="nameExpr" type="text" width="300" value="${ctrl.fields.nameExpr}" maxlength="200" size="25"></td>
                                </tr>
                                <tr height="5"></tr>
                                <tr> 
                                    <td></td>
                                    <td><input type="submit" value="Suchen"></td>
                                </tr>
                            </table>

                        </form>
                            <p />&nbsp;
                            <!-- eine auflistung von allen Rezepten -->
                            <h4>Rezept ausw&auml;hlen</h4>
		
                        ${ctrl.message}
                            <table cellpadding="0" cellspacing="2">
                                <%
                                List rlist = ctrl.getResults();
                                for (Iterator iter = rlist.iterator(); iter.hasNext();) {
                                    Rezept r = (Rezept) iter.next();
                                    Long id = r.getId();
                                    String name = r.getName();
                                    Float avgrating = r.getAvgRating();
                                    

                                %>
                                <tr>
                                    <td width="300"><a href="show_rezept.jsp?id=<%=id%>"><%=name%></a> (<%=String.valueOf(avgrating)%>)</td>
                                    <%
                                    if(ctrl.isEditor() || ctrl.isAutor(id)){
                                    %>
                                    <td>
                                        <a href="edit_rezept.jsp?id=<%=id%>"><img src="../img/edit.gif" alt="editieren" border="0"></a>
                                    </td>                                            
                                    <td>
                                        <a href="javascript:submitForm('edit_rezept.jsp?cmd=delete&id=<% out.print(id); %>', 'Sicher?')"><img border="0" src="../img/delete.gif" alt="l&ouml;schen"></a>
                                    </td>                  
                                    <%}
                                    %>
                                </tr>
                                <%
                                }%>
                            </table>    


                            
		
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>    
