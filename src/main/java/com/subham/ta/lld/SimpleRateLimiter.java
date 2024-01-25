package com.subham.ta.lld;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author subhamsantra
 */
public class SimpleRateLimiter {
  private static final long CONFIGURED_TIME = 5_000L;
  private static final long CONFIGURED_MAX_COUNT = 5L;
  private final ConcurrentMap<String, ArrayDeque<Instant>> requestTimestampsByCustomerID;
  private final Thread sweeperThread;

  public SimpleRateLimiter() {
    this.requestTimestampsByCustomerID = new ConcurrentHashMap<>();
    this.sweeperThread =
        new Thread(
            () -> {
              while (true) {
                System.out.print("--- sweaper thread ---\n");
                final Set<Map.Entry<String, ArrayDeque<Instant>>> entries =
                    requestTimestampsByCustomerID.entrySet();
                entries.forEach(
                    entry -> {
                      final String customerID = entry.getKey();
                      final ArrayDeque<Instant> previousAccessTs = entry.getValue();
                      final Instant offset =
                          Instant.now().minus(CONFIGURED_TIME, ChronoUnit.SECONDS);
                      while (!previousAccessTs.isEmpty()
                          && previousAccessTs.peekFirst().isBefore(offset)) {
                        previousAccessTs.removeFirst();
                      }
                      if (previousAccessTs == null || previousAccessTs.isEmpty()) {
                        requestTimestampsByCustomerID.remove(customerID);
                      }
                    });
                // wait for next clean cycle.
                try {
                  Thread.sleep(10_000);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              }
            });
    this.sweeperThread.setDaemon(true);
    this.sweeperThread.start();
  }

  public static void main(String[] args) {
    final SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter();
    final String[] customers = {"subham", "ram", "shyam", "hari", "nakul", "rishav"};
    final Random random = new Random();

    Thread emulator =
        new Thread(
            () -> {
              while (true) {
                String customer = customers[random.nextInt(customers.length)];
                Instant now = Instant.now();
                boolean accepted = simpleRateLimiter.accept(customer, now);
                System.out.printf(
                    "Request from [%s] at [%s] accept[%s]\n",
                    customer, now, accepted ? "yes" : "no");

                // wait for next clean cycle.
                try {
                  Thread.sleep(random.nextLong(2_000));
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              }
            });

    emulator.start();
  }

  public boolean accept(final String customerID, final Instant requestedAt) {
    System.out.printf(
        "~ customer[%s] prev ts[%s]\n", customerID, requestTimestampsByCustomerID.get(customerID));

    final int countOfAcceptedRequests = findTheCountOfAcceptedRequests(customerID, requestedAt);
    if (countOfAcceptedRequests < CONFIGURED_MAX_COUNT) {
      requestTimestampsByCustomerID.get(customerID).addLast(requestedAt);
      return true;
    }
    return false;
  }

  private int findTheCountOfAcceptedRequests(final String customerID, final Instant requestedAt) {
    final ArrayDeque<Instant> previousRequestTimestamps =
        requestTimestampsByCustomerID.get(customerID);
    if (Objects.isNull(previousRequestTimestamps)) {
      requestTimestampsByCustomerID.put(customerID, new ArrayDeque<>());
      return 0;
    }
    final Instant epochTimestamp = requestedAt.minus(CONFIGURED_TIME, ChronoUnit.SECONDS);
    while (!previousRequestTimestamps.isEmpty()
        && previousRequestTimestamps.getFirst().isBefore(epochTimestamp)) {
      previousRequestTimestamps.removeFirst();
    }
    return previousRequestTimestamps.size();
  }
}
