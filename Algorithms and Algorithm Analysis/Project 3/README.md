[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10229510&assignment_repo_type=AssignmentRepo)
## Assign 3

### Coding directions

In this third assignment we will be implementing a few graph algorithms with the
aim of solving two different versions of shortest-path problems. The first
version is the All-pairs-shortest-path (APSP) problem, which **Floyd's** algorithm
provides a solution to. The second is the version is the
single-source-shortest-path problem, for which **Dijkstra's** algorithm is a
solution. 

Although these two algorithms solve slightly different problems (with Floyd's
solving a harder, or larger problem), we will want to compare them directly. In
order to do so we will use Dijkstra's algorithm to also solve the APSP problem.
This can be done by running Dijkstra's algorithm for each vertex in the graph. 

### Functions to be implemented
* __floyd(W)__ carry out Floyd's algorithm on a graph represented by its weight matrix
  * input: adjacency matrix, W, such that W[i][j] is the weight from vertex i to vertex j
  * output/return: a distance matrix where [i][j] entry is the length of the shortest path starting at vertex i and ending at vertex j for all i and j (should have same dims as W)

* __dijkstra_w_pri_queue(W, sv)__ carry out Dijkstra's algorithm on a graph represented by its weight matrix and starting at the vertex denoted by 'sv' __using a priority queue__
  * input: adjacency matrix, W, such that W[i][j] is the weight from vertex i to vertex j; and starting vertex, 'start' that is an integer between 0 and (n-1), where n is the number of vertices in the graph
  * output/return: a single Python list where the entry at [j] denotes the length of the shortest path from 'start' to vertex j (for j = 0, ..., n-1) 

* __dijkstra_w_array(W, sv)__ carry out Dijkstra's algorithm on a graph represented by its weight matrix and starting at the vertex denoted by 'sv' __using Python lists/sets__
  * (same input and output as above)

It is probably most helpful to look at the starter code in assign03.py and then
to refer back to the directions below for any additional info needed. Also, be
sure to document your code properly by adding
[docstrings](https://www.python.org/dev/peps/pep-0257/#what-is-a-docstring).  In
addition to documentation, be sure that a reasonable coding style is followed as
well (e.g. two blank lines between functions). Your program will not pass the
first checks/tests if there is not proper documentation and styling.

If you do use an online resource, or any other reference, be sure to cite it in
your program where it is used. 

Before beginning, remember that the code provided a starting point includes
several placeholders, so be sure that you remove/replace: 
  * any and all comments that are in ALL CAPS,
  * any instances of _pass_, and 
  * all other placeholder code/comments. 


Finally, here is some sample output when running the program: 

    n_verts = 10
    
    Adjacency matrix (999 indicates no edge exists)
          0    1    2    3    4    5    6    7    8    9
      --------------------------------------------------
    0 |   0    6  999  999  999  999    6  999  999  999
    1 |   5    0  999  999  100  999   43  999  999  999
    2 |  74  999    0  999    8    2  999  999  999  999
    3 | 999  999  999    0  999  999  999    4  999    6
    4 | 999  999    7  999    0  999   64    1  999  999
    5 | 999  999    5  999  999    0  999  999  999  999
    6 |   9   96  999  999  999  999    0  999    5  999
    7 | 999  999  999   10    3  999  999    0  999  999
    8 | 999  999  999  999  999   74    7  999    0    4
    9 | 999  999  999    6  999  999  999  999    5    0

    Floyd's D_10 matrix
          0    1    2    3    4    5    6    7    8    9
      --------------------------------------------------
    0 |   0    6   35   21   28   37    6   25   11   15
    1 |   5    0   40   26   33   42   11   30   16   20
    2 |  46   52    0   19    8    2   37    9   30   25
    3 |  27   33   14    0    7   16   18    4   11    6
    4 |  38   44    7   11    0    9   29    1   22   17
    5 |  51   57    5   24   13    0   42   14   35   30
    6 |   9   15   29   15   22   31    0   19    5    9
    7 |  37   43   10   10    3   12   28    0   21   16
    8 |  16   22   24   10   17   26    7   14    0    4
    9 |  21   27   20    6   13   22   12   10    5    0

    Individual row from Floyd's: D_10[2]
    floyd's algorithm    (for starting vert 2): [46, 52, 0, 19, 8, 2, 37, 9, 30, 25]
    
    Lists returned by Dijkstra's
    dijkstra_w_pri_queue (for starting vert 2): [46, 52, 0, 19, 8, 2, 37, 9, 30, 25]
    dijkstra_w_array     (for starting vert 2): [46, 52, 0, 19, 8, 2, 37, 9, 30, 25]

    Runtimes (not very interesting for n = 10)
    Dijkstra's w/ pri queue elapsed time (single start vert): 0.00
    Dijkstra's w/ array elapsed time (single start vert): 0.00

    Dijkstra's w/ pri queue elapsed time (for all starts): 0.00
    Dijkstra's w/ array elapsed time (for all starts): 0.00
    Floyd's elapsed time: 0.00
