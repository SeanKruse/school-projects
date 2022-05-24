'''
CS3810 - Principles of Database Systems - Spring 2022
Instructor: Thyago Mota
Student Names: Sean Kruse
Description: creates and populates a file-based embedded database
'''

import sqlite3
from tabnanny import filename_only
from psycopg2 import Error

from sqlalchemy import null


def db_conn(file):
    conn = null
    try:
        conn = sqlite3.connect(file)
    except Error as e:
        print("Error while connecting to SQLite3")
    finally:
        if conn == True:
            conn.close()
        print("SQLite3 connection is closed")


# TODO: create and populate the database
if __name__ == "__main__":
    db_conn(r"careers.db")
conn = sqlite3.connect('careers.db')
with open('careers.sql') as careers:
    conn.executescript(careers.read())
conn.commit()
