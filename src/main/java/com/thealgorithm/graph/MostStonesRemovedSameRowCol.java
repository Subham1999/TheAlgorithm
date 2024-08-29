package com.thealgorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class MostStonesRemovedSameRowCol {

  static class Solution {
    static class Vertex {
      int row;
      int col;

      public Vertex(int row, int col) {
        this.row = row;
        this.col = col;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return row == vertex.row && col == vertex.col;
      }

      @Override
      public int hashCode() {
        return Objects.hash(row, col);
      }

      @Override
      public String toString() {
        return "{" + row + ", " + col + '}';
      }
    }

    /**
     * The intuition is -- As all the vertices will be connected - we will use DFS to find out no of
     * connected components
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
      Map<Vertex, Set<Vertex>> graph = new HashMap<>();
      Map<Integer, Set<Vertex>> rowMap = new HashMap<>();
      Map<Integer, Set<Vertex>> colMap = new HashMap<>();

      for (int[] stone : stones) {
        Vertex vertex = new Vertex(stone[0], stone[1]);
        if (rowMap.containsKey(vertex.row)) {
          for (Vertex sameRowVertex : rowMap.get(vertex.row)) {
            graph.putIfAbsent(vertex, new HashSet<>());
            graph.get(vertex).add(sameRowVertex);

            graph.putIfAbsent(sameRowVertex, new HashSet<>());
            graph.get(sameRowVertex).add(vertex);
          }
        }
        if (colMap.containsKey(vertex.col)) {
          for (Vertex sameColVertex : colMap.get(vertex.col)) {
            graph.putIfAbsent(vertex, new HashSet<>());
            graph.get(vertex).add(sameColVertex);

            graph.putIfAbsent(sameColVertex, new HashSet<>());
            graph.get(sameColVertex).add(vertex);
          }
        }

        rowMap.putIfAbsent(vertex.row, new HashSet<>());
        colMap.putIfAbsent(vertex.col, new HashSet<>());

        rowMap.get(vertex.row).add(vertex);
        colMap.get(vertex.col).add(vertex);
      }

      Set<Vertex> removedVertices = new HashSet<>();
      int connectedComponents = 0;
      for (int[] stone : stones) {
        Vertex vertex = new Vertex(stone[0], stone[1]);
        if (!removedVertices.contains(vertex)) {
          ++connectedComponents;
          DFS(graph, removedVertices, vertex);
        }
      }

      return stones.length - connectedComponents;
    }

    /**
     * This method will check each vertex and remove it. This will continue to remove all the
     * connected vertices
     *
     * @param graph
     * @param removedVertices
     * @param vertex
     */
    private void DFS(Map<Vertex, Set<Vertex>> graph, Set<Vertex> removedVertices, Vertex vertex) {
      if (removedVertices.contains(vertex)) {
        return;
      }

      if (!graph.containsKey(vertex)) {
        return;
      }

      removedVertices.add(vertex);
      for (Vertex nextVertex : graph.get(vertex)) {
        DFS(graph, removedVertices, nextVertex);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(
        new Solution().removeStones(new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));

    System.out.println(new Solution().removeStones(new int[][] {{0, 1}, {1, 0}, {1, 1}}));

    System.out.println(
        new Solution().removeStones(new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));

    System.out.println(
        new Solution()
            .removeStones(
                new int[][] {
                  {0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 1}, {2, 2}, {3, 2}, {3, 3}, {3, 4}, {4, 3},
                  {4, 4}
                }));
  }
}
