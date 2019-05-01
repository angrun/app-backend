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
  bio       VARCHAR(100) DEFAULT NULL,
  register_date DATE
);

CREATE TABLE IF NOT EXISTS "hobby" (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(50),
  user_id   INTEGER  NOT NULL
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
  like_value INTEGER NOT NULL,
  seen BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS "messages" (
  id        SERIAL PRIMARY KEY,
  from_user_id      INTEGER NOT NULL,
  to_user_id INTEGER NOT NULL,
  message VARCHAR(500),
  message_seen BOOLEAN DEFAULT FALSE,
  date_sent timestamp

);

--rollback drop table user;

--changeset nvoxland:2

insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Anna', 'Grund', 'angrun@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 14, 'I love coding', '2019-01-01');

insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Marina', 'Ivanova', 'mariiv@ttu.ee',  'testtest', 'testtest', 'Riga', 'Latvia', 'FEMALE', '2001-02-16', 16, 'I love bodyPump', '2019-01-02');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Lilia', 'Tünts', 'litunts@ttu.ee',  'testtest', 'testtest', 'London', 'United Kingdom', 'FEMALE', '2001-02-16', 3, 'I hate stupid students', '2019-01-01');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Marina', 'Voskonjan', 'mavosk@ttu.ee',  'testtest', 'testtest', 'London', 'United Kingdom', 'FEMALE', '2001-02-16', 40, 'I love flexing', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Katarina', 'Piven', 'kapive@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 34, 'I love design', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Roman', 'Bondarev', 'robond@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 30, 'I love coding in Kodality', '2019-01-05');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Ruslan', 'Eskov', 'ruesko@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 44, 'I love fencing', '2019-01-10');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Andrei', 'Anissimov', 'ananni@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 14, 'I am having a break now', '2019-01-11');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Denis', 'Smirnov', 'densmir@ttu.ee',  'testtest', 'testtest', 'Moscow', 'Russia', 'MALE', '2001-02-16',40, 'I love medicine', '2019-01-12');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Aleksandr', 'Boyko', 'aboyko@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 100, 'I love coding', '2019-01-15');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Jekaterina', 'Viltsenko', 'jevilt@ttu.ee',  'testtest', 'testtest', 'Moscow', 'Russia', 'FEMALE', '2001-02-16', 42, 'I love tehno', '2019-01-20');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Inna', 'Schwarzman', 'inswarz@ttu.ee',  'testtest', 'testtest', 'Moscow', 'Russia', 'FEMALE', '2001-02-16', 60, 'I love teaching', '2019-01-25');


insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, bio, register_date)
values ('Veiko', 'Saar', 'vesaar@ttu.ee',  'testtest', 'testtest', 'Tallinn', 'Estonia', 'MALE', '2001-02-16', 14, 'I love LHV', '2019-01-30');


-- insert into "matching" (from_user_id, to_user_id, like_value)
-- values (1, 13, 1);

-- insert into "matching" (from_user_id, to_user_id, like_value)
-- values (1, 11, 1);

-- insert into "matching" (from_user_id, to_user_id, like_value)
-- values (13, 1, 1);
--
--
-- insert into "matching" (from_user_id, to_user_id, like_value)
-- values (1, 13, 1);

insert into "messages" (from_user_id, to_user_id, message, date_sent)
values (1, 2, 'hei', '2019-01-30');

insert into "messages" (from_user_id, to_user_id, message, date_sent)
values (2, 1, 'hei hei', '2019-01-30');

insert into "messages" (from_user_id, to_user_id, message, date_sent)
values (2, 1, 'hei hei hei hei', '2019-01-30');


insert into "hobby" (name, user_id)
values ('duster diving', 1);

insert into "hobby" (name, user_id)
values ('duster diving', 2);

insert into "hobby" (name, user_id)
values ('duster diving', 3);

insert into "hobby" (name, user_id)
values ('photography', 2);

insert into "hobby" (name, user_id)
values ('photography', 3);

insert into "hobby" (name, user_id)
values ('coding', 1);