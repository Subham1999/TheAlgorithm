package com.thealgorithm.splitwise;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * @author: Subham Santra
 */
@Value
@Builder
public class Expense {
  Double totalAmount;
  User paidBy;
  List<UserSplit> userSplits;
}
