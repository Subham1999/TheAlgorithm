package com.thealgorithm.parkinglot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author: Subham Santra
 */
public class PaymentService {
  private final Random random = new Random();

  public CompletableFuture<PaymentResponse> doPayment(PaymentRequest paymentRequest) {
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            Thread.sleep(random.nextInt(10) * 1000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          if (Math.random() > 0.85) {
            return new PaymentResponse(
                paymentRequest.paymentId, paymentRequest.amount, PaymentStatus.FAIL);
          } else {
            return new PaymentResponse(
                paymentRequest.paymentId, paymentRequest.amount, PaymentStatus.SUCCESS);
          }
        });
  }
}
