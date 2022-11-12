/*
 * CS3210 - Principles of Programming Languages - Fall 2022
 * Instructor: Thyago Mota
 * Description: Prg 02 - Sudoku Puzzle
 * Student: Sean Kruse and Devin Schulitz
 * References / Collaborators: Devin Schulitz did most of the heavy lifting setting up task #14 and #15.
 * Scala Cookbook
 * 
 */

import scala.io._

object Sudoku {

  // TODO #1: return an 2D array of Int representing a sudoku board given a filename
  def readBoard(fileName: String): Array[Array[Int]] = {
    val f = Source.fromFile(fileName)
    for(e <- f.getLines().map(_.split("").map(_.toInt)))
    //yield f.getLines().map(_.split("", 9).map(_toInt))
    //getLines().map(_.split("", 9).map(_toInt))
      yield e
    //try f.getLines().map(_.split("", 9).map(_toInt)).toArray
    //finally f.close()
  }.toArray

  // TODO #2: return a String representation from a given sudoku board
  def boardToString(board: Array[Array[Int]]): String = {
    // format for sudoku puzzle sourced from Daniel Ciocirlan at https://blog.rockthejvm.com/sudoku-backtracking/
    // We adapted our original working code to look nicer based on Daniel's Implementation
    board.grouped(3).map { bigGroup =>
      bigGroup.map { row =>
        row.grouped(3).map { smallGroup =>
          smallGroup.mkString(" ", " ", " ")
        }.mkString("|", "|", "|")
      }.mkString("\n")
    }.mkString("+-------+-------+-------+\n", "\n+-------+-------+-------+\n", "\n+-------+-------+-------+")
  }
    //val b = board.map(_.mkString("")).mkString("\n")
    //b  

  // TODO #3: return a specific row from a sudoku board as a sequence of numbers
  def getRow(board: Array[Array[Int]], row: Int): Array[Int] = {
    val r = board(row)
    r
    //val rowValue = !row.contains(value)
  }

  // TODO #4: return a specific column from a sudoku board as a sequence of numbers
  def getCol(board: Array[Array[Int]], col: Int): Array[Int] = {
    val c = board.transpose
    c(col)
    //val colValue = !col.contains(value)
  }

  // TODO #5: return a specific box from a sudoku board as a sequence of numbers
  def getBox(board: Array[Array[Int]], x: Int, y: Int): Array[Int] = {
    //this logic was sourced from rocking the JVM - Backtracking solution
    board(0+(y*3)).slice((x*3),((x*3)+3)).concat(board(1 + (y*3)).slice((x*3),((x*3)+3))).concat(board(2 + (y*3)).slice((x*3),((x*3)+3)))
  }

  // TODO #6: a sequence is valid if it has 9 numbers in [0-9] with possibly repeated zeros
  def isValid(seq: Array[Int]): Boolean = {
    if(seq.length == 9)
      seq.filter(_ != 0).groupBy(e => e).toList.map(x => x._2.length).forall(t => t <= 1) //list of list filter out non-zero values, check for duplicates
    else
      return false  
  } 

  // TODO #7: return whether all rows of the given board are valid sequences
  def allRowsValid(board: Array[Array[Int]]): Boolean = {
    board.forall(isValid(_)) // the elements are arrays that are run against the isVaild method, if Bool returns true than allRows will be valid
  }

  // TODO #8: return whether all columns of the given board are valid sequences
  def allColsValid(board: Array[Array[Int]]): Boolean = {
    var a = true
    for(x <- 0 to 8) 
      if(!isValid(getCol(board, x)))
        a = false
    a
  }

  // TODO #9: return whether all boxes of the given board are valid sequences
  def allBoxesValid(board: Array[Array[Int]]): Boolean = {
    var a = true
    for(i <- 0 to 2)
      for(j <- 0 to 2)
        if(!isValid(getBox(board, i, j))) // 3 x 3 grid check
          a = false
    a // return a
  }   

  // TODO #10: a board is valid if all of its rows, columns, and boxes are also valid
  def isValidBoard(board: Array[Array[Int]]): Boolean = {
    (allRowsValid(board) && allColsValid(board) && allBoxesValid(board)) 
  }
  // TODO #11: a board is complete it there is no zero
  def isComplete(board: Array[Array[Int]]): Boolean = {
    var t = true // true until proven false
    for(x <- 0 to 8)
      if(getCol(board, x).contains(0))
        t = false // any 0 wil return false
    t // return t
  }
  // TODO #12: a board is solved if is complete and valid
  def isSolved(board: Array[Array[Int]]): Boolean = {
    (isComplete(board) && isValidBoard(board)) 
  }
  // TODO #13: return a new board configuration from the given one by setting a digit at a specific (row, col) location
  def getChoice(board: Array[Array[Int]], row: Int, col: Int, d: Int): Array[Array[Int]] = {
    var newBoard = board.map(_.map(x => x))
    newBoard(row)(col) = d
    newBoard
  }
  // TODO #14: return all possible new board configurations from the given one
  def getChoices(board: Array[Array[Int]]): IndexedSeq[Array[Array[Int]]] = {
    // Professor Mota provided the solution to replacing the zeroes in the board 
    for {
      i <- 0 to 8
      j <- 0 to 8
      k <- 1 to 9
      if board(i)(j) == 0  
    }yield getChoice(board, i, j, k)
  } 

  // TODO #15: return a solution to the puzzle (null if there is no solution)
  def solve(board: Array[Array[Int]]): Array[Array[Int]] = {
    for(x <- getChoices(board)){
      if(isSolved(x)){
        return x
      }
      else if(isComplete(x)){
        null
      }
      else if(isValidBoard(x)){
        return solve(x)
      }
    }
  }.asInstanceOf[Array[Array[Int]]]     
  
  def main(args: Array[String]): Unit = {
    val board = readBoard("prg_02_sudoku/sudoku1.txt")
    val sol = solve(board)
    println(boardToString(sol))
  }
}
