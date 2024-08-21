package com.subham.ta.strings;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class LongestSubstringLargeDatasize {

  static class Solution {

    public int longestSubstringWithTwoDistinctChar(final Stream<Character> characterStream) {
      AtomicInteger counter = new AtomicInteger(-1);
      AtomicInteger maxLength = new AtomicInteger(0);
      final AtomicReference<Character> c1 = new AtomicReference<>();
      final AtomicReference<Character> c2 = new AtomicReference<>();
      int[] lastIndices = new int[26];
      Arrays.fill(lastIndices, -1);

      CharWindow window = new CharWindow();

      characterStream.forEach(
          character -> {
            int currentIndex = counter.incrementAndGet();
            window.add(character, currentIndex);
            maxLength.set(Math.max(maxLength.get(), window.length()));
          });

      return maxLength.get();
    }
  }

  static class CharWindow {
    private Character c1 = (char) ('a' - 1);
    private Character c2 = (char) ('a' - 1);
    private int[] indices = new int[27];
    private int windowStart, windowEnd;

    public CharWindow() {
      Arrays.fill(indices, -1);
    }

    void add(char c, int i) {
      if (c1 == null) {
        c1 = c;
        windowStart = i;
      } else if (c2 == null) {
        c2 = c;
      } else {
        if (c != c1 && c != c2) {
          // A new character entered into window;
          // We can remove c1;
          windowStart = indexOf(c1) + 1;
          c1 = c2;
          c2 = c;
        }
      }
      windowEnd = i;
      updateIndex(c, i);
    }

    private void updateIndex(char c, int i) {
      indices[c - 'a'] = i;
    }

    private int indexOf(char c) {
      if (c == 'a' - 1) {
        return -1;
      }
      return indices[c - 'a'];
    }

    int length() {
      return windowEnd - windowStart + 1;
    }
  }

  public static void main(String[] args) {
    System.out.println(
        new Solution()
            .longestSubstringWithTwoDistinctChar(Stream.of('a', 'b', 'b', 'c', 'c', 'a', 'a')));

    System.out.println(
        new Solution().longestSubstringWithTwoDistinctChar(Stream.of('a', 'a', 'a')));

    System.out.println(new Solution().longestSubstringWithTwoDistinctChar(Stream.of('a')));

    System.out.println(new Solution().longestSubstringWithTwoDistinctChar(Stream.of()));

    System.out.println(
        new Solution()
            .longestSubstringWithTwoDistinctChar(Stream.of('a', 'b', 'a', 'b', 'a', 'b')));

    System.out.println(
        new Solution()
            .longestSubstringWithTwoDistinctChar(
                Stream.of(
                    'a', 'b', 'c', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'b', 'd', 'd', 'd', 'a', 'd',
                    'a', 'c', 'b')));
  }
}
