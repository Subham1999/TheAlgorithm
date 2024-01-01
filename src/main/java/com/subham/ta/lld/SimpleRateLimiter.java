package com.subham.ta.lld;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author subhamsantra
 */
public class SimpleRateLimiter {
  private static final long CONFIGURED_TIME = 10_000L;
  private static final long CONFIGURED_MAX_COUNT = 3L;
  private final ConcurrentMap<String, ArrayDeque<Instant>> requestTimestampsByCustomerID;

  public SimpleRateLimiter() {
    this.requestTimestampsByCustomerID = new ConcurrentHashMap<>();
  }

  public boolean accept(final String customerID, final Instant requestedAt) {
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
