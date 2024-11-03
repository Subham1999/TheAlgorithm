package com.thealgorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Value;

@Value
public class GraphCreator {
  int size;
  int[][] adjacencyMatrix;
  Map<Integer, Set<Integer>> adjacencyList;
  Set<Vertex<Integer, Integer>> vertices;
  Set<Edge<Integer, Integer>> edgeSet;
  boolean undirected;

  private GraphCreator(int size, boolean undirected) {
    this.size = size;
    this.adjacencyMatrix = new int[size][size];
    this.undirected = undirected;
    this.adjacencyList = new HashMap<>();
    this.vertices = new HashSet<>();
    this.edgeSet = new HashSet<>();
    for (int i = 0; i < size; ++i) {
      vertices.add(Vertex.create(i));
    }
    for (int i = 0; i < size; ++i) {
      adjacencyList.put(i, new HashSet<>());
    }
  }

  public static GraphCreator getUndirected(int n) {
    return new GraphCreator(n, true);
  }

  public static GraphCreator getDirected(int n) {
    return new GraphCreator(n, false);
  }

  public GraphCreator addEdge(int u, int v) {
    return addEdge(u, v, 1);
  }

  public GraphCreator addEdge(int u, int v, int w) {
    addEdge0(u, v, w);
    if (undirected) {
      addEdge0(v, u, w);
    }
    return this;
  }

  private void addEdge0(int u, int v, int w) {
    edgeSet.add(Edge.createEdge(u, v, w));
    addToAdjacencyList(u, v);
    adjacencyMatrix[u][v] = w;
  }

  private void addToAdjacencyList(int u, int v) {
    adjacencyList.get(u).add(v);
  }
}
