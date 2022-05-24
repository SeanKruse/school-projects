// Team Project Alexander Sanford & Sean Kruse
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define NUM_CHILD_THREADS 27

//Declaring all global variables

typedef int bool;
#define TRUE 1 // if elements are valid
#define FALSE 0 // if elements are invalid

int sudokuPuzzle[9][9]; // 2D array to hold SudokuPuzzle.txt
bool colArray[9]; // Validity array for columns
bool rowArray[9]; // Validity array for rows
bool subArray[9]; // Validity array for subgrids

//worker threads
//9 worker threads for the columns saved into an array
pthread_t tid_column[9];
//9 worker threads for the rows saved into an array
pthread_t tid_row[9];
//9 worker threads for the 3x3 sub-grids saved into an array
pthread_t tid_subgrid[9];

void *rowCheck(void *param);
void *columnCheck(void *param);
void *subgridCheck(void *param);

typedef struct {
    int topRow; // index of top row to be checked by a worker thread
    int bottomRow; // index of bottom row to be checked by a worker thread
    int leftColumn; // index of left column to be checked by a worker thread
    int rightColumn; // index of right column to be checked by a worker thread
} parameters;

parameters columnObjects[9];
parameters rowObjects[9];
parameters subgridObjects[9];

int main() {
  
    int i, j;
    FILE *fp;

    fp = fopen("SudokuPuzzle.txt", "r");
    for (i = 0; i < 9; i++) {
        for (j = 0; j < 9; j++) {
            fscanf(fp, "%d", &sudokuPuzzle[i][j]);
        }
    }

    fclose(fp);

    printf("%s", "--------Sudoku Puzzle--------\n");

    for (i = 0; i < 9; i++) {
        for (j = 0; j < 9; j++) {
            printf("%d", sudokuPuzzle[i][j]);
            printf("\t");
        }
        printf("\n");
        printf("\n");
    }
    
    for (i = 0; i < 9; i++) {
        columnObjects[i].topRow = 0;
        columnObjects[i].bottomRow = 8;
        columnObjects[i].leftColumn = i;
        columnObjects[i].rightColumn = i;
    }

    for (i = 0; i < 9; i++) {
        rowObjects[i].topRow = i;
        rowObjects[i].bottomRow = i;
        rowObjects[i].leftColumn = 0;
        rowObjects[i].rightColumn = 8;
    }

    subgridObjects[0].topRow = subgridObjects[1].topRow = subgridObjects[2].topRow = 0;
    subgridObjects[0].bottomRow = subgridObjects[1].bottomRow = subgridObjects[2].bottomRow = 2;
    subgridObjects[3].topRow = subgridObjects[4].topRow = subgridObjects[5].topRow = 3;
    subgridObjects[3].bottomRow = subgridObjects[4].bottomRow = subgridObjects[5].bottomRow = 5;
    subgridObjects[6].topRow = subgridObjects[7].topRow = subgridObjects[8].topRow = 6;
    subgridObjects[6].bottomRow = subgridObjects[7].bottomRow = subgridObjects[8].bottomRow = 8;
    subgridObjects[0].leftColumn = subgridObjects[3].leftColumn = subgridObjects[6].leftColumn = 0;
    subgridObjects[0].rightColumn = subgridObjects[3].rightColumn = subgridObjects[6].rightColumn = 2;
    subgridObjects[1].leftColumn = subgridObjects[4].leftColumn = subgridObjects[7].leftColumn = 3;
    subgridObjects[1].rightColumn = subgridObjects[4].rightColumn = subgridObjects[7].rightColumn = 5;
    subgridObjects[2].leftColumn = subgridObjects[5].leftColumn = subgridObjects[8].leftColumn = 6;
    subgridObjects[2].rightColumn = subgridObjects[5].rightColumn = subgridObjects[8].rightColumn = 8;

    // create and wait for column workers to finish running
    for (i = 0; i < 9; i++) {
      pthread_create(&tid_column[i], NULL, columnCheck, &columnObjects[i]);
    }
    for (i = 0; i < 9; i++) {
      pthread_join(tid_column[i], NULL);  
    }

    // create and wait for row workers to finish running
    for (i = 0; i < 9; i++) {
      pthread_create(&tid_row[i], NULL, rowCheck, &rowObjects[i]);
    }
    for (i = 0; i < 9; i++){
      pthread_join(tid_row[i], NULL);  
    }

    // create and wait for subgrid workers to finish running
    for (i = 0; i < 9; i++) {
      pthread_create(&tid_subgrid[i], NULL, subgridCheck, &subgridObjects[i]);
    }
    for (i = 0; i < 9; i++) {
      pthread_join(tid_subgrid[i], NULL);  
    }

    // Column Thread Print Statements
    for (i = 0; i < 9; i++) {
      printf("%s", "Column: ");
      printf("%lx", tid_column[i]);
      if (colArray[i] == 1) {
        printf("%s", " valid!");
      }
      else printf("%s", " invalid!");
      printf("\n");
    }

    // Row Thread Print Statements
    for (i = 0; i < 9; i++) {
      printf("%s", "Row: ");
      printf("%lx", tid_row[i]);
      if (rowArray[i] == 1) {
        printf("%s", " valid!");
      }
      else printf("%s", " invalid!");
      printf("\n");
    }

    // Subgrid Thread Print Statements
    for (i = 0; i < 9; i++) {
      printf("%s", "Subgrid: ");
      printf("%lx", tid_subgrid[i]);
      if (subArray[i] == 1) {
        printf("%s", " valid!");
      }
      else printf("%s", " invalid!");
      printf("\n");
    }
    
    // Sudoku Puzzle Invalidity Print
    for (i = 0; i < 9; i++) {
      if (rowArray[i] == 0 | colArray[i] == 0 | subArray[i] == 0) {
        printf("%s", "Sudoku Puzzle: Invalid.\n");
        return EXIT_SUCCESS;  
      }        
    }

    // Sudoku Puzzle Validity Print
    printf("%s", "Sudoku Puzzle: Valid.\n");
    return EXIT_SUCCESS;
} //end main

