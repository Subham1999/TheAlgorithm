package com.thealgorithm.backtracking;


/**
 * @author: Subham Santra
 */
public class SudokuSolver {
  public void solveSudoku(char[][] board) {
    
  }

  public static void main(String[] args) {
    char[][] board = {{}};

    SudokuSolver sudokuSolver = new SudokuSolver();
    sudokuSolver.solveSudoku(board);
    print(board);
  }

  private static void print(char[][] board) {
    for (char[] chars : board) {
      for (char c : chars) {
        System.out.printf("%c\t", c);
      }
      System.out.print("\r\n");
    }
  }
}
