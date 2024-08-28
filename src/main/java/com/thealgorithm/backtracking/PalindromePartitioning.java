package com.thealgorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class PalindromePartitioning {

  public List<List<String>> partition(String s) {
    List<List<String>> possiblePalindromes = new ArrayList<>();

    partition(s, 0, s.length() - 1, new ArrayList<>(), possiblePalindromes);

    return possiblePalindromes;
  }

  void partition(
      String s, int lo, int hi, List<String> current, List<List<String>> possiblePalindromes) {
    // System.out.println(current);
    if (lo > hi) {
      return;
    }

    for (int p = lo; p <= hi; ++p) {
      if (isPalindrome(s, lo, p)) {
        current.addLast(s.substring(lo, p + 1));
        partition(s, p + 1, hi, current, possiblePalindromes);

        if (validList(current, s.length())) {
          possiblePalindromes.addFirst(new ArrayList<>(current));
        }

        current.removeLast();
      }
    }
  }

  boolean isPalindrome(String s, int l, int h) {
    for (; l <= h; ++l, --h) {
      if (s.charAt(l) != s.charAt(h)) return false;
    }
    return true;
  }

  boolean validList(List<String> list, int n) {
    int x = 0;
    for (String s : list) x += s.length();
    return x == n;
  }

  public static void main(String[] args) {
    System.out.println(new PalindromePartitioning().partition("baab"));
    System.out.println(new PalindromePartitioning().partition("A"));
    System.out.println(new PalindromePartitioning().partition("aab"));
    System.out.println(new PalindromePartitioning().partition(""));
    System.out.println(new PalindromePartitioning().partition("aababbababbabbabba"));
  }
}
