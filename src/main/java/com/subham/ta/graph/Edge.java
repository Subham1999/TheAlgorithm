package com.subham.ta.graph;

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

  public static <V> Edge<V, V> createSimpleEdgeUnWeighted(V v1, V v2) {
    return new Edge<>(Vertex.create(v1), Vertex.create(v2), 0D);
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
}
