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
            <td  id="leftcolumn" valign="top"><div class="box mnu_application_menu">
                <div class="box-title">
                  <table class="box-title">
                    <tbody>
                      <tr>
                        <td><span class="box-titletext">Men&uuml;</span></td>
                        <td width="11">&nbsp;</td>
                      </tr>
                    </tbody>
                  </table>
                </div>

                <div id="mod-mnu_application_menu" style="display: block;" class="box-data">
                  <div id="mnu_application_menu" style="display: block;">
                    <div class="option">&nbsp;<a href="Main.htm" class="linkmenu">Home</a></div>
                    <div class="option">&nbsp;<a href="Nahrungsmittel.htm" class="linkmenu">Nahrungsmittel</a>
                    </div>

                    <div class="separator"><b>Rezepte</b>
                      <div style="display: block;" id="menu1__1">
                        <div class="option">&nbsp;<a href="RezepteAnzeigen.htm" class="linkmenu">anzeigen</a></div>
                        <div class="option">&nbsp;<a href="RezeptErstellen.htm" class="linkmenu">erstellen</a></div>
                      </div>
                    </div>

                    <div class="separator"><b>Bearbeiten</b>
                      <div style="display: block;" id="menu1__1">
                        <div class="option">&nbsp;<a href="NahrungsmittelBearbeiten.htm" class="linkmenu">Nahrungsmittel</a></div>
                        <div class="option">&nbsp;<a href="RezepteBearbeiten.htm" class="linkmenu">Rezepte</a></div>
                        <div class="option">&nbsp;<a href="AllergienBearbeiten.htm" class="linkmenu">Allergien</a></div>
                      </div>
                    </div>

                    <div class="separator"><b>Verwalten</b>
                      <div style="display: block;" id="menu1__1">
                        <div class="option">&nbsp;<a href="MeinProfil.htm" class="linkmenu">Mein Profil</a></div>
                        <div class="option">&nbsp;<a href="Benutzergruppen.htm" class="linkmenu">Benutzergruppen</a></div>
                      </div>
                    </div>

                  </div>
                </div>
              </div></td>
            <td id="centercolumn" valign="top"><div id="das-center">


<h4> Willkommen, 
<%= session.getAttribute("user") %>
!</h4><br>

<table width="600">
  <tr>
    <td><img src="img/Nahr.jpg"></td>
    <td><img src="img/RezA.jpg"></td>
    <td><img src="img/RezE.jpg"></td>
  </tr>
  <tr>
    <td><a href="Nahrungsmittel.htm">Nahrungsmittel</a></td>
    <td><a href="RezepteAnzeigen.htm">Rezepte anzeigen</a></td>
    <td><a href="RezeptErstellen.htm">Rezept erstellen</td>
  </tr>
  <tr height="20"></tr>           
  <tr>
    <td><img src="img/NahrB.jpg"></td>
    <td><img src="img/RezB.jpg"></td>
    <td><img src="img/AllB.jpg"></td>
  </tr>
  <tr>
    <td><a href="NahrungsmittelBearbeiten.htm">Nahrungsmittel bearbeiten</a></td>
    <td><a href="RezepteBearbeiten.htm">Rezepte bearbeiten</a></td>
    <td><a href="AllergienBearbeiten.htm">Allergien bearbeiten</a></td>
  </tr>          
  <tr height="20"></tr> 
  <tr>
    <td><img src="img/Prof.jpg"></td>
    <td><img src="img/Ben.jpg"></td>
  </tr>
  <tr>
    <td><a href="MeinProfil.htm">Mein Profil</a></td>
    <td><a href="Benutzergruppen.htm">Benutzergruppen</a></td>
  </tr>  
</table>




	    </div></td>
            <td id="rightcolumn" valign="top"><div class="box logout_box">
                <div class="box-title"><a href="Index.htm">Logout</a></div>
                <img src="img/logo_kl.jpg" hspace="30">
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
