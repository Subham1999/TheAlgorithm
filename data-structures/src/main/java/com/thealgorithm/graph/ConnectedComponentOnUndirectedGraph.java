package com.thealgorithm.graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class ConnectedComponentOnUndirectedGraph {
  public static void main(String[] args) {
    // TEST 1
    {
      GraphCreator graphCreator =
          GraphCreator.getUndirected(7)
              .addEdge(1, 2, 1)
              .addEdge(2, 3, 1)
              .addEdge(3, 4, 1)
              .addEdge(5, 6, 1);

      System.out.println(
          countOfConnectedComponents(graphCreator.getAdjacencyList(), graphCreator.getVertices()));
    }

    // TEST 2
    {
      GraphCreator graphCreator =
        GraphCreator.getUndirected(7);
      System.out.println(
        countOfConnectedComponents(graphCreator.getAdjacencyList(), graphCreator.getVertices()));
    }

    // TEST 3
    {
      GraphCreator graphCreator =
        GraphCreator.getUndirected(3)
          .addEdge(1, 2)
          .addEdge(2, 0)
          .addEdge(1, 0);
      System.out.println(
        countOfConnectedComponents(graphCreator.getAdjacencyList(), graphCreator.getVertices()));
    }
  }

  public static int countOfConnectedComponents(
      Map<Integer, Set<Integer>> adjacencyList, Set<Vertex<Integer, Integer>> vertexSet) {
    int count = 0;
    Set<Integer> visited = new HashSet<>();
    for (Vertex<Integer, Integer> vertex : vertexSet) {
      if (!visited.contains(vertex.getKey())) {
        count++;
        DFS(adjacencyList, vertex.getKey(), visited);
      }
    }
    return count;
  }

  private static void DFS(
      Map<Integer, Set<Integer>> adjacencyList, int vertex, Set<Integer> visited) {
    if (visited.contains(vertex)) return;
    visited.add(vertex);
    for (int child : adjacencyList.get(vertex)) {
      DFS(adjacencyList, child, visited);
    }
  }
}
