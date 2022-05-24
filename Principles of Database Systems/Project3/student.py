'''
CS3810 - Principles of Database Systems - Spring 2022
Instructor: Thyago Mota
Student Names:
Description: creates a Student entity with a 1-many mapping to StudentInterest and allows listing of all students
'''

from curses.ascii import EM
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, String, Integer, create_engine, ForeignKey
from sqlalchemy.orm import sessionmaker, relationship

Base = declarative_base()

# TODO: finish the object-relational mapping


class Student(Base):

    # table mapping
    __tablename__ = 'Students'

# column mapping
    email = Column(String, primary_key=True)
    name = Column(String, nullable=False)
    major = Column(String, nullable=False)
    graduation = Column(String, nullable=False)
    relate = relationship("StudentInterest", back_populates="relate")

    def __str__(self):
        studentval = str(self.email) + ", " + str(self.name) + \
            ", " + str(self.major) + ", " + str(self.graduation) + " ["
        for stud_interests in self.relate:
            studentval = studentval + str(stud_interests)
        return (studentval[:-1]) + "]"

# TODO: finish the object-relational mapping


class StudentInterest(Base):

    # table mapping
    __tablename__ = 'StudentInterests'

# column mapping
    abbrv = Column(String, ForeignKey("Interests.abbrv"), primary_key=True)
    email = Column(String, ForeignKey("Students.email"), primary_key=True)
    relate = relationship("Student", back_populates="relate")

    def __str__(self):
        return str(self.abbrv) + " "


if __name__ == "__main__":

    # db connection and session creation
    db_string = "sqlite:///careers.db"
    db = create_engine(db_string)
    Session = sessionmaker(db)
    session = Session()

    # TODO: list all students

    disp_students = session.query(Student)

    for StudentsLine in disp_students:
        print(StudentsLine)
