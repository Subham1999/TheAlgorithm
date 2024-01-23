package com.subham.ta.strings;

import java.util.List;

public class LeetCode1239 {
  private static class Solution {
    public int maxLength(List<String> arr) {
      int mask = ((int) Math.pow(2, arr.size())) - 1;
      int maxLength = 0;
      while (mask > 0) {
        maxLength = Math.max(maxLength, checkUniqueNess(mask, arr));
        mask--;
      }
      return maxLength;
    }

    private static int checkUniqueNess(int mask, List<String> arr) {
      int m = mask;
      int ans = 0;
      int idx = 0;
      int[] chars = new int[26];
      while (m > 0) {
        if ((m & 1) == 1) {
          putChars(chars, arr.get(idx));
          ans += arr.get(idx).length();
        }
        idx++;
        m >>= 1;
      }
      for (int i : chars) {
        if (i > 1) return -1;
      }
      return ans;
    }

    private static void putChars(int[] chars, String s) {
      for (int i = 0; i < s.length(); ++i) {
        chars[s.charAt(i) - 'a']++;
      }
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.maxLength(List.of("un", "iq", "ue")));
    System.out.println(solution.maxLength(List.of("abcdefghijklmnopqrstuvwxyz")));
    System.out.println(solution.maxLength(List.of("cha", "r", "act", "ers")));
  }
}
