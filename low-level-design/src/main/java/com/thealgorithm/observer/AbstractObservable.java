package com.thealgorithm.observer;

import java.util.List;
import lombok.Getter;

/**
 * @author: Subham Santra
 */
public abstract class AbstractObservable {
  private List<AbstractObserver> observers;
  @Getter private Object data;

  public AbstractObservable(List<AbstractObserver> observers, Object data) {
    this.observers = observers;
    this.data = data;
  }

  public void change(Object data) {
    this.data = data;
    notifyObservers();
  }

  private void notifyObservers() {
    observers.forEach(AbstractObserver::observe);
  }

  public void addObserver(AbstractObserver observer) {
    this.observers.add(observer);
  }

  public void removeObserver(AbstractObserver observer) {
    this.observers.remove(observer);
  }
}
