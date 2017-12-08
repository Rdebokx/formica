package com.rdebokx.formica.core;

import com.rdebokx.formica.metrics.DistanceMetric;

import java.util.List;

public abstract class DataPoint {

  /**
   * Array of double values that this DataPoint represents.
   */
  protected final double[] values;

  /**
   * Constructor, creating a DataPoint that can represent the given number of properties. The values of these properties are initialized at 0.0.
   * @param numProperties The number of properties or dimensions that this DataPoint will represent.
   */
  protected DataPoint(int numProperties) {
    this(new double[numProperties]);
  }

  /**
   * Constructor, creating a DataPoint that will represent the given array of property values.
   * @param values The property values that this DataPoint will represent.
   */
  public DataPoint(double ... values){
    this.values = values;
  }

  /**
   * @return The property values of this DataPoint.
   */
  public double[] getValues() {
    return values;
  }

  /**
   * This function will return the average normalized distance of this DataPoint to the other DataPoints in the given bucket.
   * Distances will be calculated using the provided calculator. Iff an empty bucket was provided, -1 will be returned.
   * @param bucket The bucket that the average distance to the current DataPoint needs to be calculated for.
   * @param distanceCalculator The DistanceCalculator to be used.
   * @return The average distance of this DataPoint to the other DataPoints in the bucket or -1 iff the bucket was empty.
   */
  public double avgDistanceToBucket(List<DataPoint> bucket, DistanceMetric distanceCalculator){
    double totalDistance = 0;
    int distancesCount = 0;
    for(DataPoint dataPoint : bucket){
      if(this != dataPoint){
        totalDistance += distanceCalculator.distance(this, dataPoint);
        distancesCount++;
      }
    }
    return distancesCount > 0 ? totalDistance / distancesCount : -1;
  }

}

