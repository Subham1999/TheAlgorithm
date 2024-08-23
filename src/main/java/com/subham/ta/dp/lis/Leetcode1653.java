package com.subham.ta.dp.lis;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode1653 {
  public static void main(String[] args) {
    System.out.println(new Solution().approach2("bbaaaaabb"));
    System.out.println(new Solution().approach2("aababbab"));
  }

  static class Solution {
    public int minimumDeletions(String s) {
      int[] lis = new int[s.length()];

      Arrays.fill(lis, 1);

      for (int i = 0; i < s.length(); ++i) {
        for (int j = 0; j < i; ++j) {
          if (s.charAt(j) <= s.charAt(i)) {
            lis[i] = Math.max(lis[i], lis[j] + 1);
          }
        }
      }

      int maxLIS = 0;

      for (int l : lis) {
        maxLIS = Math.max(maxLIS, l);
      }

      System.out.println(Arrays.toString(lis));
      return s.length() - maxLIS;
    }

    public int approach2(String s) {
      Stack<Character> stack = new Stack<>();
      int delete = 0;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (!stack.isEmpty() && stack.peek() == 'b' && c == 'a') {
          delete++;
          stack.pop();
        } else {
          stack.push(c);
        }
      }
      return delete;
    }

    int charVal(char c) {
      return c - 'a';
    }
  }
}
