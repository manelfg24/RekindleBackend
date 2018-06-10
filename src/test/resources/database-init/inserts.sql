INSERT INTO Volunteer VALUES('mailRoger', '1234', 'roger', 'poch', 'alonso', 'photo Roger');
INSERT INTO Volunteer VALUES('mailAlex', '1234', 'alex', 'sanchez', 'gil', 'photo Alex');
INSERT INTO Volunteer VALUES('mailJose', '1234', 'jose', 'ramon', 'perez', 'photo Jose');

INSERT INTO Refugee VALUES('mailFelipe', '1234', 'felipe', 'betancourt', 'rodriguez', 942342312, null, 'Masculino', 'Cuba', 'La Havana', 'hispano', 'AB+', 'Castaño', 'La biografia de Felipe', 'photo Felipe');
INSERT INTO Refugee VALUES('mailRafael', '1234', 'rafael', 'ramirez', 'pozo', 942342442, null, 'Masculino', 'España', 'Barcelona', 'hispano', 'A+', 'Castaño', 'La biografia de Rafael', 'photo Rafael');
INSERT INTO Refugee VALUES('mailLee', '1234', 'bruce', 'lee', 'chun', 634377442, null, 'Masculino', 'Japon', 'Tokyo', 'asiatico', 'B-', 'Oscuro', 'La biografia de Lee', 'photo Lee');
INSERT INTO Refugee VALUES('mailYaiza', '1234', 'yaiza', 'martinez', 'guardeño', 634322242, null, 'Femenino', 'España', 'Madrid', 'hispano', 'AB-', 'Oscuro', 'La biografia de Yaiza', 'photo Yaiza');

INSERT INTO Lodge VALUES(1, 'Lodge', 'Casa Pepe10', 'mailRoger', 936666666, 'Balmes', '2018-06-06', 2,'Alojamiento para dos personas');


INSERT INTO Donation VALUES(1, 'Donation', 'Donation numero cero', 'mailAlex', 93427512, 'adress donation cero', 10, '21:00:00', '22:00:00', 'Descripcion del donation cero');
INSERT INTO Donation VALUES(2, 'Donation', 'Donation numero uno', 'mailRoger', 93427512, 'adress donation one', 10, '10:00:00', '11:00:00', 'Descripcion del donation uno');
INSERT INTO Donation VALUES(3, 'Donation', 'Donation numero dos', 'mailRoger', 93427512, 'adress donation two', 10, '10:00:20', '15:00:00', 'Descripcion del donation dos');

INSERT INTO Education VALUES(1, 'Education', 'NombreEdu cero', 'mailAlex', 93427512, 'adress education cero', 'carpinteria', 'requirements for education 0', 'schedule cero' ,32, 50, 'Descripción Education service cero');
INSERT INTO Education VALUES(2, 'Education', 'NombreEdu uno', 'mailRoger', 93427512, 'adress education one', 'jardineria', 'requirements for education 1', 'schedule uno' ,32, 50, 'Descripción education service uno');

INSERT INTO Job VALUES(1, 'Job', 'Nombre Job cero', 'mailRoger', 93427512, 'adress job 0','cargo del job', 'requirements para el job', 5, 25, 12, 5000, 1,'Descripción Job service cero');

INSERT INTO Link VALUES(0, 'Test', 'www.test.com', 'Link para testear');
