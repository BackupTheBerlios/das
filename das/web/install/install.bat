@echo off
REM Installer for D.A.S - Diät Assistent Software
REM Copyright (C) 2005 Arash Zargamy e0126071 [AT] student.tuwien.ac.at
REM 
REM This program is free software; you can redistribute it and/or modify
REM it under the terms of the GNU General Public License as published by
REM the Free Software Foundation; either version 2 of the License, or
REM (at your option) any later version.
REM 
REM This program is distributed in the hope that it will be useful,
REM but WITHOUT ANY WARRANTY; without even the implied warranty of
REM MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
REM GNU General Public License for more details.
REM 
REM You should have received a copy of the GNU General Public License
REM along with this program; if not, write to the Free Software
REM Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

rem -- init vars --
set createdb="createdb.sql"
set createuser="createuser.sql"
set psql="C:\Programme\PostgreSQL\8.0\bin\psql.exe"
rem -- init vars --

if not exist %createdb% (
echo fehler datei %createdb% nicht vorhanden
echo programm kann nicht fortgesetzt werden
goto ende
)

if not exist %psql% (
echo %psql% wurde nicht gefunden.
echo bitte editieren sie die datei %0
echo suchen sie nach der zeile set psql=
echo und ersetzen sie den wert %psql% mit dem 
echo vollen pfad zu psql.exe
goto ende
)

if %1.==. goto fehler
if %1==-v goto version
if %2.==. goto fehler

goto go

:version
echo D.A.S - Diaet Assistent Software
echo Installationsskript fuer Datenbank
echo Version: 1.0
echo Autor: Arash Zargamy
goto ende

:usage
echo dieses script erstellt fuer sie die noetige datenbankstruktur fuer D.A.S.

:usage1
echo benutzung: %0 [username] [userpassword]
echo setzen sie statt [username] und [userpassword] den gewuenschten usernamen und das gewuenschte password ein, mit der die datenbank erstellt werden soll. 
goto ende

:fehler
echo fehler! parameter fehlen, bitte ueberpruefen sie ihre eingaben
echo.
goto usage1

:go
echo CREATE USER %1 > %createuser%
echo ENCRYPTED PASSWORD '%2' >> %createuser%
echo NOCREATEDB NOCREATEUSER; >> %createuser%
echo "bitte geben sie nun das password fuer den datenbankuser postgres ein"
%psql% -f %createuser% -h localhost -p 5432 template1 "postgres"
echo .
echo "die datenbankstruktur wird nun erstellt"
echo "bitte geben sie nun das password fuer den datenbankuser %1 ein."
%psql% -f %createdb% -h localhost -p 5432 template1 %1
echo .
echo "gratulation, ihre datenbank fuer DAS wurde eingerichtet"
echo "username: " %1
echo "password: " %2
goto ende

:ende
rem -- clear vars --
set createdb=
set createuser=
set psql=
rem -- clear vars --
@echo on