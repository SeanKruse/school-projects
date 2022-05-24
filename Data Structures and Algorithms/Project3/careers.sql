--DROP DATABASE IF EXISTS careers;

--CREATE DATABASE careers;

--\c careers;

DROP TABLE IF EXISTS Interests;

create table Interests(
    abbrv VARCHAR PRIMARY KEY,
    descr VARCHAR NOT NULL
);

DROP TABLE IF EXISTS Students;

create table Students(
    email VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    major VARCHAR NOT NULL,
    graduation VARCHAR NOT NULL
);

DROP TABLE IF EXISTS Employers;

create table Employers(
    id INT PRIMARY KEY,
    name VARCHAR NOT NULL,
    size INT NOT NULL,
    location VARCHAR NOT NULL,
    forprofit BOOLEAN NOT NULL,
    govern BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS StudentInterests;

create table StudentInterests(
    abbrv VARCHAR,
    email VARCHAR,
    FOREIGN KEY (abbrv) REFERENCES Interests(abbrv),
    FOREIGN KEY (email) REFERENCES Students(email),
    PRIMARY KEY (abbrv, email)
);

DROP TABLE IF EXISTS EmployerInterests;

create table EmployerInterests(
    abbrv VARCHAR,
    id INT,
    FOREIGN KEY (abbrv) REFERENCES Interests(abbrv),
    FOREIGN KEY (id) REFERENCES Employers(id),
    PRIMARY KEY (abbrv, id)
);

INSERT INTO Interests VALUES 
('android', 'Android Development'),
('app', 'Mobile App Development'),
('aws', 'Amazon Web Services'),
('cloud', 'Cloud Computing'),
('cyber', 'Cyber Security'),
('db', 'Databases'),
('dba', 'Database Administrator'),
('edu', 'Education'),
('java', 'Java'),
('kotlin', 'Kotlin'),
('mysql', 'MySQL'),
('postgres', 'PostgreSQL'),
('python', 'Python'),
('software', 'Software Development'),
('sql', 'SQL'),
('web', 'Web Development'); 
    
INSERT INTO Students VALUES 
('eastmanv@msudenver.edu', 'Virginia Eastman', 'cs', 'Spring 2022'),
('gilbertb@msudenver.edu', 'Barbara Gilbert', 'cs', 'Fall 2023'),
('zachariasr@msudenver.edu', 'Robert Zacharias', 'cs', 'Spring 2023');

INSERT INTO Employers VALUES 
(101, 'Wonka Industries', 350, 'Lakewood, CO', true, false),
(202, 'Cheers Agency', 1000, 'Denver, CO', false, true),
(303, 'Dominate the World', 5, 'Golden, CO', true, false),
(404, 'Stingers Org', 212, 'Denver, CO', false, false),
(505, 'Easy Peasy', 1, 'Littleton, CO', true, false);

INSERT INTO StudentInterests VALUES
('cloud', 'eastmanv@msudenver.edu'),
('cloud', 'zachariasr@msudenver.edu'),
('db', 'eastmanv@msudenver.edu'),
('db', 'gilbertb@msudenver.edu'),
('java', 'eastmanv@msudenver.edu'),
('mysql', 'eastmanv@msudenver.edu'),
('sql', 'eastmanv@msudenver.edu'),
('sql', 'gilbertb@msudenver.edu'),
('python', 'gilbertb@msudenver.edu'),
('edu', 'zachariasr@msudenver.edu'),
('web', 'zachariasr@msudenver.edu');


INSERT INTO EmployerInterests VALUES
('cloud', 202),
('cloud', 303),
('db', 101),
('dba', 101),
('java', 101),
('java', 303),
('java', 505),
('mysql', 101),
('sql', 101),
('sql', 202),
('sql', 404),
('postgres', 101),
('postgres', 404),
('python', 202),
('python', 404),
('web', 303),
('aws', 202),
('cyber', 303);