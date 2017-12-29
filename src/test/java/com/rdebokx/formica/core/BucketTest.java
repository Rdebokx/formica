package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BucketTest {

  private DataPoint2D dp1 = new DataPoint2D(0, 15);
  private DataPoint2D dp2 = new DataPoint2D(1, 25);
  private DataPoint2D dp3 = new DataPoint2D(-1, 50);
  private DataPoint2D dp4 = new DataPoint2D(0, 50);

  @Test
  public void testCalculateCentroid(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25);
    TestRandom randomizer = new TestRandom();
    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4));

    Bucket<DataPoint2D> bucket = new Bucket<>(colony, dp1, dp1, dp3, dp4);
    Assert.assertEquals(dp2, bucket.getCentroid());

    //TODO: add some

    //TODO: remove some


    Assert.fail("TODO");
  }
}
