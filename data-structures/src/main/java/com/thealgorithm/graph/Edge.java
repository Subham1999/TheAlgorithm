package com.thealgorithm.graph;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Edge<K, V> {
  private Vertex<K, V> vertex1;
  private Vertex<K, V> vertex2;
  private double weight = 1D;
  private final int capacity;
  private int currentFlow;
  private int residualCapacity;

  public static <V> Edge<V, V> createSimpleEdgeUnWeighted(V v1, V v2) {
    return new Edge<>(Vertex.create(v1), Vertex.create(v2), 0D, 0, 0, 0);
  }

  public static <V> Edge<V, V> createEdge(V v1, V v2, double weight) {
    return new Edge<>(Vertex.create(v1), Vertex.create(v2), weight, 0, 0, 0);
  }

  public static <V> Edge<V, V> createNetworkEdge(V v1, V v2, int capacity) {
    return new Edge<>(Vertex.create(v1), Vertex.create(v2), 0D, capacity, 0, capacity);
  }

  public int reduceFlow(int delta) {
    if (currentFlow - delta < 0) {
      throw new RuntimeException("Flow can not be (-)ve");
    }
    currentFlow -= delta;
    residualCapacity += delta;
    return residualCapacity;
  }

  public int increaseFlow(int delta) {
    if (currentFlow + delta > capacity) {
      throw new RuntimeException("Flow can not be more than capacity");
    }
    currentFlow += delta;
    residualCapacity -= delta;
    return residualCapacity;
  }

  public int setFlow(int flow) {
    if (flow < 0) {
      throw new RuntimeException("Flow can not be (-)ve");
    }
    if (flow > capacity) {
      throw new RuntimeException("Flow can not be more than capacity");
    }
    currentFlow = flow;
    residualCapacity = capacity - flow;
    return residualCapacity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Edge<?, ?> edge)) return false;
    return Double.compare(getWeight(), edge.getWeight()) == 0
        && Objects.equals(getVertex1(), edge.getVertex1())
        && Objects.equals(getVertex2(), edge.getVertex2());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVertex1(), getVertex2(), getWeight());
  }

  @Override
  public String toString() {
    return "<" + vertex1.getKey() + "--" + vertex2.getKey() + "|" + weight + '>';
  }
}
