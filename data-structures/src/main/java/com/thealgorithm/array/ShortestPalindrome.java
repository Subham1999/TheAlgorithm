package com.thealgorithm.array;

/**
 * @author: Subham Santra
 */
public class ShortestPalindrome {
  static class Solution {
    public String shortestPalindrome(String s) {
//      int n = s.length();
//      int mid = (s.length() % 2 == 1) ? (s.length() / 2) : ((s.length() / 2) - 1);
//      String answer = s + s;
//
//      for (int i = 0; i <= mid; ++i) {
//
//      }
//      return answer;
      return "0";
    }

    boolean match(String s, int mid, int length) {
//      for (int i = 0, j = mid + length; 0 <= i && )
      return false;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().shortestPalindrome("abbad")); // dabbad
    System.out.println(new Solution().shortestPalindrome("abcd").equals("dcbabcd"));
    System.out.println(new Solution().shortestPalindrome("aaab").equals("baaab"));
    System.out.println(new Solution().shortestPalindrome("aa").equals("aa"));
    System.out.println(new Solution().shortestPalindrome("ab").equals("bab"));
    System.out.println(new Solution().shortestPalindrome("aca").equals("aca"));
    System.out.println(new Solution().shortestPalindrome("aaca").equals("acaaca"));
  }
}
