package com.subham.ta.concurr.que;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueTest {
  public static void main(String[] args) {
    SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();
    ExecutorService threadPool = Executors.newFixedThreadPool(10, Thread::new);

    Random random = new Random();

    threadPool.submit(
        () -> {
          while (true) {
            System.out.printf(
                "THREAD[%s] POLLED %d\n",
                Thread.currentThread().getName(), simpleBlockingQueue.poll());
            Thread.sleep(random.nextInt(1000));
          }
        });

    threadPool.submit(
        () -> {
          while (true) {
            int val = random.nextInt();
            System.out.printf("THREAD[%s] ADDED %d\n", Thread.currentThread().getName(), val);
            simpleBlockingQueue.add(val);

            Thread.sleep(random.nextInt(1000));
          }
        });
  }
}
