package com.thealgorithm.lld.caching;

import java.util.concurrent.CompletableFuture;

public interface ICache<K, V> extends ICacheEvictionPolicy {
  V fetch(K key);

  CompletableFuture<Void> put(K key, V val);
}
