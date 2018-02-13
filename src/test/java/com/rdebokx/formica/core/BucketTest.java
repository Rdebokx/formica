package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.DummyCondition;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BucketTest {

  private DataPoint2D dp1 = new DataPoint2D(0, 15);
  private DataPoint2D dp2 = new DataPoint2D(-5, 25);
  private DataPoint2D dp3 = new DataPoint2D(-3, 25);
  private DataPoint2D dp4 = new DataPoint2D(0, 50);
  private DataPoint2D dp5 = new DataPoint2D(3, 150);
  private DataPoint2D dp6 = new DataPoint2D(2, 100);

  @Test
  public void testCalculateCentroidBasic(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, new DummyCondition());
    TestRandom randomizer = new TestRandom();
    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4));

    Bucket bucket = new Bucket(colony);
    Assert.assertNull(bucket.getCentroid());

    bucket.add(dp1);
    Assert.assertEquals(dp1, bucket.getCentroid());

    bucket.add(dp2);
    Assert.assertEquals(dp1, bucket.getCentroid());

    Assert.assertEquals(dp1, new Bucket(colony, dp1, dp2).getCentroid());
  }

  @Test
  public void testCalculateCentroid(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, new DummyCondition());
    TestRandom randomizer = new TestRandom();
    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6));

    Bucket<DataPoint2D> bucket = new Bucket<>(colony, dp1, dp2, dp3, dp4);
    Assert.assertEquals(dp3, bucket.getCentroid());

    //Add some
    bucket.add(dp5);
    bucket.add(dp6);

    Assert.assertEquals(dp4, bucket.getCentroid());

    //Remove some
    bucket.remove(0); //Remove dp1
    bucket.remove(0); //Remove dp2
    bucket.remove(0); //Remove dp3
    Assert.assertEquals(dp6, bucket.getCentroid());
  }
}
