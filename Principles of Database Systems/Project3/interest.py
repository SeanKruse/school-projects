'''
CS3810 - Principles of Database Systems - Spring 2022
Instructor: Thyago Mota
Student Names: Sean Kruse
Description: creates Interest entity and allows listing of all interests
'''

from curses.ascii import EM
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, ForeignKey, String, Integer, create_engine
from sqlalchemy.orm import sessionmaker

Base = declarative_base()

# TODO: finish the object-relational mapping


class Interest(Base):

    # table mapping
    __tablename__ = 'Interests'

# column mapping
    abbrv = Column(String, primary_key=True)
    descr = Column(String, nullable=False)

    def __str__(self):
        return str(self.abbrv) + ", " + str(self.descr)


if __name__ == "__main__":

    # db connection and session creation
    db_string = "sqlite:///careers.db"
    db = create_engine(db_string)
    Session = sessionmaker(db)
    session = Session()

    # TODO: list all interests

    disp_interests = session.query(Interest)

    for InterestsRow in disp_interests:
        print(InterestsRow)

    # android = "Android Development"
    # app = "Mobile App Development"
    # aws = "Amazon Web Services"
    # cloud = "Cloud Computing"
    # cyber = "Cyber Security"
    # db = "Databases"
    # dba = "Database Administrator"
    # edu = "Education"
    # java = "Java"
    # kotlin = "Kotlin"
    # mysql = "MySQL"
    # postgres = "PostgreSQL"
    # python = "Python"
    # software = "Software Development"
    # sql ="SQL"
    # web = "Web Development"
