package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.metrics.distance.DistanceMetric;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataPointTest {

  @Test
  public void testAvgDistanceToBucket(){
    List<DataPoint> bucket = Arrays.asList(
      new DataPoint2D(1, 1),
      new DataPoint2D(5, 5),
      new DataPoint2D(9, 9),
      new DataPoint2D(9, 9)
    );

    DistanceMetric metric = new ManhattanMetric(bucket);
    double maxDistance = 16; //Manhattan distance between datapoints at indices 0 and 3.

    Assert.assertEquals(-1, bucket.get(0).avgDistanceToBucket(Arrays.asList(), metric), .0001);
    Assert.assertEquals((16 + 8 + 0) / 3.0 / maxDistance, bucket.get(3).avgDistanceToBucket(bucket, metric), .0001);

    // Test distance of p2 to rest
    Assert.assertEquals((8 + 8 + 8) / 3.0 / maxDistance, bucket.get(2).avgDistanceToBucket(bucket, metric), .0001);

    // Test of new dp to rest
    DataPoint dp5 = new DataPoint2D(5, 5);
    Assert.assertEquals((8 + 0 + 8 + 8) / 4.0 / maxDistance, dp5.avgDistanceToBucket(bucket, metric), .0001);

    DataPoint dp6 = new DataPoint2D(6, 6);
    Assert.assertEquals((10 + 2 + 6 + 6) / 4.0 / maxDistance, dp6.avgDistanceToBucket(bucket, metric), .0001);
  }
}
