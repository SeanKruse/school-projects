import math

'''
Water-Jug solution using Breadth First Search Algorithm
'''

print("Solution for water jug problem")
x_capacity = input("Enter Jug 1 capacity:")
y_capacity = input("Enter Jug 2 capacity:")
end = input("Enter target volume:")


def bfs(start, end, x_capacity, y_capacity):
    final_path = []
    front = []
    front.append(start)
    visited = []
    visited.append(start)
    while not (not front):
        current = front.pop()
        x = current[0]
        y = current[1]
        final_path.append(current)

        # rule 1
        if current[0] < x_capacity and ([x_capacity, current[1]] not in visited):
            front.append([x_capacity, current[1]])
            visited.append([x_capacity, current[1]])

        # rule 2
        if current[1] < y_capacity and ([current[0], y_capacity] not in visited):
            front.append([current[0], y_capacity])
            visited.append([current[0], y_capacity])

        # rule 3
        if current[0] > x_capacity and ([0, current[1]] not in visited):
            front.append([0, current[1]])
            visited.append([0, current[1]])

        # rule 4
        if current[1] > y_capacity and ([x_capacity, 0] not in visited):
            front.append([x_capacity, 0])
            visited.append([x_capacity, 0])

        # rule 5
        # (x, y) -> (min(x + y, x_capacity), max(0, x + y - x_capacity)) if y > 0
        if current[1] > 0 and ([min(x + y, x_capacity), max(0, x + y - x_capacity)] not in visited):
            front.append([min(x + y, x_capacity), max(0, x + y - x_capacity)])
            visited.append([min(x + y, x_capacity), max(0, x + y - x_capacity)])

        # rule 6
        # (x, y) -> (max(0, x + y - y_capacity), min(x + y, y_capacity)) if x > 0
        if current[0] > 0 and ([max(0, x + y - y_capacity), min(x + y, y_capacity)] not in visited):
            front.append([max(0, x + y - y_capacity), min(x + y, y_capacity)])
            visited.append([max(0, x + y - y_capacity), min(x + y, y_capacity)])

    return final_path


# start state: x = 0 , y = 0
start = [0, 0]
# end = 2
# x_capacity = 4
# y_capacity = 3

# condition for getting a solution:
# the target volume 'end' should be a multiple of gcd(a,b)

if int(end) % math.gcd(int(x_capacity), int(y_capacity)) == 0:
    path = bfs(int(x_capacity), int(y_capacity), int(end))
else:
    print("No solution for containers with these sizes and with this final goal amount")

print(path)
