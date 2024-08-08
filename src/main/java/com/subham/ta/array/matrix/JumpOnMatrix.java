package com.subham.ta.array.matrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class JumpOnMatrix {

  public static void main(String[] args) {
    int[][] matrix = {{2, -4, 2, -2, 6}};
    int[][] matrix2 = {{5, 2, 9}, {-6, 2, -5}, {-1, 0, -5}};
    int[][] matrix3 = {{-4, 8, -3, 2, -4, -8, 7, 5, -2}, {-5, 5, -7, -2, 6, -6, -8, -4, -4}};
    int[][] matrix4 = {{1, 1}, {1, 1}};
    //    System.out.println(new Solution().maxIncreasingCells(matrix));
    //    System.out.println(new Solution().maxIncreasingCells(matrix));
    System.out.println(new Solution().maxIncreasingCells(matrix2));
    //    System.out.println(new Solution().maxIncreasingCells(matrix3));
    System.out.println(new Solution().maxIncreasingCells(matrix4));
  }

  static class Solution {
    public int maxIncreasingCells(int[][] matrix) {
      List<Cell> cells = new ArrayList<>();

      int m = matrix.length;
      int n = matrix[0].length;

      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          cells.add(new Cell(i, j, matrix[i][j], 1));
        }
      }

      cells.sort(Comparator.comparingInt(c -> c.val));

      SortedSet<Cell>[] rows = new SortedSet[m];
      SortedSet<Cell>[] cols = new SortedSet[n];

      for (int i = 0; i < m; ++i) {
        rows[i] =
            new TreeSet<Cell>(
                (c1, c2) -> {
                  if (c1.val != c2.val) {
                    return c1.val - c2.val;
                  }
                  return c2.count - c1.count;
                });
      }

      for (int i = 0; i < n; ++i) {
        cols[i] =
            new TreeSet<Cell>(
                (c1, c2) -> {
                  if (c1.val != c2.val) {
                    return c1.val - c2.val;
                  }
                  return c2.count - c1.count;
                });
      }

      for (int i = cells.size() - 1; i >= 0; i--) {
        Cell cell = cells.get(i);

        //        System.out.println("-- " + cell);
        //        System.out.println(cols[cell.col]);
        //        System.out.println(rows[cell.row]);
        if (!rows[cell.row].isEmpty()) {
          for (Cell greaterCell : rows[cell.row].tailSet(cell)) {
            if (greaterCell.val != cell.val) {
              cell.count = Math.max(cell.count, greaterCell.count + 1);
              break;
            }
          }
        }

        if (!cols[cell.col].isEmpty()) {
          for (Cell greaterCell : cols[cell.col].tailSet(cell)) {
            if (greaterCell.val != cell.val) {
              cell.count = Math.max(cell.count, greaterCell.count + 1);
              break;
            }
          }
        }

        rows[cell.row].add(cell);
        cols[cell.col].add(cell);
        //        System.out.println("-- " + cell);
        //        System.out.println();
      }

      //      System.out.println(cells);
      return Math.max(
          cells.stream().map(c -> c.count).mapToInt(Integer::intValue).max().getAsInt(), 1);
    }

    static class Cell {
      int row;
      int col;
      int val;
      int count;

      public Cell(int row, int col, int val, int count) {
        this.row = row;
        this.col = col;
        this.val = val;
        this.count = count;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return row == cell.row && col == cell.col && val == cell.val;
      }

      @Override
      public int hashCode() {
        return Objects.hash(row, col, val);
      }

      @Override
      public String toString() {
        return "{r=" + row + ", c=" + col + ", v=" + val + ", f=" + count + "}";
      }
    }
  }
}
