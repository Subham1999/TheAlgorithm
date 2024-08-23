package com.subham.ta.array;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1024Hard {
  public static void main(String[] args) {
    int[][] mat = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
    //    System.out.println(new Solution().numSubmatrixSumTarget(mat, 0));
    int[][] mat2 = {{1, -1}, {-1, 1}};
    System.out.println(new Solution().numSubmatrixSumTarget(mat2, 0));
  }

  static class Solution {
    private static int[][] buildPrefixSum2D(int[][] matrix) {
      int[][] psa = new int[matrix.length][matrix[0].length];

      psa[0][0] = matrix[0][0];

      // build col
      for (int j = 1; j < psa[0].length; j++) {
        psa[0][j] = psa[0][j - 1] + matrix[0][j];
      }

      // build first row
      for (int i = 1; i < psa.length; i++) {
        psa[i][0] = psa[i - 1][0] + matrix[i][0];
      }

      for (int i = 1; i < psa.length; ++i) {
        for (int j = 1; j < psa.length; ++j) {
          psa[i][j] = psa[i - 1][j] + psa[i][j - 1] - psa[i - 1][j - 1] + matrix[i][j];
        }
      }
      return psa;
    }

    private static int querySum(int r1, int j1, int r2, int j2, int[][] psa) {
      return querySum(r2, j2, psa)
          - querySum(r1 - 1, j2, psa)
          - querySum(r2, j1 - 1, psa)
          + querySum(r1 - 1, j1 - 1, psa);
    }

    private static int querySum(int r, int j, int[][] psa) {
      if (r < 0) {
        return 0;
      }
      if (j < 0) {
        return 0;
      }
      return psa[r][j];
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
      int[][] psa = buildPrefixSum2D(matrix);

      // start the process
      int count = 0;

      for (int r1 = 0; r1 < psa.length; ++r1) {
        for (int r2 = r1; r2 < psa.length; ++r2) {

          Map<Integer, Integer> sumFrequency = new HashMap<>();
          for (int c = 0; c < psa[0].length; ++c) {
            int querySum = querySum(r1, 0, r2, c, psa);
            if (querySum == target) count++;
            count += sumFrequency.getOrDefault(querySum - target, 0);
            sumFrequency.put(querySum, 1 + sumFrequency.getOrDefault(querySum, 0));
          }
        }
      }

      return count;
    }
  }
}
