package com.thealgorithm.decorator;

/**
 * @author: Subham Santra
 */
public class Test {
  public static void main(String[] args) {
    BasePizza pizza = new Margherita();

    // Adding toppings
    BasePizza finalPizza = new ExtraSauce(new ExtraCheese(pizza));

    System.out.println(finalPizza.cost());
  }
}
