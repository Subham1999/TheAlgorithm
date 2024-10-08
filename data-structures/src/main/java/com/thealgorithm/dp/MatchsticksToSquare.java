package com.thealgorithm.dp;

/**
 * @author: Subham Santra
 */
public class MatchsticksToSquare {
  static class Solution {
    Boolean[][] DP;

    int getMask(boolean a, boolean b, boolean c, boolean d) {
      int mask = 0b0000;
      if (a) mask |= 0b1000;
      if (b) mask |= 0b0100;
      if (c) mask |= 0b0010;
      if (d) mask |= 0b0001;
      return mask;
    }

    public boolean makesquare(int[] matchsticks) {
      int n = matchsticks.length;
      DP = new Boolean[n + 1][5];
      int sum = 0;
      for (int x : matchsticks) sum += x;

      if (sum % 4 == 0) {
        //        return func(matchsticks, 0, 0, 0, 0, 0, (sum / 4));
        return funcWithoutDP(matchsticks, 0, 0, 0, 0, 0, (sum / 4));
      }
      return false;
    }

    boolean funcWithoutDP(int[] arr, int index, int s1, int s2, int s3, int s4, int t) {
      if (index == arr.length) {
        if (s1 == s2 && s2 == s3 && s3 == s4 && s4 == t) {
          return true;
        }
        return false;
      }

      return func(arr, index + 1, s1 + arr[index], s2, s3, s4, t)
          || func(arr, index + 1, s1, s2 + arr[index], s3, s4, t)
          || func(arr, index + 1, s1, s2, s3 + arr[index], s4, t)
          || func(arr, index + 1, s1, s2, s3, s4 + arr[index], t);
    }

    boolean func(int[] arr, int index, int s1, int s2, int s3, int s4, int t) {
      if (index == arr.length) {
        if (s1 == s2 && s2 == s3 && s3 == s4 && s4 == t) {
          return true;
        }
        return false;
      }

      if (DP[index][4] != null
          && DP[index][1] != null
          && DP[index][2] != null
          && DP[index][3] != null) {
        return DP[index][4] || DP[index][1] || DP[index][2] || DP[index][3];
      }

      DP[index][1] = func(arr, index + 1, s1 + arr[index], s2, s3, s4, t);
      DP[index][2] = func(arr, index + 1, s1, s2 + arr[index], s3, s4, t);
      DP[index][3] = func(arr, index + 1, s1, s2, s3 + arr[index], s4, t);
      DP[index][4] = func(arr, index + 1, s1, s2, s3, s4 + arr[index], t);

      return DP[index][1] || DP[index][2] || DP[index][3] || DP[index][4];
    }
  }

  /**
   * 1 1 2 2 2
   *
   * <p>-> 8 / 4 == 2
   *
   * <p>1 1 2 2 2
   *
   * <p>{1, 1} {2} {2} {2}
   *
   * <p>size = 5
   *
   * <p>{1, 1, 1, 2, 3, 3, 4, 5} {3, 2} {4, 1} {5} {3, 1, 1}
   *
   * <p>0000 --> 1111 0 to 31 {1, 4, 3, 3, 4, 5}
   *
   * <p>1. Subset SUM
   *
   * <p>[] [] [] []
   *
   * <p>--> bit DP --> 0000 -> int[index][mask] boolean [][][][][]DP
   *
   * <p>f (s1, s2, s3, s4, i, T) { // O(4^N) if (s1 == s2 == s3 == s4 == t) --> true; if i >= length
   * --> false; if any of (s1, s2, s3, s4) > T --> false
   *
   * <p>return f(s1 + a[i], s2, s3, s4, i + 1, T) OR f(s1, s2 + a[i], s3, s4, i + 1, T) OR f(s1, s2,
   * s3 + a[i], s4, i + 1, T) OR f(s1, s2, s3, s4 + a[i], i + 1, T) }
   */
  public static void main(String[] args) {
    System.out.println(new Solution().makesquare(new int[] {1, 1, 2, 2, 2}));
    System.out.println(new Solution().makesquare(new int[] {3, 3, 3, 3, 4}));
    System.out.println(new Solution().makesquare(new int[] {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
  }
}
