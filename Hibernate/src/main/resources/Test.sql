INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('FGeorge', 'FORET', 'George', 'george.foret@mail.fr', 'mP4qKleR', '2000/11/28', 'STUDENT');
INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('SLaruto', 'SAGE', 'Laruto', 'larutoww@mail.fr', 'heyheyhey', '2002/12/13', 'TEACHER');
INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('PEmma', 'PAYSAN', 'Emma', 'pay.emme@mail.fr', '00000000', '1998/02/09', 'STUDENT');

INSERT INTO Student VALUES ('FGeorge');
INSERT INTO Teacher VALUES ('SLaruto');
INSERT INTO Student VALUES ('PEmma');

INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('Admin', 'AdminNom', 'AdminPrenom', 'admin@mail.fr', '1234', '1970/01/01', 'ADMIN');
INSERT INTO Administrator VALUES ('Admin');
INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('Student', 'StudentNom', 'StudentPrenom', 'student@mail.fr', 'cytech0001', '1970/01/01', 'STUDENT');
INSERT INTO Student VALUES ('Student');
INSERT INTO Users (id,lastName,firstName,contact,encryptedPassword, dateOfBirth, rights) VALUES ('Teacher', 'TeacherNom', 'TeacherPrenom', 'teacher@mail.fr', 'cytech0001', '1970/01/01', 'TEACHER');
INSERT INTO Teacher VALUES ('Teacher');

INSERT INTO Subjects (id,label,descr,subject_coefficient) VALUES (1, 'Statistics', 'Advanced statistics for data mining', 2);
INSERT INTO Subjects (id,label,descr,subject_coefficient) VALUES (2, 'Quantum modelisation', 'Basic quantum modelisation', 1);
INSERT INTO Subjects (id,label,descr,subject_coefficient) VALUES (3, 'Software Engineering', 'About programming and stuff', 3);

INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (1,'Student',1,14,0.5);
INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (2,'Student',1,12,2);
INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (3,'Student',2,8,1);
INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (4,'Student',2,15,2);
INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (5,'Student',3,18,3);
INSERT INTO Grades (id,student_id,course_id,grade_value,grade_coefficient) VALUES (6,'Student',3,13,2);

INSERT INTO Courses (id,subject_id,teacher_id) VALUES (1, 1, 'SLaruto');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (2, 1, 'SLaruto');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (3, 2, 'SLaruto');

INSERT INTO Student_Courses (student_id,course_id) VALUES ('FGeorge', 1);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('PEmma', 2);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('PEmma', 3);