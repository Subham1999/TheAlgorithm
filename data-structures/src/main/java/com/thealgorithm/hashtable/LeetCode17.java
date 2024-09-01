package com.thealgorithm.hashtable;

import java.util.*;

public class LeetCode17 {
  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.letterCombinations("23"));
    System.out.println(solution.letterCombinations("32462"));
  }

  static class Solution {
    private final Map<Character, Set<Character>> mobileKeypad;

    public Solution() {
      mobileKeypad = new HashMap<>();
      mobileKeypad.put('2', new HashSet<>(Arrays.asList('a', 'b', 'c')));
      mobileKeypad.put('3', new HashSet<>(Arrays.asList('d', 'e', 'f')));
      mobileKeypad.put('4', new HashSet<>(Arrays.asList('g', 'h', 'i')));
      mobileKeypad.put('5', new HashSet<>(Arrays.asList('j', 'k', 'l')));
      mobileKeypad.put('6', new HashSet<>(Arrays.asList('m', 'n', 'o')));
      mobileKeypad.put('7', new HashSet<>(Arrays.asList('p', 'q', 'r', 's')));
      mobileKeypad.put('8', new HashSet<>(Arrays.asList('t', 'u', 'v')));
      mobileKeypad.put('9', new HashSet<>(Arrays.asList('w', 'x', 'y', 'z')));
    }

    public List<String> letterCombinations(String digits) {
      List<String> letterCombinations = new ArrayList<>();
      letterCombinations(digits, -1, letterCombinations, null);
      return letterCombinations;
    }

    private void letterCombinations(
        String digits,
        int currentIndex,
        List<String> letterCombinations,
        StringBuilder calculatedPrefix) {

      if (currentIndex >= digits.length()) {
        letterCombinations.add(calculatedPrefix.toString());
        return;
      }
      if (currentIndex == -1) {
        currentIndex++;
        calculatedPrefix = new StringBuilder();
      }
      char currentChar = digits.charAt(currentIndex);
      for (char c : mobileKeypad.get(currentChar)) {
        calculatedPrefix.append(c);
        letterCombinations(digits, currentIndex + 1, letterCombinations, calculatedPrefix);
        calculatedPrefix.deleteCharAt(calculatedPrefix.length() - 1);
      }
    }
  }
}
