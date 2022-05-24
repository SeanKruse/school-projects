#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

//a two-dimension array recording all numbers in the given sudoku puzzle
//Declaring all global variables
int sudokuPuzzle[9][9];
int boolCol[9];
int boolRow[9];
int boolGrid[9];

//worker threads
tid_column int [9];
tid_row int [9];
tid_grid int [9];

#define num_threads 27


//a 9 element Boolean array for 9 columns
typedef int bool;
#define true // if elements are valid
#define false // if elements are invalid

//a 9 element Boolean array for 9 rows
typedef int bool;
#define TRUE 1 // if elements are valid
#define FALSE 0 // if elements are invalid

//a 9 element Bollean array for 9 3x3 grids
typedef int bool;
#define true // if elements are valid
#define false // if elements are invalid

typedef struct {
    int topRow; // index of top row to be checked by a worker thread
    int bottomRow; // index of bottom row to be checked by a worker thread
    int leftColumn; // index of left column to be checked by a worker thread
    int rightColumn; // index of right column to be checked by a worker thread
} parameters;

//from textbook
parameters *data = (parameters *) malloc(sizeof(parameters));
data->row = 1;
data->column = 1;
/* Now create the thread passing it data as a parameter*/

int main(int argc, char *argv[]) {

    bool x = TRUE, y = FALSE;
    int i = 0;
    int j = 0;

    //read in from text file to fill sudoku puzzle grid
    FILE *SudokuPuzzle.txt;
    SudokuPuzzle.txt = fopen(argv[1], "r");

    for(i = 0; i < 9; i++){
        for(j = 0; j < 9; j++){
            fscanf(SudokuPuzzle.txt, "%d ", &sudokuPuzzle[i][j]);
        }
    }
}
