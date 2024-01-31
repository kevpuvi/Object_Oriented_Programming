-- Create SCHEMA

CREATE SCHEMA `studentDB` ;

-- Create the table
CREATE TABLE `students` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `studyProgram` varchar(255) NOT NULL,
  `hasVoted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- Insert the data inside the 'students' table, this will be the default students you can either nominee or propose a nominee.
INSERT INTO students (id, name, studyProgram, hasVoted) VALUES
(1, 'Alice Smith', 'Computer Science', 0),
(2, 'Bob Johnson', 'Arts', 0),
(3, 'Charlie Brown', 'Medicine', 0),
(4, 'David Williams', 'Arts', 0),
(5, 'Ben Adams', 'Medicine', 0),
(6, 'Nina Halvorsen', 'Medicine', 0),
(7, 'Knut Magnus', 'Computer Science', 0),
(8, 'Lars Halvorsen', 'Art', 0);

