[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=10044612&assignment_repo_type=AssignmentRepo)
## Assign 2

### Coding directions

In this second assignment we will be implementing several common sorting
algorithms but with some slight variations. As with the first assignment, you'll
use assign02.py as a starting point and complete the functions from there. Of
course, you're free to implement new functions that can serve as helper
functions that can be called from the ones in assign02.py.  As with assign01 
we'll want to return the run time so that we can compare the performance of 
the algorithms. 

Note that you should remove/replace: 
  * any and all comments that are in ALL CAPS,
  * any instances of _pass_, and 
  * all other placeholder code/comments. 

It is probably most helpful to look at the starter code in assign02.py and then
to refer back to the directions below for any additional info needed. Also, be
sure to document your code properly by adding
[docstrings](https://www.python.org/dev/peps/pep-0257/#what-is-a-docstring).  In
addition to documentation, be sure that a reasonable coding style is followed as
well (e.g. two blank lines between functions). Your program will not pass the
first checks/tests if there is not proper documentation and styling.

### Functions to be implemented
* __bubbleSort(a_list)__ should sort items in list_of_items in ascending order
* __mergeSort(a_list, split_by_k=2)__ should sort items in list_of_items in ascending order
* __quickSort(a_list, pivot='first')__ should sort items in list_of_items in ascending order
* __radixSort(a_list)__ should sort items in list_of_items in ascending order

Note that there are essentially two version of mergeSort() and two versions of
quickSort() that need to be implemented. The reason is that we want to
understand how mergeSort behaves when we split by 3 (rather than 2), and how
the choice of the pivot location in quickSort affects runtime for different
types of input lists (e.g. shuffled vs sorted in reverse). 

The above functions should return a tuple containing two elements, namely:
1. a `list` with all the items from the input list in asending sorted order
2. a `float` representing number of seconds needed to sort the items
