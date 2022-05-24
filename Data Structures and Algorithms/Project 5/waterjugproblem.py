import math
import unittest
import queue


# Creating constructor method to think about the three inputs in terms of variable names
# Variable names to represent the states of the water jugs (capacity of jug, initial or starting_state, and goal or c)
def __init__(self, capacity, initial, goal, a, b, c):
    self.capacity = capacity
    self.initial = initial
    self.goal = goal
    self.a = a
    self.b = b
    self.c = c


def findWaterContainerPath(a, b, c):
    """ DOCUMENTATION FOR THIS FUNCTION """
    starting_state = (0, 0)
    final_path = list()
    final_path.append(starting_state)

    """ THIS IS WHERE THE REAL WORK FOR THIS ASSIGNMENT WILL BE """


# Solving the 3 operations for the water jug problem
# Fill Jug A, Fill Jug B, Pour from A to B, and from B to A, and lastly, pour out Jugs A & B
# Fill Jug A
def fill_a(self, x, y):
    x = self.a
    return x, y


# Fill Jug B
def fill_b(self, x, y):
    x = self.b
    return x, y


# Pour Jug A into Jug B
def pour_a_to_b(self, x, y):
    if x + y <= self.b:
        x = 0
        y = x + y
    else:
        temp = self.b - y
        x = x - temp
        y = self.b
    return x, y


# Pour Jug B into A
def pour_b_to_a(self, x, y):
    if x + y <= self.a:
        x = x + y
        y = 0
    else:
        temp = self.a - x
        y = y - temp
        x = self.a
    return x, y


# Empty Jug A
def pour_out_a(self, x, y):
    x = 0
    return x, y


# Empty Jug B
def pour_out_b(self, x, y):
    y = 0
    return x, y


# Method to return list of successors to state
# This is a redundant function that will not be utilized...
# ...just trying to visualize how to create a list from traversal operations - ie. Traversed or visited nodes
def neighbor(self, a, b):
    neighbors = []
    (C1, C2) = self.capacity
    if a > 0:
        neighbors.append((0, b))  # Dump out Jug A
    if b > 0:
        neighbors.append((a, 0))  # Dump out Jug B
    if b < C2 and a > 0:
        pour_into = min(a, C2 - b)
        neighbors.append((a - pour_into, b + pour_into))  # Pour Jug A into Jug B
    if a < C1 and b > 0:
        pour_out = min(b, C1 - a)
        neighbors.append((a + pour_into, b - pour_into))  # Pour Jug B into Jug A
    return neighbors


# Breadth first search function
def bfs(g, start, traversed=None):
    if traversed is None:
        traversed = list()
    final_path = list
    start.setDistance(0)
    start.setPred(None)
    vert_queue = queue()
    vert_queue.enqueue(start)
    vert_queue.append(start)
    while vert_queue.size() > 0:
        current_vert = vert_queue.dequeue()
        # Build the graph before entering the inner loop
        if start not in traversed:
            traversed.append(start)
            for x in g[start]:
                if x not in traversed:
                    vert_queue.append(str(x))
            vert_queue.append(final_path[0])
        del vert_queue[0]
        if vert_queue:
            start = vert_queue[0]
        # look at the current state and determine what are the eligible states
        # g.addEdge((0,0), (3,0)) add edge for filling container a
        # for nbr in currentVert.getConnections():
        # if (nbr.getColor() == 'white'):
        # vertQueue.enqueue(nbr)
        # currentVert.setColor('black')

    return final_path


def traverse(y):
    x = y
    while (x.getPred()):
        print(x.getId())
        x = x.getPred()
    print(x.getId())


""" ADD ANY OTHER (HELPER) FUNCTIONS THAT ARE NEEDED HERE """


class TestWaterContainerGraphSearch(unittest.TestCase):

    def testFindWaterContainerPath(self):
        """ INSERT DESCRIPTION OF WHAT THIS TEST IS CHECKING """
        """ IMPLEMENT YOUR FIRST TEST HERE """
        pass

    """ ADD MORE TESTS TO CHECK YOUR FUNCTIONS WORKS FOR OTHER VALUES """


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


# unittest_main() - run all of TestWaterContainerGraphSearch's methods (i.e. test cases)
def unittest_main():
    unittest.main()


# evaluates to true if run as standalone program
if __name__ == '__main__':
    main()
    unittest_main()
