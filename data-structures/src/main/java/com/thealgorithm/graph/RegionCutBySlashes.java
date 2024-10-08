package com.thealgorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: Subham Santra
 * @implNote : For in depth understanding check this <a
 *     href="https://leetcode.com/problems/regions-cut-by-slashes/solutions/5888200/simple-solution-union-find-omn/">leetcode
 *     post</a>
 */
public class RegionCutBySlashes {

  public static void main(String[] args) {
    System.out.println(new RegionCutBySlashes().regionsBySlashes(new String[] {" /", "/ "}));
    System.out.println(new RegionCutBySlashes().regionsBySlashes(new String[] {" /", "  "}));
    System.out.println(new RegionCutBySlashes().regionsBySlashes(new String[] {"/\\", "\\/"}));
  }

  public int regionsBySlashes(String[] grid) {
    List<Edge> edges = new ArrayList<>();
    int m = grid.length + 1;
    int n = grid[0].toCharArray().length + 1;

    for (int i = 0; i < grid.length; ++i) {
      char[] row = grid[i].toCharArray();
      // System.out.println(Arrays.toString(row));
      for (int j = 0; j < row.length; ++j) {
        if (row[j] == ' ') continue;
        if (row[j] == '/') {
          int u = vertexCode(i + 1, j, n);
          int v = vertexCode(i, j + 1, n);
          edges.add(new Edge(u, v));
        } else if (row[j] == '\\') {
          int u = vertexCode(i, j, n);
          int v = vertexCode(i + 1, j + 1, n);
          edges.add(new Edge(u, v));
        }
      }
    }

    for (int j = 0; j < n - 1; ++j) {
      int u = vertexCode(0, j, n);
      int v = vertexCode(0, j + 1, n);
      edges.add(new Edge(u, v));
    }

    for (int j = 0; j < n - 1; ++j) {
      int u = vertexCode(m - 1, j, n);
      int v = vertexCode(m - 1, j + 1, n);
      edges.add(new Edge(u, v));
    }

    for (int i = 0; i < m - 1; ++i) {
      int u = vertexCode(i, 0, n);
      int v = vertexCode(i + 1, 0, n);
      edges.add(new Edge(u, v));
    }

    for (int i = 0; i < m - 1; ++i) {
      int u = vertexCode(i, n - 1, n);
      int v = vertexCode(i + 1, n - 1, n);
      edges.add(new Edge(u, v));
    }

    // RUN U-F here
    int cycleCount = 0;
    UnionFind unionFind = new UnionFind((m + 1) * (n + 1) + 1);

    for (Edge edge : edges) {
      if (unionFind.addEdge(edge)) {
        cycleCount++;
      }
    }

    return cycleCount;
  }

  int vertexCode(int i, int j, int C) {
    return (i * C) + j + 1;
  }

  static class UnionFind {
    private int[] UF;

    public UnionFind(int size) {
      UF = new int[size];
      Arrays.fill(UF, -1);
    }

    boolean addEdge(Edge e) {
      int up = parent(e.u);
      int vp = parent(e.v);
      if (up == vp) {
        // If already part of same group then cycle exists;
        return true;
      }
      join(up, vp);
      return false;
    }

    int parent(int n) {
      if (UF[n] < 0) {
        return n;
      }
      return parent(UF[n]);
    }

    void join(int a, int b) {
      int x = UF[b];
      UF[b] = a;
      UF[a] = UF[a] - Math.abs(x);
    }
  }

  static class Edge {
    int u;
    int v;

    public Edge(int u, int v) {
      this.u = u;
      this.v = v;
    }

    public int hashCode() {
      return Objects.hash(u, v);
    }

    public boolean equals(Object e) {
      return (e != null) && (e instanceof Edge) && (((Edge) e).u == u && ((Edge) e).v == v);
    }
  }
}
