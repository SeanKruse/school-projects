"""
Assign 03 - <Sean Kruse>

Directions:
    * Complete the graph algorithm functions given below. Note that it may be
      helpful to define auxiliary/helper functions that are called from the
      functions below.  Refer to the README.md file for additional info.

    * NOTE: As with other assignments, please feel free to share ideas with
      others and to reference sources from textbooks or online. However, be
      sure to **cite your resources in your code. Also, do your best to attain
      a reasonable grasp of the algorithm that you are implementing as there
      will very likely be questions related to it on quizzes/exams.

    * NOTE: Remember to add a docstring for each function, and that a
      reasonable coding style is followed (e.g. blank lines between functions).
      Your program will not pass the tests if this is not done!
"""
# could be useful for Dijkstra
from queue import PriorityQueue

# for timing checks
import time

# use a very large number as placeholder for infinity
import sys
INF = sys.maxsize


def adjMatFromFile(filename):
    """ Create an adj/weight matrix from a file with verts, neighbors, and
    weights.
    """
    f = open(filename, "r")
    n_verts = int(f.readline())
    print(f" n_verts = {n_verts}")
    adjmat = [[INF] * n_verts for i in range(n_verts)]
    for i in range(n_verts):
        adjmat[i][i] = 0
    for line in f:
        int_list = [int(i) for i in line.split()]
        vert = int_list.pop(0)
        assert len(int_list) % 2 == 0
        n_neighbors = len(int_list) // 2
        neighbors = [int_list[n] for n in range(0, len(int_list), 2)]
        distances = [int_list[d] for d in range(1, len(int_list), 2)]
        for i in range(n_neighbors):
            adjmat[vert][neighbors[i]] = distances[i]
    f.close()
    return adjmat


def printAdjMat(mat, width=3):
    """ Print an adj/weight matrix padded with spaces and with vertex names."""
    res_str = '     ' + ' '.join([str(v).rjust(width, ' ')
                                 for v in range(len(mat))]) + '\n'
    res_str += '    ' + '-' * ((width + 1) * len(mat)) + '\n'
    for i, row in enumerate(mat):
        row_str = [str(elem).rjust(width, ' ') if elem < INF else ' 999' for elem in row]
        res_str += ' ' + str(i).rjust(2, ' ') + ' |' + ' '.join(row_str) + "\n"
    print(res_str)


def floyd(W):
    """
    Adapted from starter code provided by Dr. Geinitz.
    Documentation provided by ChatGPT-3:
    Carry out Floyd's algorithm to find the shortest path between every pair
    of vertices in a weighted graph using W as a weight/adjacency matrix.

    Parameters:
    W (list of lists): A weight/adjacency matrix representing the graph.
    W[i][j] represents the weight of the edge from vertex i to vertex j.
    If there is no edge between vertex i and vertex j, W[i][j] is infinity.

    Returns:
    D (list of lists): A matrix containing the shortest distances between
    every pair of vertices in the graph. D[i][j] represents the shortest
    distance between vertex i and vertex j.

    """
    # Initialize the distance array to be the same as the weight array.
    D = []
    for i in range(len(W)):
        row = []
        for j in range(len(W)):
            row.append(W[i][j])
        D.append(row)
    # Iterate through all vertices in the graph, as intermediate points.
    for k in range(len(W)):
        # Iterate through all pairs of vertices i, j.
        for i in range(len(W)):
            for j in range(len(W)):
                # If the path from i to j through k is shorter than the
                # current shortest path from i to j, update D[i][j] with the
                # shorter distance.
                if D[i][j] > D[i][k] + D[k][j]:
                    D[i][j] = D[i][k] + D[k][j]
    # Return the distance array.
    return D


def dijkstra_w_pri_queue(W, sv):
    """
    Starting Example Source:
    https://levelup.gitconnected.com/dijkstra-algorithm-in-python-8f0e75e3f16e
    Adapted from Source:
    ChatGPT-3
    GitHub Copilot was used to generate some of the code in this function.
    Documentation provided by ChatGPT-3:
    Use Dijkstra's algorithm to find the shortest path from a starting vertex
    sv to every other vertex in a weighted graph, represented by an adjacency
    matrix W, using a priority queue to efficiently select the next vertex to
    explore.

    Parameters:
    W (list of lists): A weight/adjacency matrix representing the graph.
    sv (int): The starting vertex to find the shortest path from.

    Returns:
    vertexCosts[i] represents the cost of the shortest path from vertex sv to
    vertex i.
    parentsMap[i] represents the parent vertex of vertex i in the shortest
    path from vertex sv to vertex i.
    """
    # Initialize a set of visited vertices.
    visited = set()
    # Initialize a map of parent vertices.
    parents = {}
    # Initialize a priority queue object.
    pq = PriorityQueue()
    distances = [INF] * len(W)
    distances[sv] = 0
    # Add the starting vertex to the priority queue.
    pq.put((distances[sv], sv))
    # While there are vertices in the priority queue, continue searching.
    while not pq.empty():
        # Get the vertex with the smallest distance from the priority queue.
        dist, vertex = pq.get()
        # If the vertex has already been visited, continue.
        if vertex in visited:
            continue
        # Add the vertex to the set of visited vertices.
        visited.add(vertex)
        # For each neighbor of the current vertex, update the cost of the
        # shortest path to that neighbor if the cost of the path through the
        # current vertex is less than the current cost of the shortest path
        # to that neighbor.
        for neighbors in range(len(W)):
            if neighbors in visited:
                continue
            weight = W[vertex][neighbors]
            neighbor_dist = distances[vertex] + weight
            if neighbor_dist < distances[neighbors]:
                distances[neighbors] = neighbor_dist
                parents[neighbors] = vertex
                pq.put((distances[neighbors], neighbors))
    # Return the cost of the shortest path from the starting vertex sv to
    # every other vertex in the graph and the parent of each vertex in that
    # shortest path.
    return distances


