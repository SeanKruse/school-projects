-- TODO: add a comment section with the names of the team members of the project (limited to 2). You are allowed to work solo as well. 

-- TODOd: create the cms database
CREATE DATABASE cms;

-- TODOd: "open" the database for use
\c cms

-- TODO: (optional) drop all tables

DROP TABLE IF EXISTS cms;

-- TODO: create all tables (with primary keys, NULL constraints, and foreign keys)

create table RUCA(
    Rndrng_Prvdr_Ruca DECIMAL PRIMARY KEY,
    Rndrng_Prvdr_Ruca_Desc VARCHAR NOT NULL
);

create table Procedures(
    DRG_Cd INT PRIMARY KEY,
    DRG_Desc VARCHAR NOT NULL
);

create table Provider(
    Rndrng_CCN INT PRIMARY KEY,
    Rndrng_Prvdr_Org_Name VARCHAR NOT NULL,
    Rndrng_Prvdr_St VARCHAR NOT NULL,
    Rndrng_Prvdr_City VARCHAR NOT NULL,
    Rndrng_Prvdr_Zip5 INT NOT NULL,
    Rndrng_Prvdr_Ruca DECIMAL REFERENCES RUCA(Rndrng_Prvdr_Ruca)
);

create table FederalStandard(
    Rndrng_Prvdr_State_Abrvtn VARCHAR PRIMARY KEY,
    Rndrng_Prvdr_State_Fips INT NOT NULL,
    Rndrng_CCN INT REFERENCES Provider(Rndrng_CCN)
);

create table Billing(
    PRIMARY KEY (Rndrng_CCN, DRG_Cd),
    Tot_Dschrgs INT NOT NULL,
    Avg_Submtd_Cvrd_Chrg DECIMAL NOT NULL,
    Avg_Tot_Pymt_Amt DECIMAL NOT NULL,
    Avg_Mdcr_Pymnt_Amt DECIMAL NOT NULL,
    Rndrng_CCN int references Provider(Rndrng_CCN),
    DRG_Cd int references Procedures(DRG_Cd)
);

-- TODO: create users 

create user "cms_admin" password '12345';
create user "cms" password '54321';

-- TODO: grant access to users 

grant select on table Provider,FederalStandard,RUCA,procedures,Billing to "cms";
grant all on table Provider,FederalStandard,RUCA,procedures,Billing to "cms_admin";

-- TODO: answer all queries

-- a) List all diagnostic names in alphabetical order.

SELECT DISTINCT COUNT(*)
FROM Billing
ORDER BY 1;
  
-- b) List the names and correspondent states (including Washington D.C.) of all of the providers in alphabetical order (state first, provider name next, no repetition).   
  
-- c) List the total number of providers.  
  
-- d) List the total number of providers per state (including Washington D.C.) in alphabetical order (also printing out the state). 

SELECT DISTINCT COUNT(Rndrng_CCN) 
FROM FederalStandard f, Provider p
WHERE f.Rndrng_Prvdr_State_Abrvtn = p.Rndrng_Prvdr_State_Abrvtn
GROUP BY Rndrng_Prvdr_State_Abr
ORDER BY 1;

-- e) List the providers names in Denver (CO) or in Lakewood (CO) in alphabetical order 

-- f) List the number of providers per RUCA code (showing the code and description)

-- g) Show the DRG description for code 308
  
-- h) List the top 10 providers (with their correspondent state) that charged (as described in Avg_Submtd_Cvrd_Chrg) the most for the DRG code 308. Output should display the provider name, their city, state, and the average charged amount in descending order.  

-- i) List the average charges (as described in Avg_Submtd_Cvrd_Chrg) of all providers per state for the DRG code 308. Output should display the state and the average charged amount per state in descending order (of the charged amount) using only two decimals.   
 
-- j) Which provider and clinical condition pair had the highest difference between the amount charged (as described in Avg_Submtd_Cvrd_Chrg) and the amount covered by Medicare only (as described in Avg_Mdcr_Pymt_Amt)?  

-- TODO (optional) - BONUS POINTS: prove that you didn't do the normalization only using your "guts" but also what you learned in class; show all 2NF or 3NF violations and normalization steps in detail and you will get up to +10 points.