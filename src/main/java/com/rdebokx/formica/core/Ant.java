package com.rdebokx.formica.core;

import com.rdebokx.formica.execution.Configuration;

import java.util.List;

public class Ant {


  private Colony colony;

  private DataPoint payload;


  public Ant(Colony colony){
    this.colony = colony;
  }

  public void move(List<DataPoint> nextBucket) {
    if(payload == null){
      pickUp(nextBucket);
    } else {
      drop(nextBucket);
    }
  }

  /**
   * TODO javadoc. Probabilistic function for picking up.
   * @param nextBucket
   */
  public void pickUp(List<DataPoint> nextBucket){
    if(!nextBucket.isEmpty()) {
      int candidateIndex = colony.randomizer.nextInt(nextBucket.size());
      double distanceToBucket = distanceToBucket(nextBucket.get(candidateIndex), nextBucket, Configuration.BASIC_PICKUP_PROB);

      if (performAction(distanceToBucket)) {
        payload = nextBucket.remove(candidateIndex);
      }
    }
  }

  public void drop(List<DataPoint> nextBucket){
    if(performAction(1 - distanceToBucket(payload, nextBucket, 1 - Configuration.BASIC_DROP_PROB))){
      nextBucket.add(payload);
      payload = null;
    }
  }

  public double distanceToBucket(DataPoint candidate, List<DataPoint> bucket, double defaultDistance){
    double totalDistance = 0;
    int distancesCount = 0;
    for(DataPoint dataPoint : bucket){
      if(dataPoint != candidate){
        totalDistance += colony.distanceCalculator.distance(candidate, dataPoint);
        distancesCount++;
      }
    }
    return distancesCount > 0 ? totalDistance / distancesCount : defaultDistance;
  }

  /**
   * TODO javadoc.
   * Probabilistic function.
   * @param probability double value between 0 and 1. The higher the number, the higher the chance of true being returned.
   * @return
   */
  private boolean performAction(double probability) {
    return colony.randomizer.nextDouble() < probability;
  }
}
