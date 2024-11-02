package com.thealgorithm.splitwise;

/**
 * @author: Subham Santra
 */
public abstract class BalanceSheet {

  abstract void update(User paidBy, User borrower, Double amount);
}
