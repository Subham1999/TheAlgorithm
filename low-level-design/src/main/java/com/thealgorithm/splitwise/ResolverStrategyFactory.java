package com.thealgorithm.splitwise;

/**
 * @author: Subham Santra
 */
public class ResolverStrategyFactory {
  public static ResolverStrategy resolverStrategy(DebtResolverType resolverStyle) {
    switch (resolverStyle) {
      case SMART -> {
        return new SmartDebtResolverStrategy();
      }
      case SIMPLE -> {
        return new SimpleDebtResolverStrategy();
      }
      default -> {
        return new ResolverStrategy() {
          @Override
          public void resolveBalanceSheet(BalanceSheet balanceSheet) {
            // DO NOTHING
          }
        };
      }
    }
  }
}
