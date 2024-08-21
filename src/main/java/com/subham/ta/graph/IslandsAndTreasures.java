package com.subham.ta.graph;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class IslandsAndTreasures {
  static class Solution {
    public void islandsAndTreasure(int[][] grid) {
      final int M = grid.length;
      final int N = grid[0].length;
      final int INF = Integer.MAX_VALUE;
      List<Pair> treasures = new ArrayList<>();

      for (int i = 0; i < grid.length; ++i) {
        for (int j = 0; j < grid[0].length; ++j) {
          if (grid[i][j] == 0) {
            treasures.add(new Pair(i, j));
          }
        }
      }

      // perform DFS from each treasure chest;
      for (Pair treasureCell : treasures) {
        Queue<Pair> q1 = new ArrayDeque<>();
        q1.add(treasureCell);
        int distance = 0;

        while (!q1.isEmpty()) {
          Queue<Pair> q2 = new ArrayDeque<>();
          while (!q1.isEmpty()) {
            Pair index = q1.poll();
            Integer row = index.getKey();
            Integer col = index.getValue();
            if (isNotValid(row, M) || isNotValid(col, N)) {
              continue;
            }
            if (grid[row][col] == -1) {
              continue;
            }
            if (grid[row][col] != 0 && grid[row][col] < distance) {
              continue;
            }
            updateGrid(grid, row, col, distance);
            q2.add(new Pair(row - 1, col));
            q2.add(new Pair(row + 1, col));
            q2.add(new Pair(row, col + 1));
            q2.add(new Pair(row, col - 1));
          }
          q1 = q2;
          distance++;
        }
      }
    }

    private void updateGrid(int[][] grid, int i, int j, int distance) {
      if (isNotValid(i, grid.length) || isNotValid(j, grid[0].length)) {
        return;
      }
      grid[i][j] = Math.min(grid[i][j], distance);
    }

    boolean isNotValid(int i, int M) {
      return 0 > i || i >= M;
    }

    static class Pair extends AbstractMap.SimpleEntry<Integer, Integer> {
      public Pair(int i, int j) {
        super(i, j);
      }
    }
  }

  public static void main(String[] args) {
    //    emptyTest();
    test1();
    test2();
    test4();
  }

  private static void test1() {
    int[][] grid = {
      {2147483647, -1, 0, 2147483647},
      {2147483647, 2147483647, 2147483647, -1},
      {2147483647, -1, 2147483647, -1},
      {0, -1, 2147483647, 2147483647}
    };

    new Solution().islandsAndTreasure(grid);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.printf("%d\t", grid[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void test2() {
    int[][] grid = {
      {-1, 0},
      {Integer.MAX_VALUE, -1},
      {Integer.MAX_VALUE, Integer.MAX_VALUE}
    };

    new Solution().islandsAndTreasure(grid);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.printf("%d\t", grid[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void emptyTest() {
    int[][] grid = {};

    new Solution().islandsAndTreasure(grid);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.printf("%d\t", grid[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void test4() {
    int[][] grid = {
      {2147483647, 2147483647, 2147483647},
      {2147483647, -1, 2147483647},
      {0, 2147483647, 2147483647}
    };

    new Solution().islandsAndTreasure(grid);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.printf("%d\t", grid[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
