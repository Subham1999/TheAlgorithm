package com.subham.ta.graph;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Edge<K, V> {
  private Vertex<K, V> start;
  private Vertex<K, V> end;
  private double weight = 1D;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Edge<?, ?> edge)) return false;
    return Double.compare(getWeight(), edge.getWeight()) == 0
        && Objects.equals(getStart(), edge.getStart())
        && Objects.equals(getEnd(), edge.getEnd());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStart(), getEnd(), getWeight());
  }
}
