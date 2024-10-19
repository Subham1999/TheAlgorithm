package com.thealgorithm.lld.caching;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author: Subham Santra
 */
public class LeastRecentlyUsedCache<K, V> extends AbstractCache<K, V> {
  private LinkedHashMap<K, CacheEntry<K, V>> linkedHashMap = new LinkedHashMap<>();
  private Thread sweeperThread;

  public LeastRecentlyUsedCache() {
    sweeperThread = new Thread(() -> {});
  }

  @Override
  public V fetch(K key) {
    return null;
  }

  @Override
  public CompletableFuture<Void> put(K key, V val) {
    return CompletableFuture.runAsync(
        () -> {
          linkedHashMap.remove(key);
          linkedHashMap.putLast(key, new CacheEntry<>(key, val, Timestamp.from(Instant.now())));
        });
  }

  @Override
  public CompletableFuture<Void> evict() {
    return CompletableFuture.runAsync(() -> {});
  }
}
