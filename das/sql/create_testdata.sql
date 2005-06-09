
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

insert into zutat values(1, 'Mehl', 'g', 1.5, 0.1, 0.2, 1);
insert into zutat values(2, 'Marmelade', 'g', 2, 0.2, 0.4, 1);