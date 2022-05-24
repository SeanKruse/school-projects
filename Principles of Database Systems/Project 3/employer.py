'''
CS3810 - Principles of Database Systems - Spring 2022
Instructor: Thyago Mota
Student Names: Sean Kruse
Description: creates an Employer entity with a 1-many mapping to EmployerInterest and allows listing of all employers
'''

from curses.ascii import EM
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, String, Integer, Boolean, create_engine, ForeignKey
from sqlalchemy.orm import sessionmaker, relationship

Base = declarative_base()

# TODO: finish the object-relational mapping


class Employer(Base):

    # table mapping
    __tablename__ = 'Employers'

# column mapping
    id = Column(Integer, primary_key=True)
    name = Column(String, nullable=False)
    size = Column(Integer, nullable=False)
    location = Column(String, nullable=False)
    forprofit = Column(Boolean, nullable=False)
    govern = Column(Boolean, nullable=False)
    relate = relationship("EmployerInterest", back_populates="relate")

    def __str__(self):
        employerval = str(self.id) + ", " + str(self.name) + ", " + str(self.size) + \
            ", " + str(self.location) + ", " + \
            str(self.forprofit) + ", " + str(self.govern) + " ["
        for emp_interests in self.relate:
            employerval = employerval + str(emp_interests)
        return (employerval[:-1]) + "]"

# TODO: finish the object-relational mapping


class EmployerInterest(Base):

    # table mapping
    __tablename__ = 'EmployerInterests'

# column mapping
    abbrv = Column(String, ForeignKey("Interests.abbrv"), primary_key=True)
    id = Column(Integer, ForeignKey("Employers.id"), primary_key=True)
    relate = relationship("Employer", back_populates="relate")

    def __str__(self):
        return str(self.abbrv) + " "


if __name__ == "__main__":

    # db connection and session creation
    db_string = "sqlite:///careers.db"
    db = create_engine(db_string)
    Session = sessionmaker(db)
    session = Session()

    # TODO: list all employers

    disp_employers = session.query(Employer)

    for EmployersLine in disp_employers:
        print(EmployersLine)
