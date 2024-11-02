package com.thealgorithm.splitwise;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: Subham Santra
 */
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
  @EqualsAndHashCode.Include
  @ToString.Include
  private String name;
  @ToString.Include
  private List<Group> groups;
  private PersonalBalanceSheet personalBalanceSheet;

  public User(String name) {
    this.name = name;
    this.groups = new ArrayList<>();
    this.personalBalanceSheet = new PersonalBalanceSheet();
  }

  public void add(Expense expense) {
    // add new expense
  }
}
