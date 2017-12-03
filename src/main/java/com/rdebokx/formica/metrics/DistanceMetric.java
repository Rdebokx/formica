package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public interface DistanceMetric<T extends DataPoint> {

  /**
   * Get the normalized distance between two DataPoints. This should always be a double value in the range [0, 1].
   * @param p1 DataPoint p1.
   * @param p2 DataPoint p2.
   * @return The normalized distance between p1 and p2.
   */
  public double distance(T p1, T p2);
}
