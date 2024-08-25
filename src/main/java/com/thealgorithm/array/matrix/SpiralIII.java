package com.thealgorithm.array.matrix;

public class SpiralIII {
  public static void main(String[] args) {
    int[][] matrixIII = new Solution().spiralMatrixIII(3, 3, 2, 2);

    for (int i = 0; i < matrixIII.length; i++) {
      System.out.println(matrixIII[i][0] + " - " + matrixIII[i][1]);
    }
  }

  static class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
      final int N = rows * cols;
      final int[][] res = new int[N][2];

      int currentResultIndex = 0;
      int currentRow = rStart;
      int currentCol = cStart;

      int spireLen = 0;

      while (currentResultIndex < N) {
        System.out.println(currentResultIndex);
        spireLen++;
        // GO east
        for (int east = 0; east < spireLen; ++east) {
          if (valid(currentRow, currentCol, rows, cols)) {
            res[currentResultIndex][0] = currentRow;
            res[currentResultIndex][1] = currentCol;
            currentResultIndex++;
          }
          currentCol++;
        }
        // GO south
        for (int south = 0; south < spireLen; ++south) {
          if (valid(currentRow, currentCol, rows, cols)) {
            res[currentResultIndex][0] = currentRow;
            res[currentResultIndex][1] = currentCol;
            currentResultIndex++;
          }
          currentRow++;
        }

        spireLen++;
        // GO west
        for (int west = 0; west < spireLen; ++west) {
          if (valid(currentRow, currentCol, rows, cols)) {
            res[currentResultIndex][0] = currentRow;
            res[currentResultIndex][1] = currentCol;
            currentResultIndex++;
          }
          currentCol--;
        }
        // GO north
        for (int north = 0; north < spireLen; ++north) {
          if (valid(currentRow, currentCol, rows, cols)) {
            res[currentResultIndex][0] = currentRow;
            res[currentResultIndex][1] = currentCol;
            currentResultIndex++;
          }
          currentRow--;
        }
      }

      return res;
    }

    boolean valid(int i, int j, int m, int n) {
      return (0 <= i && i < m) && (0 <= j && j < n);
    }
  }
}
