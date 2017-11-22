package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public class ManhattanMetric<T extends DataPoint> implements DistanceMetric<T> {

  /**
   * Distance function to calculate the Euclidean distance between two DataPoints p1 and p2.
   * @param p1 DataPoint 1
   * @param p2 DataPoint 2
   * @return The Euclidean distance between p1 and p2.
   */
  public double distance(T p1, T p2){
    double[] p1Values = p1.getValues();
    double[] p2Values = p2.getValues();

    double result = 0;
    for(int i = 0; i < p1Values.length; i++) {
      result += Math.abs(p1Values[i] - p2Values[i]);
    }
    return result;
  }
}
