package com.thealgorithm.observer;

import lombok.AllArgsConstructor;

/**
 * @author: Subham Santra
 */
@AllArgsConstructor
public abstract class AbstractObserver {
  protected AbstractObservable observable;

  public abstract void observe();
}
