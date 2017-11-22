package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceMetric;

public class DistanceCalculator {

  private static DistanceMetric metric;

  public static double distance(DataPoint p1, DataPoint p2) {
    if(metric == null){
      switch (Configuration.METRIC) {
        case EuclideanMetric.METRIC_NAME:
          metric = new EuclideanMetric();
          break;
      }
    }
    return metric.distance(p1, p2);
  }
}
