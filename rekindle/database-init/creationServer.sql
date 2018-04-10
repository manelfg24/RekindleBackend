USE PES;
/*spring.datasource.url=jdbc:mysql://10.4.41.149:3306/PES*/

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

DELIMITER $$
CREATE PROCEDURE `is_not_a_refugee` (IN mail VARCHAR(30))
BEGIN
 DECLARE count INTEGER;
 SELECT count(*) INTO count
 FROM Refugee r
 WHERE r.mail = mail;
    IF count > 0 
  THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This user already exists as refugee';
 END IF;
END 
$$

DELIMITER $$
CREATE TRIGGER user_exists_as_refugee BEFORE INSERT ON Volunteer 
FOR EACH ROW 
BEGIN CALL is_not_a_refugee(NEW.mail);
END;
$$

DELIMITER $$
CREATE PROCEDURE `is_not_a_volunteer` (IN mail VARCHAR(30))
BEGIN
 DECLARE count INTEGER;
 SELECT count(*) INTO count
 FROM Volunteer v
 WHERE v.mail = mail;
    IF count > 0 
  THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This user already exists as volunteer';
 END IF;
END 
$$

DELIMITER $$
CREATE TRIGGER user_exists_as_volunteer BEFORE INSERT ON Refugee 
FOR EACH ROW 
BEGIN CALL is_not_a_volunteer(NEW.mail);
END;
$$

CREATE TABLE Lodge (
	id int PRIMARY KEY auto_increment,
    name varchar(50),
    volunteer varchar(30),
    phoneNumber int,
    adress varchar(50),
    places int,
    dateLimit DATE,
    description varchar(300),
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);


CREATE TABLE Donation (
	id int PRIMARY KEY auto_increment,
    name varchar(50),
    volunteer varchar(30),
	phoneNumber int,
	adress varchar(50),
    places int,
    startTime time(0),
    endTime time(0),
	description varchar(300),
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE Education (
	id int PRIMARY KEY auto_increment,
    name varchar(50),
    volunteer varchar(30),
	phoneNumber int,
	adress varchar(50),
	ambit varchar(50),
    prerequisite varchar(100),
    schedule varchar(30),
    places int,
    price int,
	description varchar(300),
    
    FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);
