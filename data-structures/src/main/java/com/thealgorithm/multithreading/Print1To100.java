package com.thealgorithm.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Print1To100 {
  static class Counter {
    transient int val;

    synchronized void incrementOnce() {
      val++;
    }

    void print() {
      System.out.println(Thread.currentThread().getName() + " - " + val);
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Counter counter = new Counter();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    for (int i = 0; i < 100; ++i) {
      executorService.submit(
          () -> {
            readWriteLock.writeLock().lock();
            counter.incrementOnce();
            counter.print();
            readWriteLock.writeLock().unlock();
          });
    }
  }
}
