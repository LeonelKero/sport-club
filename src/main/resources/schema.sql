-- Create database if not exists
SELECT 'CREATE DATABASE sports'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'sports');

-- Define sequence
CREATE SEQUENCE IF NOT EXISTS cycling_id_seq INCREMENT BY 1 MINVALUE 1;
-- Create cycling table with that sequence
create table if not exists cycling (
id int NOT NULL DEFAULT nextval('cycling_id_seq'),
title varchar(250) not null,
started_on timestamp not null,
completed_on timestamp not null,
distance double precision not null,
location varchar(20) not null,
version int,
primary key (id)
);
-- Alter the sequence
ALTER SEQUENCE cycling_id_seq OWNED BY cycling.id;

-- For testing purposes only
--create table if not exists cycling (
--id int auto_increment NOT NULL,
--title varchar(250) not null,
--started_on timestamp not null,
--completed_on timestamp not null,
--distance double precision not null,
--location varchar(20) not null,
--version int,
--primary key (id)
--);
