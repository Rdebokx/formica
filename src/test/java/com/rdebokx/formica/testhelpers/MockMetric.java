package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.metrics.DistanceMetric;

import java.util.Map;
import java.util.Objects;

public class MockMetric<T extends DataPoint> implements DistanceMetric<T>{

  public Map<DataPointTuple<T, T>, Double> distances;

  public MockMetric(Map<DataPointTuple<T, T>, Double> distances){
    this.distances = distances;
  }

  public double distance(T p1, T p2) {
    return distances.get(new DataPointTuple<>(p1, p2));
  }
}

class DataPointTuple<L extends DataPoint, R extends DataPoint> {
  private L left;

  private R right;

  public DataPointTuple(L left, R right){
    this.left = left;
    this.right = right;
  }

  @Override
  public int hashCode(){
    return Objects.hash(left, right);
  }
}
