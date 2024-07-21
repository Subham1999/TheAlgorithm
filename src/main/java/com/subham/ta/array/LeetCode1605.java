package com.subham.ta.array;

import java.util.function.Consumer;

public class LeetCode1605 {
  static class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
      int ROW = rowSum.length;
      int COL = colSum.length;

      int[][] mat = new int[ROW][COL];

      for (int r = 0; r < ROW; ++r) {
        for (int c = 0; c < COL; ++c) {
          mat[r][c] = Math.min(rowSum[r], colSum[c]);
          rowSum[r] -= mat[r][c];
          colSum[c] -= mat[r][c];
        }
      }
      return mat;
    }
  }

  public static void main(String[] args) {
    Consumer<int[][]> debug =
        (mat) -> {
          for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
              System.out.printf("%d\t", mat[i][j]);
            }
            System.out.println();
          }
        };

    debug.accept(new LeetCode1605.Solution().restoreMatrix(new int[] {1000_000_000, 1}, new int[] {0, 1000_000_000}));
  }
}
