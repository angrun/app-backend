--liquibase formatted sql

--changeset nvoxland:1
--
-- CREATE SEQUENCE serial START 1;

CREATE TABLE IF NOT EXISTS "user" (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(50),
  surname   VARCHAR(50),
  email     VARCHAR(50),
  password  VARCHAR(50),
  password2 VARCHAR(50),
  city      VARCHAR(100),
  country   VARCHAR(100),
  gender    VARCHAR(50),
  birth     DATE,
  likes     INT DEFAULT 0,
  hobby     TEXT [],
  bio       VARCHAR(100) DEFAULT NULL,
  register_date DATE
);

CREATE TABLE IF NOT EXISTS "hobby" (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS "image" (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(100),
  user_id INTEGER  NOT NULL,
  date_created timestamp,
  FOREIGN KEY (user_id) REFERENCES "user" (id)

);

CREATE TABLE IF NOT EXISTS "matching" (
  id        SERIAL PRIMARY KEY,
  from_user_id      INTEGER NOT NULL,
  to_user_id INTEGER NOT NULL,
  like_value INTEGER NOT NULL

);



--rollback drop table user;

--changeset nvoxland:2

insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Anna', 'Grund', 'angrun@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 14, ARRAY ['(408)-589-5846'], 'I love coding', '2019-01-01');

insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Marina', 'Ivanova', 'mariiv@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 16, ARRAY ['(408)-589-5846'], 'I love bodyPump', '2019-01-02');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Lilia', 'Tünts', 'litunts@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 3, ARRAY ['(408)-589-5846'], 'I hate stupid students', '2019-01-01');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Marina', 'Voskonjan', 'mavosk@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 40, ARRAY ['(408)-589-5846'], 'I love flexing', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Katarina', 'Piven', 'kapive@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 34, ARRAY ['(408)-589-5846'], 'I love design', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Roman', 'Bondarev', 'robond@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 30, ARRAY ['(408)-589-5846'], 'I love coding in Kodality', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Ruslan', 'Eskov', 'ruesko@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 44, ARRAY ['(408)-589-5846'], 'I love fencing', '2019-01-10');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Andrei', 'Anissimov', 'ananni@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 14, ARRAY ['(408)-589-5846'], 'I am having a break now', '2019-01-11');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Denis', 'Smirnov', 'densmir@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16',40, ARRAY ['(408)-589-5846'], 'I love medicine', '2019-01-12');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Aleksandr', 'Boyko', 'aboyko@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 100, ARRAY ['(408)-589-5846'], 'I love coding', '2019-01-15');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Jekaterina', 'Viltsenko', 'jevilt@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 42, ARRAY ['(408)-589-5846'], 'I love tehno', '2019-01-20');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Inna', 'Schwarzman', 'inswarz@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 60, ARRAY ['(408)-589-5846'], 'I love teaching', '2019-01-25');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Veiko', 'Saar', 'vesaar@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 14, ARRAY ['(408)-589-5846'], 'I love LHV', '2019-01-30');


insert into "matching" (from_user_id, to_user_id, like_value)
values (1, 13, 1);

insert into "matching" (from_user_id, to_user_id, like_value)
values (1, 11, 1);

insert into "matching" (from_user_id, to_user_id, like_value)
values (13, 1, 1);
