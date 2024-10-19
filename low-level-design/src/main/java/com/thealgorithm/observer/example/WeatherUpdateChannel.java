package com.thealgorithm.observer.example;

import com.thealgorithm.observer.AbstractObservable;
import com.thealgorithm.observer.AbstractObserver;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class WeatherUpdateChannel extends AbstractObservable {
  public WeatherUpdateChannel(List<AbstractObserver> abstractObservers, WeatherUpdate data) {
    super(abstractObservers, data);
  }
}
