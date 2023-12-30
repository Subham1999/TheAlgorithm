package com.subham.ta.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Vertex<K, V> {
    @Setter
    private K key;
    @Setter
    private V val;
    private Set<Vertex<K, V>> neighbors = new HashSet<>();
    private Set<Edge<K, V>> edges = new HashSet<>();

    public Edge<K, V> addNewEdge(Vertex<K, V> other) {
        Edge<K, V> newEdge = new Edge<>(this, other, 1D);
        edges.add(newEdge);
        neighbors.add(other);
        return newEdge;
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
}
