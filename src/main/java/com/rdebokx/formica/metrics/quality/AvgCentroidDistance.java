package com.rdebokx.formica.metrics.quality;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.metrics.distance.DistanceMetric;

import java.util.List;

public class AvgCentroidDistance<T extends DataPoint<?>> extends QualityMetric<T> {

  public AvgCentroidDistance(DistanceMetric<T> distanceMetric){
    super(distanceMetric);
  }

  public double getScore(List<Bucket<T>> clustering, DistanceMetric<T> metric) {
    double result = 0;
    for(Bucket<?> bucket : clustering){
      result = bucket.getCentroid().avgDistanceToBucket(bucket, metric) / clustering.size();
    }
    return result;
  }
}
