package com.thealgorithm.caching;

/**
 * @author: Subham Santra
 */
public class DataExpiredException extends Exception {
  public DataExpiredException(String s) {
    super(s);
  }
}
