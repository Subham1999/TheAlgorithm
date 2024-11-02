package com.thealgorithm.splitwise;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
@AllArgsConstructor
public class BalanceSheetEntry {
  User owner;
  User other;
  Double amount;
}
