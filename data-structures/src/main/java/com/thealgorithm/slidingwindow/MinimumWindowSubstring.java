package com.thealgorithm.slidingwindow;

/**
 * @author: Subham Santra
 */
public class MinimumWindowSubstring {
  private static class Solution {
    int ord(char c) {
      return ('A' <= c && c <= 'Z') ? 26 + (c - 'A') : (c - 'a');
    }

    boolean isMatch(int[] w, int[] t) {
      for (int i = 0; i < 52; ++i) {
        if (t[i] != 0 && t[i] > w[i]) {
          return false;
        }
      }
      return true;
    }

    public String minWindow(String s, String t) {
      int[] tScore = new int[52];
      int[] wScore = new int[52];

      for (int i = 0; i < t.length(); ++i) {
        tScore[ord(t.charAt(i))]++;
      }

      int minWindowLength = Integer.MAX_VALUE;
      int start = -1;
      int end = -1;

      for (int lo = 0, hi = 0; hi < s.length(); ++hi) {
        //System.out.println("- " + s.charAt(hi) + " - " + s.substring(lo, hi + 1));
        wScore[ord(s.charAt(hi))]++;

        while (lo <= hi && isMatch(wScore, tScore)) {
          //System.out.println(s.charAt(hi) + " - " + s.substring(lo, hi + 1));
          if (minWindowLength > (hi - lo + 1)) {
            minWindowLength = hi - lo + 1;
            start = lo;
            end = hi;
          }
          wScore[ord(s.charAt(lo))]--;
          ++lo;
          //System.out.println(s.charAt(hi) + " - " + s.substring(lo, hi + 1));
        }
      }

      if (start > end) {
        return "";
      }
      if (start == -1) {
        return "";
      }
      return s.substring(start, end + 1);
    }
  }

  public static void main(String[] args){
    System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
    System.out.println(new Solution().minWindow("ACACBDEA", "ABC"));
    System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
  }
}
