package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.DummyCondition;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestRandom;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntTest {

  @Test
  public void testPickup() {
    TestColony colony = ColonyTest.getDefaultTestColony();
    Ant ant = colony.getAnts()[0];

    Assert.assertNull(ant.getPayload());
    //Don't pick up from empty list
    ant.pickUp(new Bucket<>(colony));
    Assert.assertNull(ant.getPayload());

    //Don't pick up dp2 from coherent bucket
    colony.getRandomizer().setIntSeq(1);
    colony.getRandomizer().setDoubleSeq(0.5, 0.5);
    Bucket bucket = new Bucket(colony, ColonyTest.TEST_DATA_POINTS.subList(0, 5));
    ant.pickUp(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(5, bucket.size());
    Assert.assertTrue(bucket.contains(ColonyTest.TEST_DATA_POINTS.get(1)));

    //Pick up dp4 from similar bucket with basic prob
    colony.getRandomizer().setIntSeq(3);
    colony.getRandomizer().setDoubleSeq(0.5, 0.04);
    bucket = new Bucket(colony, ColonyTest.TEST_DATA_POINTS.subList(0, 4));
    ant.pickUp(bucket);
    Assert.assertEquals(ColonyTest.dp4, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(ColonyTest.dp4));

    //Drop
    colony.getRandomizer().setDoubleSeq(0);
    ant.drop(new Bucket<>(colony));
    Assert.assertNull(ant.getPayload());

    //Pick up from dissimilar bucket
    colony.getRandomizer().setIntSeq(0);
    colony.getRandomizer().setDoubleSeq(0.5, 0.5);
    bucket = new Bucket(colony, ColonyTest.dp3, ColonyTest.dp6, ColonyTest.dp7, ColonyTest.dp8);
    ant.pickUp(bucket);
    Assert.assertEquals(ColonyTest.dp3, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(ColonyTest.dp3));
  }

  @Test
  public void testDrop() {
    TestColony colony = ColonyTest.getDefaultTestColony();
    Ant ant = colony.getAnts()[0];
    pickUp(colony, ant, colony.getRandomizer(), ColonyTest.dp2);

    //Don't drop dp2 at dissimilar bucket
    colony.getRandomizer().setDoubleSeq(0.5, 0.5);
    Bucket bucket = new Bucket(colony, ColonyTest.dp6, ColonyTest.dp7, ColonyTest.dp8);
    ant.drop(bucket);
    Assert.assertEquals(ColonyTest.dp2, ant.getPayload());
    Assert.assertEquals(3, bucket.size());
    Assert.assertFalse(bucket.contains(ColonyTest.dp2));

    //Drop dp2 at dissimilar bucket with basic prob.
    colony.getRandomizer().setDoubleSeq(0.5, 0.09);
    bucket = new Bucket(colony, ColonyTest.dp6, ColonyTest.dp7, ColonyTest.dp8);
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(4, bucket.size());
    Assert.assertTrue(bucket.contains(ColonyTest.dp2));

    pickUp(colony, ant, colony.getRandomizer(), ColonyTest.dp2);

    //Drop with basic prob eg if empty bucket
    colony.getRandomizer().setDoubleSeq(0.09);
    bucket = new Bucket(colony);
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(1, bucket.size());
    Assert.assertTrue(bucket.contains(ColonyTest.dp2));

    pickUp(colony, ant, colony.getRandomizer(), ColonyTest.dp2);

    //Don't always drop in empty bucket
    colony.getRandomizer().setDoubleSeq(0.5, 0.5);
    bucket = new Bucket(colony);
    ant.drop(bucket);
    Assert.assertEquals(ColonyTest.dp2, ant.getPayload());
    Assert.assertEquals(0, bucket.size());
    Assert.assertFalse(bucket.contains(ColonyTest.dp2));

    //Drop at similar bucket
    colony.getRandomizer().setDoubleSeq(0.5, 0.5);
    bucket = new Bucket(colony, ColonyTest.dp1, ColonyTest.dp3, ColonyTest.dp4, ColonyTest.dp5);
    ant.drop(bucket);
    Assert.assertNull(ant.getPayload());
    Assert.assertEquals(5, bucket.size());
    Assert.assertTrue(bucket.contains(ColonyTest.dp2));
  }

  private void pickUp(Colony colony, Ant ant, TestRandom randomizer, DataPoint dp){
    randomizer.setIntSeq(0);
    randomizer.setDoubleSeq(0, 0.04);
    ant.pickUp(new Bucket<>(colony, dp));
    Assert.assertEquals(dp, ant.getPayload());
  }

}
