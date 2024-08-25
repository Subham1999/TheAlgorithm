package com.thealgorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LeetCode2191 {
  public static void main(String[] args) {}

  static class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
      Integer[] NUMS = new Integer[nums.length];

      Map<Integer, Integer> indx = new HashMap<>();
      Map<Integer, Integer> translated = new HashMap<>();

      for (int i = 0; i < nums.length; ++i) {
        NUMS[i] = nums[i];
        translated.put(nums[i], translate(mapping, nums[i]));
        indx.put(nums[i], i);
      }

      // System.out.println(translated);

      Arrays.sort(
          NUMS,
          (a1, a2) -> {
            Integer v1 = translated.get(a1);
            Integer v2 = translated.get(a2);
            return Objects.equals(v1, v2) ? indx.get(a1) - indx.get(a2) : v1 - v2;
          });

      for (int i = 0; i < nums.length; ++i) {
        nums[i] = NUMS[i];
      }

      return nums;
    }

    private Integer translate(int[] m, int a) {
      int res = 0;
      char[] chars = Integer.toString(a).toCharArray();
      for (int i = 0; i < chars.length; ++i) {
        res = (res * 10) + m[chars[i] - '0'];
      }
      return res;
    }
  }
}
