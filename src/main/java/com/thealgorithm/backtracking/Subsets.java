package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
  static class Solution {
    public List<List<Integer>> subsets(int[] nums) {
      int len = nums.length;
      int powerLen = 1 << len;

      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < powerLen; ++i) {
        int j = i;
        int k = 0;
        List<Integer> set = new ArrayList<>();
        while (j > 0) {
          if ((j & 1) == 1) {
            set.add(nums[k]);
          }
          ++k;
          j >>= 1;
        }
        result.add(set);
      }
      return result;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().subsets(new int[] {1, 2}));
  }
}
