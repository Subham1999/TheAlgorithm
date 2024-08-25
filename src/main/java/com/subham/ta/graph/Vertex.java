package com.subham.ta.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Vertex<K, V> {
  private K key;
  private V val;
  private Set<Vertex<K, V>> neighbors = new HashSet<>();
  private Set<Edge<K, V>> edges = new HashSet<>();

  public static <K, V> Vertex<K, V> create(K key, V val) {
    return new Vertex<>(key, val, new HashSet<>(), new HashSet<>());
  }

  public static <V> Vertex<V, V> create(V val) {
    return create(val, val);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vertex<?, ?> vertex)) return false;
    return Objects.equals(getKey(), vertex.getKey());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKey());
  }

  @Override
  public String toString() {
    return "V[" + val + ']';
  }
}
