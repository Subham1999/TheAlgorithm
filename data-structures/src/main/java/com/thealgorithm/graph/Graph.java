package com.thealgorithm.graph;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Graph<K, V> {
  private Set<Vertex<K, V>> vertexSet;
  private Set<Edge<K, V>> edgeSet;
  private boolean isDirected;

  public Graph(boolean isDirected) {
    this.isDirected = isDirected;
    this.vertexSet = new HashSet<>();
    this.edgeSet = new HashSet<>();
  }

  public void addEdge(Edge<K, V> edge) {
    if (!edgeSet.contains(edge)) {
      vertexSet.add(edge.getVertex1());
      vertexSet.add(edge.getVertex2());
      edgeSet.add(edge);

      edge.getVertex1().getEdges().add(edge);
      edge.getVertex1().getNeighbors().add(edge.getVertex2());
      if (!this.isDirected()) {
        edge.getVertex2().getEdges().add(edge);
        edge.getVertex2().getNeighbors().add(edge.getVertex1());
      }
    }
  }

  void clear() {
    vertexSet.clear();
    edgeSet.clear();
    isDirected = false;
  }

  Vertex<K, V> getVertex(K key) {
    return vertexSet.stream().filter(v -> v.getKey().equals(key)).findFirst().get();
  }
}
