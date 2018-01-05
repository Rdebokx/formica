package com.rdebokx.formica.metrics.quality;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.metrics.distance.DistanceMetric;

import java.util.List;

public class AvgCentroidDist<T extends DataPoint<?>> extends QualityMetric<T> {

  public AvgCentroidDist(DistanceMetric<T> distanceMetric){
    super(distanceMetric);
  }

  public double getScore(List<Bucket<T>> clustering){
    double result = 0;
    for(Bucket<?> bucket : clustering){
      result = bucket.getCentroid().avgDistanceToBucket(bucket, distanceMetric) / clustering.size();
    }
    return result;
  }
}
