package com.thealgorithm.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@Getter
@Setter
@AllArgsConstructor
public class ParkingSpot {
  private int spotNumber;
  private boolean isAvailable;
  private Vehicle vehicle;

  public void park(Vehicle vehicle) {
    this.vehicle = vehicle;
    this.isAvailable = false;
  }
}
