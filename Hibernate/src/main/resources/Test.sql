-- Test Values
INSERT INTO Users VALUES ('FGeorge', 'FORET', 'George', 'george.foret@mail.fr', 'mP4qKleR', '2000/11/28', null);
INSERT INTO Users VALUES ('SLaruto', 'SAGE', 'Laruto', 'larutoww@mail.fr', 'heyheyhey', '2002/12/13', null);
INSERT INTO Users VALUES ('PEmma', 'PAYSAN', 'Emma', 'pay.emme@mail.fr', '00000000', '1998/02/09', null);
INSERT INTO Subjects VALUES ('science1', 'Statistique', 'Cours de statistiques avancé', 4);
INSERT INTO Subjects VALUES ('science2', 'Modélisation Quantique', 'Cours de modélisation quantique de base', default);
INSERT INTO Courses VALUES ('statistique1', 'science1');
INSERT INTO Courses VALUES ('statistique2', 'science1');
INSERT INTO Courses VALUES ('modelQuantique1', 'science2');
INSERT INTO Grades VALUES ('FGeorge', 'statistique1', 12);
INSERT INTO Grades VALUES ('PEmma', 'statistique2', 14);
INSERT INTO Grades VALUES ('PEmma', 'modelQuantique1', 6);