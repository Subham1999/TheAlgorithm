package com.thealgorithm.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author: Subham Santra
 */
public class PrimsMinimumSpanningTree {

  public <K, V> Set<Edge<K, V>> findMST(final Graph<K, V> graph) {
    Set<Vertex<K, V>> visited = new HashSet<>();
    PriorityQueue<Edge<K, V>> edgePQ =
        new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
    Set<Edge<K, V>> minimumSpanningTree = new HashSet<>();

    edgePQ.addAll(graph.getEdgeSet());

    while ((!edgePQ.isEmpty()) && (visited.size() < graph.getVertexSet().size())) {
      Edge<K, V> currentMinimumEdge = edgePQ.poll();
      visited.add(currentMinimumEdge.getVertex1());
      visited.add(currentMinimumEdge.getVertex2());
      minimumSpanningTree.add(currentMinimumEdge);
    }

    return minimumSpanningTree;
  }

  // Test code
  public static void main(String[] args) {
    Graph<Integer, Integer> graph = new Graph<>(false);

    graph.addEdge(Edge.createEdge(1, 2, 4));
    graph.addEdge(Edge.createEdge(1, 3, 1));
    graph.addEdge(Edge.createEdge(1, 5, 3));
    graph.addEdge(Edge.createEdge(5, 4, 1));
    graph.addEdge(Edge.createEdge(5, 3, 1));
    graph.addEdge(Edge.createEdge(2, 4, 2));

    System.out.println(new PrimsMinimumSpanningTree().findMST(graph));
  }
}
