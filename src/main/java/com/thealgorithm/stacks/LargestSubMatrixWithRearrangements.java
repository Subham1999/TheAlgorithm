package com.thealgorithm.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: Subham Santra
 */
public class LargestSubMatrixWithRearrangements {
  public int largestSubmatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] prefixSum = new int[m][n];

    System.arraycopy(matrix[0], 0, prefixSum[0], 0, n);

    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        prefixSum[i][j] = matrix[i][j] == 0 ? 0 : prefixSum[i - 1][j] + matrix[i][j];
      }
    }

    int result = 0;

    for (int i = 0; i < m; ++i) { // O(R)
      // O(ClogC) + O(C) = O(ClogC)
      Arrays.sort(prefixSum[i]); // O(ClogC)
      result = Math.max(result, largestHistogram(prefixSum[i])); // O(C)
    }

    // Overall TC : O(RClogC) -> O(mn(log n))
    return result;
  }

  private int largestHistogram(int[] arr) {
    Stack<int[]> stack = new Stack<>();
    int[] temp = new int[arr.length];
    for (int i = 0; i < arr.length; ++i) {
      int backIndex = i;
      while (!stack.isEmpty() && (stack.peek()[0] > arr[i])) {
        backIndex = stack.pop()[1];
      }
      temp[i] = arr[i] * (backIndex - i + 1);
      stack.push(new int[] {arr[i], backIndex, i});
    }
    while (!stack.isEmpty()) {
      temp[stack.peek()[2]] = stack.peek()[0] * (arr.length - stack.peek()[2]);
      stack.pop();
    }
    return Arrays.stream(temp).max().orElse(0);
  }

  public static void main(String[] args) {
    System.out.println(
        new LargestSubMatrixWithRearrangements()
            .largestSubmatrix(new int[][] {{1, 1, 0}, {1, 0, 1}}));

    System.out.println(
        new LargestSubMatrixWithRearrangements()
            .largestSubmatrix(new int[][] {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}}));

    System.out.println(
        new LargestSubMatrixWithRearrangements()
            .largestSubmatrix(new int[][] {{1, 0, 1, 1, 0, 1}}));
  }
}
