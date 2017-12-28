package com.rdebokx.formica.execution;

import com.rdebokx.formica.core.Colony;

public class TimeCondition implements StopCondition {

  public final long startTime = System.currentTimeMillis();

  public final long maxEcecutionTime;

  public TimeCondition(long maxExecutionTime){
    this.maxEcecutionTime = maxExecutionTime;
  }

  @Override
  public boolean shouldStop(Colony colony){
    return startTime + maxEcecutionTime < System.currentTimeMillis();
  }
}
