package com.thealgorithm.dp;

import java.util.Stack;

public class Leetcode907 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.sumSubarrayMins(new int[] {3, 1, 2, 4}));
    System.out.println(solution.sumSubarrayMins(new int[] {11, 81, 94, 43, 3}));
  }

  private static class Solution {
    private static final int LARGE_NUM = 1000_000_000 + 7;

    public int sumSubarrayMins(int[] arr) {
      int[] rightBoundary = new int[arr.length];
      int[] leftBoundary = new int[arr.length];
      buildRightBoundary(arr, rightBoundary, arr.length);
      buildLeftBoundary(arr, leftBoundary, arr.length);
      int ans = 0;
      for (int i = 0; i < arr.length; ++i) {
        // calculate contributions of arr[i]
        // = no. of sub arrays where arr[i] is minimum
        int lb = leftBoundary[i];
        int rb = rightBoundary[i];
        int countOfSubArrays = calculateCountOfSubArrays(lb + 1, i, rb - 1);
        // System.out.printf("Contributing arr[%d] x %d = %d\n", i, arr[i], countOfSubArrays);
        ans = (ans + (countOfSubArrays * arr[i]) % LARGE_NUM) % LARGE_NUM;
      }
      return ans;
    }

    private int calculateCountOfSubArrays(int from, int i, int to) {
      int n1 = i - from + 1;
      int n2 = to - i + 1;
      return n1 * n2;
    }

    private void buildLeftBoundary(int[] arr, int[] previousSmallerElement, int n) {
      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < n; ++i) {
        while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
          stack.pop();
        }
        previousSmallerElement[i] = stack.isEmpty() ? -1 : stack.peek();
        stack.push(i);
      }
    }

    private void buildRightBoundary(int[] arr, int[] nextSmallerElement, int n) {
      Stack<Integer> stack = new Stack<>();
      for (int i = n - 1; i >= 0; --i) {
        while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
          stack.pop();
        }
        nextSmallerElement[i] = stack.isEmpty() ? arr.length : stack.peek();
        stack.push(i);
      }
    }
  }
}
