package com.subham.ta.dp.lcs;

import com.subham.ta.commons.Pair;

import java.util.HashMap;
import java.util.Map;

/** sequence1 : {a b c d e f g} sequence2 : {b x q a d a e f y a g} */
public class LongestCommonSubsequence {

  public static void main(String[] args) {
    System.out.println(SimpleSolution.longestCommonSubsequenceLength("abcdefg", "bxqadaefyag"));
    System.out.println(
        SolutionWithMemoization.longestCommonSubsequenceLength("abcdefg", "bxqadaefyag"));
  }

  /** This solution only finds the solution by recursion */
  static class SimpleSolution {
    public static int longestCommonSubsequenceLength(String a, String b) {
      return (a == null || b == null)
          ? 0
          : longestCommonSubsequenceLength(a, b, a.length() - 1, b.length() - 1);
    }

    private static int longestCommonSubsequenceLength(String a, String b, int i, int j) {
      if (i < 0 || j < 0) {
        return 0;
      }

      if (a.charAt(i) == b.charAt(j)) {
        return 1 + longestCommonSubsequenceLength(a, b, i - 1, j - 1);
      } else {
        return Math.max(
            longestCommonSubsequenceLength(a, b, i, j - 1),
            longestCommonSubsequenceLength(a, b, i - 1, j));
      }
    }
  }

  static class SolutionWithMemoization {
    public static int longestCommonSubsequenceLength(String a, String b) {
      Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
      return (a == null || b == null)
          ? 0
          : longestCommonSubsequenceLength(a, b, a.length() - 1, b.length() - 1, memo);
    }

    private static int longestCommonSubsequenceLength(
        String a, String b, int i, int j, Map<Pair<Integer, Integer>, Integer> memo) {
      if (i < 0 || j < 0) {
        return 0;
      }

      Pair<Integer, Integer> pair = Pair.of(i, j);

      if (memo.containsKey(pair)) {
        memo.get(pair);
      }

      int answer;

      if (a.charAt(i) == b.charAt(j)) {
        answer = 1 + longestCommonSubsequenceLength(a, b, i - 1, j - 1, memo);
      } else {
        answer =
            Math.max(
                longestCommonSubsequenceLength(a, b, i, j - 1, memo),
                longestCommonSubsequenceLength(a, b, i - 1, j, memo));
      }

      memo.put(pair, answer);
      return answer;
    }
  }

  static class Solution3WithMemoization {
    public static String longestCommonSubsequence(String a, String b) {
      return null;
    }

    private static StringBuilder longestCommonSubsequence(String a, String b, int i, int j) {
      return null;
    }
  }
}
