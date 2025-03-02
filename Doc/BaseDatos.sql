--
-- PostgreSQL database cluster dump
--

-- Started on 2025-03-02 11:17:08

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-02 11:17:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-03-02 11:17:08

--
-- PostgreSQL database dump complete
--

--
-- Database "accounts-movements-service" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-02 11:17:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3363 (class 1262 OID 16384)
-- Name: accounts-movements-service; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "accounts-movements-service" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE "accounts-movements-service" OWNER TO postgres;

\connect -reuse-previous=on "dbname='accounts-movements-service'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16475)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    id character varying(255) NOT NULL,
    creation_timestamp timestamp without time zone,
    update_timestamp timestamp without time zone,
    initial_balance double precision NOT NULL,
    account_number character varying(255) NOT NULL,
    account_type character varying(255),
    is_delete boolean,
    status boolean,
    client_id uuid NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16482)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    id character varying(255) NOT NULL,
    creation_timestamp timestamp without time zone,
    update_timestamp timestamp without time zone,
    amount double precision,
    balance double precision,
    date date,
    transaction_type character varying(255),
    account_id character varying(255) NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 16475)
-- Dependencies: 215
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (id, creation_timestamp, update_timestamp, initial_balance, account_number, account_type, is_delete, status, client_id) FROM stdin;
\.


--
-- TOC entry 3357 (class 0 OID 16482)
-- Dependencies: 216
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (id, creation_timestamp, update_timestamp, amount, balance, date, transaction_type, account_id) FROM stdin;
\.


--
-- TOC entry 3207 (class 2606 OID 16481)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 16488)
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);


--
-- TOC entry 3209 (class 2606 OID 16490)
-- Name: accounts uk_6kplolsdtr3slnvx97xsy2kc8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT uk_6kplolsdtr3slnvx97xsy2kc8 UNIQUE (account_number);


--
-- TOC entry 3212 (class 2606 OID 16491)
-- Name: transactions fk20w7wsg13u9srbq3bd7chfxdh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT fk20w7wsg13u9srbq3bd7chfxdh FOREIGN KEY (account_id) REFERENCES public.accounts(id);


-- Completed on 2025-03-02 11:17:08

--
-- PostgreSQL database dump complete
--

--
-- Database "clients-persons-service" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-02 11:17:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3374 (class 1262 OID 16385)
-- Name: clients-persons-service; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "clients-persons-service" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE "clients-persons-service" OWNER TO postgres;

\connect -reuse-previous=on "dbname='clients-persons-service'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16443)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    is_delete boolean,
    password character varying(255),
    status boolean,
    client_id character varying(255) NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16450)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id character varying(255) NOT NULL,
    creation_timestamp timestamp without time zone,
    update_timestamp timestamp without time zone,
    address character varying(255),
    age integer NOT NULL,
    gender character varying(255),
    identification character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255)
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16457)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id character varying(255) NOT NULL,
    creation_timestamp timestamp without time zone,
    update_timestamp timestamp without time zone,
    active character varying(255),
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255),
    about_me character varying(255),
    address character varying(255),
    city character varying(255),
    country character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    phone_number character varying(255),
    postal_code character varying(255),
    profile_picture character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3366 (class 0 OID 16443)
-- Dependencies: 215
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (is_delete, password, status, client_id) FROM stdin;
f	123456	t	b86ca8a6-56b9-4289-9c6a-4cab13ad5969
f	123456	t	e8545826-3518-4cfe-8b40-6d6208b6931b
\.


--
-- TOC entry 3367 (class 0 OID 16450)
-- Dependencies: 216
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persons (id, creation_timestamp, update_timestamp, address, age, gender, identification, name, phone) FROM stdin;
b86ca8a6-56b9-4289-9c6a-4cab13ad5969	2025-03-02 10:24:05.292295	2025-03-02 10:24:05.292295	Mitad del Mindo	37	M	1718726085	Diego Pozo Salas	0999112498
e8545826-3518-4cfe-8b40-6d6208b6931b	2025-03-02 10:02:22.765774	2025-03-02 10:25:08.129548	Lulumbamba y catequilla	38	M	1718726084	Diego Gonzalo Pozo Salas	0999112498
\.


--
-- TOC entry 3368 (class 0 OID 16457)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, creation_timestamp, update_timestamp, active, email, password, role, about_me, address, city, country, first_name, last_name, phone_number, postal_code, profile_picture, username) FROM stdin;
c1ff95f7-1fd8-451f-9d91-9df40811314f	2025-03-02 10:00:38.843426	2025-03-02 10:00:38.843426	\N	admin@gmail.com	$2a$10$BK3yUdkWkAwAmvNCF7arOe0Dp0uz.ThXrrEXhhEPisJrM/3Z4OguK	ADMIN	\N	\N	\N	\N	\N	\N	\N	\N	\N	admin
\.


--
-- TOC entry 3211 (class 2606 OID 16449)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (client_id);


--
-- TOC entry 3213 (class 2606 OID 16456)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 16467)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 3215 (class 2606 OID 16465)
-- Name: persons uk_q9hehhi7b6xsv7sp8cpb4mo21; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT uk_q9hehhi7b6xsv7sp8cpb4mo21 UNIQUE (identification);


--
-- TOC entry 3219 (class 2606 OID 16469)
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 3221 (class 2606 OID 16463)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 16470)
-- Name: clients fkpnhrp6ctlsaxqyge4owep2fqs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT fkpnhrp6ctlsaxqyge4owep2fqs FOREIGN KEY (client_id) REFERENCES public.persons(id);


-- Completed on 2025-03-02 11:17:08

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-03-02 11:17:08

--
-- PostgreSQL database cluster dump complete
--

