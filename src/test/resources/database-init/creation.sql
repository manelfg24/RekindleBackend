/*
 * Resources local:
 spring.datasource.url=jdbc:mysql://localhost:3306/local_rekindle
 spring.datasource.username=alumne
 spring.datasource.password=rekindle
 
 * Resources server:
 spring.datasource.url=jdbc:mysql://10.4.41.149:3306/PES
 spring.datasource.username=aplicacion
 spring.datasource.password=rekindle
 */

USE PES;
USE local_rekindle;

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
	eyeColor varchar(20),
    biography varchar(300),
    photo varchar(200)
);

CREATE TABLE Volunteer (
	mail varchar(30) PRIMARY KEY,
	password varchar(15) NOT NULL,
	name varchar(20) NOT NULL,
	surname1 varchar(20) NOT NULL,
	surname2 varchar(20),
	photo varchar(200)
);

CREATE TABLE Donation (
	id int PRIMARY KEY auto_increment,
    serviceType varchar(20),
    name varchar(50) NOT NULL,
    volunteer varchar(30) NOT NULL,
	phoneNumber int NOT NULL,
	adress varchar(250) NOT NULL,
    places int,
    startTime time(0) NOT NULL,
    endTime time(0) NOT NULL,
	description varchar(300) NOT NULL,
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE Job (
	id int PRIMARY KEY auto_increment,
    serviceType varchar(20),
	name varchar(50) NOT NULL,
	volunteer varchar(30) NOT NULL,
	phoneNumber int NOT NULL,
	adress varchar(250) NOT NULL,
	charge varchar(30) NOT NULL,
	requirements varchar(50) NOT NULL,
	hoursDay decimal(10,2) NOT NULL,
	hoursWeek decimal(10,2) NOT NULL,
	contractDuration int NOT NULL,
	salary decimal(10,2) NOT NULL,
	places int,
	description varchar(300) NOT NULL,
  
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE Education (
	id int PRIMARY KEY auto_increment,
    serviceType varchar(20),
    name varchar(50) NOT NULL,
    volunteer varchar(30) NOT NULL,
	phoneNumber int NOT NULL,
	adress varchar(250) NOT NULL,
	ambit varchar(50) NOT NULL,
    requirements varchar(100) NOT NULL,
    schedule varchar(30) NOT NULL,
    places int,
    price int,
	description varchar(300) NOT NULL,
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE Lodge (
	id int PRIMARY KEY auto_increment,
    serviceType varchar(20),
    name varchar(50) NOT NULL,
    volunteer varchar(30) NOT NULL,
	phoneNumber int NOT NULL,
	adress varchar(250) NOT NULL,
    places int,
    dateLimit Date,
	description varchar(300) NOT NULL,
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE LodgeEnrollment (
	refugeeMail varchar(30), 
	lodgeId int,
	
	PRIMARY KEY (refugeeMail, lodgeId),
	FOREIGN KEY (refugeeMail) REFERENCES Refugee(mail),
	FOREIGN KEY (lodgeId) REFERENCES Lodge(id)
);

CREATE TABLE EducationEnrollment (
	refugeeMail varchar(30), 
	educationId int,
	
	PRIMARY KEY (refugeeMail, educationId),
	FOREIGN KEY (refugeeMail) REFERENCES Refugee(mail),
	FOREIGN KEY (educationId) REFERENCES Education(id)
);

CREATE TABLE JobEnrollment (
	refugeeMail varchar(30), 
	jobId int,
	
	PRIMARY KEY (refugeeMail, jobId),
	FOREIGN KEY (refugeeMail) REFERENCES Refugee(mail),
	FOREIGN KEY (jobId) REFERENCES Job(id)
);

CREATE TABLE DonationEnrollment (
	refugeeMail varchar(30), 
	donationId int,
	
	PRIMARY KEY (refugeeMail, donationId),
	FOREIGN KEY (refugeeMail) REFERENCES Refugee(mail),
	FOREIGN KEY (donationId) REFERENCES Donation(id)
);

CREATE TABLE Chat (
	id int PRIMARY KEY auto_increment,
	mailUser1 varchar(30) NOT NULL,
	mailUser2 varchar(30) NOT NULL
);

CREATE TABLE Message (
	id int PRIMARY KEY auto_increment,
	idChat int NOT NULL,
	mailSender varchar(30) NOT NULL,
	content varchar(200) NOT NULL,
	timeStamp Date NOT NULL,
    
	FOREIGN KEY (idChat) REFERENCES Chat(id)
);
