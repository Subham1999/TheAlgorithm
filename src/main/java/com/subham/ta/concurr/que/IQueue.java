package com.subham.ta.concurr.que;

public interface IQueue<T> {
  void add(T val);

  T poll();

  int size();
}
