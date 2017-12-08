package com.rdebokx.formica.execution;

public class Configuration {

  /**
   * The Distance Metric to be used during the execution.
   */
  public final String metric;

  /**
   * The number of ants in the colony.
   */
  public final int nrOfAnts;

  /**
   * The probability of an ant picking up a DataPoint if it's the only DataPoint in the bucket.
   */
  public final double basicPickupProb;

  /**
   * The probability of an ant dropping a DataPoint in an empty bucket.
   */
  public final double basicDropProb;

  public Configuration(String metric, int nrOfAnts, double basicPickupProb, double basicDropProb){
    this.metric = metric;
    this.nrOfAnts = nrOfAnts;
    this.basicPickupProb = basicPickupProb;
    this.basicDropProb = basicDropProb;
  }

  public String getMetric() {
    return metric;
  }

  public int getNrOfAnts() {
    return nrOfAnts;
  }

  public double getBasicPickupProb() {
    return basicPickupProb;
  }

  public double getBasicDropProb() {
    return basicDropProb;
  }
}
