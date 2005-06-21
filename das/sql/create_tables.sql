drop table bewertung;
drop table bzr2all;
drop table zut2all;
drop table allergie;
drop table zut2rez;
drop table rezept;
drop table zutat;
drop table kategorie;
drop view benutzergruppe;
drop table benutzer;
drop table gruppe;
drop sequence seq;


create sequence seq
    start with 1000;

-- kuerzel: gru
create table gruppe (
   id varchar(50) primary key,
   name varchar(100) not null unique
);

-- kuerzel: bzr
create table benutzer (
   id int primary key,
   name varchar(100) not null unique,	
   login varchar(50) not null unique,
   passwort varchar(50) not null,
   email varchar(50),
   gru_id int not null references gruppe
);

create index i_bzr_gru_id on benutzer(gru_id);

create view benutzergruppe(login, gruppe) as
   select benutzer.login, gruppe.name 
   from benutzer join gruppe on benutzer.gru_id = gruppe.id;
   
/*
-- kuerzel: bg
create table benutzergruppe (
   login varchar(50) not null references benutzer on delete cascade,
   gru_id varchar(50) not null references gruppe on delete cascade,
   primary key (login, gru_id)
);

create index i_bg_gru_id on benutzergruppe(gru_id);
*/

-- kuerzel: kat
create table kategorie (
   id int primary key,
   name varchar(100) unique
);

-- kuerzel: zut
create table zutat (
   id int primary key,
   name varchar(100) not null unique,
   einheit varchar(20) not null,
   kalorien real not null,
   fett smallint,
   zucker smallint,
   kat_id int not null references kategorie
);

create index i_zut_einheit on zutat(einheit);
create index i_zut_kalorien on zutat(kalorien);
create index i_zut_kat_id on zutat(kat_id);

-- kuerzel: rez
create table rezept (
   id int primary key,
   name varchar(100) not null unique,
   anleitung text,
   bzr_id int references benutzer on delete set null
);

-- kuerzel: zr
create table zut2rez (
   id int primary key,
   zut_id int not null references zutat,
   rez_id int not null references rezept on delete cascade,
   menge real
);

create index i_zr_zut_id on zut2rez(zut_id);
create index i_zr_rez_id on zut2rez(rez_id);

-- kuerzel: all
create table allergie (
   id int primary key,
   name varchar(100) not null unique
);

-- kuerzel: za
create table zut2all (
   zut_id int not null references zutat on delete cascade,
   all_id int not null references allergie on delete cascade,
   primary key(zut_id, all_id)
);

create index i_za_all_id on zut2all(all_id);

-- kuerzel: ba
create table bzr2all (
   bzr_id int not null references benutzer on delete cascade,
   all_id int not null references allergie on delete cascade,
   primary key(bzr_id, all_id)
);

create index i_ba_bzr_id on bzr2all(bzr_id);
create index i_ba_all_id on bzr2all(all_id);

-- kuerzel: bew
create table bewertung (
   bzr_id int not null references benutzer on delete cascade,
   rez_id int not null references benutzer on delete cascade,
   rating float not null,
   primary key(bzr_id, rez_id)
);

create index i_bew_bzr_id on bewertung(bzr_id);
create index i_bew_rating on bewertung(rating);
