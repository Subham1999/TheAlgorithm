package com.thealgorithm.lld.caching;

/**
 * @author: Subham Santra
 */
public abstract class AbstractCache<K, V> implements ICache<K, V> {
  @Override
  public long timeToLive() {
    return 10 * 1000L;
  }
}
