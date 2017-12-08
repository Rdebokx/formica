package com.rdebokx.formica.metrics;

import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.example.DataPoint2D;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class EuclideanMetricTest {

  private List<DataPoint> bucket = Arrays.asList(
      new DataPoint2D(1, 1),
      new DataPoint2D(-5, -5),
      new DataPoint2D(-1.5, 9),
      new DataPoint2D(2.5, -5.75)
  );

  private double maxDistance = 233.5625; //Distance between DataPoints at indices 2 and 3

  private EuclideanMetric metric = new EuclideanMetric(bucket);

  @Test
  public void testDistance() {
    testNormalizedDistance(0, 0, 0, 0);
    testNormalizedDistance(0, 1, 6, 6);
    testNormalizedDistance(0, 2, 2.5, 8);
    testNormalizedDistance(0, 3, 1.5, 6.75);
    testNormalizedDistance(1, 0, 6, 6);
    testNormalizedDistance(1, 1, 0, 0);
    testNormalizedDistance(1, 2, 3.5, 14);
    testNormalizedDistance(1, 3, 7.5, .75);
    testNormalizedDistance(2, 0, 2.5, 8);
    testNormalizedDistance(2, 1, 3.5, 14);
    testNormalizedDistance(2, 2, 0, 0);
    testNormalizedDistance(2, 3, 4, 14.75); //Max distance, should be 1
    testNormalizedDistance(3, 0, 1.5, 6.75);
    testNormalizedDistance(3, 1, 7.5, .75);
    testNormalizedDistance(3, 2, 4, 14.75); //Max distance, should be 1
    testNormalizedDistance(3, 3, 0, 0);
  }

  /**
   * Helper function for testing the Euclidean Distance between two DataPoints form the bucket.
   * This helper function will perform an assert to test whether the (normalized) distance returned by the metric matches the expectation.
   * @param index1 The index of DataPoint 1.
   * @param index2 The index of DataPoint 2.
   * @param distDx The distance between the two DataPoints in the X dimension.
   * @param distDy The distance between the two DataPoints in the Y dimension.
   */
  private void testNormalizedDistance(int index1, int index2, double distDx, double distDy){
    Assert.assertEquals((distDx * distDx + distDy * distDy) / maxDistance, metric.normalizedDistance(bucket.get(index1), bucket.get(index2)), .0001);
  }
}
