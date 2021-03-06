DROP DATABASE IF EXISTS GAMEGO;
CREATE DATABASE GAMEGO;
USE GAMEGO;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users 
(
	uid INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	age INT NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	password varchar(50) NOT NULL
);
DROP TABLE IF EXISTS Admins;
CREATE TABLE Admins
(
	uid INT PRIMARY KEY,
	name varchar(50) NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	password varchar(50) NOT NULL,
	FOREIGN KEY (uid) 
	REFERENCES users (uid)
	ON DELETE CASCADE
);
DROP TABLE IF EXISTS Games;
CREATE TABLE Games
(
	gid INT PRIMARY KEY AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	author varchar(50) NOT NULL,
	genre varchar(50) NOT NULL,
	console_type varchar(50) NOT NULL,
	rating INT NOT NULL,
	price DOUBLE(6, 2) NOT NULL,
	stock INT NOT NULL,
	UNIQUE KEY gameKey (title, author, console_type)
);
DROP TABLE IF EXISTS Consoles;
CREATE TABLE Consoles
(	cid INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	price DOUBLE(6, 2) NOT NULL,
	stock INT NOT NULL,
	UNIQUE KEY consoleKey (name)
);
DROP TABLE IF EXISTS Memberships;
CREATE TABLE Memberships
(
	mid INT PRIMARY KEY AUTO_INCREMENT,
	uid INT,
	points INT NOT NULL,
	FOREIGN KEY (uid) 
	REFERENCES users (uid)
	ON DELETE CASCADE
);
DROP TABLE IF EXISTS Rentals;
CREATE TABLE Rentals
(	rid INT PRIMARY KEY AUTO_INCREMENT,
	mid INT,
	gid INT,
	price DOUBLE NOT NULL,
	date_rented TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	date_due TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (gid) 
	REFERENCES games (gid)
	ON DELETE CASCADE
);
DROP TABLE IF EXISTS Transactions;
CREATE TABLE Transactions
(
	tid INT PRIMARY KEY AUTO_INCREMENT,
	uid INT,
	gid INT,
	cid INT,
	price DOUBLE NOT NULL,
	date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (uid) 
	REFERENCES users (uid),
	FOREIGN KEY (gid)
	REFERENCES games (gid),
	FOREIGN KEY (cid)
	REFERENCES consoles (cid)
);
DROP TABLE IF EXISTS Sales;
CREATE TABLE Sales
(
	gid INT PRIMARY KEY,
	discount DOUBLE(6, 2) NOT NULL,
	originalPrice DOUBLE(6, 2) NOT NULL,
	FOREIGN KEY (gid)
	REFERENCES games (gid)
);
DROP TABLE IF EXISTS Prizes;
CREATE TABLE Prizes
(
    pid INT PRIMARY KEY AUTO_INCREMENT,
    prize_name VARCHAR(50),
    prize_points INT
);
DROP TABLE IF EXISTS archive_transactions;
CREATE TABLE archive_transactions
(
	tid INT PRIMARY KEY,
	uid INT,
	gid INT,
	cid INT,
	price DOUBLE NOT NULL,
	date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (uid) 
	REFERENCES users (uid),
	FOREIGN KEY (gid)
	REFERENCES games (gid),
	FOREIGN KEY (cid)
	REFERENCES consoles (cid)
);
# Create Default Admin
INSERT 
INTO users(name, age, email, password) 
VALUE("admin", 100, "gamegoadmin@gmail.com", "admin");

INSERT
INTO admins
VALUE(1, "admin", "gamegoadmin@gmail.com", "admin");