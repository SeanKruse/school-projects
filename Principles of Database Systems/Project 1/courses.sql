-- TODO: add a comment section with the names of the team members of the project (limited to 2). You are allowed to work solo as well. 

-- created at: 3/01/2022
-- author: Sean Kruse

-- TODO: create the courses database

CREATE DATABASE courses;

-- TODO: "open" the database for use

\c courses;

-- TODO: (optional) drop all tables

DROP TABLE IF EXISTS courses;

-- TODO: create table instructors

CREATE TABLE Instructors (
    email VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    office VARCHAR(255),
    hours VARCHAR(255)
);

-- TODO: create table courses

CREATE TABLE Courses(
    prefix VARCHAR(3),
    "number" CHAR(4),
    title VARCHAR(255) NOT NULL,
    description VARCHAR NOT NULL,
    credits INT NOT NULL,
    prereqs VARCHAR(255),
    PRIMARY KEY (prefix, "number")
);

-- TODO: create table sections

CREATE TABLE Sections(
    crn INT PRIMARY KEY,
    prefix VARCHAR(255) NOT NULL,
    "number" CHAR(4)  NOT NULL,
    section VARCHAR(3) NOT NULL,
    semester VARCHAR(10) NOT NULL,
    "year" INT NOT NULL,
    instructor VARCHAR(255),
    "times" VARCHAR(255),
    "start" DATE, 
    "end" DATE,
    location VARCHAR(255),
    campus VARCHAR(255),
    FOREIGN KEY(prefix, "number") REFERENCES Courses(prefix, "number"),
    FOREIGN KEY(instructor) REFERENCES Instructors(email)
);

-- TODO: manually insert a few instructors

INSERT INTO Instructors VALUES 
('codd@msudenver.edu', 'Codd, Edgar', '', '', ''),
('cohenb@msudenver.edu', 'Cohen, Blanche', '', '', ''),
('fzeng@msudenver.edu', 'Zeng, Fanyu', '', '', ''),
('aibrahi8@msudenver.edu', 'Ibrahim, Adil', '', '', ''),
('rranjidh@msudenver.edu', 'Ranjan, Ranjidah','', '', ''),
('fjiang@msudenver.edu', 'Jiang, Feng', '', '', ''),
('tle61@msudenver.edu', 'Le, ThienNgo', '', '', ''),
('wzhu1@msudenver.edu', 'Zhu, Weiying', '', '', '');

-- TODO: manually insert a few courses

INSERT INTO Courses VALUES 
('CS','1030','Computer Science Principles','Computer Science Principles introduces students to the central ideas of computer science vital for success in today''s world. Students are invited to develop the computational thinking skills that apply across disciplines, as we explore computing from multiple perspectives, including: cognitive, economic, ethical, legal, mathematical, philosophical, social, and technical. The course integrates computational thinking practices with big ideas in computing to address: collaborative teamwork, communication, creativity, critical thinking, innovation, problem solving, and programming. Students are afforded the opportunity to participate in active-learning experiences and to create computational artifacts that bring ideas to life.',4,''),
('CS','1050','Computer Science 1','This is the first course in the computer science core sequence. Students learn a modern programming language and the basic skills needed to analyze problems and construct programs for their solutions. The emphasis of the course is on the techniques of algorithm development, correctness, and programming style. Students are also introduced to the fundamentals of software engineering and the software-development life cycle.',4,'CS 1030 or readiness for MTH 1110 with a grade of "C-" or better.'),
('CS','1400','Computer Organization 1',' In this course, students will study the internal organization, characteristics, performance and interactions of a computer system''s functional components. Binary codes and binary arithmetic, digital logic, central processor organization, instruction set architecture, input/output fundamentals, and memory architecture are covered.',4,'An intermediate algebra course or one and one-half years of secondary school algebra or equivalent and appropriate score on the mathematics pre-assessment placement test or higher-level math course with a grade of "C-" or better.'),
('CS','2050','Computer Science 2','This course, a continuation of CS 1050, further emphasizes the concepts of the software development cycle and introduces the concept of an abstract data type (ADT). The topics covered include linked-lists, trees, stacks, queues, classes, recursion, and a variety of data representation methods. Further topics in software engineering and programming style as well as algorithms for sorting and searching are included.',4,'CS 1050 and MTH 1110 (or equivalent) with a grade of "C-" or better, or permission of instructor.'),
('CS','2240','Discrete Structures for CS','This course provides a solid theoretical foundation for the understanding of computer science, with emphasis on the application of formal structures and reasoning to problems in computer science. The course introduces and demonstrates application of discrete mathematics concepts commonly used in computer science and needed to solve many computational problems. Topics include formal logic systems, Boolean algebra, techniques for formal reasoning (including proof methods), set theory, graph theory, functions, relations, sequences, and recursive structures.',4,'CS 2050 and either (MTH 1400 or equivalent) or (MTH 1120 and (MTH 1110 or equivalent)) all with grades of "C-" or better; or permission of instructor'),
('CS','2400','Computer Organization 2','The course presents the functional organization of computers, multicore and multithreaded processors, high-performance storage, multiprocessor and multicomputer parallel architectures, and error detecting/correcting codes. Students learn assembly language programming and create software using a contemporary development environment.',4,'CS 1050, CS 1400, and MTH 1110 (or equivalent), each with a grade of "C-" or better, or permission of instructor)');

-- TODO: manually insert a few sections

