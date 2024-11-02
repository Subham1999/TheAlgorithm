package com.thealgorithm.splitwise;

/**
 * @author: Subham Santra
 */
public class SimpleDebtResolverStrategy implements ResolverStrategy {
  @Override
  public void resolveBalanceSheet(BalanceSheet balanceSheet) throws OperationNotSupportedException {
    // Do something
    if (!(balanceSheet instanceof GroupBalanceSheet)) {
      throw new OperationNotSupportedException(
          "The given balance sheet is not a Group balance sheet");
    }


  }
}
