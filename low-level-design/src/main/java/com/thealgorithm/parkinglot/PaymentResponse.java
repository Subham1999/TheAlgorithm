package com.thealgorithm.parkinglot;

import lombok.AllArgsConstructor;

/**
 * @author: Subham Santra
 */
@AllArgsConstructor
public class PaymentResponse {
  String paymentId;
  Double amount;
  PaymentStatus paymentStatus;
}
