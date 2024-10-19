package com.thealgorithm.lld.caching;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author: Subham Santra
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CacheEntry<K, V> {
  @EqualsAndHashCode.Include
  private K key;
  private V val;
  private Timestamp timestamp;
}
