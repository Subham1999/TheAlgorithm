package com.thealgorithm.splitwise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Subham Santra
 */
@AllArgsConstructor
@Getter
@Setter
public class GroupExpenseValidator {
  private Group group;

  void validate(Expense expense) throws UserIsNotPartOfGroupException, InvalidSplitException {}
}
