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

INSERT INTO Courses (id,subject_id,teacher_id) VALUES (1, 1, 'SLaruto');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (2, 3, 'SLaruto');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (3, 2, 'Teacher');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (4, 1, 'Teacher');
INSERT INTO Courses (id,subject_id,teacher_id) VALUES (5, 2, 'Teacher');

INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (1,'Note 1','Student',1,14,2);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (2,'Note 2','Student',1,12,2);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (3,'Note 3','Student',2,8,1);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (4,'Note 4','Student',2,15,2);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (5,'Note 5','Student',3,18,3);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (6,'Note 6','Student',3,13,2);

INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (7,'Note 7','PEmma',1,17,1.5);
INSERT INTO Grades (id,label,student_id,course_id,g_value,g_coefficient) VALUES (8,'Note 8','PEmma',1,8,0.5);

INSERT INTO Student_Courses (student_id,course_id) VALUES ('FGeorge', 1);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('PEmma', 2);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('PEmma', 3);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('Student', 1);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('Student', 2);
INSERT INTO Student_Courses (student_id,course_id) VALUES ('Student', 3);