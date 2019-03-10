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



--rollback drop table user;

--changeset nvoxland:2

insert into "user" (name, surname,email,  password, password2, city, country, gender, birth, likes, hobby, bio, register_date)
values ('Anna', 'Grund', 'angrun@ttu.ee',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '2001-02-16', 14, ARRAY ['(408)-589-5846'], 'I love coding', '2001-02-16');

-- insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
-- values ('Marina', 'Ivanova',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '22-02-1999', 22, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I love bodyPump');
--
--
-- insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Katarina', 'Piven',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '22-02-1998', 12, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I would really like to become a developer');
--
--   insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
-- values ('Lilia', 'Tünts', 'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '28-08-1998', 5, ARRAY [ '(408)-589-5846',
-- '(408)-589-5555' ], 'I am very angry when I am hungry');
--
--  insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Marina', 'Voskanjan',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '24-02-1997', 7, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I love flexing');
--
--   insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Ruslan', 'Eskov',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '09-09-1998', 9, ARRAY [ '(408)-589-5846',
--   '(408)-589-5555' ], 'I love fencing');
--
--   insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Roman', 'Bondarev',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '11-11-1998', 32, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I am Junior Developer');
--
--  insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Andrei', 'Anissimov',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '24-08-1998', 12, ARRAY [ '(408)-589-5846',
--   '(408)-589-5555' ], 'I am on chill');
--
--   insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Jekaterina', 'Viltsenko',  'test', 'netest', 'Tallinn', 'Estonia', 'FEMALE', '31-12-1997', 4, ARRAY [ '(408)-589-5846',
--   '(408)-589-5555' ], 'I want to become a thread analytic');
--
--  insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Martin', 'Rebane',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '23-10-1997', 20, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I love my job');
--
--    insert into "user" (name, surname, password, password2, city, country, gender, birth, likes, hobby, bio)
--  values ('Veiko', 'Soomets',  'test', 'netest', 'Tallinn', 'Estonia', 'MALE', '21-05-1997', 3, ARRAY [ '(408)-589-5846',
--  '(408)-589-5555' ], 'I love my students too');




