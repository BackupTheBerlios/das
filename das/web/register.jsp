<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>DAS</title>
        <link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/dasneat.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dasutil.js">
        </script>
    </head>
    <body>
        <div id="das-main">
            <div id="das-top">DAS - GUI Prototyp v 0.3
                <div id="das-mid">
                    <table border="0" cellpadding="0" cellspacing="0" id="das-midtbl">
                        <tbody>
                            <tr>
                                <td  id="leftcolumn" valign="top"><div class="box info">
                                    <div class="box-title">Info</div>
                                    <div id="mod-login_box" style="display: block" class="box-data">
                                        Willkommen zu DAS - der Di&auml;t Assistent Software.<br><br>
                                        Bitte melden Sie sich rechts mit Ihrem
                                        Benutzernamen an, oder klicken Sie auf Registrieren um einen neuen Benutzer anzulegen.
                                    </div>
                                </div></td>
                                <td id="centercolumn" valign="top"><div id="das-center">
                                    <h4>Registrieren</h4>
                                    <br>
                                    <form action="register.jsp" method="post" name="" id="">
                                        <table width="400" border="0">
                                            <tr>
                                                <td>Benutzername:</td>
                                                <td><input name="username" type="text" id="username" value="" size="40" width="100"></td>
                                            </tr>  
                                            <tr>
                                                <td>E-Mail:</td>
                                                <td><input name="email" type="text" id="email" value="" size="40" width="100"></td>
                                            </tr>  
                                            <tr height="20"></tr>
                                            <tr>
                                                <td>Passwort:</td>
                                                <td><input name="pass1" type="text" id="pass1" value="" size="20" width="100"></td>
                                            </tr>  
                                            <tr>
                                                <td>Nochmal:</td>
                                                <td><input name="pass2" type="text" id="pass2" value="" size="20" width="100"></td>
                                            </tr>  
                                            <tr height="20"></tr>  
                                            <tr> 
                                                <td valign="top">Allergien:</td>
                                                <td><input name="" type="checkbox">Zucker<br>
                                                    <input name="" type="checkbox">Alkohol<br>
                                                    <input name="" type="checkbox">...<br>
                                                </td>
                                            </tr>
                                            <tr height="20"></tr>  
                                            <tr>
                                                <td ><input name="cancel" type="button" value="Abbrechen" onClick="gotourl('index.jsp')")>                  </td>
                                                <td><input name="register" type="submit" value="Registrieren"></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div></td>
                                <td id="rightcolumn" valign="top">&nbsp;</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="das-bot">
                    <div id="das-power">DAS - Di&auml;t Assistent Software </div>
                </div>
            </div>
        </div>
    </body>
</html>
