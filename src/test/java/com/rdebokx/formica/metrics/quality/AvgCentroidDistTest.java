package com.rdebokx.formica.metrics.quality;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.DistanceMetric;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.DummyCondition;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AvgCentroidDistTest {

  //Bucket 0
  private DataPoint2D dp1 = new DataPoint2D(0, 15);
  private DataPoint2D dp2 = new DataPoint2D(1, 25);
  private DataPoint2D dp3 = new DataPoint2D(-1, 50);
  private DataPoint2D dp4 = new DataPoint2D(10, 50);

  //Bucket 1
  private DataPoint2D dp5 = new DataPoint2D(3, 100);
  private DataPoint2D dp6 = new DataPoint2D(2, 100);

  @Test
  public void testGetScore(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, new DummyCondition());
    TestRandom randomizer = new TestRandom();
    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6));

    //Clear buckets
    Bucket bucket0 = new Bucket(colony, dp1, dp2, dp3, dp4);
    Bucket bucket1 = new Bucket(colony, dp5, dp6);

    //Double check this test is based on the right centroid
    Assert.assertEquals(dp2, bucket0.getCentroid());
    Assert.assertEquals(dp5, bucket1.getCentroid());


    DistanceMetric metric = colony.getDistanceCalculator();
    double avgDistBucket0 = (metric.distance(dp2, dp1) + metric.distance(dp2, dp3) + metric.distance(dp2, dp4)) / 3;
    double avgDistBucket1 = metric.distance(dp6, dp5);

    Assert.assertEquals((avgDistBucket0 + avgDistBucket1) / 2, new AvgCentroidDist(metric).getScore(Arrays.asList(bucket0, bucket1)), .0001);
  }
}
