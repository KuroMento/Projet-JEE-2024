
-- Create the tables
CREATE TABLE Users(
	id VARCHAR(30) PRIMARY KEY,
	lastName VARCHAR(30),
	firstName VARCHAR(30),
	contact VARCHAR(30),
	encryptedPassword VARCHAR(30) NOT NULL,
	dateOfBirth DATE NOT NULL,
	rights VARCHAR(50) NOT NULL
);

CREATE TABLE Student(
	id VARCHAR(50) PRIMARY KEY,
	FOREIGN KEY fk_id(id) REFERENCES Users(id)
);

CREATE TABLE Teacher(
	id VARCHAR(50) PRIMARY KEY,
	FOREIGN KEY fk_id(id) REFERENCES Users(id)
);

CREATE TABLE Administrator(
	id VARCHAR(50) PRIMARY KEY,
	FOREIGN KEY fk_id(id) REFERENCES Users(id)
);

CREATE TABLE Subjects(
	id BIGINT PRIMARY KEY,
	label VARCHAR(50),
	descr VARCHAR(200),
	subject_coefficient DOUBLE DEFAULT 1 NOT NULL
);

CREATE TABLE Courses(
	id BIGINT PRIMARY KEY,
	subject_id BIGINT,
	teacher_id VARCHAR(50),
	FOREIGN KEY fk_idSubject(subject_id) REFERENCES Subjects(id),
	FOREIGN KEY fk_idTeacher(teacher_id) REFERENCES Teacher(id)
);

CREATE TABLE Grades(
    id BIGINT,
	student_id VARCHAR(50),
	course_id BIGINT,
	grade_value DOUBLE,
	grade_coefficient DOUBLE DEFAULT 1 NOT NULL,
	FOREIGN KEY fk_student(studentId) REFERENCES Student(id),
	FOREIGN KEY fk_courses(courseId) REFERENCES Courses(id),
	CONSTRAINT grade_value CHECK (grade_value >= 0),
	CONSTRAINT pk_grades PRIMARY KEY (studentId, courseId)
);

CREATE TABLE Student_Courses(
    student_id VARCHAR(50) NOT NULL,
    course_id BIGINT NOT NULL,
	FOREIGN KEY fk_student(student_id) REFERENCES Student(id),
	FOREIGN KEY fk_courses(course_id) REFERENCES Courses(id)
);
