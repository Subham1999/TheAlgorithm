package com.thealgorithm.splitwise;

/**
 * @author: Subham Santra
 */
@FunctionalInterface
public interface ResolverStrategy {
  void resolveBalanceSheet(BalanceSheet balanceSheet) throws OperationNotSupportedException;
}
