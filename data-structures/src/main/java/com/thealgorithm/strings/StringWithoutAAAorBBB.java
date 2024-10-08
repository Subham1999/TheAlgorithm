package com.thealgorithm.strings;

/**
 * @author: Subham Santra
 */
public class StringWithoutAAAorBBB {
  static class Solution {
    public String strWithout3a3b(int a, int b) {
      StringBuilder ans = new StringBuilder();
      while (a > 0 || b > 0) {
        if (ans.toString().endsWith("aa")) {
          ans.append("b");
          --b;
        } else if (ans.toString().endsWith("bb")) {
          ans.append("a");
          --a;
        } else {
          if (a > b) {
            ans.append("a");
            --a;
          } else {
            ans.append("b");
            --b;
          }
        }
      }

      return ans.toString();
    }
  }

  /**
   * 3 1 aaba
   *
   * <p>if (a == b) --> ababa....
   *
   * <p>if (a == b + 1) --> abab...ba
   *
   * <p>if (a == b + 2) --> aabab...ba if (a == b + 3) --> aabaab...ba if (a == b + b + 1) -->
   * aabaab...baa
   *
   * <p>1 2
   *
   * <p>a a b a a
   */
  public static void main(String[] args) {
    System.out.println(new Solution().strWithout3a3b(6, 10));
  }
}
