[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10686602&assignment_repo_type=AssignmentRepo)
## Assign 4

### Coding directions

In this fourth assignment we will be implementing a few graph algorithms that
aim to solve the minimum spanning tree (MST) problem. The MST problem is about
finding the spanning tree of a connected, undirected, weighted graph, _G = (V,
E)_, that has the lowest total cost/distance. Specifically, a solution to the MST
problem is a spanning tree, _T = (V, F)_, where _F_ is a subset of _E_, and the sum of
the edge weights in _F_ is lowest across all possible spanning trees of _G_. 

### Functions to be implemented

Note that both of the functions to be implemented have the exact same input and
output (see example input and output below for more details)

* __prims(W)__ carry out Prim's algorithm on a graph represented by its weight matrix
  * input: adjacency weight matrix, W such that W[i][j] is the weight from vertex i to vertex j
  * output/return: a list of tuples where each tuple is an edge in the MST and is in the form (i, j, wij), denoting an edge between vertex i and j with a weight of wij (see example below)

* __kruskals(W)__ carry out Dijkstra's algorithm on a graph represented by its weight matrix
  * input: adjacency weight matrix, W such that W[i][j] is the weight from vertex i to vertex j
  * output/return: a list of tuples where each tuple is an edge in the MST and is in the form (i, j, wij), denoting an edge between vertex i and j with a weight of wij (see example below)




### Example with a 3-vertex graph

    # a graph defined by its adjacency matrix
    g = [ [0, 8, 9], [8, 0, 3], [9, 3, 0] ]
    prim_result = prims(g)
    kruskal_result = kruskals(g)

    # both prim_result and kruskal_result should be a (Python) list of (Python) tuples, 
    # which represents a (mathematical) set of (mathematical) tuples 
    print(prim_result) 
    [(0, 1, 8), (1, 2, 3)]

Note: when creating a tuple for an edge, be sure that the lower vertex number
comes first. That is, if an edge between vertex 0 and vertex 3 is to be added to
the MST (and has a weight of c), then vertex 0 should come first so that it
looks like: (0, 3, c). DO NOT add/include the edge that looks like (3, 0, c). 


Also, as with previous assignments, be sure to document your code properly by adding
[docstrings](https://www.python.org/dev/peps/pep-0257/#what-is-a-docstring).  In
addition to documentation, be sure that a reasonable coding style is followed as
well (e.g. two blank lines between functions). Your program will not pass the
first checks/tests if there is not proper documentation and styling.

Before beginning, remember that the code provided a starting point includes
several placeholders, so be sure that you remove/replace: 
  * any and all comments that are in ALL CAPS,
  * any instances of _pass_, and 
  * all other placeholder code/comments. 

