import math
import unittest

def findWaterContainerPath(a, b, c):
    """ DOCUMENTATION FOR THIS FUNCTION """
    starting_state = (0, 0)
    final_path = list()
    final_path.append(starting_state)
def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)


def Calc(to_jugcup, jug_cup, d):
    fromJug = jug_cup
    toJug = 0
    c = 1
    while ((fromJug is not d) and (toJug is not d)):
        temp = min(fromJug, to_jugcup - toJug)
        toJug = toJug + temp
        fromJug = fromJug - temp
        c = c + 1
        if ((fromJug == d) or (toJug == d)):
            break
        if fromJug == 0:
            fromJug = jug_cup
            c = c + 1
        if toJug == to_jugcup:
            toJug = 0
            c = c + 1
    return c


def min_s(n, m, d):
    if m > n:
        temp = m
        m = n
        n = temp

    if (d % (gcd(n, m)) != 0):
        return -1
    return (min(Calc(n, m, d), Calc(m, n, d)))


a = int(input("Enter the capacity of container A: "))
b = int(input("Enter the capacity of container B: "))
c = int(input("Enter the goal quantity: "))
print('Minimum number of cs required is', min_s(a, b, c))

""" ADD ANY OTHER (HELPER) FUNCTIONS THAT ARE NEEDED HERE """


def main():
    capacity_a = input("Enter the capacity of container A: ")
    capacity_b = input("Enter the capacity of container B: ")
    goal_amount = input("Enter the goal quantity: ")

    # ADD SOME TYPE/VALUE CHECKING FOR THE INPUTS (OR INSIDE YOUR FUNCTION)

    if int(goal_amount) % math.gcd(int(capacity_a), int(capacity_b)) == 0:
        path = findWaterContainerPath(int(capacity_a), int(capacity_b), int(goal_amount))
    else:
        print("No solution for containers with these sizes and with this final goal amount")

    print(path)

