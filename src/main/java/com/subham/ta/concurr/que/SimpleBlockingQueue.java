package com.subham.ta.concurr.que;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleBlockingQueue<T> implements IQueue<T> {
  private final Queue<T> queue;

  public SimpleBlockingQueue() {
    this.queue = new ArrayDeque<>();
  }

  @Override
  public void add(T val) {
    synchronized (queue) {
      queue.add(val);
      debug();
    }
  }

  @Override
  public T poll() {
    T polledVal;
    synchronized (queue) {
      polledVal = queue.poll();
      debug();
    }
    return polledVal;
  }

  private void debug() {
    System.err.printf("THREAD[%s] queue[%s]\n", Thread.currentThread().getName(), queue);
  }

  @Override
  public int size() {
    int size = 0;
    synchronized (queue) {
      size = queue.size();
    }
    return size;
  }
}
