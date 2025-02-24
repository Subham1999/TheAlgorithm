package com.thealgorithm.dp;

public class MoveOnMatrix {

  public static void main(String[] args) {
    System.out.println(new MoveOnMatrix().solve(3, 3));
  }

  int solve(int m, int n) {
    return solve(m - 1, 0, m - 1, n - 1);
  }

  private int solve(int i, int j, int I, int J) {
    if (i == I && j == J) {
      return 1;
    }
    if (i > I || j > J) return 0;

    int w1 = solve(i - 1, j + 1, I, J);
    int w2 = solve(i, j + 1, I, J);
    int w3 = solve(i + 1, j + 1, I, J);
    return w1 + w2 + w3;
  }
}
