package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public abstract class DistanceMetric<T extends DataPoint> {

  /**
   * Get the normalized distance between two DataPoints. This should always be a double value in the range [0, 1].
   * This function will call the implemented normalizedDistance(p1, p2) function and return the result.
   * Will throw a RuntimeException iff the implemented normalizedDistance function will return a value that is out of bounds.
   * @param p1 DataPoint p1.
   * @param p2 DataPoint p2.
   * @return The normalized distance between p1 and p2.
   */
  public double distance(T p1, T p2){
    double result = this.normalizedDistance(p1, p2);
    if(result < 0 || result > 1){
      throw new RuntimeException("Distance out of bounds. " + this.getClass() + ".normalizedDistance(p1, p2) should be in range [0, 1] but was " + result);
    }
    return result;
  }

  /**
   * Get the normalized distance between two DataPoints. This shoudl always be a double value in the range [0, 1].
   * This function will be used by the distance(p1, p2).
   * @param p1
   * @param p2
   * @return
   */
  protected abstract double normalizedDistance(T p1, T p2);
}
