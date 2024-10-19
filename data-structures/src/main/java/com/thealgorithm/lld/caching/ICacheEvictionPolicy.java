package com.thealgorithm.lld.caching;


import java.util.concurrent.CompletableFuture;

/**
 * @author: Subham Santra
 */
public interface ICacheEvictionPolicy {
  CompletableFuture<Void> evict();

  long timeToLive();
}
