package com.thealgorithm.decorator;

/**
 * @author: Subham Santra
 */
public abstract class ToppingDecorator extends BasePizza {
  protected BasePizza basePizza;

  public ToppingDecorator(BasePizza basePizza) {
    this.basePizza = basePizza;
  }
}
