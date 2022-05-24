"""
CS3810 - Principles of Database Systems - Spring 2022
Instructor: Thyago Mota
Description: Homework 08
Student Name: Sean Kruse
"""

from sqlalchemy.ext.declarative import declarative_base  
from sqlalchemy import Column, String, Integer, ForeignKey, create_engine, null
from sqlalchemy.orm import sessionmaker, relationship 

Base = declarative_base() 

# TODO: finish this sqlalchemy class
class Agency(Base): 

    # table mapping
    __tablename__ = 'Agencies'

    # column mapping
    ouid = Column(Integer, primary_key=True)
    name = Column(String(15), nullable=False)
    website = Column(String(30))
    agents = relationship("Agent", primaryjoin="Agency.ouid == Agent.ouid")

    def __str__(self):
        s = str(self.ouid) + ", " + self.name + ", " + self.website + ", [" 
        for agent in self.agents: 
            s += "{" + str(agent)  + "}, " 
        if len(self.agents) > 0:
            s = s[:-2] + ']' 
        return s 

# TODO: finish this sqlalchemy class
class Agent(Base): 

    #table mapping
    __tablename__ = 'agents'

    id = Column(Integer, primary_key=True)
    name = Column(String(20), nullable=False)
    ouid = Column(Integer, ForeignKey("agencies.ouid"), nullable=False)

    def __str__(self): 
      return str(self.id) + ", " + str(self.name)
    
    
# sqlalchemy initialization
db_string = "postgresql://real_estate:135791@localhost:5432/real_estate" 
db = create_engine(db_string)  
Session = sessionmaker(db)  
session = Session() 

# TODO: use session's query to find and then display "Keller Williams" agency, showing all of its agents

disp_agency = session.query(Agency).filter(Agency.name == "Keller Williams")

for agenciesRow in disp_agency:
    print(agenciesRow)

session.close()
