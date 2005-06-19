
insert into gruppe values ('admins', 'Administratoren');
insert into gruppe values ('editors', 'Editoren');
insert into gruppe values ('users', 'Benutzer');

insert into benutzer values('a', 'User A', 'a', 'a@das.com');
insert into benutzer values('b', 'User B', 'b', 'b@das.com');
insert into benutzer values('c', 'User C', 'c', 'c@das.com');

insert into benutzergruppe values('a', 'admins');
insert into benutzergruppe values('b', 'editors');
insert into benutzergruppe values('c', 'users');

insert into kategorie values (1, 'Speisen');
insert into kategorie values (2, 'Getränke');

insert into zutat values(1, 'Mehl', 'g', 1.5, 10, 20, 1);
insert into zutat values(2, 'Marmelade', 'g', 2, 30, 40, 1);

insert into allergie values(1, 'Nussallergie');
insert into allergie values(2, 'Eiweissallergie');

insert into zut2all values(1, 1);
insert into zut2all values(1, 2);

insert into rezept values (1, 'Omas Torte', 'Hier gehoert die Anleitung', 'c');
insert into zut2rez values (1, 1, 100);
insert into zut2rez values (2, 1, 300);
