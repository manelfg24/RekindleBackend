INSERT INTO Volunteer VALUES('roger@gmail.com', '1234', 'roger', 'poch', 'alonso', 'photo Roger');
INSERT INTO Volunteer VALUES('alex@gmail.com', '1234', 'alex', 'sanchez', 'gil', 'photo Alex');

INSERT INTO Refugee VALUES('felipe@gmail.com', '1234', 'felipe', 'betancourt', 'rodriguez', 942342312, null, 'Masculino', 'Cuba', 'La Havana', 'hispano', 'AB+', 'Castaño', 'La biografia de Felipe', 'photo Felipe');
INSERT INTO Refugee VALUES('rafael@gmail.com', '1234', 'rafael', 'ramirez', 'pozo', 942342442, null, 'Masculino', 'España', 'Barcelona', 'hispano', 'A+', 'Castaño', 'La biografia de Rafael', 'photo Rafael');
INSERT INTO Refugee VALUES('lee@gmail.com', '1234', 'bruce', 'lee', 'chun', 634377442, null, 'Masculino', 'Japon', 'Tokyo', 'asiatico', 'B-', 'Oscuro', 'La biografia de Lee', 'photo Lee');
INSERT INTO Refugee VALUES('yaiza@gmail.com', '1234', 'yaiza', 'martinez', 'guardeño', 634322242, null, 'Femenino', 'España', 'Madrid', 'hispano', 'AB-', 'Oscuro', 'La biografia de Yaiza', 'photo Yaiza');

INSERT INTO Donation VALUES(1, 'd', 'Donation numero uno', 'roger@gmail.com', 93427512, 'adress donation one', 10, '10:00:00', '11:00:00', 'Descripcion del donation uno');
INSERT INTO Donation VALUES(2, 'd', 'Donation numero dos', 'roger@gmail.com', 93427512, 'adress donation two', 10, '10:00:20', '15:00:00', 'Descripcion del donation dos');
INSERT INTO Donation VALUES(3, 'd', 'Donation numero tres', 'alex@gmail.com', 93427512, 'adress donation three', 10, '21:00:00', '22:00:00', 'Descripcion del donation tres');

INSERT INTO Education VALUES(1, 'e', 'NombreEdu', 'roger@gmail.com', 93427512, 'adress education one', 'jardineria', 'requirements for education 1', 'schedule uno' ,32, 50, 'Descripción education service');
INSERT INTO Education VALUES(2, 'e', 'NombreEdu', 'alex@gmail.com', 93427512, 'adress education two', 'carpinteria', 'requirements for education 2', 'schedule dos' ,32, 50, 'Descripción Education service');

INSERT INTO Job VALUES(1, 'j', 'Nombre Job 1', 'roger@gmail.com', 93427512, 'adress job 1','cargo del job', 'requirements para el job', 5, 25, 12, 5000, 1,'Descripción Job service');
