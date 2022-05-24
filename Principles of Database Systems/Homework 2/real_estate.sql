CREATE DATABASE real_estate;

\c real_estate;

CREATE TABLE agencies (
    ouid INT PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    website VARCHAR(30)
);

INSERT INTO agencies VALUES (101, 'Keller Williams', 'kw.com' );
INSERT INTO agencies VALUES (202, 'RE/MAX',          'remax.com');
INSERT INTO agencies VALUES (303, 'Century 21',      'century21.com');
INSERT INTO agencies (ouid, name) VALUES (404, 'Morbid Home');

CREATE TABLE agents (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    ouid INT NOT NULL,
    FOREIGN KEY (ouid) REFERENCES agencies (ouid)
);

INSERT INTO agents VALUES (1, 'Janet',   101);
INSERT INTO agents VALUES (2, 'Paul',    101);
INSERT INTO agents VALUES (3, 'Bob',     101);
INSERT INTO agents VALUES (4, 'Zenobia', 202);
INSERT INTO agents VALUES (5, 'Morbid Mojito Jr.', 404);

CREATE USER "real_estate" PASSWORD '135791';
GRANT ALL ON TABLE agencies TO "real_estate";
GRANT ALL ON TABLE agents TO "real_estate";