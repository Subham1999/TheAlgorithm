package com.thealgorithm.tierIII;

public class Pattern_0 {
  public static void main(String[] args) {
    printLevelGoNext(1, 50);
  }

  public static void printLevelGoNext(int level, int n) {
    if (level > n) {
      return;
    } else {
      printC('\n', 1);
    }

    printLevel(level, n);
    printLevelGoNext(level + 1, n);
  }

  private static void printLevel(int level, int n) {
    printSpaces(level, n);
    printStars((level << 1) - 1);
  }

  private static void printC(char c, int n) {
    if (n > 0) {
      System.out.print(c);
      printC(c, n - 1);
    }
  }

  private static void printStars(int n) {
    printC('*', n);
  }

  private static void printSpaces(int level, int n) {
    printC(' ', (n - level));
  }
}