def dijkstra_w_array(W, sv):
    """
    Documentation provided by ChatGPT-3:
    GitHub Copilot was used to generate some of the code in this function.
    Dijkstra's algorithm for finding the shortest path from a given starting
    vertex to all other vertices in a graph, implemented using an array with
    adjacency matrix W.

    Parameters:
    W (list of lists): A weight/adjacency matrix representing the graph.
    sv (int): The starting vertex to find the shortest path from.

    Returns:
    distance [i]: A list containing the shortest distance from the starting
    vertex to every other vertex in the graph.
    """
    # Initialize the distance array to be infinity for all vertices.
    distances = [INF] * len(W)
    # Initialize the visited array to be False for all vertices.
    visited = [False] * len(W)
    # Set the distance of the starting vertex to be 0.
    distances[sv] = 0

    while True:
        # Find the vertex with the smallest distance that has not been visited.
        min_dist = INF
        min_vert = None
        for i in range(len(W)):
            if not visited[i] and distances[i] < min_dist:
                min_dist = distances[i]
                min_vert = i
        # If all vertices have been visited, break out of the loop
        # and return the distance array.
        if min_vert is None:
            break
        # Mark the vertex with the smallest distance as visited.
        visited[min_vert] = True
        # Update the distance of all unvisited
        # neighbors of the vertex with the smallest distance.
        for i in range(len(W)):
            if not visited[i] and W[min_vert][i] != INF:
                distances[i] = min(
                    distances[i], distances[min_vert] + W[min_vert][i])
    # Return the distance array containing the shortest distances from the
    # starting vertex to every other vertex in the graph.
    return distances


def assign03_main():
    """ Demonstrate the functions, starting with creating the graph. """
    g = adjMatFromFile("graph_20verts.txt")
    if len(g) <= 20:
        printAdjMat(g, width=4)

    # Run Floyd's algorithm
    start_time = time.time()
    res_floyd = floyd(g)
    elapsed_time_floyd = time.time() - start_time
    if len(g) <= 20 and res_floyd:
        printAdjMat(res_floyd, width=4)

    # Run Dijkstra's for a single starting vertex, v2
    start_vert = 2
    res_dijkstra_single1 = dijkstra_w_pri_queue(g, start_vert)
    print(
        f" dijkstra_w_pri_queue (for starting vert {start_vert})= : {res_dijkstra_single1}")
    res_dijkstra_single2 = dijkstra_w_array(g, start_vert)
    print(
        f" dijkstra_w_array (for starting vert {start_vert})= : {res_dijkstra_single2}")
    if len(g) <= 20 and res_floyd:
        print(
            f" floyd's algorithm    (for starting vert {start_vert}): {res_floyd[start_vert]}")
        print(
            f" dijkstra_w_pri_queue (for starting vert {start_vert}): {res_dijkstra_single1}")
        print(
            f" dijkstra_w_array     (for starting vert {start_vert}): {res_dijkstra_single2}")
    elif res_floyd:
        print(
            f" adjacency matrix for starting vert {start_vert}      : {g[start_vert][:20]}")
        print(
            f" floyd's algorithm    (for starting vert {start_vert}): {res_floyd[start_vert][:20]}")
        print(
            f" dijkstra_w_pri_queue (for starting vert {start_vert}): {res_dijkstra_single1[:20]}")
        print(
            f" dijkstra_w_array     (for starting vert {start_vert}): {res_dijkstra_single2[:20]}")

    # Check that the two produce same results by comparing result from
    # dijkstra with the corresponding row from floyd's output matrix
    assert res_floyd[start_vert] == res_dijkstra_single1
    assert res_floyd[start_vert] == res_dijkstra_single2

    # Run Dijkstra's overall starting points (note this is not the intended way
    # to utilize this algorithm, however we are using
    # it as point of comparion).
    res_dijkstra1 = [[None] * len(g) for i in range(len(g))]
    start_time = time.time()
    for sv in range(len(g)):
        res_dijkstra1[sv] = dijkstra_w_pri_queue(g, sv)
    elapsed_time_dijkstra1 = time.time() - start_time

    res_dijkstra2 = [[None] * len(g) for i in range(len(g))]
    start_time = time.time()
    for sv in range(len(g)):
        res_dijkstra2[sv] = dijkstra_w_array(g, sv)
    elapsed_time_dijkstra2 = time.time() - start_time

    # Compare times, Dijkstra's should be slower (for non-trivial sized graphs)
    print(
        f"  Dijkstra's w/ pri queue elapsed time (for all starts): {elapsed_time_dijkstra1:.2f}")
    print(
        f"  Dijkstra's w/ array elapsed time (for all starts): {elapsed_time_dijkstra2:.2f}")
    print(f"  Floyd's elapsed time: {elapsed_time_floyd:.2f}")

    # Double check again that the results are all the same
    assert res_floyd == res_dijkstra1
    assert res_floyd == res_dijkstra2


# Check if the program is being run directly (i.e. not being imported)
if __name__ == '__main__':
    assign03_main()
