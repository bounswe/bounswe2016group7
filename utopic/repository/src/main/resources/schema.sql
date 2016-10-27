/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ugurbor
 * Created: Oct 27, 2016
 */

-- Table: public.authority

-- DROP TABLE public.authority;

CREATE TABLE public.authority
(
    id bigint NOT NULL,
    name character varying(50) COLLATE "default".pg_catalog NOT NULL,
    CONSTRAINT authority_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.authority
    OWNER to postgres;

-- Table: public.user_authority

-- DROP TABLE public.user_authority;

CREATE TABLE public.user_authority
(
    record_id bigint NOT NULL,
    authority_id integer,
    user_id bigint,
    CONSTRAINT user_authority_pkey PRIMARY KEY (record_id),
    CONSTRAINT fk_5losscgu02yaej7prap7o6g5s FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_r26d2qfcm6jm4jykhho6kn3u6 FOREIGN KEY (authority_id)
        REFERENCES public.authority (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_authority
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL,
    email character varying(255) COLLATE "default".pg_catalog NOT NULL,
    enabled boolean,
    firstname character varying(255) COLLATE "default".pg_catalog NOT NULL,
    gender character varying(1) COLLATE "default".pg_catalog,
    last_password_reset_date timestamp without time zone,
    lastname character varying(255) COLLATE "default".pg_catalog NOT NULL,
    middlename character varying(255) COLLATE "default".pg_catalog,
    password character varying(255) COLLATE "default".pg_catalog NOT NULL,
    username character varying(255) COLLATE "default".pg_catalog NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;