package com.thealgorithm.splitwise;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Subham Santra
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupBalanceSheet extends BalanceSheet {
  private Group group;
  private Map<User, Map<User, BalanceSheetEntry>> balanceSheetEntryMap = new HashMap<>();

  public void add(User user) {
    balanceSheetEntryMap.put(user, new HashMap<>());
  }

  @Override
  void update(User paidBy, User borrower, Double amount) {
    update0(paidBy, borrower, amount);
    update0(borrower, paidBy, -amount);
  }

  private void update0(User paidBy, User borrower, Double amount) {
    Map<User, BalanceSheetEntry> paidUserBalanceSheet = balanceSheetEntryMap.get(paidBy);
    paidUserBalanceSheet.putIfAbsent(borrower, new BalanceSheetEntry(paidBy, borrower, 0D));
    paidUserBalanceSheet
        .get(borrower)
        .setAmount(paidUserBalanceSheet.get(borrower).getAmount() + amount);
  }

  public void print() {
    System.out.println("SHOWING BALANCESHEET FOR " + group.name);
    balanceSheetEntryMap.forEach(
        (user, map) -> {
          System.out.printf("BALANCE FOR %s\n", user);

          map.forEach(
              (u, e) -> {
                System.out.printf("%s %s\n", u, e.getAmount());
              });
        });
  }
}
