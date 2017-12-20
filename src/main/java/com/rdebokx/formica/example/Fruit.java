package com.rdebokx.formica.example;

import com.rdebokx.formica.core.DataPoint;

/**
 * Fruit DataPoint, representing the Weight, Volume, Sweetness and GrowTime of a fruit.
 */
public class Fruit extends DataPoint<Double> {

  /**
   * Index of Weight property.
   */
  private static final int WEIGHT = 0;

  /**
   * Index of Volume property.
   */
  private static final int VOLUME = 1;

  /**
   * Index of Sweetness property.
   */
  private static final int SWEETNESS = 2;

  /**
   * Index of GrowTime property.
   */
  private static final int GROW_TIME = 3;

  /**
   * String representation of this fruit. For testing purposes only, will not be considered during sorting.
   */
  private String name;

  /**
   * Constructor, constructing a fruit with the given properties.
   * @param name The name of the fruit. Used for testing purposes only, will not be considered during sorting.
   * @param weight The weight of the fruit.
   * @param volume The volume of the fruit.
   * @param sweetness The sweetness of the fruit.
   * @param growTime The grow time of the fruit.
   */
  public Fruit(String name, double weight, double volume, double sweetness, double growTime){
    super(weight, volume, sweetness, growTime);
    this.name = name;
  }

  /**
   * @return The weight of this fruit.
   */
  public double getWeight() {
    return values[WEIGHT];
  }

  /**
   * @return The volume of this fruit.
   */
  public double getVolume() {
    return values[VOLUME];
  }

  /**
   * @return The sweetness of this fruit.
   */
  public double getSweetness() {
    return values[SWEETNESS];
  }

  /**
   * @return The grow time of this fruit.
   */
  public double getGrowTime() {
    return values[GROW_TIME];
  }

  @Override
  public String toString(){
    return this.name;
  }

}
