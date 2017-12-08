package com.rdebokx.formica.execution;

public class Configuration {

  /**
   * The Distance Metric to be used during the execution.
   */
  private final String metric;

  /**
   * The number of ants in the colony.
   */
  private final int nrOfAnts;

  /**
   * The probability of an ant picking up a DataPoint if it's the only DataPoint in the bucket.
   */
  private final double basicPickupProb;

  /**
   * The probability of an ant dropping a DataPoint in an empty bucket.
   */
  private final double basicDropProb;

  /**
   * Constructor, constructing a new Configuration object with the given parameters.
   * @param metric The name of the DistanceMetric to be used.
   * @param nrOfAnts The number of ants in the Colony.
   * @param basicPickupProb The probability of an ant picking up a DataPoint from a (non-empty) bucket regardless of the contents of the bucket.
   * @param basicDropProb The probability of an ant dropping it's current payload regardless of the contents of the bucket.
   */
  public Configuration(String metric, int nrOfAnts, double basicPickupProb, double basicDropProb){
    this.metric = metric;
    this.nrOfAnts = nrOfAnts;
    this.basicPickupProb = basicPickupProb;
    this.basicDropProb = basicDropProb;
  }

  /**
   * @return The Metric name.
   */
  public String getMetric() {
    return metric;
  }

  /**
   * @return The number of ants in the colony.
   */
  public int getNrOfAnts() {
    return nrOfAnts;
  }

  /**
   * @return The probability of an ant picking up a DataPoint from a (non-empty) bucket regardless of the contents of the bucket.
   */
  public double getBasicPickupProb() {
    return basicPickupProb;
  }

  /**
   * @return The probability of an ant dropping it's current payload regardless of the contents of the bucket.
   */
  public double getBasicDropProb() {
    return basicDropProb;
  }
}
