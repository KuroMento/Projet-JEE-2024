-- Table creation script
-- Erase any existing table with its data (BEWARE!)
DROP TABLE Grades;
DROP TABLE Courses;
DROP TABLE Users;
DROP TABLE Subjects;
-- Create the tables
CREATE TABLE Users(
	id VARCHAR(30) PRIMARY KEY,
	lastName VARCHAR(30),
	firstName VARCHAR(30),
	contact VARCHAR(30),
	encryptedPassword VARCHAR(30) NOT NULL,
	dateOfBirth DATE NOT NULL,
	rights VARCHAR(30)
	);
CREATE TABLE Subjects(
	id VARCHAR(30) PRIMARY KEY,
	label VARCHAR(30),
	descr VARCHAR(100),
	coefficient DOUBLE DEFAULT 1 NOT NULL
	);
CREATE TABLE Courses(
	id VARCHAR(30) PRIMARY KEY,
	idSubject VARCHAR(30),
	FOREIGN KEY fk_idSubject(idSubject) REFERENCES Subjects(id)
	);
CREATE TABLE Grades(
	idStudent VARCHAR(30),
	idCourses VARCHAR(30),
	grade DOUBLE,
	FOREIGN KEY fk_student(idStudent) REFERENCES Users(id),
	FOREIGN KEY fk_courses(idCourses) REFERENCES Courses(id),
	CONSTRAINT grade CHECK (grade >= 0),
	CONSTRAINT pk_Grades PRIMARY KEY (idStudent, idCourses)
	);	
