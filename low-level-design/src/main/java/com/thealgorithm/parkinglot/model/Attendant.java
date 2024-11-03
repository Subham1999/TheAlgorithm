package com.thealgorithm.parkinglot.model;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.thealgorithm.parkinglot.PaymentRequest;
import com.thealgorithm.parkinglot.PaymentService;
import com.thealgorithm.parkinglot.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@AllArgsConstructor
@Getter
@Setter
public class Attendant {
  private String name;
  private EntryGate entryGate;
  private ExitGate exitGate;
  private PaymentService paymentService;

  public CompletableFuture<Vehicle> askEntry(Vehicle vehicle) {
    return CompletableFuture.supplyAsync(
        () -> {
          while (true) {
            if (entryGate.getVehicles().peekFirst() != vehicle) {
              continue;
            }
            Vehicle polledFirst = entryGate.getVehicles().pollFirst();
            polledFirst.markEntered();
          }
        });
  }

  public CompletableFuture<Vehicle> askExit(Vehicle vehicle) {
    return CompletableFuture.supplyAsync(
        () -> {
          while (true) {
            if (entryGate.getVehicles().peekFirst() != vehicle) {
              continue;
            }
            Vehicle polledFirst = entryGate.getVehicles().pollFirst();
            if (polledFirst.entered()) {
              paymentService.doPayment(new PaymentRequest(UUID.randomUUID().toString(), 10D));
            } else {
              throw new RuntimeException("Unknown Vehicle");
            }
          }
        });
  }
}
