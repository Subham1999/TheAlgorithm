package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Subham Santra
 * @implNote
 *     <p>This is a pure back-tracking approach This problem can be solved using dynamic programming
 *     also, I will add DP code in DP section
 */
public class PalindromePartitioning {

  Boolean[][] memo;

  public List<List<String>> partition(String s) {
    memo = new Boolean[s.length()][s.length()];
    List<List<String>> possiblePalindromes = new ArrayList<>();
    partition(s, 0, new ArrayList<>(), possiblePalindromes);
    return possiblePalindromes;
  }

  void partition(String s, int lo, List<String> current, List<List<String>> possiblePalindromes) {
    if (lo == s.length()) {
      possiblePalindromes.addFirst(new ArrayList<>(current));
      return;
    }

    for (int p = lo; p < s.length(); ++p) {
      if (isPalindrome(s, lo, p)) {
        current.addLast(s.substring(lo, p + 1));
        partition(s, p + 1, current, possiblePalindromes);
        current.removeLast();
      }
    }
  }

  boolean isPalindrome(String s, int l, int h) {
    if (memo[l][h] == null) {
      for (int i = l, j = h; i <= j; ++i, --j) {
        if (s.charAt(i) != s.charAt(j)) {
          return memo[l][h] = false;
        }
      }
      return memo[l][h] = true;
    }
    return memo[l][h];
  }

  public static void main(String[] args) {
    System.out.println(new PalindromePartitioning().partition("baab"));
    System.out.println(new PalindromePartitioning().partition("A"));
    System.out.println(new PalindromePartitioning().partition("aab"));
    System.out.println(new PalindromePartitioning().partition(""));
    System.out.println(new PalindromePartitioning().partition("aababbababbabbabba"));
  }
}
