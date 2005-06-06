<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>DAS</title>
<link type="text/css" href="css/dasneat.css" rel="stylesheet">
</head>
<body>
<div id="das-main">
  <div id="das-top">DAS - GUI Prototyp v 0.2
    <div id="das-mid">
      <table border="0" cellpadding="0" cellspacing="0" id="das-midtbl">
        <tbody>
          <tr>
            <td  id="leftcolumn" valign="top"><div class="box info">
                <div class="box-title">Info</div>
                Ein Fehler ist aufgetretten.
              </div></td>
            <td id="centercolumn" valign="top"><div id="das-center">
              
			<!-- Fehlermeldung -->
			<h2>Fehler:</h2>
			<font color="Red"><b>
			<%= request.getParameter("error") %>
			</b></font>

            </div></td>
            <td id="rightcolumn" valign="top"><div class="box login_box">
                <div class="box-title">Login</div>
                <div id="mod-login_box" style="display: block" class="box-data">
                  <form name="loginbox" action="user" method="post" >
                    <table border="0">
                      <tr>
                        <td><table>
                            <tr>
                              <td class="module"><label for="login-user">Benutzer:</label></td>
                            </tr>
                            <tr>
                              <td><input type="text" name="user" id="login-user" size="20" /></td>
                            </tr>
                            <tr>
                              <td class="module"><label for="login-pass">Passwort:</label></td>
                            </tr>
                            <tr>
                              <td><input type="password" name="pass" id="login-pass" size="20" /></td>
                            </tr>
                            <tr>
                              <td><a href="Main.htm"><input type="submit" name="login" value="login" /></a></td>
                            </tr>
                            <tr>
                              <td  class="module" valign="bottom">[ <a class="linkmodule" href="das-register.jsp" title="Click here to register">Registrieren</a> | <a class="linkmodule" href="das-remind_password.jsp" title="Click here if you've forgotten your password">Passwort vergessen?</a> ]</td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                  </form>
                </div>
              </div></td>
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
