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


INSERT INTO Volunteer VALUES('mailRoger', '1234', 'roger', 'poch', 'alonso', 'photo Roger',7,2,1,0);
INSERT INTO Volunteer VALUES('mailAlex', '1234', 'alex', 'sanchez', 'gil', 'photo Alex', 5, 2, 1,0);
INSERT INTO Volunteer VALUES('mailJose', '1234', 'jose', 'ramon', 'perez', 'photo Jose', 1, 1, 1,1);

INSERT INTO Lodge VALUES(1, 'Lodge', 'Casa Pepe10', 'mailRoger', 936666666, 'Balmes', '2019-06-06', 2,'Alojamiento cercania 1', FALSE, 41.390205, 2.154007);

INSERT INTO Lodge VALUES(2, 'Lodge', 'Casa Pepe10', 'mailAlex', 936666666, 'Balmes', '2032-06-06', 2,'Alojamiento cercania 2', FALSE, 41.617592, 0.620015);

INSERT INTO Lodge VALUES(3, 'Lodge', 'Casa Pepe10', 'mailRoger', 936666666, 'Balmes', '2054-06-06', 2,'Alojamiento cercania 3', FALSE, 40.416775, -3.703790);

INSERT INTO Lodge VALUES(4, 'Lodge', 'Casa Pepe10', 'mailJose', 936666666, 'Balmes', '2010-06-06', 2,'Alojamiento cercania 4', FALSE, 38.736946, -9.142685);

SET @fromDate='2005-06-06';
SET @toDate='2040-06-06';
SET @minimumRating=4;
SET @positionLat=41.390205;
SET @positionLng=2.154007;
SET @distance=500;

SELECT *
FROM (
	SELECT l.id, l.serviceType, l.name, l.volunteer, l.phoneNumber, l.adress, l.description, l.positionLat, l.positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Lodge AS l
	JOIN (   /* these are the query parameters */
		SELECT  @positionLat  AS latpoint,  @positionLng AS longpoint,
				@distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
	WHERE l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
) AS d, Lodge l join Volunteer v on l.volunteer = v.mail
WHERE distance <= radius and dateLimit between @fromDate and @toDate
	and ( (v.averageValoration/v.numberOfValorations) >= @minimumRating
		or @minimumRating is null)
UNION
SELECT *
FROM (
	SELECT l.id, l.serviceType, l.name, l.volunteer, l.phoneNumber, l.adress, l.description, l.positionLat, l.positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Education AS l
	JOIN (   /* these are the query parameters */
		SELECT  @positionLat  AS latpoint,  @positionLng AS longpoint,
				@distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
	WHERE l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
) AS d, Education l join Volunteer v on l.volunteer = v.mail
WHERE distance <= radius and ((v.averageValoration/v.numberOfValorations) >= @minimumRating
		or @minimumRating is null)
UNION
SELECT *
FROM (
	SELECT l.id, l.serviceType, l.name, l.volunteer, l.phoneNumber, l.adress, l.description, l.positionLat, l.positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Job AS l
	JOIN (   /* these are the query parameters */
		SELECT  @positionLat  AS latpoint,  @positionLng AS longpoint,
				@distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
	WHERE l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
) AS d, Job l join Volunteer v on l.volunteer = v.mail
WHERE distance <= radius and ((v.averageValoration/v.numberOfValorations) >= @minimumRating
		or @minimumRating is null)
UNION
SELECT *
FROM (
	SELECT l.id, l.serviceType, l.name, l.volunteer, l.phoneNumber, l.adress, l.description, l.positionLat, l.positionLng,
		p.radius,
		p.distance_unit
				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
				 * COS(RADIANS(l.positionLat))
				 * COS(RADIANS(p.longpoint - l.positionLng))
				 + SIN(RADIANS(p.latpoint))
				 * SIN(RADIANS(l.positionLat)))) AS distance
	FROM Donation AS l
	JOIN (   /* these are the query parameters */
		SELECT  @positionLat  AS latpoint,  @positionLng AS longpoint,
				@distance AS radius,      111.045 AS distance_unit
	) AS p ON 1=1
	WHERE l.positionLat
	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)
		 AND p.latpoint  + (p.radius / p.distance_unit)
	AND l.positionLng
	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
) AS d, Donation l join Volunteer v on l.volunteer = v.mail
WHERE distance <= radius and ((v.averageValoration/v.numberOfValorations) >= @minimumRating
		or @minimumRating is null)
ORDER BY distance