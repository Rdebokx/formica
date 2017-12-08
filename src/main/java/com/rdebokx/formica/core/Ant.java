package com.rdebokx.formica.core;

import java.util.List;

/**
 * Ant class, representing an ant that can pick up and drop DataPoints when moving from bucket to bucket, based on similarity of (or to) the DataPoints in the bucket.
 */
public class Ant {

  /**
   * The Colony that this ant is currently part of.
   * Ant will use the randomizer and distanceCalculator of this colony.
   */
  private final Colony colony;

  /**
   * The DataPoint that the ant is currently carrying.
   */
  private DataPoint payload;

  /**
   * Constructor, constructing an Ant for the provided Colony.
   * @param colony The Colony that this Ant belongs to.
   */
  public Ant(Colony colony){
    this.colony = colony;
  }

  /**
   * Move this Ant to the given bucket. Upon moving this bucket, the probabilistic function drop or pickUp will be called, based on whether this ant is carrying a payload or not respectively.
   * Note that after this function finished, the ant will have no notion of which bucket it is currently at.
   * @param nextBucket The bucket that this ant is moving to.
   */
  public void move(List<DataPoint> nextBucket) {
    if(payload == null){
      pickUp(nextBucket);
    } else {
      drop(nextBucket);
    }
  }

  /**
   * Probabilistic function for picking up a new DataPoint from the given bucket. This function will randomly select a DataPoint from the given bucket.
   * With a chance P, which is based on the similarity of this DataPoint to the rest of the bucket, this DataPoint will then be removed from the bucket and carried by this ant as payload.
   * The less similar the selected DataPoint is to the other DataPoints in the bucket, the higher the chance that this DataPoint will be picked up by this ant.
   * Will use the randomizer of the Colony that this ant belongs to, to evaluate whether or not to pickup the selected DataPoint.
   * With a probability P as defined by Configuration.basicPickupProb there is a chance that the ant will pick up the randomly selected DataPoint, regardless of the contents of the nextBucket.
   * @param nextBucket The bucket that this ant has moved to and a DataPoint may or may not be picked up from.
   */
  protected void pickUp(List<DataPoint> nextBucket){
    if(payload != null){
      throw new RuntimeException("Ant is already carrying payload.");
    }
    if(!nextBucket.isEmpty()) {
      int candidateIndex = colony.randomizer.nextInt(nextBucket.size());
      double distanceToBucket = nextBucket.get(candidateIndex).avgDistanceToBucket(nextBucket, colony.distanceCalculator);

      if (performAction(distanceToBucket) || performAction(colony.config.getBasicPickupProb())) {
        payload = nextBucket.remove(candidateIndex);
      }
    }
  }

  /**
   * Probabilistic function for dropping the current payload that this ant is carrying in the given bucket.
   * With a chance P, which is based on the similarity of the current payload to the DataPoints in the given bucket, the current payload will be dropped by the ant and added to the given bucket.
   * The more similar the current payload is to the other DataPoints in the bucket, de higher the chance that the current payload will be added to the bucket.
   * Will use the randomizer of the Colony that this ant belongs to, to evaluate whether or not to drop the current payload.
   * With a probability P as defined by Configuration.basicDropProb, the ant will drop its current payload, regardless of the contents the nextBucket.
   * @param nextBucket The bucket that his ant may or may not its current payload in.
   */
  protected void drop(List<DataPoint> nextBucket){
    if(payload == null){
      throw new RuntimeException("Ant has no payload to drop.");
    }
    if(!nextBucket.isEmpty() && performAction(1 - payload.avgDistanceToBucket(nextBucket, colony.distanceCalculator)) || performAction(colony.config.getBasicDropProb())){
      nextBucket.add(payload);
      payload = null;
    }
  }

  /**
   * Probabilistic helper function to determine whether or not to perform an action, based on the probability P.
   * @param p Probability P that true will be returned.
   *          Double value between 0 and 1, where 0 gives a 0% chance that this function will return true and 1 will give a 100% chance that this function will return true.
   * @return true with probability P.
   */
  private boolean performAction(double p) { return colony.randomizer.nextDouble() < p; }

  /**
   * @return The payload that this ant is currently carrying.
   */
  public DataPoint getPayload() {
    return payload;
  }
}
