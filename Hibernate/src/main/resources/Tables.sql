-- Table creation script
-- Erase any existing table with its data (BEWARE!)
DROP TABLE Grades;
DROP TABLE Courses;
DROP TABLE Students;
DROP TABLE Teachers;
DROP TABLE Admins;
DROP TABLE Users;
DROP TABLE Subjects;
-- Create the tables
CREATE TABLE Users(
	id VARCHAR(50) PRIMARY KEY,
	lastName VARCHAR(50),
	firstName VARCHAR(50),
	contact VARCHAR(50),
	encryptedPassword VARCHAR(50) NOT NULL,
	dateOfBirth DATE NOT NULL
	);
CREATE TABLE Students(
	id VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY fk_id(id) REFERENCES Users(id)
);
CREATE TABLE Teachers(
	id VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY fk_id(id) REFERENCES Users(id)
);
CREATE TABLE Admins(
	id VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY fk_id(id) REFERENCES Users(id)
);
CREATE TABLE Subjects(
	id VARCHAR(50) PRIMARY KEY,
	label VARCHAR(50),
	descr VARCHAR(200),
	coefficient DOUBLE DEFAULT 1 NOT NULL
	);
CREATE TABLE Courses(
	id BIGINT PRIMARY KEY,
	idSubject VARCHAR(50),
    idStudent VARCHAR(50),
    idTeacher VARCHAR(50),
	FOREIGN KEY fk_idSubject(idSubject) REFERENCES Subjects(id),
    FOREIGN KEY fk_idStudent(idStudent) REFERENCES Students(id),
    FOREIGN KEY fk_idTeacher(idTeacher) REFERENCES Teachers(id)
	);
CREATE TABLE Grades(
	idStudent VARCHAR(50),
	idCourses BIGINT,
	grade DOUBLE,
	FOREIGN KEY fk_student(idStudent) REFERENCES Users(id),
	FOREIGN KEY fk_courses(idCourses) REFERENCES Courses(id),
	CONSTRAINT grade CHECK (grade >= 0),
	CONSTRAINT pk_Grades PRIMARY KEY (idStudent, idCourses)
	);	
