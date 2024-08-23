package com.subham.ta.array;

import java.util.Arrays;

public class LeetCode1636 {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(new Solution().frequencySort(new int[] {1, 1, 2, 2, 2, 3})));
    System.out.println(
        Arrays.toString(new Solution().frequencySort(new int[] {-1, 1, -6, 4, 5, -6, 1, 4, 1})));
  }

  static class Solution {
    public int[] frequencySort(int[] nums) {
      int[] a200 = new int[201];
      for (int x : nums) {
        a200[x + 100]++;
      }

      Integer[] NUMS = new Integer[nums.length];
      for (int c = 0; c < nums.length; c++) {
        NUMS[c] = nums[c];
      }

      Arrays.sort(
          NUMS,
          (a1, a2) -> a200[a1 + 100] == a200[a2 + 100] ? a2 - a1 : a200[a1 + 100] - a200[a2 + 100]);

      for (int c = 0; c < nums.length; c++) {
        nums[c] = NUMS[c];
      }
      return nums;
    }
  }
}
