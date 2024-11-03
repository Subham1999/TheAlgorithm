package com.thealgorithm.parkinglot;

import com.thealgorithm.parkinglot.model.Attendant;
import com.thealgorithm.parkinglot.model.EntryGate;
import com.thealgorithm.parkinglot.model.ExitGate;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Subham Santra
 */
public class ParkingLotSystem {
  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private Lock readLock = readWriteLock.readLock();
  private Lock writeLock = readWriteLock.writeLock();
  private final List<ExitGate> exitGates;
  private final List<EntryGate> entryGates;
  private final List<ParkingFloor> parkingFloors;
  private final BlockingDeque<Attendant> availableAttendants;

  public ParkingLotSystem(
      List<ExitGate> exitGates, List<EntryGate> entryGates, List<ParkingFloor> parkingFloors) {
    this.exitGates = exitGates;
    this.entryGates = entryGates;
    this.parkingFloors = parkingFloors;
    this.availableAttendants = new LinkedBlockingDeque<>();
  }

  public void addAttendant(Attendant attendant) {
    availableAttendants.addLast(attendant);
  }

  public CompletableFuture<Vehicle> tryEntry(EntryGate entryGate, Vehicle vehicle) {
    return entryGate.tryEntry(vehicle);
  }

  public CompletableFuture<Vehicle> tryExit(ExitGate exitGate, Vehicle vehicle) {
    return exitGate.tryExit(vehicle);
  }

  public CompletableFuture<Void> tryPark(Vehicle vehicle) {
    return CompletableFuture.runAsync(
        () -> {
          if (vehicle == null) throw new NullPointerException();
          if (!vehicle.entered()) throw new RuntimeException();

          readLock.lock();
          for (ParkingFloor parkingFloor : this.parkingFloors) {
            CompletableFuture<ParkingSpot> available = parkingFloor.getAvailable();
            available.thenAccept(parkingSpot -> parkingSpot.park(vehicle)).join();
          }
          readLock.unlock();
        });
  }
}
