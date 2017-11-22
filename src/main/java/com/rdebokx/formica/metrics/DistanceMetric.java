package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public interface DistanceMetric<T extends DataPoint> {

  public double distance(T p1, T p2);
}
