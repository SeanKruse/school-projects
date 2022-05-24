import math
import unittest
from queue import Queue


def findWaterContainerPath(a, b, c):
    # Set the starting state for the graph to be (0,0) or Jug A and Jug B to be empty
    starting_state = (0, 0)
    # Initializing list to save the path of vertices that created from the graph
    final_path = list()
    # Now that the list is initialized, append the starting state to the list - First item in the list is (0,0)
    final_path.append(starting_state)
    # THIS IS WHERE THE REAL WORK FOR THIS ASSIGNMENT WILL BE


# Solving the 3 operations for the water jug problem
# Fill Jug A, Fill Jug B, Pour from A to B, and from B to A, and lastly, pour out Jugs A & B
# Fill Jug A
def fill_a(self, x, y):
    # Set capacity to be equal to a
    x = self.a
    return x, y


# Fill Jug B
def fill_b(self, x, y):
    # Set capacity to be equal to b
    x = self.b
    return x, y


# Empty Jug A
def pour_out_a(self, x, y):
    # Set a to 0
    x = 0
    return x, y


# Empty Jug B
def pour_out_b(self, x, y):
    # Set b to 0
    y = 0
    return x, y


# Pour Jug A into Jug B
def pour_a_to_b(self, x, y):
    # if a + b is less than or equal to the capacity of b, then pour a into b and set a to be empty
    if x + y <= self.b:
        y = x + y
        x = 0
    # else create a temp variable that stores capacity of b minus current amount
    # set a equal to a minus the difference of b's current amount and b's capacity
    # finally set b back to capacity
    else:
        temp = self.b - y
        x = x - temp
        y = self.b
    return x, y


# Pour Jug B into A
def pour_b_to_a(self, x, y):
    # if a + b is less than or equal to the capacity of a, then pour b into a and set b to be empty
    if x + y <= self.a:
        x = x + y
        y = 0
    # else create a temp variable that stores capacity of a minus current amount
    # set b equal to b minus the difference of a's current amount and a's capacity
    # finally set a back to capacity
    else:
        temp = self.a - x
        y = y - temp
        x = self.a
    return x, y


# Breadth first search function
# I have the concept of what I'm trying to do, but the python code eludes me for returning the path vertices
# The concept would be a list of the path - final_path, and a queue holding all the vertices visited
# When building the graph it would need to check against 6 possible states for the current water in both jugs
# I have some pseudo-logic for all 6 states and feel like with
# I would need to track visited vertices, as not to check states against these vertices again
visited = []


def bfs(start, capacity_a, capacity_b):
    visited = []
    visited.append(start)
    final_path = []
    vert_queue = Queue()
    vert_queue.enqueue(start)

    while vert_queue.size() > 0:
        current_vert = vert_queue.dequeue()
        x = vert_queue[0]
        y = vert_queue[1]
        final_path.append(current_vert)

        # Build the graph before entering the inner loop
        # look at the current state and determine what are the eligible states
        # 6 states to compare against

        # Inner loop with not visited - appends start to visited list
        if start not in visited:
            visited.append(start)

        # A = less than capacity then fill
        if current_vert[0] < capacity_a:
            if [capacity_a, current_vert[1]] not in visited:
                vert_queue.enqueue(fill_a(x, y))
                visited.append(fill_b(x, y))

        # B = less than capacity then fill
        if current_vert[1] < capacity_b:
            if [current_vert[0], capacity_b] not in visited:
                vert_queue.enqueue(fill_b(x, y))
                visited.append(fill_b(x, y))

        # pour out a
        if current_vert[0] > capacity_a:
            if [0, current_vert[1]] not in visited:
                vert_queue.enqueue(pour_out_a(x, y))
                visited.append(pour_out_a(x, y))

        # pour out b
        if current_vert[1] > capacity_b:
            if [capacity_a, 0] not in visited:
                vert_queue.enqueue(pour_out_b(x, y))
                visited.append(pour_out_b(x, y))

        # pour a to b
        # (x, y) -> (min(x + y, capacity_a), max(0, x + y - capacity_a)) if y > 0
        if current_vert[1] > 0:
            if [pour_a_to_b(x,y)] not in visited:
                vert_queue.enqueue(pour_a_to_b(x, y))
                visited.append(pour_a_to_b(x, y))

        # pour b to a
        if current_vert[0] > 0:
            if [pour_b_to_a(x, y)] not in visited:
                vert_queue.enqueue(pour_b_to_a(x, y))
                visited.append(pour_b_to_a(x, y))

    return final_path


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

    b = bfs(visited, capacity_a, capacity_b)

    print(b)

if __name__ == '__main__':
    main()

