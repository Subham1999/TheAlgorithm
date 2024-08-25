package com.thealgorithm.commons;

import java.util.*;

class FoodRatings {
  final Map<String, TreeSet<FoodNode>> map = new HashMap<>();
  final Map<String, Set<String>> foodToCuisine = new HashMap<>();
  final Map<String, Integer> lastRatingOFood = new HashMap<>();

  public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
    for (String cuisine : cuisines) {
      map.put(
          cuisine,
          new TreeSet<>(
              (fd1, fd2) -> {
                if (fd1.getRating() == fd2.getRating()) {
                  return fd1.getFood().compareTo(fd2.getFood());
                } else {
                  return fd2.getRating() - fd1.getRating();
                }
              }));
    }

    for (int i = 0; i < foods.length; ++i) {
      String cuisine = cuisines[i];
      String food = foods[i];
      int rating = ratings[i];
      FoodNode foodNode = new FoodNode(food, rating);

      map.get(cuisine).add(foodNode);

      if (!foodToCuisine.containsKey(food)) {
        foodToCuisine.put(food, new HashSet<>());
      }
      foodToCuisine.get(food).add(cuisine);
      lastRatingOFood.put(food, rating);
    }
  }

  public void changeRating(String food, int newRating) {
    Set<String> cuisines = foodToCuisine.get(food);

    for (String cuisine : cuisines) {
      FoodNode oldNode = new FoodNode(food, lastRatingOFood.get(food));
      FoodNode newNode = new FoodNode(food, newRating);
      boolean b = map.get(cuisine).remove(oldNode);
      //            System.out.println(b);
      map.get(cuisine).add(newNode);
      lastRatingOFood.put(food, newRating);
    }
  }

  public String highestRated(String cuisine) {
    // System.out.println(map);
    return map.get(cuisine).first().getFood();
  }

  private static class FoodNode {
    private String food;
    private int rating;

    public FoodNode(String food, int rating) {
      this.food = food;
      this.rating = rating;
    }

    public String getFood() {
      return food;
    }

    public void setFood(String food) {
      this.food = food;
    }

    public int getRating() {
      return rating;
    }

    public void setRating(int rating) {
      this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof FoodNode foodNode)) return false;
      return Objects.equals(getFood(), foodNode.getFood());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getFood());
    }

    public String toString() {
      return String.format("food[%s] rating[%d]", food, rating);
    }
  }
}

/**
 * Your FoodRatings object will be instantiated and called as such: FoodRatings obj = new
 * FoodRatings(foods, cuisines, ratings); obj.changeRating(food,newRating); String param_2 =
 * obj.highestRated(cuisine);
 */
public class DesignAFoodRatingSystems {

  public static void main(String[] args) {

    String[] foods = {"emgqdbo", "jmvfxjohq", "qnvseohnoe", "yhptazyko", "ocqmvmwjq"};
    String[] cuisines = {"snaxol", "snaxol", "snaxol", "fajbervsj", "fajbervsj"};
    int[] ratings = {2, 6, 18, 6, 5};
    FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);

    // ["FoodRatings","changeRating","highestRated","changeRating","changeRating","changeRating","highestRated","highestRated"]
    // [,["qnvseohnoe",11],["fajbervsj"],["emgqdbo",3],["jmvfxjohq",9],["emgqdbo",14],["fajbervsj"],["snaxol"]]
    foodRatings.changeRating("qnvseohnoe", 11);
    System.out.println(foodRatings.highestRated("fajbervsj"));
    foodRatings.changeRating("emgqdbo", 3);
    foodRatings.changeRating("jmvfxjohq", 9);
    foodRatings.changeRating("emgqdbo", 14);
    System.out.println(foodRatings.highestRated("fajbervsj"));
    System.out.println(foodRatings.highestRated("snaxol"));
  }
}
