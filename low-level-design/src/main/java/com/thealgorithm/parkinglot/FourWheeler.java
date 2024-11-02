package com.thealgorithm.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@Setter
@AllArgsConstructor
public class FourWheeler extends Vehicle {
  private boolean isEntered;
  private String vehicleNumber;

  @Override
  String vehicleNumber() {
    return vehicleNumber;
  }

  @Override
  public boolean entered() {
    return isEntered;
  }

  @Override
  public void markEntered() {
    this.isEntered = true;
  }
}
