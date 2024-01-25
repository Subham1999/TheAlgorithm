package com.subham.ta.dp.miscelleneous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode446 {
  public static void main(String[] args) {
    Solution solution = new Solution();

    System.out.println(solution.numberOfArithmeticSlices(new int[] {1, 2, 1, 2, 4, 1, 5, 10}));
    System.out.println(solution.numberOfArithmeticSlices(new int[] {2, 4, 6, 8, 10}));
    System.out.println(
        solution.numberOfArithmeticSlices(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    System.out.println(solution.numberOfArithmeticSlices(new int[] {10, 10, 10, 10}));
    System.out.println(solution.numberOfArithmeticSlices(new int[] {0, 2000000000, -294967296}));
  }

  private static class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
      final List<Map<Integer, Integer>> differenceToElementCount = new ArrayList<>();

      for (int i = 0; i < nums.length; ++i) {
        differenceToElementCount.add(new HashMap<>());
      }

      int answer = 0;

      for (int i = 0; i < nums.length; ++i) {
        for (int j = 0; j < i; ++j) {
          long diffLong = (long) nums[i] - nums[j];
          if (diffLong > Integer.MAX_VALUE || diffLong < Integer.MIN_VALUE) {
            continue;
          }
          int diff = (int) diffLong;

          // Updating the count of all sequences with diff and ending at index i.
          differenceToElementCount
              .get(i)
              .put(diff, 1 + differenceToElementCount.get(i).getOrDefault(diff, 0));

          // May be, few sequences are also ending at j with diff.
          // The current element will also contribute to all the sequences ending at jth index.
          int prevCount = differenceToElementCount.get(j).getOrDefault(diff, 0);
          differenceToElementCount
              .get(i)
              .put(diff, prevCount + differenceToElementCount.get(i).get(diff));

          answer += prevCount;
        }
      }
      System.out.println(differenceToElementCount);
      return answer;
    }
  }
}
