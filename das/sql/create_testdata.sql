
insert into gruppe values ('31', 'Administrator');
insert into gruppe values ('32', 'Editor');
insert into gruppe values ('33', 'Benutzer');

insert into benutzer values(10, 'User A', 'a', 'a', 'a@das.com', 31);
insert into benutzer values(11, 'User B', 'b', 'b', 'b@das.com', 32);
insert into benutzer values(12, 'User C', 'c', 'c', 'c@das.com', 33);

/*
insert into benutzergruppe values('a', 'admins');
insert into benutzergruppe values('b', 'editors');
insert into benutzergruppe values('c', 'users');
*/

insert into kategorie values (1, 'Speisen');
insert into kategorie values (2, 'Getränke');

insert into zutat values(1, 'Mehl', 'g', 1.5, 10, 20, 1);
insert into zutat values(2, 'Marmelade', 'g', 2, 30, 40, 1);

insert into allergie values(1, 'Nussallergie');
insert into allergie values(2, 'Eiweissallergie');

insert into zut2all values(1, 1);
insert into zut2all values(1, 2);

insert into rezept values (1, 'Omas Torte', 'Hier gehoert die Anleitung her', 12);
insert into zut2rez values (1, 1, 1, 100);
insert into zut2rez values (2, 2, 1, 300);
