package com.thealgorithm.splitwise;

import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Subham Santra
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupBalanceSheet extends BalanceSheet {
  Map<User, Map<User, BalanceSheetEntry>> balanceSheetEntryMap;
}
