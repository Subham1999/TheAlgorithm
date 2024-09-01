package com.thealgorithm.miscelleneous;

/**
 * @see <a
 *     href="https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/description/">check</a>
 */
public class LeetCode1155 {
  public static void main(String[] args) {
    System.out.println(new Solution().numRollsToTarget(1, 1, 1));
    System.out.println(new Solution().numRollsToTarget(1, 6, 3));
    System.out.println(new Solution().numRollsToTarget(1, 6, 3));
    System.out.println(new Solution().numRollsToTarget(30, 30, 500));
  }

  static class Solution {
    private static final int MOD = 1000_000_000 + 7;

    public int numRollsToTarget(int n, int k, int target) {
      int[][] memo = new int[n + 1][1001];

      for (int i = 0; i <= n; ++i) {
        for (int j = 0; j <= 1000; ++j) {
          memo[i][j] = -1;
        }
      }

      return dp(n, k, target, memo);
    }

    private int dp(int n, int k, int t, int[][] memo) {
      if (n == 0 && k == 0 && t == 0) {
        return 1;
      }
      if (n == 0) {
        return 0;
      }
      if (t <= 0) {
        return 0;
      }
      if (memo[n][t] != -1) {
        return memo[n][t];
      }
      if (n == 1) {
        if (t <= k) {
          return memo[n][t] = 1;
        } else {
          return memo[n][t] = 0;
        }
      }
      int ans = 0;
      for (int cs = 1; cs <= k; ++cs) {
        ans = (ans + dp(n - 1, k, t - cs, memo)) % MOD;
      }
      return memo[n][t] = ans;
    }
  }
}
