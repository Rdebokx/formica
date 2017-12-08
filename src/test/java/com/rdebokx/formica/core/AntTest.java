package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.ManhattanMetric;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestRandom;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntTest {

  private DataPoint2D dp1 = new DataPoint2D(1, 1);
  private DataPoint2D dp2 = new DataPoint2D(1, 1);
  private DataPoint2D dp3 = new DataPoint2D(1.1, 1.1);
  private DataPoint2D dp4 = new DataPoint2D(1.2, 1.2);
  private DataPoint2D dp5 = new DataPoint2D(1.3, 1.3);
  private DataPoint2D dp6 = new DataPoint2D(5.1, 5.2);
  private DataPoint2D dp7 = new DataPoint2D(5.2, 5.3);
  private DataPoint2D dp8 = new DataPoint2D(5.3, 5.5);

  @Test
  public void testPickup() {
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25);
    TestRandom randomizer = new TestRandom();

    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8));
    Ant ant = colony.getAnts()[0];

    Assert.assertNull(ant.getPayload());
    //Don't pick up from empty list
    ant.pickUp(Arrays.asList());
    Assert.assertNull(ant.getPayload());

    //Don't pick up dp2 from coherent bucket
    randomizer.setIntSeq(1);
    randomizer.setDoubleSeq(0.5, 0.5);
    List<DataPoint> bucket = new ArrayList<>(Arrays.asList(dp1, dp2, dp3, dp4, dp5));
    ant.pickUp(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(5, bucket.size());
    Assert.assertTrue(bucket.contains(dp2));

    //Pick up dp4 from similar bucket with basic prob
    randomizer.setIntSeq(3);
    randomizer.setDoubleSeq(0.5, 0.04);
    bucket = new ArrayList<>(Arrays.asList(dp1, dp2, dp3, dp4));
    ant.pickUp(bucket);
    Assert.assertEquals(dp4, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(dp4));

    //Drop
    randomizer.setDoubleSeq(0);
    ant.drop(new ArrayList<>());
    Assert.assertNull(ant.getPayload());

    //Pick up from dissimilar bucket
    randomizer.setIntSeq(0);
    randomizer.setDoubleSeq(0.5, 0.5);
    bucket = new ArrayList<>(Arrays.asList(dp3, dp6, dp7, dp8));
    ant.pickUp(bucket);
    Assert.assertEquals(dp3, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(dp3));
  }

  @Test
  public void testDrop() {
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.1);
    TestRandom randomizer = new TestRandom();

    TestColony colony = new TestColony(config, randomizer, Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8));
    Ant ant = colony.getAnts()[0];
    pickUp(ant, randomizer, dp2);

    //Don't drop dp2 at dissimilar bucket
    randomizer.setDoubleSeq(0.5, 0.5);
    ArrayList<DataPoint> bucket = new ArrayList<>(Arrays.asList(dp6, dp7, dp8));
    ant.drop(bucket);
    Assert.assertEquals(dp2, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(dp2));

    //Drop dp2 at dissimilar bucket with basic prob.
    randomizer.setDoubleSeq(0.5, 0.09);
    bucket = new ArrayList<>(Arrays.asList(dp6, dp7, dp8));
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(4, bucket.size());
    Assert.assertTrue(bucket.contains(dp2));

    pickUp(ant, randomizer, dp2);

    //Drop with basic prob eg if empty bucket
    randomizer.setDoubleSeq(0.09);
    bucket = new ArrayList<>();
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(1, bucket.size());
    Assert.assertTrue(bucket.contains(dp2));

    pickUp(ant, randomizer, dp2);

    //Don't always drop in empty bucket
    randomizer.setDoubleSeq(0.5, 0.5);
    bucket = new ArrayList<>();
    ant.drop(bucket);
    Assert.assertEquals(dp2, ant.getPayload());
    Assert.assertEquals(0, bucket.size());
    Assert.assertFalse(bucket.contains(dp2));

    //Drop at similar bucket
    randomizer.setDoubleSeq(0.5, 0.5);
    bucket = new ArrayList<>(Arrays.asList(dp1, dp3, dp4, dp5));
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(5, bucket.size());
    Assert.assertTrue(bucket.contains(dp2));
  }

  private void pickUp(Ant ant, TestRandom randomizer, DataPoint dp){
    randomizer.setIntSeq(0);
    randomizer.setDoubleSeq(0, 0.04);
    ant.pickUp(new ArrayList<>(Arrays.asList(dp)));
    Assert.assertEquals(dp, ant.getPayload());
  }

}
