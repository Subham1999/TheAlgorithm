package com.thealgorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Subham Santra
 */
public class NumberOfWonderfulSubstrings {
  static class Solution {
    int getmask(char c) {
      int cc = (c - 'a');
      return (1 << cc);
    }

    public long wonderfulSubstrings(String word) {
      int n = word.length();
      int[] bitmask = new int[n + 1];

      for (int i = 0; i < word.length(); ++i) {
        bitmask[i + 1] = (bitmask[i] ^ getmask(word.charAt(i)));
      }

      Map<Integer, Long> freq = new HashMap<>();
      long answer = 0;
      freq.put(0, 1L);

      for (int i = 0; i < n; ++i) {
        int xor = bitmask[i + 1];
        answer += freq.getOrDefault(xor, 0L);
        for (int bit = 0; bit < 11; ++bit) {
          if ((xor & (1 << bit)) > 0) {
            answer += freq.getOrDefault((xor & (~(1 << bit))), 0L);
          } else {
            answer += freq.getOrDefault((xor | (1 << bit)), 0L);
          }
        }
        freq.put(xor, 1 + freq.getOrDefault(xor, 0L));
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    System.out.println(new Solution().wonderfulSubstrings("aabb"));
    System.out.println(new Solution().wonderfulSubstrings("aba"));
    //    System.out.println(new Solution().wonderfulSubstrings("abaa"));
    System.out.println(new Solution().wonderfulSubstrings("ab"));
  }
}
