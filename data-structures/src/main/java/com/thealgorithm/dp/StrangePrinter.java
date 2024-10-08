package com.thealgorithm.dp;

/**
 * @author: Subham Santra
 */
public class StrangePrinter {
  static class Solution {
    public int strangePrinter(String s) {
      return strangePrinter(s, 0, s.length() - 1);
    }

    int strangePrinter(String s, int lo, int hi) {
      if (lo < 0 || lo >= s.length() || hi < 0 || hi >= s.length()) {
        return 0;
      }
      if (lo > hi) {
        return 0;
      }

      System.out.println(s.substring(lo, hi + 1));

      boolean allSame = true;
      for (int i = lo; i <= hi; ++i) {
        if (s.charAt(i) != s.charAt(lo)) {
          allSame = false;
        }
      }

      if (allSame) {
        return 1;
      }
      int ans = 10000;

      for (int i = lo, j = hi; i <= j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
          int v =
              strangePrinter(s, lo, i) + strangePrinter(s, i + 1, j) + strangePrinter(s, j + 1, hi);
          ans = Math.min(ans, v);
          break;
        }
      }

      return ans;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().strangePrinter("abcabccb"));
  }
}
