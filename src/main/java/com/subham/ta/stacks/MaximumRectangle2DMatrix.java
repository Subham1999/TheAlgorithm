package com.subham.ta.stacks;

import java.util.Stack;

public class MaximumRectangle2DMatrix {

  private static int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();

    int[] area = new int[heights.length];
    int[] area2 = new int[heights.length];

    // Right expansion
    for (int i = 0; i < heights.length; ++i) {
      while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
        area[stack.peek()] = heights[stack.peek()] * (i - stack.peek());
        stack.pop();
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      area[stack.peek()] = heights[stack.peek()] * (heights.length - stack.peek());
      stack.pop();
    }

    //System.out.println(Arrays.toString(area));

    stack.clear();

    // Left expansion
    for (int i = heights.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
        area2[stack.peek()] = heights[stack.peek()] * (stack.peek() - i);
        stack.pop();
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      area2[stack.peek()] = heights[stack.peek()] * (stack.peek() + 1);
      stack.pop();
    }

    //System.out.println(Arrays.toString(area2));

    int res = 0;
    for (int i = 0; i < heights.length; ++i) {
      res = Math.max(res, area[i] + area2[i] - heights[i]);
    }
    return res;
  }

  public static int maximalRectangle(char[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] histogram = new int[m][n];

    for (int j = 0; j < n; ++j) {
      histogram[0][j] = matrix[0][j] - '0';
    }

    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; j++) {
        histogram[i][j] = matrix[i][j] == '1' ? histogram[i - 1][j] + (matrix[i][j] - '0') : 0;
      }
    }

    // histogram is ready;
    int res = 0;
    for (int i = 0; i < m; i++) {
      res = Math.max(res, largestRectangleArea(histogram[i]));
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(
        maximalRectangle(
            new char[][] {
              {'1', '0', '1', '0', '0'},
              {'1', '0', '1', '1', '1'},
              {'1', '1', '1', '1', '1'},
              {'1', '0', '0', '1', '0'}
            }));
  }
}
