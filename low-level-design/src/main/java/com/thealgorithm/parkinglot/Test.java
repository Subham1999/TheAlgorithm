package com.thealgorithm.parkinglot;

import com.thealgorithm.parkinglot.model.EntryGate;
import com.thealgorithm.parkinglot.model.ExitGate;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: Subham Santra
 */
public class Test {
  public static void main(String[] args) {
    List<ExitGate> exitGates =
        List.of(
            new ExitGate(100, null, new LinkedBlockingDeque<>(2)),
            new ExitGate(101, null, new LinkedBlockingDeque<>(4)),
            new ExitGate(102, null, new LinkedBlockingDeque<>(5)));

    List<EntryGate> entryGates =
        List.of(
            new EntryGate(300, null, new LinkedBlockingDeque<>(4)),
            new EntryGate(301, null, new LinkedBlockingDeque<>(4)),
            new EntryGate(302, null, new LinkedBlockingDeque<>(4)));



//    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
  }
}
