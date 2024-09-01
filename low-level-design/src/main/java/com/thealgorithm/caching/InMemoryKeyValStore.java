package com.thealgorithm.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Value;

/**
 * @author: Subham Santra
 * @apiNote
 *     <li>$ put key val
 *     <li>$ put key val ttl_in_seconds
 *     <li>$ get key
 *     <li>$ rem key
 */
public class InMemoryKeyValStore {
  @Value
  private static class Entry {
    byte[] val;
    long created;
    long ttl_millis;
  }

  private final Map<String, Entry> kvStore;

  private InMemoryKeyValStore() {
    kvStore = new ConcurrentHashMap<>();
  }

  public static InMemoryKeyValStore create() {
    return new InMemoryKeyValStore();
  }

  void put(String key, byte[] val, long ttl) {
    kvStore.put(key, new Entry(val, System.currentTimeMillis(), ttl));
  }

  byte[] get(String key) throws DataNotPresentException, DataExpiredException {
    if (!kvStore.containsKey(key)) {
      throw new DataNotPresentException("'key' : " + key + " do not present");
    }

    Entry e = kvStore.get(key);
    if (System.currentTimeMillis() - e.created > e.ttl_millis) {
      kvStore.remove(key);
      throw new DataExpiredException("'key' : " + key + " expired");
    }

    return e.val;
  }

  boolean rem(String key) throws DataNotPresentException, DataExpiredException {
    if (!kvStore.containsKey(key)) {
      throw new DataNotPresentException("'key' : " + key + " do not present");
    }

    Entry e = kvStore.get(key);
    kvStore.remove(key);
    return (System.currentTimeMillis() - e.created <= e.ttl_millis);
  }
}
