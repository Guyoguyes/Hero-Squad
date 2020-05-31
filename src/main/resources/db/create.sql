SET MODE postgreSQL;

CREATE TABLE IF NOT EXIST  heroes (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    age int,
    superPower VARCHAR,
    weakness VARCHAR,
    squadId int
);

CREATE TABLE IF NOT EXISTS squads (
    id int PRIMARY KEY  auto_increment,
    name VARCHAR ,
    size int,
    source VARCHAR
)