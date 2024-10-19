package com.thealgorithm.graph;

import com.thealgorithm.commons.Pair;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author: Subham Santra
 */
public class EdmondKarpAlgorithm {
  private static final int INF = Integer.MAX_VALUE;

  private <K, V> int BFS(
      final Vertex<K, V> source,
      final Vertex<K, V> sink,
      final Map<Vertex<K, V>, Vertex<K, V>> parent,
      final Map<Vertex<K, V>, Map<Vertex<K, V>, Integer>> capacityMatrix) {

    Queue<Pair<Vertex<K, V>, Integer>> queue = new ArrayDeque<>();
    queue.add(Pair.of(source, INF));

    while (!queue.isEmpty()) {
      Pair<Vertex<K, V>, Integer> vPair = queue.poll();
      Vertex<K, V> currentVertex = vPair.getFirst();
      for (Vertex<K, V> neighbor : currentVertex.getNeighbors()) {
        if ((parent.get(neighbor) == null)
            && (capacityMatrix.get(currentVertex).get(neighbor) > 0)) {
          parent.put(neighbor, currentVertex);
          int maxFlow =
              Math.min(capacityMatrix.get(currentVertex).get(neighbor), vPair.getSecond());
          if (currentVertex.equals(sink)) {
            return maxFlow;
          }
          queue.add(Pair.of(neighbor, maxFlow));
        }
      }
    }
    return 0;
  }

  public <K, V> int maxFlow(
      final Graph<K, V> graph, final Vertex<K, V> source, final Vertex<K, V> sink) {
    Map<Vertex<K, V>, Map<Vertex<K, V>, Integer>> capacityMatrix = new HashMap<>();

    for (var v : graph.getVertexSet()) {
      capacityMatrix.put(v, new HashMap<>());
    }
    for (var e : capacityMatrix.keySet()) {
      for (var v : graph.getVertexSet()) {
        capacityMatrix.get(e).put(v, 0);
      }
    }
    for (var edge : graph.getEdgeSet()) {
      capacityMatrix.get(edge.getVertex1()).put(edge.getVertex2(), edge.getResidualCapacity());
    }

    int totalFlow = 0;

    while (true) {
      Map<Vertex<K, V>, Vertex<K, V>> parent = new HashMap<>();
      int maxFlow = BFS(source, sink, parent, capacityMatrix);
      if (maxFlow == 0) {
        break;
      }
      totalFlow += maxFlow;
      Vertex<K, V> v = sink;
      while (!v.equals(source)) {
        Vertex<K, V> p = parent.get(v);
        capacityMatrix.get(p).put(v, capacityMatrix.get(p).get(v) - maxFlow);
        capacityMatrix.get(v).put(p, capacityMatrix.get(v).get(p) + maxFlow);
        v = p;
      }
    }
    return totalFlow;
  }

  public static void main(String[] args) {
    // Finding MAX FLOW from source to destination
    Graph<Character, Character> graph = new Graph<>(true);

    graph.addEdge(Edge.createNetworkEdge('S', 'A', 7));
    graph.addEdge(Edge.createNetworkEdge('S', 'D', 4));
    graph.addEdge(Edge.createNetworkEdge('D', 'A', 3));
    graph.addEdge(Edge.createNetworkEdge('D', 'A', 3));
    graph.addEdge(Edge.createNetworkEdge('D', 'C', 2));
    graph.addEdge(Edge.createNetworkEdge('A', 'C', 3));
    graph.addEdge(Edge.createNetworkEdge('A', 'B', 5));
    graph.addEdge(Edge.createNetworkEdge('B', 'T', 8));
    graph.addEdge(Edge.createNetworkEdge('C', 'B', 3));
    graph.addEdge(Edge.createNetworkEdge('C', 'T', 5));

    System.out.println(
        new EdmondKarpAlgorithm().maxFlow(graph, graph.getVertex('S'), graph.getVertex('T')));
  }
}
