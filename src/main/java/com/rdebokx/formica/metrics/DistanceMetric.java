package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public interface DistanceMetric {

  public double distance(DataPoint p1, DataPoint p2);
}