INSERT INTO Sections VALUES 
(31998,'CS','1030','001','Spring',2022,'cohenb@msudenver.edu','TR 12:00pm-01:50pm','2022-01-18','2022-05-14','AES-285','Main'),
(31999,'CS','1030','002','Spring',2022,'cohenb@msudenver.edu','MW 12:00pm-01:50pm','2022-01-18','2022-05-14','AES-285','Main'), 
(32000,'CS','1030','003','Spring',2022,'cohenb@msudenver.edu','MW 04:00pm-05:50pm','2022-01-18','2022-05-14','AES-285','Main'),
(32380,'CS','1030','004','Spring',2022,'fzeng@msudenver.edu','TR 06:00pm-07:50pm','2022-01-18','2022-05-14','','Online'),
(30640,'CS','1050','001','Spring',2022,'aibrahi8@msudenver.edu','MW 10:00am-11:50am', '2022-01-18', '2022-05-14','AES-285','Main'),
(30641,'CS','1050','002','Spring',2022,'rranjidh@msudenver.edu','MW 12:00pm-01:50pm', '2022-01-18', '2022-05-14','AES-220','Main'),
(30783,'CS','1050','003','Spring',2022,'fzeng@msudenver.edu','MW 06:00pm-07:50pm', '2022-01-18', '2022-05-14','','Online'),
(34662,'CS','1050','004','Spring',2022,'cohenb@msudenver.edu','TR 10:00am-11:50am', '2022-01-18', '2022-05-14','AES-285','Main'),
(31296,'CS','1400','001','Spring',2022,'fjiang@msudenver.edu','TR 12:00pm-01:50pm', '2022-01-18', '2022-05-14','AES-210','Main'),
(34663,'CS','1400','002','Spring',2022,'rranjidh@msudenver.edu','MW 04:00pm-05:50pm', '2022-01-18', '2022-05-14','AES-210','Main'),
(30643,'CS','2050','001','Spring',2022,'aibrahi8@msudenver.edu','TR 02:00pm-03:50pm', '2022-01-18', '2022-05-14','AES-210','Main'),
(30644,'CS','2050','002','Spring',2022,'tle61@msudenver.edu','MW 06:00pm-07:50pm', '2022-01-18', '2022-05-14','AES-210','Main'),
(34664,'CS','2050','003','Spring',2022,'aibrahi8@msudenver.edu','MW 02:00pm-03:50pm', '2022-01-18', '2022-05-14','AES-285','Main'),
(34665,'CS','2240','001','Spring',2022,'tle61@msudenver.edu','TR 02:00pm-03:50pm', '2022-01-18', '2022-05-14','AES-207','Main'), 
(30645,'CS','2400','001','Spring',2022,'rranjidh@msudenver.edu','TR 04:00pm-05:50pm', '2022-01-18', '2022-05-14','AES-210','Main'),
(31107,'CS','2400','002','Spring',2022,'wzhu1@msudenver.edu','MW 10:00am-11:50am', '2022-01-18', '2022-05-14','AES-210','Main');

-- TODO: the total number of courses (name the count as "total")

SELECT COUNT(*) AS "Total" FROM Courses;

-- TODO: a list of all courses prefix, number, and title, sorted by prefix and then number

SELECT prefix, "number", title 
FROM Courses 
ORDER BY prefix, "number" ASC;

-- TODO: an alphabetical list of all instructors in the database

SELECT name 
FROM Instructors 
ORDER BY 1;

-- TODO: the prefix, number, section, and (course) title of all courses sections in the database, sorted by prefix, number and section

SELECT prefix, "number", section, title 
FROM Sections natural join Courses 
ORDER by prefix, "number", section, title ASC;

-- TODO: the prefix, number, the number of sections (named as "sections"), and (course) title of all courses sections in the database, sorted by prefix and number

SELECT prefix, "number", title, COUNT(section) AS "Sections" 
FROM Sections NATURAL JOIN Courses 
GROUP by prefix, "number", title 
ORDER BY prefix, "number";

-- TODO: an alphabetical list of the instructors that are teaching CS 1050 or CS 2050 (must avoid showing names repeated)

SELECT DISTINCT name 
FROM Instructors
inner join Sections ON email = instructor 
where "number" = '1050' OR "number" = '2050';

-- TODO: show an alphabetical list of instructors followed by the number of sections (named as "sections") that they are teaching, sorted in descending order of "sections"

SELECT name, Count(section) AS "Sections" 
FROM Instructors inner join Sections on email = instructor 
GROUP BY name
ORDER BY "Sections" DESC;

-- TODO: same as before, but limit the output to the top 3 instructors based on the number of sections that they are teaching

SELECT name, Count(section) AS "Sections" 
FROM Instructors inner join Sections on email = instructor 
GROUP BY name
ORDER BY "Sections" DESC 
LIMIT 3;

-- TODO: show an alphabetical list of the instructor(s) that are NOT currently teaching a section this semester 

SELECT I.name 
FROM Instructors I, Sections S
WHERE I.email NOT IN(SELECT instructor FROM Sections)
GROUP BY name
ORDER BY 1;

-- TODO: show the sections (with the instructor assigned to them) of CS 1050 that are being offered in the spring (2022) on TR 10:00am-11:50am

SELECT S.section, I.name
FROM Instructors I, Sections S 
WHERE I.email = S.instructor AND
"number" = '1050' AND "times" = 'TR 10:00am-11:50am'
GROUP BY section, name;