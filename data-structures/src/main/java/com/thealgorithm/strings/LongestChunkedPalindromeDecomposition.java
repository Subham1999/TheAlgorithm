package com.thealgorithm.strings;

/**
 * @author: Subham Santra
 */
public class LongestChunkedPalindromeDecomposition {
  static class Solution {
    private final long p = 31;
    private final long mod = (int) 1e9 + 7;
    private final long[] p_pow = new long[1001];

    public Solution() {
      p_pow[0] = 1;
      for (int i = 1; i <= 1000; ++i) {
        p_pow[i] = p_pow[i - 1] * p;
        p_pow[i] = (p_pow[i] + mod) % mod;
      }
    }

    void rollingHash(long[] rh, String txt) {
      for (int i = 0; i < txt.length(); ++i) {
        int c = txt.charAt(i) - 'a' + 1;
        rh[i + 1] = ((rh[i] * 31) % mod + c + mod) % mod;
      }
    }

    long hash(long[] RH, int i, int j) {
      return ((RH[j + 1] - RH[i] * p_pow[j - i + 1]) % mod + mod) % mod;
    }

    public int longestDecomposition(String text) {
      int[][] DP = new int[text.length()][text.length()];
      long[] RH = new long[text.length() + 1];
      for (int i = 0; i < text.length(); ++i) {
        for (int j = 0; j < text.length(); ++j) {
          DP[i][j] = -1;
        }
      }
      rollingHash(RH, text);
      // System.out.println(Arrays.toString(RH));
      return decompose(text, 0, text.length() - 1, DP, RH);
    }

    int decompose(String text, int lo, int hi, int[][] DP, long[] RH) {
      if (lo == hi + 1) return 0;
      if (lo > hi) return -1;

      if (DP[lo][hi] != -1) return DP[lo][hi];

      int ans = 1;

      for (int len = 1; len <= (hi - lo + 1) / 2; ++len) {
        long h1 = hash(RH, lo, lo + len - 1);
        long h2 = hash(RH, hi - len + 1, hi);

        //        System.out.printf(
        //            "%s %d %s %d\n",
        //            text.substring(lo, lo + len), h1, text.substring(hi - len + 1, hi + 1), h2);

        if (h1 == h2) {
          int a = decompose(text, lo + len, hi - len, DP, RH);
          if (a > -1) ans = Math.max(a + 2, ans);
        }
      }

      return DP[lo][hi] = ans;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    System.out.println(
        new Solution().longestDecomposition("wuamlwxskgjzegshjhcwchkgjzegshjwuamlwxs"));
    System.out.println(new Solution().longestDecomposition("antaprezatepzapreanta"));
    System.out.println(new Solution().longestDecomposition("aabaab"));
    System.out.println(new Solution().longestDecomposition("aabaabaab"));
    System.out.println(new Solution().longestDecomposition("aabaabaabaab"));
    System.out.println(new Solution().longestDecomposition("aabaabaabaabc"));
  }
}
