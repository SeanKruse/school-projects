"""
<Sean Kruse>
"""

# for timing checks
import time

# use a very large number as placeholder for infinity
import sys
INF = sys.maxsize


def adjMatFromFile(filename):
    """ Create an adj/weight matrix from a file with verts, neighbors, and weights. """
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
    """ Print an adj/weight matrix padded with spaces and with vertex names. """
    res_str = '     ' + ' '.join([str(v).rjust(width, ' ') for v in range(len(mat))]) + '\n'
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
    distance (list of lists): A matrix containing the shortest distances between
    every pair of vertices in the graph. distance[i][j] represents the shortest
    distance between vertex i and vertex j.

    """
    # Initialize the distance array to be the same as the weight array.
    distance = []
    for i in range(len(W)):
        row = []
        for j in range(len(W)):
            row.append(W[i][j])
        distance.append(row)
    # Iterate through all vertices in the graph, as intermediate points.
    for k in range(len(W)):
        # Iterate through all pairs of vertices i, j.
        for i in range(len(W)):
            for j in range(len(W)):
                # If the path from i to j through k is shorter than the
                # current shortest path from i to j, update D[i][j] with the
                # shorter distance.
                if distance[i][j] > distance[i][k] + distance[k][j]:
                    distance[i][j] = distance[i][k] + distance[k][j]
    # Return the distance array.
    return distance


def assign03_main():
    """ Demonstrate the functions, starting with creating the graph. """
    g = adjMatFromFile("py_vs_X_assign3.txt")

    # Run Floyd's algorithm
    start_time = time.time()
    res_floyd = floyd(g)
    elapsed_time_floyd = time.time() - start_time
    # Print the elapsed time
    print(f"  Floyd's elapsed time: {elapsed_time_floyd:.2f}")
    # Print the graph
    printAdjMat(res_floyd, 3)


# Check if the program is being run directly (i.e. not being imported)
if __name__ == '__main__':
    assign03_main()
