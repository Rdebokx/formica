package com.rdebokx.formica.testhelpers;

import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.execution.StopCondition;

/**
 * TestStopCondition that can be used during tests.
 * This stopping condition allows you to configure the values that are returned for shouldStop.
 */
public class TestStopCondition implements StopCondition{

  /**
   * List of values that should consecutively be returned by shouldStop.
   */
  private final boolean[] returnValues;

  /**
   * Index to track where in the returnValues array we currently are.
   */
  private int index = 0;

  /**
   * Constructor, constructing a new TestStopCondition that allows you to configure the values that should be returned by shouldStop.
   * @param returnValues
   */
  public TestStopCondition(boolean ... returnValues){
    this.returnValues = returnValues;
  }

  @Override
  public boolean shouldStop(Colony colony) {
    return returnValues[index++];
  }
}
