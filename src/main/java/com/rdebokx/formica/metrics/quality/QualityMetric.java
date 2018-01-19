package com.rdebokx.formica.metrics.quality;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.metrics.distance.DistanceMetric;

import java.util.List;

public abstract class QualityMetric<T extends DataPoint<?>> {

  protected final DistanceMetric<T> distanceMetric;

  public QualityMetric(DistanceMetric<T> distanceMetric){
    this.distanceMetric = distanceMetric;
  }

  public abstract double getScore(List<Bucket<T>> clustering);

}
