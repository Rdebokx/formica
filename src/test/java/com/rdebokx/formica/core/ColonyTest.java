package com.rdebokx.formica.core;

import com.rdebokx.formica.example.DataPoint2D;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.testhelpers.DummyCondition;
import com.rdebokx.formica.testhelpers.TestColony;
import org.junit.Assert;
import org.junit.Test;

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


  @Test
  public void testNextStepDoNextStep() {
    Assert.fail("TODO");
  }

  @Test
  public void testNextStepWindDown() {
    Assert.fail("TODO");
  }

  @Test
  public void testStop() {
    Assert.fail("TODO");
  }

  public static TestColony getDefaultTestColony() {
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25, new DummyCondition());
    return new TestColony(config, TEST_DATA_POINTS);
  }
}
