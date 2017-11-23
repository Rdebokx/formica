package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

public class EuclideanMetric<T extends DataPoint> implements DistanceMetric<T> {

  public final static String METRIC_NAME = "Euclidean";

  private final double maxDistance;

  /**
   * TODO javadoc
   * Don't cache all distances, will require too much memory.
   * @param dataPoints
   */
  public EuclideanMetric(T ... dataPoints){
    double maxDistance = -1;

    for(int i = 0; i < dataPoints.length; i++){
      for(int j = i + 1; j < dataPoints.length; j++){
        maxDistance = Math.max(maxDistance, absoluteDistance(dataPoints[i], dataPoints[j]));
      }
    }

    this.maxDistance = maxDistance;
  }

  /**
   * Distance function to calculate the Euclidean distance between two DataPoints p1 and p2.
   * @param p1 DataPoint 1
   * @param p2 DataPoint 2
   * @return The Euclidean distance between p1 and p2.
   */
  public double distance(T p1, T p2){
    return absoluteDistance(p1, p2) / maxDistance;
  }

  /**
   * Distance function to calculate the Euclidean distance between two DataPoints p1 and p2.
   * @param p1 DataPoint 1
   * @param p2 DataPoint 2
   * @return The Euclidean distance between p1 and p2.
   */
  private double absoluteDistance(T p1, T p2){
    double[] p1Values = p1.getValues();
    double[] p2Values = p2.getValues();

    double result = 0;
    for(int i = 0; i < p1Values.length; i++) {
      double diff = p1Values[i] - p2Values[i];
      result += diff * diff;
    }
    return result;
  }
}
