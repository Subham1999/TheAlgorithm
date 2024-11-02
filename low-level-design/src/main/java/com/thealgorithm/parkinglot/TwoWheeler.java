package com.thealgorithm.parkinglot;

/**
 * @author: Subham Santra
 */
public class TwoWheeler extends Vehicle {
  private String vehicleNumber;
  private boolean isEntered;

  @Override
  public void markEntered() {
    this.isEntered = true;
  }

  @Override
  String vehicleNumber() {
    return vehicleNumber;
  }

  @Override
  public boolean entered() {
    return isEntered;
  }
}
