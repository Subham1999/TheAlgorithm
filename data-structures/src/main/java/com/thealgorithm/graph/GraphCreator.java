package com.thealgorithm.graph;

import lombok.Value;

@Value
public class GraphCreator {
  final int size;
  final int[][] graph;
  final boolean undirected;

  private GraphCreator(int size, boolean undirected) {
    this.size = size;
    this.graph = new int[size][size];
    this.undirected = undirected;
  }

  public static GraphCreator getUndirected(int n) {
    return new GraphCreator(n, true);
  }

  public static GraphCreator getDirected(int n) {
    return new GraphCreator(n, false);
  }

  public GraphCreator addEdge(int u, int v, int w) {
    graph[u][v] = w;
    if (undirected) graph[v][u] = w;
    return this;
  }
}
