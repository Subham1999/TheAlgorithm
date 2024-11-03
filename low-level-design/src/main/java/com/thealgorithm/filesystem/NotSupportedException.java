package com.thealgorithm.filesystem;

/**
 * @author: Subham Santra
 */
public class NotSupportedException extends Throwable {
  public NotSupportedException(String operationIsNotSupported) {
    super(operationIsNotSupported);
  }
}
