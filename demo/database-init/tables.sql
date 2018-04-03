CREATE TABLE User (
	mail varchar(20) PRIMARY KEY,
    password varchar(15) NOT NULL,
	name varchar(20) NOT NULL,
    surname1 varchar(20) NOT NULL,
    surname2 varchar(20)	
);

CREATE TABLE Volunteer (
	mail varchar(20) PRIMARY KEY,
    
    FOREIGN KEY (mail) REFERENCES User(mail)
);

CREATE TABLE Refugee (
	mail varchar(20) PRIMARY KEY,
    phoneNumber int,
    birthdate date,
    sex varchar(20),
    country varchar(20),
    town varchar(20),
    ethnic varchar(20),
    bloodType varchar(20), 
    eyeColor varchar(20),
    
    FOREIGN KEY (mail) REFERENCES User(mail)
);

CREATE TABLE Service (
  id int PRIMARY KEY,
  name varchar(20) NOT NULL,
  volunteer varchar(20) NOT NULL,
  phone varchar(20) NOT NULL,
  address varchar(20) NOT NULL,
  description varchar(300),
  
  FOREIGN KEY (volunteer) REFERENCES Volunteer(mail)
);

CREATE TABLE Donation (
  id int PRIMARY KEY,
  maxRequests int,
  startHour time,
  finishHour time,
  
  FOREIGN KEY (id) REFERENCES Service(id)
);

CREATE TABLE Education (
  id int PRIMARY KEY,
  requirements varchar(50),
  schedule varchar(30),
  theme varchar(20),
  places int,
  price decimal(10,2),
  
  FOREIGN KEY (id) REFERENCES Service(id)
);

CREATE TABLE Job (
  id int PRIMARY KEY,
  charge varchar(30) NOT NULL,
  requirements varchar(50),
  hoursDay decimal(10,2),
  hoursWeek decimal(10,2),
  contractDuration int,
  salary decimal(10,2),
  places int,
  
  FOREIGN KEY (id) REFERENCES Service(id)
);

CREATE TABLE Lodge (
  id int PRIMARY KEY,
  places int,
  deadline date,
  
  FOREIGN KEY (id) REFERENCES Service(id)
);