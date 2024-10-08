package com.thealgorithm.strings;

import java.util.ArrayDeque;

/**
 * @author: Subham Santra
 */
public class BasicCalculator {
  public int calculate(String s) {
    s = clean(s);
    ArrayDeque<Integer> runstack = new ArrayDeque<>();
    ArrayDeque<Character> opstack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); ) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        int[] e = nextInt(s, i);
        runstack.addLast(e[0]);
        i = e[1];
        while (!opstack.isEmpty()
            && (opstack.getLast() == '*' || opstack.getLast() == '/')
            && runstack.size() > 1) {
          int last = runstack.pollLast();
          int secondLast = runstack.pollLast();
          runstack.addLast(perform(secondLast, last, opstack.pollLast()));
        }
      } else {
        opstack.addLast(c);
        ++i;
      }
    }
    while (runstack.size() > 1) {
      int last = runstack.pollFirst();
      int secondLast = runstack.pollFirst();
      int result = perform(last, secondLast, opstack.pollFirst());
      runstack.addFirst(result);
    }
    return runstack.peek();
  }

  int ord(char c) {
    switch (c) {
      case '/':
        return 4;
      case '*':
        return 3;
      case '+':
        return 1;
      case '-':
        return 1;
    }
    return 0;
  }

  private int perform(int i, int j, char op) {
    switch (op) {
      case '-':
        return i - j;
      case '+':
        return i + j;
      case '*':
        return i * j;
      case '/':
        return i / j;
    }
    return 0;
  }

  String clean(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c != ' ') sb.append(c);
    }
    return sb.toString();
  }

  int[] nextInt(String s, int i) {
    int n = 0;
    while (i < s.length() && Character.isDigit(s.charAt(i))) {
      n = (n * 10) + (s.charAt(i) - '0');
      ++i;
    }
    return new int[] {n, i};
  }

  public static void main(String[] args) {
    System.out.println(new BasicCalculator().calculate("1-1"));
    System.out.println(new BasicCalculator().calculate("1-1+1"));
    System.out.println(new BasicCalculator().calculate("2+3*4/2-1"));
    System.out.println(new BasicCalculator().calculate(" 3+5 / 2 "));
    // [8,1
    // [-
  }
}
