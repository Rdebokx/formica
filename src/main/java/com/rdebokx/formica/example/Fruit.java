package com.rdebokx.formica.example;

import com.rdebokx.formica.core.DataPoint;

public class Fruit extends DataPoint {

  //Indices
  private static final int WEIGHT = 0;
  private static final int VOLUME = 1;
  private static final int SWEETNESS = 2;
  private static final int GROW_TIME = 3;

  public Fruit(){
    super(WEIGHT, VOLUME, SWEETNESS, GROW_TIME);
  }

  public void setWeight(double weight) {
    values[WEIGHT] = weight;
  }

  public double getWeight() {
    return values[WEIGHT];
  }

  public void setVolume(double volume){
    values[VOLUME] = volume;
  }

  public double getVolume() {
    return values[VOLUME];
  }

  public void setSweetness(double sweetness) {
    values[SWEETNESS] = sweetness;
  }

  public double getSweetness() {
    return values[SWEETNESS];
  }

  public void setGrowTime(double growTime) {
    values[GROW_TIME] = growTime;
  }

  public double getGrowTime() {
    return values[GROW_TIME];
  }
}
