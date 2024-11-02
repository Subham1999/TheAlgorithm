package com.thealgorithm.parkinglot.model;

import com.thealgorithm.parkinglot.Gate;
import com.thealgorithm.parkinglot.Vehicle;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CompletableFuture;

/**
 * @author: Subham Santra
 */
public class ExitGate extends Gate {
  public ExitGate(int gateNo, Attendant attendant, BlockingDeque<Vehicle> vehicles) {
    super(gateNo, attendant, vehicles);
  }

  public CompletableFuture<Vehicle> tryExit(Vehicle vehicle) {
    this.getVehicles().addLast(vehicle);
    return this.getAttendant().askExit(vehicle);
  }
}