// function that determines if a column is valid by only containing numbers 1-9 once
void *columnCheck(void* param) {
    // Confirm that parameters indicate a valid col subsection
    parameters *params = (parameters*) param;
    
    int row = params->topRow;
    int col = params->leftColumn;
    bool invalid = 0;
    
    if (row != 0 || col > 8) {
        invalid = 1;
    }
    // Check if numbers 1-9 only appear once in the column
    int checkArray[9] = {0};
    int i;
    for (i = 0; i < 9; i++) {
        int num = sudokuPuzzle[i][col];
        if (num < 1 || num > 9 || checkArray[num - 1] == 1) {
            invalid = 1;
        } else {
            checkArray[num - 1] = 1;
        }
    }
    // Column grid is valid
    if (invalid == 0) 
      colArray[col] = 1;
    
    printf("%lx", tid_column[col]);
    printf("%s", " TRow: ");
    printf("%d", columnObjects[col].topRow);
    printf("\t");
    printf("%s", "BRow: ");
    printf("%d", columnObjects[col].bottomRow);
    printf("\t");
    printf("%s", "LCol: ");
    printf("%d", columnObjects[col].leftColumn);
    printf("\t");
    printf("%s", "RCol: ");
    printf("%d", columnObjects[col].rightColumn);
    if (colArray[col] == 1) {
        printf("%s", " valid!");
    }
    else printf("%s", " invalid!");
    printf("\n");
    
    pthread_exit(0);
    //Exit the thread
}

//Function that determines if a row is valid by only containing numbers 1-9 once
void *rowCheck(void *param){
    parameters *params = (parameters*) param;
    int row = params->topRow;
    int col = params->leftColumn;
    bool invalid = 0;
    if (col != 0 || row > 8) {
        invalid = 1;
    }
    // Check if numbers 1-9 only appear once
    int checkArray[9] = {0};
    int i;
    for (i = 0; i < 9; i++) {
        int num = sudokuPuzzle[row][i];
        if (num < 1 || num > 9 || checkArray[num - 1] == 1) {
            invalid = 1;
        } else {
            checkArray[num - 1] = 1;
        }
    }
    // Row is valid
    if (invalid == 0) 
      rowArray[row] = 1;
    
    printf("%lx", tid_row[row]);
    printf("%s", " TRow: ");
    printf("%d", rowObjects[row].topRow);
    printf("\t");
    printf("%s", "BRow: ");
    printf("%d", rowObjects[row].bottomRow);
    printf("\t");
    printf("%s", "LCol: ");
    printf("%d", rowObjects[row].leftColumn);
    printf("\t");
    printf("%s", "RCol: ");
    printf("%d", rowObjects[row].rightColumn);
    if (rowArray[row] == 1) {
        printf("%s", " valid!");
    }
    else printf("%s", " invalid!");
    printf("\n");
    pthread_exit(0);
}

// Method that determines if numbers 1-9 only appear once in a 3x3 subsection
void *subgridCheck(void* param) {
    // Confirm that parameters indicate a valid 3x3 subsection
    parameters *params = (parameters*) param;
    int row = params->topRow;
    int col = params->leftColumn;
    bool invalid = 0;

    if (row > 6 || row % 3 != 0 || col > 6 || col % 3 != 0) {
        invalid = 1;
    }
    int validArray[9] = {0};
    int i, j;
    for (i = row; i < row + 3; i++) {
        for (j = col; j < col + 3; j++) {
            int num = sudokuPuzzle[i][j];
            if (num < 1 || num > 9 || validArray[num - 1] == 1) {
                invalid = 1;
            } 
            else {
                validArray[num - 1] = 1;
            }
        }
    }
    // if SubGrid is valid.
    if (invalid == 0) { 
      subArray[row + col/3] = 1;
    } 
     
    printf("%lx", tid_subgrid[row + col/3]);
    printf("%s", " TRow: ");
    printf("%d", subgridObjects[row + col/3].topRow);
    printf("\t");
    printf("%s", "BRow: ");
    printf("%d", subgridObjects[row + col/3].bottomRow);
    printf("\t");
    printf("%s", "LCol: ");
    printf("%d", subgridObjects[row + col/3].leftColumn);
    printf("\t");
    printf("%s", "RCol: ");
    printf("%d", subgridObjects[row + col/3].rightColumn);
    
    if (subArray[row + col/3] == 1) {
        printf("%s", " valid!");
    }
    else printf("%s", " invalid!");
    printf("\n");
    //Exit the thread
    pthread_exit(0);
}
