package com.thealgorithm.lld;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Service {
  public void doSomething(String key) {
    // read the data using key
    Object data = new Object();
  }
}

public class DatabaseLocks {
  private CompletableFuture<String> doSomething() {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            Thread.sleep(1000);
            return "Shubham";
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    DatabaseLocks databaseLocks = new DatabaseLocks();

    databaseLocks
        .doSomething()
        .thenAcceptAsync(
            (aString) -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
              System.out.println(aString);
            });

    Thread.sleep(10000);
  }
}
