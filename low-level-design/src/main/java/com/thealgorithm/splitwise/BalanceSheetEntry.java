package com.thealgorithm.splitwise;

import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
public class BalanceSheetEntry {
  User owner;
  User other;
  Double amount;
}
