package com.thealgorithm.splitwise;

import java.util.Map;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
public class PersonalBalanceSheet extends BalanceSheet {
  Double totalOwe;
  Double totalGetBack;
  Map<User, BalanceSheetEntry> balanceSheetEntryMap; // otherUser --> entry
}
