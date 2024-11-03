package com.thealgorithm.splitwise;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * @author: Subham Santra
 */
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Group {
  @ToString.Include String name;
  @ToString.Include List<User> users;
  ResolverStrategy resolverStrategy;
  GroupBalanceSheet groupBalanceSheet;
  GroupExpenseValidator groupExpenseValidator;

  public Group(String name) {
    this.name = name;
    users = new ArrayList<>();
    resolverStrategy = null;
    groupBalanceSheet = new GroupBalanceSheet();
    groupExpenseValidator = new GroupExpenseValidator(this);
    groupBalanceSheet.setGroup(this);
  }

  void changeDebtResolver(DebtResolverType debtResolverType) {
    this.resolverStrategy = ResolverStrategyFactory.resolverStrategy(debtResolverType);
  }

  void add(User user) {
    this.users.add(user);
    this.getGroupBalanceSheet().add(user);
  }

  void add(Expense expense) throws UserIsNotPartOfGroupException, InvalidSplitException {
    groupExpenseValidator.validate(expense);

    Double totalAmount = expense.getTotalAmount();
    for (UserSplit eachUserSplit : expense.getUserSplits()) {
      User user = eachUserSplit.getUser();
      Double shareAmount = eachUserSplit.getShareAmount();
      if (user.equals(expense.getPaidBy())) {
        double totalGetBack =
            user.getPersonalBalanceSheet().getTotalGetBack() + (totalAmount - shareAmount);
        user.getPersonalBalanceSheet().setTotalGetBack(totalGetBack);
      } else {
        PersonalBalanceSheet personalBalanceSheet = user.getPersonalBalanceSheet();
        Double totalOweAmount = personalBalanceSheet.getTotalOwe() + eachUserSplit.getShareAmount();
        personalBalanceSheet.setTotalOwe(totalOweAmount);
        groupBalanceSheet.update(expense.getPaidBy(), user, eachUserSplit.getShareAmount());
      }
    }
  }

  void resolve() throws OperationNotSupportedException {
    resolverStrategy.resolveBalanceSheet(getGroupBalanceSheet());
  }
}
