-- CREATE SCHEMA

CREATE SCHEMA `voteDB`;


-- CREATE TABLE

CREATE TABLE `nominees` (
  `studentID` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- -- Insert the data inside the 'nominees' table, this will be the default students that is already a nominee.

INSERT INTO nominees (id, name) VALUES
(1, 'Alice Smith'),
(3, 'Charlie Brown'),
(6, 'Nina Halvorsen');


-- CREATE TABLE

CREATE TABLE `votes` (
  `studentID` int DEFAULT NULL,
  `nomineeID` int DEFAULT NULL,
  `comment` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci





