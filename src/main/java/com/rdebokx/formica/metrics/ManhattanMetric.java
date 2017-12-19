package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;

import java.util.List;

public class ManhattanMetric<T extends DataPoint<Double>> extends DistanceMetric<T> {

  /**
   * The name of this Distance Metric.
   */
  public final static String METRIC_NAME = "Manhattan";

  /**
   * The maximum distance recorded among all DataPoints during initialization.
   * Will be used for calculating the normalized distance.
   */
  private final double maxDistance;

  /**
   * Constructor, constructing a Euclidean Distance Metric that is initialized with the given list of DataPoints.
   * Constructor will calculate the maxDistance among the provided list of DataPoints to be able to normalize the distance returned by the distance() function.
   * Note that distances between DataPoints are not cached for later use as ad-hoc calculations for this Metric are relatively fast,
   * meaning that caching will most likely not increase performance while requiring significant amounts of memory for larger datasets.
   * @param dataPoints The list of DataPoints to initialize on, required for returning a normalized distance.
   */
  public ManhattanMetric(List<T> dataPoints){
    double maxDistance = -1;

    for(int i = 0; i < dataPoints.size(); i++){
      for(int j = i + 1; j < dataPoints.size(); j++){
        maxDistance = Math.max(maxDistance, absoluteDistance(dataPoints.get(i), dataPoints.get(j)));
      }
    }

    this.maxDistance = maxDistance;
  }

  @Override
  protected double normalizedDistance(T p1, T p2){
    return absoluteDistance(p1, p2) / maxDistance;
  }

  /**
   * Distance function to calculate the absolute Manhattan distance between two DataPoints p1 and p2.
   * @param p1 DataPoint 1
   * @param p2 DataPoint 2
   * @return The Manhattan distance between p1 and p2.
   */
  private double absoluteDistance(T p1, T p2){
    Double[] p1Values = p1.getValues();
    Double[] p2Values = p2.getValues();

    double result = 0;
    for(int i = 0; i < p1Values.length; i++) {
      result += Math.abs(p1Values[i] - p2Values[i]);
    }
    return result;
  }
}
