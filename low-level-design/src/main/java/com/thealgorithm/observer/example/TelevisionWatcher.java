package com.thealgorithm.observer.example;

import com.thealgorithm.observer.AbstractObserver;

/**
 * @author: Subham Santra
 */
public class TelevisionWatcher extends AbstractObserver {
  public TelevisionWatcher(WeatherUpdateChannel observable) {
    super(observable);
  }

  @Override
  public void observe() {
    System.out.println("FROM T.V. " + this.observable.getData());
  }
}
