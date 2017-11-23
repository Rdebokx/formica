package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.DistanceMetric;

public class DistanceCalculator {

  private DistanceMetric metric;

  public DistanceCalculator(DataPoint ... dataPoints) {
    switch (Configuration.METRIC) {
      case EuclideanMetric.METRIC_NAME:
        metric = new EuclideanMetric(dataPoints);
        break;
    }
  }

  public double distance(DataPoint p1, DataPoint p2) {
    return metric.distance(p1, p2);
  }
}
