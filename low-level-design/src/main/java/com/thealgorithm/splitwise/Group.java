package com.thealgorithm.splitwise;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author: Subham Santra
 */
@Data
public class Group {
  String name;
  List<User> users;
  ResolverStrategy resolverStrategy;
  GroupBalanceSheet groupBalanceSheet;
  GroupExpenseValidator groupExpenseValidator;

  public Group(String name) {
    this.name = name;
    users = new ArrayList<>();
    resolverStrategy = null;
    groupBalanceSheet = new GroupBalanceSheet();
    groupExpenseValidator = new GroupExpenseValidator(this);
  }

  void changeDebtResolver(DebtResolverType debtResolverType) {
    this.resolverStrategy = ResolverStrategyFactory.resolverStrategy(debtResolverType);
  }

  void add(User user) {
    this.users.add(user);
  }

  void add(Expense expense) throws UserIsNotPartOfGroupException, InvalidSplitException {
    groupExpenseValidator.validate(expense);

    // add expense now
  }

  void resolve() throws OperationNotSupportedException {
    resolverStrategy.resolveBalanceSheet(getGroupBalanceSheet());
  }
}
