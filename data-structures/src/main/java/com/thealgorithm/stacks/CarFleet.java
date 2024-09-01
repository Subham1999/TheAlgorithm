package com.thealgorithm.stacks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class CarFleet {
  public static void main(String[] args) {
    System.out.println(
        new Solution().carFleet(12, new int[] {10, 8, 0, 5, 3}, new int[] {2, 4, 1, 1, 3}));

    System.out.println(new Solution().carFleet(10, new int[] {0, 4, 2}, new int[] {2, 1, 3}));
  }

  static class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
      final int n = position.length;
      double[][] carFleet = new double[n][3];

      for (int i = 0; i < n; ++i) {
        carFleet[i][0] = speed[i];
        carFleet[i][1] = position[i];
        carFleet[i][2] = ((double) target - position[i]) / speed[i];
      }

      // Sorting the car fleets by their positions
      Arrays.sort(carFleet, Comparator.comparingDouble(cf -> cf[1]));

      Stack<Double> stack = new Stack<>();

      for (int i = n - 1; i >= 0; i--) {
        if (stack.isEmpty() || (carFleet[i][2] > stack.peek())) {
          stack.push(carFleet[i][2]);
        }
      }
      return stack.size();
    }
  }
}
