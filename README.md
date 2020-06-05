# Hero-Squad

## AUTHOR

Guyoguyes

## DESCRIPTION

Hero Squad is a java application cretae to enable users create a squad and hero and add heroes to a specific squad according to power 
and their power source

Heroes have both strength and weakness

## TECHNOLOGY USED

Java
Junit
Spark
Gradle
Maven 
Postgres

## SETUP INSTRUCTION

Clone the project from the repo 'https://github.com/Guyoguyes/Hero-Squad.git'

Navigate to the project path 

Open the directory on your IDE

NB: Make sure your IDE supports java and you have java installed in your computer

Build the project and Run the App class file

Navigate to localhost:4567 in a browser

## DATABASE SETUP

Install Postgresql in your computer

Open terminal and type psql to access postgres shell

In psql

CREATE DATABASE herosquad;
\c herosquad;
CREATE TABLE heroes (id serial PRIMARY KEY, name VARCHAR, superpower VARCHAR, weakness VARCHAR);
CREATE TABLE squad(id serial PRIMARY KEY, name VACHAR)

change the postgres url in app file to your localhost

## BUGS

none

##CONTRIBUTION

email for contribution g.abduba43@gmail.com

## COPYRIGHT AND LICENSE
MIT LICENSE
