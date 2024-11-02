package com.thealgorithm.splitwise;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
public class User {
  String name;
  List<Group> groups;
  PersonalBalanceSheet personalBalanceSheet;

  public User(String name) {
    this.name = name;
    this.groups = new ArrayList<>();
    this.personalBalanceSheet = new PersonalBalanceSheet();
  }

  public void add(Expense expense) {
    // add new expense
  }
}
