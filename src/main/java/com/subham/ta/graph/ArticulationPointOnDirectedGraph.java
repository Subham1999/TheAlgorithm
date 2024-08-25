package com.subham.ta.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class ArticulationPointOnDirectedGraph {
  public static <K, V> Set<Vertex<K, V>> findArticulationPoint(final Graph<K, V> graph) {
    final Map<Vertex<K, V>, Integer> highTime = new HashMap<>();
    final Map<Vertex<K, V>, Integer> lowTime = new HashMap<>();
    final Set<Vertex<K, V>> articulationPoints = new HashSet<>();
    final Set<Vertex<K, V>> visitedSet = new HashSet<>();
    final AtomicInteger timer = new AtomicInteger(0);

    Vertex<K, V> root = graph.getVertexSet().iterator().next();
    DFS(timer, graph, root, null, highTime, lowTime, articulationPoints, visitedSet);

    return articulationPoints;
  }

  private static <K, V> void DFS(
      final AtomicInteger timer,
      final Graph<K, V> graph,
      final Vertex<K, V> currentVertex,
      final Vertex<K, V> parent,
      final Map<Vertex<K, V>, Integer> highTime,
      final Map<Vertex<K, V>, Integer> lowTime,
      final Set<Vertex<K, V>> articulationPoints,
      final Set<Vertex<K, V>> visitedSet) {

    if (visitedSet.contains(currentVertex)) {
      return;
    }

    visitedSet.add(currentVertex);
    int currentTime = timer.incrementAndGet();

    highTime.put(currentVertex, currentTime);
    lowTime.put(currentVertex, currentTime);

    for (final Vertex<K, V> children : currentVertex.getNeighbors()) {
      if (children.equals(parent)) {
        continue;
      }
      DFS(timer, graph, children, currentVertex, highTime, lowTime, articulationPoints, visitedSet);
    }

    // calculate low time for current vertex.
    for (final Vertex<K, V> children : currentVertex.getNeighbors()) {
      if (children.equals(parent)) {
        continue;
      }
      lowTime.put(
          currentVertex,
          Math.min(lowTime.getOrDefault(children, Integer.MAX_VALUE), lowTime.get(currentVertex)));
    }
  }

  public static void main(String[] args) {
    Graph<Character, Character> graph = new Graph<>(false);
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('A', 'B'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('A', 'C'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('C', 'B'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('C', 'D'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('E', 'D'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('E', 'G'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('E', 'F'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('G', 'F'));
    graph.addEdge(Edge.createSimpleEdgeUnWeighted('H', 'F'));

    System.out.println(findArticulationPoint(graph));
  }
}
