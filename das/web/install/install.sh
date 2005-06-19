# Installer for D.A.S - Diät Assistent Software
# Copyright (C) 2005 Arash Zargamy e0126071 [AT] student.tuwien.ac.at
# 
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA


#  variablen initialisierung  #
createdb="createdb.sql" # file enthaelt dbstruktur
createuser="createuser.sql" # file erstellt user fuer db. das file wird von diesem script erstellt
#  variablen initialisierung  #

if [ "$1" = "-v" ]; then
cat << EOF 
D.A.S - Diät Assistent Software
Installationsskript für Datenbank
Version: 1.0
Autor: Arash Zargamy
EOF
exit
fi

if [ -e "$createdb" ]; then
# do nothing
else
echo 'ERROR:' file "$createdb" not found
echo datenbankstruktur kann nicht erstellt werden.
exit 1
fi

if [ -z "$1" ]; then
cat << EOF
dieses script erstellt für sie die nötige datenbankstruktur für D.A.S.

benutzung: $0 [username] [userpassword]
setzen sie statt [username] und [userpassword] den gewünschten usernamen und das gewünschte password ein, mit der die datenbank erstellt werden soll. 

EOF
exit 1 
fi

if [ $# -ne 2 ]; then 
cat << EOF 
falsche eingabe! 

benutzung: $0 [username] [userpassword]
setzen sie statt [username] und [userpassword] den gewünschten usernamen und das gewünschte password ein, mit der die datenbank erstellt werden soll. 

EOF
exit 1
fi

echo "CREATE USER $1" > $createuser
echo "ENCRYPTED PASSWORD '$2'" >> $createuser
echo "NOCREATEDB NOCREATEUSER;" >> $createuser
echo "bitte geben sie das password für den datenbankuser postgres ein"
psql -f $createuser  -h localhost -p 5432 template1 "postgres"
echo
echo "die datenbankstruktur wird nun erstellt."
echo "bitte geben sie jetzt das datenbankpassword für den user $1 ein."
psql -f $createdb  -h localhost -p 5432 template1 "$1"
echo \n "gratulation, ihre datenbank für DAS wurde nun eingerichtet"
echo "username:" $1 
echo "password:" $2

