package com.thealgorithm.splitwise;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
public class PersonalBalanceSheet extends BalanceSheet {
  Double totalOwe = 0D;
  Double totalGetBack = 0D;
  Map<User, BalanceSheetEntry> balanceSheetEntryMap = new HashMap<>(); // otherUser --> entry

  @Override
  void update(User paidBy, User borrower, Double amount) {

  }
}
