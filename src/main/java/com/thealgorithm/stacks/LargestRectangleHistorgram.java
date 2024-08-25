package com.thealgorithm.stacks;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleHistorgram {
  public static int largestRectangleArea(int[] heights) {

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

    System.out.println(Arrays.toString(area));

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

    System.out.println(Arrays.toString(area2));

    int res = 0;
    for (int i = 0; i < heights.length; ++i) {
      res = Math.max(res, area[i] + area2[i] - heights[i]);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(largestRectangleArea(new int[] {2, 1, 3, 4, 1, 5, 1}));
  }
}
