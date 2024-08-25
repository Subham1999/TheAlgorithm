package com.thealgorithm.strings;

import java.util.Arrays;

public class LeetCode1624 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    //    System.out.println(solution.maxLengthBetweenEqualCharacters("aa"));
    //    System.out.println(solution.maxLengthBetweenEqualCharacters("baab"));
    System.out.println(solution.maxLengthBetweenEqualCharacters("mgntdygtxrvxjnwksqhxuxtrv"));
  }

  static class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
      int[] lastOccurrence = new int[26];
      int maxLength = -1;

      Arrays.fill(lastOccurrence, -1);

      for (int i = 0; i < s.length(); ++i) {
        int charOrd = (s.charAt(i) - 'a');
        if (lastOccurrence[charOrd] != -1) {
          maxLength = Math.max(maxLength, i - lastOccurrence[charOrd] - 1);
        } else {
          lastOccurrence[charOrd] = i;
        }
      }

      return maxLength;
    }
  }
}
