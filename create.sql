CREATE DATABASE heroSquad;
\c heroSquad;
 CREATE TABLE heroes ( id SERIAL  PRIMARY KEY, name VARCHAR, age INTEGER, superPower VARCHAR, weakness VARCHAR,squadId INTEGER);
 CREATE TABLE squads (id SERIAL PRIMARY KEY, name VARCHAR, size INTEGER, source VARCHAR);
 CREATE  DATABASE heroSquad_test WITH TEMPLATE heroSquad;