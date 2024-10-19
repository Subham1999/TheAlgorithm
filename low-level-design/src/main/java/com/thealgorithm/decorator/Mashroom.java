package com.thealgorithm.decorator;

/**
 * @author: Subham Santra
 */
public class Mashroom extends ToppingDecorator {
  public Mashroom(BasePizza basePizza) {
    super(basePizza);
  }

  @Override
  public double cost() {
    return basePizza.cost() + 65;
  }
}
