package com.thealgorithm.decorator;

/**
 * @author: Subham Santra
 */
public class ExtraCheese extends ToppingDecorator {
  public ExtraCheese(BasePizza basePizza) {
    super(basePizza);
  }

  @Override
  public double cost() {
    return this.basePizza.cost() + 35;
  }
}
