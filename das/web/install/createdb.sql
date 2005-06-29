--
-- PostgreSQL database dump
--

-- Started on 2005-06-29 12:30:29 �������� ������ (����)

SET client_encoding = 'UNICODE';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1581 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

--
-- TOC entry 17 (class 1255 OID 17230)
-- Dependencies: 4
-- Name: plpgsql_call_handler(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION plpgsql_call_handler() RETURNS language_handler
    AS '$libdir/plpgsql', 'plpgsql_call_handler'
    LANGUAGE c;


ALTER FUNCTION public.plpgsql_call_handler() OWNER TO postgres;

--
-- TOC entry 18 (class 1255 OID 17231)
-- Dependencies: 4
-- Name: plpgsql_validator(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION plpgsql_validator(oid) RETURNS void
    AS '$libdir/plpgsql', 'plpgsql_validator'
    LANGUAGE c;


ALTER FUNCTION public.plpgsql_validator(oid) OWNER TO postgres;

--
-- TOC entry 274 (class 16402 OID 17232)
-- Dependencies: 18 17
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: public; Owner: 
--

CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql HANDLER plpgsql_call_handler VALIDATOR plpgsql_validator;


--
-- TOC entry 19 (class 1255 OID 17233)
-- Dependencies: 4
-- Name: database_size(name); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION database_size(name) RETURNS bigint
    AS '$libdir/dbsize', 'database_size'
    LANGUAGE c STRICT;


ALTER FUNCTION public.database_size(name) OWNER TO postgres;

--
-- TOC entry 22 (class 1255 OID 17236)
-- Dependencies: 4
-- Name: pg_database_size(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_database_size(oid) RETURNS bigint
    AS '$libdir/dbsize', 'pg_database_size'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_database_size(oid) OWNER TO postgres;

--
-- TOC entry 32 (class 1255 OID 17246)
-- Dependencies: 4
-- Name: pg_dir_ls(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_dir_ls(text, boolean) RETURNS SETOF text
    AS '$libdir/admin', 'pg_dir_ls'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_dir_ls(text, boolean) OWNER TO postgres;

--
-- TOC entry 26 (class 1255 OID 17240)
-- Dependencies: 4
-- Name: pg_file_length(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_length(text) RETURNS bigint
    AS $_$SELECT len FROM pg_file_stat($1) AS s(len int8, c timestamp, a timestamp, m timestamp, i bool)$_$
    LANGUAGE sql STRICT;


ALTER FUNCTION public.pg_file_length(text) OWNER TO postgres;

--
-- TOC entry 27 (class 1255 OID 17241)
-- Dependencies: 4
-- Name: pg_file_read(text, bigint, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_read(text, bigint, bigint) RETURNS text
    AS '$libdir/admin', 'pg_file_read'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_file_read(text, bigint, bigint) OWNER TO postgres;

--
-- TOC entry 29 (class 1255 OID 17243)
-- Dependencies: 4
-- Name: pg_file_rename(text, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_rename(text, text, text) RETURNS boolean
    AS '$libdir/admin', 'pg_file_rename'
    LANGUAGE c;


ALTER FUNCTION public.pg_file_rename(text, text, text) OWNER TO postgres;

--
-- TOC entry 31 (class 1255 OID 17245)
-- Dependencies: 4
-- Name: pg_file_rename(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_rename(text, text) RETURNS boolean
    AS $_$SELECT pg_file_rename($1, $2, NULL); $_$
    LANGUAGE sql STRICT;


ALTER FUNCTION public.pg_file_rename(text, text) OWNER TO postgres;

--
-- TOC entry 25 (class 1255 OID 17239)
-- Dependencies: 4
-- Name: pg_file_stat(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_stat(text) RETURNS record
    AS '$libdir/admin', 'pg_file_stat'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_file_stat(text) OWNER TO postgres;

--
-- TOC entry 30 (class 1255 OID 17244)
-- Dependencies: 4
-- Name: pg_file_unlink(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_unlink(text) RETURNS boolean
    AS '$libdir/admin', 'pg_file_unlink'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_file_unlink(text) OWNER TO postgres;

--
-- TOC entry 28 (class 1255 OID 17242)
-- Dependencies: 4
-- Name: pg_file_write(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_file_write(text, text, boolean) RETURNS bigint
    AS '$libdir/admin', 'pg_file_write'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_file_write(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 35 (class 1255 OID 17249)
-- Dependencies: 4
-- Name: pg_logdir_ls(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_logdir_ls() RETURNS SETOF record
    AS '$libdir/admin', 'pg_logdir_ls'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_logdir_ls() OWNER TO postgres;

--
-- TOC entry 34 (class 1255 OID 17248)
-- Dependencies: 4
-- Name: pg_postmaster_starttime(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_postmaster_starttime() RETURNS timestamp without time zone
    AS '$libdir/admin', 'pg_postmaster_starttime'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_postmaster_starttime() OWNER TO postgres;

--
-- TOC entry 23 (class 1255 OID 17237)
-- Dependencies: 4
-- Name: pg_relation_size(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_relation_size(oid) RETURNS bigint
    AS '$libdir/dbsize', 'pg_relation_size'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_relation_size(oid) OWNER TO postgres;

--
-- TOC entry 33 (class 1255 OID 17247)
-- Dependencies: 4
-- Name: pg_reload_conf(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_reload_conf() RETURNS integer
    AS '$libdir/admin', 'pg_reload_conf'
    LANGUAGE c STABLE STRICT;


ALTER FUNCTION public.pg_reload_conf() OWNER TO postgres;

--
-- TOC entry 24 (class 1255 OID 17238)
-- Dependencies: 4
-- Name: pg_size_pretty(bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_size_pretty(bigint) RETURNS text
    AS '$libdir/dbsize', 'pg_size_pretty'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_size_pretty(bigint) OWNER TO postgres;

--
-- TOC entry 21 (class 1255 OID 17235)
-- Dependencies: 4
-- Name: pg_tablespace_size(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pg_tablespace_size(oid) RETURNS bigint
    AS '$libdir/dbsize', 'pg_tablespace_size'
    LANGUAGE c STRICT;


ALTER FUNCTION public.pg_tablespace_size(oid) OWNER TO postgres;

--
-- TOC entry 20 (class 1255 OID 17234)
-- Dependencies: 4
-- Name: relation_size(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION relation_size(text) RETURNS bigint
    AS '$libdir/dbsize', 'relation_size'
    LANGUAGE c STRICT;


ALTER FUNCTION public.relation_size(text) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- TOC entry 1196 (class 1259 OID 25747)
-- Dependencies: 4
-- Name: allergie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE allergie (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.allergie OWNER TO postgres;

--
-- TOC entry 1190 (class 1259 OID 25687)
-- Dependencies: 4
-- Name: benutzer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE benutzer (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    login character varying(50) NOT NULL,
    passwort character varying(50) NOT NULL,
    email character varying(50),
    gru_id integer NOT NULL
);


ALTER TABLE public.benutzer OWNER TO postgres;

--
-- TOC entry 1189 (class 1259 OID 25681)
-- Dependencies: 4
-- Name: gruppe; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gruppe (
    id character varying(50) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.gruppe OWNER TO postgres;

--
-- TOC entry 1191 (class 1259 OID 25698)
-- Dependencies: 1262 4
-- Name: benutzergruppe; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW benutzergruppe AS
    SELECT benutzer.login, gruppe.name AS gruppe FROM (benutzer JOIN gruppe ON (((benutzer.gru_id)::text = (gruppe.id)::text)));


ALTER TABLE public.benutzergruppe OWNER TO postgres;

--
-- TOC entry 1199 (class 1259 OID 25780)
-- Dependencies: 4
-- Name: bewertung; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bewertung (
    login character varying(50) NOT NULL,
    rez_id integer NOT NULL,
    rating double precision NOT NULL
);


ALTER TABLE public.bewertung OWNER TO postgres;

--
-- TOC entry 1198 (class 1259 OID 25766)
-- Dependencies: 4
-- Name: bzr2all; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bzr2all (
    bzr_id integer NOT NULL,
    all_id integer NOT NULL
);


ALTER TABLE public.bzr2all OWNER TO postgres;

--
-- TOC entry 1192 (class 1259 OID 25701)
-- Dependencies: 4
-- Name: kategorie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE kategorie (
    id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.kategorie OWNER TO postgres;

--
-- TOC entry 1187 (class 1259 OID 17250)
-- Dependencies: 1261 4
-- Name: pg_logdir_ls; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW pg_logdir_ls AS
    SELECT a.filetime, a.filename FROM pg_logdir_ls() a(filetime timestamp without time zone, filename text);


ALTER TABLE public.pg_logdir_ls OWNER TO postgres;

--
-- TOC entry 1194 (class 1259 OID 25720)
-- Dependencies: 4
-- Name: rezept; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE rezept (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    anleitung text,
    bzr_login character varying(50)
);


ALTER TABLE public.rezept OWNER TO postgres;

--
-- TOC entry 1188 (class 1259 OID 25679)
-- Dependencies: 4
-- Name: seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.seq OWNER TO postgres;

--
-- TOC entry 1583 (class 0 OID 0)
-- Dependencies: 1188
-- Name: seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq', 1027, true);


--
-- TOC entry 1197 (class 1259 OID 25753)
-- Dependencies: 4
-- Name: zut2all; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE zut2all (
    zut_id integer NOT NULL,
    all_id integer NOT NULL
);


ALTER TABLE public.zut2all OWNER TO postgres;

--
-- TOC entry 1195 (class 1259 OID 25733)
-- Dependencies: 4
-- Name: zut2rez; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE zut2rez (
    zut_id integer NOT NULL,
    rez_id integer NOT NULL,
    menge real
);


ALTER TABLE public.zut2rez OWNER TO postgres;

--
-- TOC entry 1193 (class 1259 OID 25707)
-- Dependencies: 4
-- Name: zutat; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE zutat (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    einheit character varying(20) NOT NULL,
    kalorien real NOT NULL,
    fett real,
    zucker real,
    kat_id integer NOT NULL
);


ALTER TABLE public.zutat OWNER TO postgres;

--
-- TOC entry 1575 (class 0 OID 25747)
-- Dependencies: 1196
-- Data for Name: allergie; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO allergie (id, name) VALUES (1, 'Nussallergie');
INSERT INTO allergie (id, name) VALUES (2, 'Eiweissallergie');
INSERT INTO allergie (id, name) VALUES (1025, 'Zölliakie');


--
-- TOC entry 1570 (class 0 OID 25687)
-- Dependencies: 1190
-- Data for Name: benutzer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (11, 'User B', 'b', 'b', 'b@das.com', 32);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (12, 'User C', 'c', 'c', 'c@das.com', 33);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (1000, 'aaa', 'aaa', 'aaa', 'aaa', 33);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (1003, 'stephan', 'stephan', 'stephan', 'stephan.haubenberger@gmx.at', 33);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (10, 'User A', 'a', 'a', 'a@das.com', 31);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (1020, 'test2', 'test34', 'test34', NULL, 33);
INSERT INTO benutzer (id, name, login, passwort, email, gru_id) VALUES (1022, 'Max Mustermann', 'Max', 'Max', 'max@mustermann.at', 33);


--
-- TOC entry 1578 (class 0 OID 25780)
-- Dependencies: 1199
-- Data for Name: bewertung; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO bewertung (login, rez_id, rating) VALUES ('c', 1016, 5);


--
-- TOC entry 1577 (class 0 OID 25766)
-- Dependencies: 1198
-- Data for Name: bzr2all; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO bzr2all (bzr_id, all_id) VALUES (1003, 1);
INSERT INTO bzr2all (bzr_id, all_id) VALUES (1003, 2);
INSERT INTO bzr2all (bzr_id, all_id) VALUES (1022, 1);


--
-- TOC entry 1569 (class 0 OID 25681)
-- Dependencies: 1189
-- Data for Name: gruppe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO gruppe (id, name) VALUES ('31', 'admins');
INSERT INTO gruppe (id, name) VALUES ('32', 'editors');
INSERT INTO gruppe (id, name) VALUES ('33', 'users');


--
-- TOC entry 1571 (class 0 OID 25701)
-- Dependencies: 1192
-- Data for Name: kategorie; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO kategorie (id, name) VALUES (1, 'Speisen');
INSERT INTO kategorie (id, name) VALUES (2, 'Getränke');


--
-- TOC entry 1573 (class 0 OID 25720)
-- Dependencies: 1194
-- Data for Name: rezept; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO rezept (id, name, anleitung, bzr_login) VALUES (1015, 'Nieren - Apfel - Spieße', '500 g  	  Niere(n)
125 g 	Speck, durchwachsen
8  	Zwiebel(n), klein
4  	Äpfel, klein
  	Öl
  	Salz
  	Pfeffer

Nieren gründlich waschen, säubern, abtropfen lassen und in Würfel schneiden. In eine Schüssel geben und den Wein darüber geben, einige Zeit ziehen lassen.

Speck in Scheiben schneiden. Äpfel waschen, vierteln, entkernen und dann in Würfel schneiden. Zwiebeln abziehen und vierteln.

Nierenwürfel, Speck, Apfelstücken und Zwiebelstücken gleichmäßig auf Spieße verteilen. Mit Öl bepinseln und auf dem Rost von jeder Seite etwa 6 Minuten grillen.

Anschließend salzen und pfeffern und sofort servieren. Verschiedene Saucen dazu reichen.', 'a');
INSERT INTO rezept (id, name, anleitung, bzr_login) VALUES (1016, '24 Stunden - Salat', '1   	  Eisbergsalat
2 Gläser 	Sellerie
3 Stange/n 	Lauch
1 Dose/n 	Ananas
10  	Ei(er)
400 g 	Schinken, gekocht
  	Für die Sauce:
500 g 	Sahne
500 g 	Miracel Whip Balance

 Den Eisbergsalat waschen und klein schneiden. Sellerie abtropfen lassen. Lauch in feine Ringe schneiden oder hobeln. Ananas abtropfen lassen und ebenfalls klein schneiden. Eier hart kochen, schälen und mit dem Eierschneider einmal längs und einmal quer durchschneiden. Den Schinken fein würfeln.

Die Zutaten der Reihe nach in eine riesige Salatschüssel (bzw. zwei kleinere Schüsseln) geben. Mayonnaise und Sahne verrühren und darüber geben. Über Nacht in den Kühlschrank stellen.

Abwandlung: Saft der Ananas mit entsprechend weniger Sahne und Miracel Whip verrühren.', 'a');
INSERT INTO rezept (id, name, anleitung, bzr_login) VALUES (1017, 'Aldi - Thunfisch - Salat', '1 kl. Dose  	  Erbsen und Möhren
1 Dose 	Thunfisch (ohne Flüssigkeit)
1 Pck. 	Salat (Fleischsalat)
1 m.-große 	Zwiebel(n)
  	Salz und Pfeffer
  	Knoblauch

Zwiebel klein schneiden. Zutaten mischen und mit Salz und evt. Knoblauch abschmecken.', 'a');
INSERT INTO rezept (id, name, anleitung, bzr_login) VALUES (1018, 'Piadina', 'Mehl mit Backpulver in eine Schüssel sieben. Salz dazugeben und eine Mulde formen. In diese den gehackten Speck geben. Alles miteinander verkneten und nach und nach das lauwarme Wasser zugießen, bis der Teig glatt ist. Den Teig in etwa 10 gleiche Teile teilen und 0,5 cm dick ausrollen. Die Piadinen auf einer heißen Platte oder in einen Tiegel ohne Fettzusatz geben und beidseitig einige Minuten backen.

Die Piadina wird mit Käse (Frischkäse, Schnittkäse), Salami oder auch Schokolade gefüllt. Schmeckt am besten, wenn sie noch warm gegessen wird.', NULL);


--
-- TOC entry 1576 (class 0 OID 25753)
-- Dependencies: 1197
-- Data for Name: zut2all; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO zut2all (zut_id, all_id) VALUES (1, 1);
INSERT INTO zut2all (zut_id, all_id) VALUES (1, 2);


--
-- TOC entry 1574 (class 0 OID 25733)
-- Dependencies: 1195
-- Data for Name: zut2rez; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO zut2rez (zut_id, rez_id, menge) VALUES (1009, 1015, 125);
INSERT INTO zut2rez (zut_id, rez_id, menge) VALUES (1007, 1015, 500);
INSERT INTO zut2rez (zut_id, rez_id, menge) VALUES (1009, 1018, 50);
INSERT INTO zut2rez (zut_id, rez_id, menge) VALUES (1, 1018, 500);


--
-- TOC entry 1572 (class 0 OID 25707)
-- Dependencies: 1193
-- Data for Name: zutat; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1, 'Mehl', 'g', 1.5, 10, 20, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (2, 'Marmelade', 'g', 2, 30, 40, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1010, 'Zwiebel', 'g', 1, NULL, NULL, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1009, 'Speck', 'g', 3, 13, NULL, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1007, 'Niere', 'g', 1.2, 7, NULL, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1014, 'Pfeffer', 'g', 0, 0, 0, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1013, 'Salz', 'g', 0, NULL, NULL, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1012, 'Öl', 'l', 7, 80, NULL, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1011, 'Apfel', 'g', 0.51999998, 0, 7, 1);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1008, 'Wein', 'l', 1.5, 0, 5, 2);
INSERT INTO zutat (id, name, einheit, kalorien, fett, zucker, kat_id) VALUES (1005, 'Zucker', 'g', 10, 0, 100, 1);


--
-- TOC entry 1544 (class 16386 OID 25752)
-- Dependencies: 1196 1196
-- Name: allergie_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY allergie
    ADD CONSTRAINT allergie_name_key UNIQUE (name);


ALTER INDEX public.allergie_name_key OWNER TO postgres;

--
-- TOC entry 1546 (class 16386 OID 25750)
-- Dependencies: 1196 1196
-- Name: allergie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY allergie
    ADD CONSTRAINT allergie_pkey PRIMARY KEY (id);


ALTER INDEX public.allergie_pkey OWNER TO postgres;

--
-- TOC entry 1520 (class 16386 OID 25692)
-- Dependencies: 1190 1190
-- Name: benutzer_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY benutzer
    ADD CONSTRAINT benutzer_login_key UNIQUE (login);


ALTER INDEX public.benutzer_login_key OWNER TO postgres;

--
-- TOC entry 1522 (class 16386 OID 25690)
-- Dependencies: 1190 1190
-- Name: benutzer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY benutzer
    ADD CONSTRAINT benutzer_pkey PRIMARY KEY (id);


ALTER INDEX public.benutzer_pkey OWNER TO postgres;

--
-- TOC entry 1555 (class 16386 OID 25783)
-- Dependencies: 1199 1199 1199
-- Name: bewertung_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bewertung
    ADD CONSTRAINT bewertung_pkey PRIMARY KEY (login, rez_id);


ALTER INDEX public.bewertung_pkey OWNER TO postgres;

--
-- TOC entry 1551 (class 16386 OID 25769)
-- Dependencies: 1198 1198 1198
-- Name: bzr2all_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bzr2all
    ADD CONSTRAINT bzr2all_pkey PRIMARY KEY (bzr_id, all_id);


ALTER INDEX public.bzr2all_pkey OWNER TO postgres;

--
-- TOC entry 1516 (class 16386 OID 25686)
-- Dependencies: 1189 1189
-- Name: gruppe_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gruppe
    ADD CONSTRAINT gruppe_name_key UNIQUE (name);


ALTER INDEX public.gruppe_name_key OWNER TO postgres;

--
-- TOC entry 1518 (class 16386 OID 25684)
-- Dependencies: 1189 1189
-- Name: gruppe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gruppe
    ADD CONSTRAINT gruppe_pkey PRIMARY KEY (id);


ALTER INDEX public.gruppe_pkey OWNER TO postgres;

--
-- TOC entry 1525 (class 16386 OID 25706)
-- Dependencies: 1192 1192
-- Name: kategorie_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY kategorie
    ADD CONSTRAINT kategorie_name_key UNIQUE (name);


ALTER INDEX public.kategorie_name_key OWNER TO postgres;

--
-- TOC entry 1527 (class 16386 OID 25704)
-- Dependencies: 1192 1192
-- Name: kategorie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY kategorie
    ADD CONSTRAINT kategorie_pkey PRIMARY KEY (id);


ALTER INDEX public.kategorie_pkey OWNER TO postgres;

--
-- TOC entry 1536 (class 16386 OID 25728)
-- Dependencies: 1194 1194
-- Name: rezept_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rezept
    ADD CONSTRAINT rezept_name_key UNIQUE (name);


ALTER INDEX public.rezept_name_key OWNER TO postgres;

--
-- TOC entry 1538 (class 16386 OID 25726)
-- Dependencies: 1194 1194
-- Name: rezept_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY rezept
    ADD CONSTRAINT rezept_pkey PRIMARY KEY (id);


ALTER INDEX public.rezept_pkey OWNER TO postgres;

--
-- TOC entry 1549 (class 16386 OID 25756)
-- Dependencies: 1197 1197 1197
-- Name: zut2all_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zut2all
    ADD CONSTRAINT zut2all_pkey PRIMARY KEY (zut_id, all_id);


ALTER INDEX public.zut2all_pkey OWNER TO postgres;

--
-- TOC entry 1542 (class 16386 OID 25736)
-- Dependencies: 1195 1195 1195
-- Name: zut2rez_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zut2rez
    ADD CONSTRAINT zut2rez_pkey PRIMARY KEY (zut_id, rez_id);


ALTER INDEX public.zut2rez_pkey OWNER TO postgres;

--
-- TOC entry 1532 (class 16386 OID 25712)
-- Dependencies: 1193 1193
-- Name: zutat_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zutat
    ADD CONSTRAINT zutat_name_key UNIQUE (name);


ALTER INDEX public.zutat_name_key OWNER TO postgres;

--
-- TOC entry 1534 (class 16386 OID 25710)
-- Dependencies: 1193 1193
-- Name: zutat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY zutat
    ADD CONSTRAINT zutat_pkey PRIMARY KEY (id);


ALTER INDEX public.zutat_pkey OWNER TO postgres;

--
-- TOC entry 1552 (class 1259 OID 25779)
-- Dependencies: 1198
-- Name: i_ba_all_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_ba_all_id ON bzr2all USING btree (all_id);


ALTER INDEX public.i_ba_all_id OWNER TO postgres;

--
-- TOC entry 1553 (class 1259 OID 25778)
-- Dependencies: 1198
-- Name: i_ba_bzr_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_ba_bzr_id ON bzr2all USING btree (bzr_id);


ALTER INDEX public.i_ba_bzr_id OWNER TO postgres;

--
-- TOC entry 1556 (class 1259 OID 25792)
-- Dependencies: 1199
-- Name: i_bew_bzr_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_bew_bzr_id ON bewertung USING btree (login);


ALTER INDEX public.i_bew_bzr_id OWNER TO postgres;

--
-- TOC entry 1557 (class 1259 OID 25793)
-- Dependencies: 1199
-- Name: i_bew_rating; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_bew_rating ON bewertung USING btree (rating);


ALTER INDEX public.i_bew_rating OWNER TO postgres;

--
-- TOC entry 1523 (class 1259 OID 25697)
-- Dependencies: 1190
-- Name: i_bzr_gru_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_bzr_gru_id ON benutzer USING btree (gru_id);


ALTER INDEX public.i_bzr_gru_id OWNER TO postgres;

--
-- TOC entry 1547 (class 1259 OID 25765)
-- Dependencies: 1197
-- Name: i_za_all_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_za_all_id ON zut2all USING btree (all_id);


ALTER INDEX public.i_za_all_id OWNER TO postgres;

--
-- TOC entry 1539 (class 1259 OID 25746)
-- Dependencies: 1195
-- Name: i_zr_rez_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_zr_rez_id ON zut2rez USING btree (rez_id);


ALTER INDEX public.i_zr_rez_id OWNER TO postgres;

--
-- TOC entry 1540 (class 1259 OID 25745)
-- Dependencies: 1195
-- Name: i_zr_zut_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_zr_zut_id ON zut2rez USING btree (zut_id);


ALTER INDEX public.i_zr_zut_id OWNER TO postgres;

--
-- TOC entry 1528 (class 1259 OID 25717)
-- Dependencies: 1193
-- Name: i_zut_einheit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_zut_einheit ON zutat USING btree (einheit);


ALTER INDEX public.i_zut_einheit OWNER TO postgres;

--
-- TOC entry 1529 (class 1259 OID 25718)
-- Dependencies: 1193
-- Name: i_zut_kalorien; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_zut_kalorien ON zutat USING btree (kalorien);


ALTER INDEX public.i_zut_kalorien OWNER TO postgres;

--
-- TOC entry 1530 (class 1259 OID 25719)
-- Dependencies: 1193
-- Name: i_zut_kat_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX i_zut_kat_id ON zutat USING btree (kat_id);


ALTER INDEX public.i_zut_kat_id OWNER TO postgres;

--
-- TOC entry 1558 (class 16386 OID 25693)
-- Dependencies: 1189 1517 1190
-- Name: benutzer_gru_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY benutzer
    ADD CONSTRAINT benutzer_gru_id_fkey FOREIGN KEY (gru_id) REFERENCES gruppe(id);


--
-- TOC entry 1567 (class 16386 OID 25784)
-- Dependencies: 1190 1519 1199
-- Name: bewertung_login_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bewertung
    ADD CONSTRAINT bewertung_login_fkey FOREIGN KEY (login) REFERENCES benutzer(login) ON DELETE CASCADE;


--
-- TOC entry 1568 (class 16386 OID 25788)
-- Dependencies: 1194 1537 1199
-- Name: bewertung_rez_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bewertung
    ADD CONSTRAINT bewertung_rez_id_fkey FOREIGN KEY (rez_id) REFERENCES rezept(id) ON DELETE CASCADE;


--
-- TOC entry 1566 (class 16386 OID 25774)
-- Dependencies: 1196 1198 1545
-- Name: bzr2all_all_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bzr2all
    ADD CONSTRAINT bzr2all_all_id_fkey FOREIGN KEY (all_id) REFERENCES allergie(id) ON DELETE CASCADE;


--
-- TOC entry 1565 (class 16386 OID 25770)
-- Dependencies: 1521 1190 1198
-- Name: bzr2all_bzr_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bzr2all
    ADD CONSTRAINT bzr2all_bzr_id_fkey FOREIGN KEY (bzr_id) REFERENCES benutzer(id) ON DELETE CASCADE;


--
-- TOC entry 1560 (class 16386 OID 25729)
-- Dependencies: 1519 1194 1190
-- Name: rezept_bzr_login_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY rezept
    ADD CONSTRAINT rezept_bzr_login_fkey FOREIGN KEY (bzr_login) REFERENCES benutzer(login) ON DELETE SET NULL;


--
-- TOC entry 1564 (class 16386 OID 25761)
-- Dependencies: 1197 1545 1196
-- Name: zut2all_all_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY zut2all
    ADD CONSTRAINT zut2all_all_id_fkey FOREIGN KEY (all_id) REFERENCES allergie(id) ON DELETE CASCADE;


--
-- TOC entry 1563 (class 16386 OID 25757)
-- Dependencies: 1197 1533 1193
-- Name: zut2all_zut_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY zut2all
    ADD CONSTRAINT zut2all_zut_id_fkey FOREIGN KEY (zut_id) REFERENCES zutat(id) ON DELETE CASCADE;


--
-- TOC entry 1562 (class 16386 OID 25741)
-- Dependencies: 1195 1537 1194
-- Name: zut2rez_rez_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY zut2rez
    ADD CONSTRAINT zut2rez_rez_id_fkey FOREIGN KEY (rez_id) REFERENCES rezept(id) ON DELETE CASCADE;


--
-- TOC entry 1561 (class 16386 OID 25737)
-- Dependencies: 1193 1195 1533
-- Name: zut2rez_zut_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY zut2rez
    ADD CONSTRAINT zut2rez_zut_id_fkey FOREIGN KEY (zut_id) REFERENCES zutat(id);


--
-- TOC entry 1559 (class 16386 OID 25713)
-- Dependencies: 1192 1526 1193
-- Name: zutat_kat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY zutat
    ADD CONSTRAINT zutat_kat_id_fkey FOREIGN KEY (kat_id) REFERENCES kategorie(id);


--
-- TOC entry 1582 (class 0 OID 0)
-- Dependencies: 4
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2005-06-29 12:30:30 �������� ������ (����)

--
-- PostgreSQL database dump complete
--

