package com.thealgorithm;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {

    System.out.println(new Solution().takeCharacters("aabbccca", 2));
  }
}

class Solution {
  private int[] count;
  private int k;
  private int[][] DP;

  public int takeCharacters(String s, int k) {
    if (k == 0) {
      return 0;
    }
    count = new int[3];
    this.k = k;
    DP = new int[s.length()][s.length()];
    for (int[] _d : DP) Arrays.fill(_d, -1);

    takeAndReturn(s, 0, s.length() - 1, 0);

    return DP[0][s.length() - 1] == Integer.MAX_VALUE ? -1 : DP[0][s.length() - 1];
  }

  int takeAndReturn(String s, int left, int right, int minutes) {
    // System.out.println(Arrays.toString(count) + " " + left + " " + right);
    if (count[0] >= k && count[1] >= k && count[2] >= k) {
      return minutes;
    }

    if (left > right) {
      return Integer.MAX_VALUE;
    }

    if (DP[left][right] != -1) {
      return DP[left][right];
    }

    int ans;

    count[s.charAt(left) - 'a']++;
    ans = takeAndReturn(s, left + 1, right, minutes + 1);
    count[s.charAt(left) - 'a']--;

    count[s.charAt(right) - 'a']++;
    ans = Math.min(ans, takeAndReturn(s, left, right - 1, minutes + 1));
    count[s.charAt(right) - 'a']--;

    return DP[left][right] = ans;
  }
}
