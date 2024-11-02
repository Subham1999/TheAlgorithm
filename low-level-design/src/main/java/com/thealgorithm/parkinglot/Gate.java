package com.thealgorithm.parkinglot;

import com.thealgorithm.parkinglot.model.Attendant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.BlockingDeque;

/**
 * @author: Subham Santra
 */
@Setter
@AllArgsConstructor
@Getter
public abstract class Gate {
  private int gateNo;
  private Attendant attendant;
  private BlockingDeque<Vehicle> vehicles;
}
