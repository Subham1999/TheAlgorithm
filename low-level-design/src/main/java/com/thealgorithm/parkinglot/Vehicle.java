package com.thealgorithm.parkinglot;

import lombok.Getter;

/**
 * @author: Subham Santra
 */
@Getter
public abstract class Vehicle {
  public abstract void markEntered();

  abstract String vehicleNumber();

  public abstract boolean entered();
}
