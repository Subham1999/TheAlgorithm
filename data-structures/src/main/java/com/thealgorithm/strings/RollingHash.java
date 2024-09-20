package com.thealgorithm.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class RollingHash {
  private static final long PRIME = 67;
  private static final long MOD = (long) (1e9 + 7);

  private long hash(char[] txt, int i, int j) {
    long hash = 0;
    for (int idx = i; idx <= j; ++idx) {
      hash = (hash + ((long) ((txt[idx] - 'a') * Math.pow(PRIME, (idx - i)))) % MOD) % MOD;
    }
    return hash;
  }

  public List<Integer> findOccurrences(char[] pattern, char[] text) {
    List<Integer> occurrences = new ArrayList<>();

    int PL = pattern.length;
    long hashOfPattern = hash(pattern, 0, PL - 1);

//    for (int i = 0)

    return occurrences;
  }

  public static void main(String[] args) {
    System.out.println(new RollingHash().hash("abc".toCharArray(), 0, 2));
  }
}
