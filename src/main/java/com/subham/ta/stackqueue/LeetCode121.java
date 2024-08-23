package com.subham.ta.stackqueue;

public class LeetCode121 {
  public static void main(String[] args) {
    System.out.println(new Solution().maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    System.out.println(new Solution().maxProfit(new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1}));
  }

  private static class Solution {
    public int maxProfit(int[] prices) {
      int maxValue = 0;
      int maxProfit = 0;

      for (int i = prices.length - 1; i >= 0; --i) {
        maxProfit = Math.max(maxProfit, maxValue - prices[i]);
        maxValue = Math.max(maxValue, prices[i]);
      }

      return maxProfit;
    }
  }
}
