package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.DummyCondition;
import com.rdebokx.formica.testhelpers.TestColony;
import com.rdebokx.formica.testhelpers.TestStopCondition;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.ConsoleWriter;

import java.util.Arrays;
import java.util.List;

public class ColonyTest {


  public final static DataPoint2D dp1 = new DataPoint2D(1, 1);
  public final static DataPoint2D dp2 = new DataPoint2D(1, 1);
  public final static DataPoint2D dp3 = new DataPoint2D(1.1, 1.1);
  public final static DataPoint2D dp4 = new DataPoint2D(1.2, 1.2);
  public final static DataPoint2D dp5 = new DataPoint2D(1.3, 1.3);
  public final static DataPoint2D dp6 = new DataPoint2D(5.1, 5.2);
  public final static DataPoint2D dp7 = new DataPoint2D(5.2, 5.3);
  public final static DataPoint2D dp8 = new DataPoint2D(5.3, 5.5);

  public static final List<DataPoint> TEST_DATA_POINTS = Arrays.asList(dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8);

  @BeforeClass
  public static void configureLogging(){
    Configurator.defaultConfig()
        .formatPattern("{date:yyyy-MM-dd HH:mm:ss.SSS} [{thread}] {level}: {class}.{method}()\t{message}")
        .writer(new ConsoleWriter())
        .level(Level.DEBUG)
        .activate();
  }

  @Test
  public void testNextStepDoNextStep() {
    //create colony
    TestColony colony = getDefaultTestColony();
    colony.getRandomizer().setIntSeq(0, 0, 0);
    colony.getRandomizer().setDoubleSeq(0, 0);

    //Verify that a next step was executed where an Ant picked up the element of bucket 0
    Assert.assertTrue(colony.nextStep());
    Assert.assertEquals(1, colony.getStatsLogger().getAntMoves(colony.getAnts()[0]));
    Assert.assertEquals(dp1, colony.getAnts()[0].getPayload());
  }

  @Test
  public void testManualStop() {
    TestColony unstartedColony = getDefaultTestColony();

    //Test that colony has terminated when no ant has picked anything up yet.
    unstartedColony.stop();
    Assert.assertFalse(unstartedColony.isStopping());
    Assert.assertTrue(unstartedColony.hasStopped());

    //Do first step and then stop.
    TestColony colony = getDefaultTestColony();
    colony.getRandomizer().setIntSeq(0, 0, 0);
    colony.getRandomizer().setDoubleSeq(0, 0);
    Assert.assertTrue(colony.nextStep());
    //Ant does now picked dp1 from first bucket.
    checkAntPickedDp1(colony);
    Assert.assertFalse(colony.isStopping());
    Assert.assertFalse(colony.hasStopped());
    colony.stop();
    Assert.assertTrue(colony.isStopping());
    Assert.assertFalse(colony.hasStopped());

    //Do next step so Colony will get terminated
    colony.getRandomizer().setIntSeq(0, 0);
    colony.getRandomizer().setDoubleSeq(0.1);
    Assert.assertTrue(colony.nextStep());
    Assert.assertFalse(colony.isStopping());
    Assert.assertTrue(colony.hasStopped());
    Assert.assertFalse(colony.nextStep());
    Assert.assertFalse(colony.nextStep());
  }

  @Test
  public void testNextStepStopConditionMetUnstarted() {
    TestStopCondition stopCondition = new TestStopCondition(true);
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, stopCondition);
    TestColony colony = new TestColony(config, TEST_DATA_POINTS);

    //First step, pick up dp1 from bucket 1.
    Assert.assertFalse(colony.nextStep());
    Assert.assertFalse(colony.isStopping());
    Assert.assertTrue(colony.hasStopped());
  }

  /**
   * Run test where colony will be stopped at second step and will need an additional 2 steps to actually terminate.
   */
  @Test
  public void testNextStepStopConditionMet() {
    TestStopCondition stopCondition = new TestStopCondition(false, true);
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, stopCondition);
    TestColony colony = new TestColony(config, TEST_DATA_POINTS);
    colony.getRandomizer().setIntSeq(0, 0, 0);
    colony.getRandomizer().setDoubleSeq(0, 0);

    //First step, pick up dp1 from bucket 1.
    Assert.assertTrue(colony.nextStep());
    Assert.assertFalse(colony.isStopping());
    Assert.assertFalse(colony.hasStopped());
    checkAntPickedDp1(colony);

    //Second step, trigger StopCondition
    colony.getRandomizer().setIntSeq(0, 0, 0);
    colony.getRandomizer().setDoubleSeq(1.0);
    Assert.assertTrue(colony.nextStep());
    Assert.assertTrue(colony.isStopping());
    Assert.assertFalse(colony.hasStopped());
    checkAntPickedDp1(colony);

    //Third step, should drop the payload now, colony should be terminated.
    colony.getRandomizer().setIntSeq(0, 0, 0);
    colony.getRandomizer().setDoubleSeq(0.0);
    Assert.assertTrue(colony.nextStep());
    Assert.assertFalse(colony.isStopping());
    Assert.assertTrue(colony.hasStopped());
    Assert.assertFalse(colony.nextStep());
  }

  /**
   * Helper function for performing a check that the first ant in the colony has dp1 as a payload and that this dp1 is no longer in the first bucket of the colony.
   * @param colony The colony to check this for.
   */
  private void checkAntPickedDp1(TestColony colony) {
    Assert.assertEquals(dp1, colony.getAnts()[0].getPayload());
    List<Bucket> bucketsCopy = colony.getBucketsCopy();
    Assert.assertTrue(bucketsCopy.get(0).isEmpty());
  }

  public static TestColony getDefaultTestColony() {
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, new DummyCondition());
    return new TestColony(config, TEST_DATA_POINTS);
  }
}
