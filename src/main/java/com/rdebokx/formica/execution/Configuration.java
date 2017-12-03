package com.rdebokx.formica.execution;

import com.rdebokx.formica.metrics.EuclideanMetric;

public class Configuration {

  /**
   * The Distance Metric to be used during the execution.
   */
  public final static String METRIC = EuclideanMetric.METRIC_NAME;

  /**
   * The number of ants in the colony.
   */
  public final static int NR_OF_ANTS = 5;

  /**
   * The probability of an ant picking up a DataPoint if it's the only DataPoint in the bucket.
   */
  public final static double BASIC_PICKUP_PROB = 0.5;

  /**
   * The probability of an ant dropping a DataPoint in an empty bucket.
   */
  public final static double BASIC_DROP_PROB = 0.5;

}
