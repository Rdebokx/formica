package com.rdebokx.formica.execution;

import com.rdebokx.formica.core.Colony;

public interface StopCondition {

  public boolean shouldStop(Colony colony);
}
