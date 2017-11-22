package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.metrics.DistanceMetric;

public class DistanceCalculator {

  private static DistanceMetric metric;

  public static double distance(DataPoint p1, DataPoint p2) {
    if(metric == null){
      //TODO: use metric based off of config.
      metric = new EuclideanMetric();
    }
    return metric.distance(p1, p2);
  }
}
