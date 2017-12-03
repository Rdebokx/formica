package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceMetric;

public class DistanceCalculator {

  /**
   * The DisanceMetric configured for this calculator.
   */
  private DistanceMetric metric;

  /**
   * Constructor, constructing a new DistanceCalculator using the DistanceMetric as defined by Configuration.METRIC.
   * This constructor will also initialize the DistanceMetric if need be, eg in the situation of a normalized metric.
   * @param dataPoints The initial collection of DataPoints to initialize the DistanceMetric with, if needed.
   */
  public DistanceCalculator(DataPoint ... dataPoints) {
    switch (Configuration.METRIC) {
      case EuclideanMetric.METRIC_NAME:
        metric = new EuclideanMetric(dataPoints);
        break;
    }
  }

  /**
   * Get the normalized distance between two DataPoints using the DistanceMetric of this calculator. This should always be a double value in the range [0, 1].
   * @param p1 DataPoint p1.
   * @param p2 DataPoint p2.
   * @return The normalized distance between p1 and p2.
   */
  public double distance(DataPoint p1, DataPoint p2) {
    double result = metric.distance(p1, p2);
    if(result < 0 || result > 1){
      throw new RuntimeException("Distance out of bounds. " + Configuration.METRIC + ".distance(p1, p2) should be in range [0, 1] but was " + result);
    }
    return result;
  }
}
