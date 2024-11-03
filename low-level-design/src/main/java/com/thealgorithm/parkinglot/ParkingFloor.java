package com.thealgorithm.parkinglot;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Subham Santra
 */
@Getter
@AllArgsConstructor
public class ParkingFloor {
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private Lock readLock = readWriteLock.readLock();
  private Lock writeLock = readWriteLock.writeLock();

  private List<ParkingSpot> parkingSpots;

  public void tryPark(Vehicle vehicle) {}

  public CompletableFuture<ParkingSpot> getAvailable() {
    return CompletableFuture.supplyAsync(
        () -> {
          readLock.lock();
          ParkingSpot availableParkingSpot = null;

          for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isAvailable()) {
              availableParkingSpot = parkingSpot;
              break;
            }
          }

          readLock.unlock();
          return availableParkingSpot;
        });
  }
}
