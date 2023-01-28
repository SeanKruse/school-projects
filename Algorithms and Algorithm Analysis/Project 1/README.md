[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=9773643&assignment_repo_type=AssignmentRepo)
## Assign 1

### Coding directions

In this first assignment you'll be implementing linear and binary search and
comparing their performance. Use assign01.py as a starting point and complete
the functions so that they return the proper data or value. Note that you should
remove/replace a) any and all comments that are in ALL CAPS, b) any instances of
_pass_, and c) all other placeholder code/comments. 

It is probably most helpful to look at the starter code in assign01.py and then
to refer back to the directions below for any additional info needed. Also, be
sure to document your code properly by adding
[docstrings](https://www.python.org/dev/peps/pep-0257/#what-is-a-docstring).  In
addition to documentation, be sure that a reasonable coding style is followed as
well (e.g. two blank lines between functions). Your program will not pass the
first checks done by the [linter](https://realpython.com/python-code-quality/#linters) 
if there is not proper documentation and styling.

Also be sure to add your name to the top of the assignment where the placeholder, 
'INSERT YOUR NAME HERE' is. 

### Functions to be implemented
* __linearSearch(list_of_items, item_sought)__ should search list_of_items for item_sought using linear search
* __binarySearch(list_of_items, item_sought)__ should search list_of_items for item_sought using binary search

Both functions should return a tuple containing three elements, namely:
1. a `bool` representing T/F if the item was found in the list_of_items or not
2. an `int` representing the total number of comparisons made before the search was stopped
3. a `float` representing the number of seconds required for the search to execute
