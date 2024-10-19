package com.thealgorithm.observer.example;

import com.thealgorithm.observer.AbstractObserver;

/**
 * @author: Subham Santra
 */
public class FacebookWatcher extends AbstractObserver {
  public FacebookWatcher(WeatherUpdateChannel observable) {
    super(observable);
  }

  @Override
  public void observe() {
    System.out.println("FROM FACEBOOK " + this.observable.getData());
  }
}
