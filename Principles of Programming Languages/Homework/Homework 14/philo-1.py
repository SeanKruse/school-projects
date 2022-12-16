'''
CS3210 - Principles of Programming Languages - Fall 2022
Instructor: Thyago Mota
Description: Homework 12 - Dining Philosopher Problem
Student Name: Sean Kruse
'''

import threading
import time
import random


class Philosopher(threading.Thread):

    def __init__(self, name, left_fork, right_fork):
        threading.Thread.__init__(self)
        self.name = name
        self.left_fork = left_fork
        self.right_fork = right_fork

    def think(self):
        time.sleep(
            random.randint(1, 10) / 1000
        )

    def eat(self):
        time.sleep(
            random.randint(1, 50) / 1000
        )

    def run(self):

        while self.running:
            # Pick up the forks
            self.left_fork.acquire()
            print(self.name + " got the left fork")
            self.right_fork.acquire()
            print(self.name + " got the right fork")
            self.think()

            # Gotta think
            print(str(self.name) + " is thinking")
            self.eat()

            # Gotta Eat
            print(str(self.name) + " is eating")

            # Put the forks down
            self.right_fork.release()
            print(str(self.name) + " released the right fork")
            self.left_fork.release()
            print(str(self.name) + " released the left fork")


if __name__ == "__main__":

    # number of philosopher
    n = 5

    # TODO #1: create n=5 forks
    #forks = threading.Semaphore(value=n)
    forks = []
    for i in range(n):
        forks.append(threading.Lock())

    # TODO #2: create n=5 philosophers, allocating the forks in a way to avoid the deadlock
    philosophers = []
    for i in range(n):
        philosophers.append(Philosopher(i, forks[i % n], forks[(i + 1) % n]))

    # TODO #3: start the philosopher threads
    Philosopher.running = True
    for philosopher in philosophers:
        philosopher.start()
    Philosopher.running = False
