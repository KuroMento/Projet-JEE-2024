-- Test Values
INSERT INTO Users VALUES ('FGeorge', 'FORET', 'George', 'george.foret@mail.fr', 'mP4qKleR', '2000/11/28');
INSERT INTO Students VALUES ('FGeorge');
INSERT INTO Users VALUES ('SLaruto', 'SAGE', 'Laruto', 'larutoww@mail.fr', 'heyheyhey', '2002/12/13');
INSERT INTO Teachers VALUES ('SLaruto');
INSERT INTO Users VALUES ('PEmma', 'PAYSAN', 'Emma', 'pay.emme@mail.fr', '00000000', '1998/02/09');
INSERT INTO Students VALUES ('PEmma');
INSERT INTO Users VALUES ('Admin', 'AdminNom', 'AdminPrenom', 'admin.fr', '1234', '1970/01/01');
INSERT INTO Admins VALUES ('Admin');
INSERT INTO Subjects VALUES ('science1', 'Statistique', 'Cours de statistiques avancé', 4);
INSERT INTO Subjects VALUES ('science2', 'Modélisation Quantique', 'Cours de modélisation quantique de base', default);
INSERT INTO Courses VALUES (1, 'science1', 'FGeorge', 'SLaruto');
INSERT INTO Courses VALUES (2, 'science1', 'PEmma', 'SLaruto');
INSERT INTO Courses VALUES (3, 'science2', 'PEmma', 'SLaruto');
INSERT INTO Grades VALUES ('FGeorge', 1, 12);
INSERT INTO Grades VALUES ('PEmma', 2, 14);
INSERT INTO Grades VALUES ('PEmma', 3, 6);