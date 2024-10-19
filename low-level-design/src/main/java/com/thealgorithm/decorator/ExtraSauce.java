package com.thealgorithm.decorator;

/**
 * @author: Subham Santra
 */
public class ExtraSauce extends ToppingDecorator {
  public ExtraSauce(BasePizza basePizza) {
    super(basePizza);
  }

  @Override
  public double cost() {
    return basePizza.cost() + 95;
  }
}
