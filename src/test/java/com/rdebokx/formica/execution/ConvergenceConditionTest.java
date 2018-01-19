package com.rdebokx.formica.execution;

import com.rdebokx.formica.metrics.distance.ManhattanMetric;
import com.rdebokx.formica.metrics.quality.QualityMetric;
import com.rdebokx.formica.testhelpers.TestColony;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Matchers;

import java.util.Arrays;

public class ConvergenceConditionTest {

  @Test
  public void testShouldStopBasic(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25);
    TestColony colony = new TestColony(config, null, Arrays.asList());

    //Stub quality metric
    QualityMetric qmMock = Mockito.mock(QualityMetric.class);
    Assert.assertTrue(new ConvergenceCondition(10, 0, qmMock).shouldStop(colony));

    Mockito.when(qmMock.getScore(Matchers.anyList())).thenReturn(5.0);
    Assert.assertTrue(new ConvergenceCondition(10, 1, qmMock).shouldStop(colony));

    Mockito.when(qmMock.getScore(Matchers.anyList())).thenReturn(15.0, 25.0, 34.9);
    ConvergenceCondition twoPointsCondition = new ConvergenceCondition(10, 2, qmMock);
    Assert.assertFalse(twoPointsCondition.shouldStop(colony));
    Assert.assertFalse(twoPointsCondition.shouldStop(colony));
    Assert.assertTrue(twoPointsCondition.shouldStop(colony));
  }

  @Test
  public void testShouldStopAfterFilled(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25);
    TestColony colony = new TestColony(config, null, Arrays.asList());

    //Stub quality metric
    QualityMetric qmMock = Mockito.mock(QualityMetric.class);
    Mockito.when(qmMock.getScore(Matchers.anyList())).thenReturn(
        //5 non-convergent points
        100.0, 99.0, 101.0, 88.0, 80.0,
        //7 non-convergent points
        200.0, 201.0, 200.0, 200.5, 220.8, 205.8, 250.9,
        //5 convergent points
        81.0, 85.0, 82.0, 84.0, 89.1);

    //Minimal deviation of 10 over last 5 iterations
    ConvergenceCondition condition = new ConvergenceCondition(10, 5, qmMock);
    for(int i = 0; i < 16; i++){
      Assert.assertFalse("Failed at iteration " + i, condition.shouldStop(colony));
    }

    Assert.assertTrue(condition.shouldStop(colony));
  }

  @Test
  public void testShouldStopAt3rd(){
    Configuration config = new Configuration(ManhattanMetric.METRIC_NAME, 1, 0.05, 0.25);
    TestColony colony = new TestColony(config, null, Arrays.asList());

    //Stub quality metric
    QualityMetric qmMock = Mockito.mock(QualityMetric.class);
    Mockito.when(qmMock.getScore(Matchers.anyList())).thenReturn(
        81.0, 85.0, 82.0);

    ConvergenceCondition condition = new ConvergenceCondition(10, 3, qmMock);
    Assert.assertFalse(condition.shouldStop(colony));
    Assert.assertFalse(condition.shouldStop(colony));
    Assert.assertTrue(condition.shouldStop(colony));
  }
}
