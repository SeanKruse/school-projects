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

-- TODO: answer all queries

-- a) List all diagnostic names in alphabetical order.

select drg_desc
from procedures
order by 1;

-- b) List the names and correspondent states (including Washington D.C.) of all of the providers in alphabetical order (state first, provider name next, no repetition).

select distinct p.rndrng_prvdr_org_name, f.rndrng_prvdr_state_abrvtn
from Provider p, FederalStandard f
where p.rndrng_prvdr_org_name = f.rndrng_prvdr_state_abrvtn
GROUP BY rndrng_prvdr_state_abrvtn, rndrng_prvdr_org_name
ORDER BY 1;

-- c) List the total number of providers.

select count(rndrng_ccn)
from provider;

-- d) List the total number of providers per state (including Washington D.C.) in alphabetical order (also printing out the state).

select count(rndrng_ccn),rndrng_prvdr_state_abrvtn
from billing
natural join federalstandard
group by rndrng_ccn,rndrng_prvdr_state_abrvtn
order by rndrng_prvdr_state_abrvtn ASC;

-- e) List the providers names in Denver (CO) or in Lakewood (CO) in alphabetical order

select distinct rndrng_prvdr_org_name,rndrng_prvdr_city,rndrng_prvdr_zip5
from billing
natural join provider
where rndrng_prvdr_city='Lakewood' and rndrng_prvdr_zip5=80228 or rndrng_prvdr_city='Denver'
order by rndrng_prvdr_org_name;

-- f) List the number of providers per RUCA code (showing the code and description)

select count(rndrng_ccn),rndrng_prvdr_ruca, rndrng_prvdr_ruca_desc
from provider natural join Ruca on rndrng_prvdr_ruca
group by rndrng_prvdr_ruca,procedures.drg_cd ,drg_desc;

-- g) Show the DRG description for code 308
 select drg_cd,drg_desc
 from procedures
 where drg_cd=308;

-- h) List the top 10 providers (with their correspondent state) that charged (as described in Avg_Submtd_Cvrd_Chrg) the most for the DRG code 308. Output should display the provider name, their city, state, and the average charged amount in descending order.  
select rndrng_prvdr_org_name,rndrng_prvdr_city,rndrng_prvdr_st,rndrng_prvdr_state_abrvtn,avg_submtd_cvrd_chrg
from provider natural join billing natural join federalstandard
where drg_cd=308
order by avg_submtd_cvrd_chrg
DESC limit 10;

-- i) List the average charges (as described in Avg_Submtd_Cvrd_Chrg) of all providers per state for the DRG code 308. Output should display the state and the average charged amount per state in descending order (of the charged amount) using only two decimals.

select rndrng_prvdr_state_abrvtn,avg(Avg_Submtd_Cvrd_Chrg)::numeric(10,2)
from billing natural join federalstandard
where drg_cd=308
group by rndrng_prvdr_state_abrvtn
order by avg DESC;

-- j) Which provider and clinical condition pair had the highest difference between the amount charged (as described in Avg_Submtd_Cvrd_Chrg) and the amount covered by Medicare only (as described in Avg_Mdcr_Pymt_Amt)?

select rndrng_ccn,drg_cd,max(avg_submtd_cvrd_chrg-avg_mdcr_pymnt_amt)
from provider natural join billing
 group by rndrng_ccn,drg_cd
 order by max DESC limit 1;

-- TODO (optional) - BONUS POINTS: prove that you didn't do the normalization only using your "guts" but also what you learned in class; show all 2NF or 3NF violations and normalization steps in detail and you will get up to +10 points.