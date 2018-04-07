use local_rekindle;
CREATE TABLE Refugee (
	mail varchar(30) PRIMARY KEY,
	password varchar(15) NOT NULL,
	name varchar(20) NOT NULL,
	surname1 varchar(20) NOT NULL,
	surname2 varchar(20), 
	phoneNumber int,
	birthdate date,
	sex varchar(20),
	country varchar(20),
	town varchar(40),
	ethnic varchar(20),
	bloodType varchar(20),
	eyeColor varchar(20)
);

CREATE TABLE Volunteer (
	mail varchar(30) PRIMARY KEY,
	password varchar(15) NOT NULL,
	name varchar(20) NOT NULL,
	surname1 varchar(20) NOT NULL,
	surname2 varchar(20)
);